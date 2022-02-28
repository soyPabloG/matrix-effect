(ns matrix-effect.events
  (:require
   [matrix-effect.dimensions :as dimensions]
   [matrix-effect.timer.util :as timer]
   [matrix-effect.character :as character]
   [re-frame.core :as re-frame]))

;; A symbol is a map with the following keys
;; - col: Used to determine the x coordinate
;; - row: Used to determinte the y coordinate
;; - char: The char to be drawn

(defn ^:private initial-symbols!
  "Returns a set of `n` symbols with random characters. Each symbol will
  be at row 0 and the columns will go from 0 to n."
  [n]
  (into #{}
        (map-indexed #(hash-map :row 0 :col % :char %2)
                     (repeatedly n character/random))))

(defn ^:private increase-row
  "Increases `row` by 1. If `row` is greater than 60% of
  `dimensions/max-row` plus a random factor the row will be randomly
  set as 0 (in order to restart the column drop)."
  [row reset-factor]
  (if (> row
         (+ (* dimensions/max-row 0.60)
            (* reset-factor dimensions/max-row)))
    0
    (inc row)))

(defn ^:private update-symbol
  [symbol new-char reset-factor]
  (-> symbol
      (assoc :char new-char)
      (update :row #(increase-row % reset-factor))))

(defn ^:private update-symbols
  "Updates [:db :symbols].

  For each symbol in `symbols` it replaces its char with the
  corresponding one from `new-chars`. At the same time it displaces
  each symbol one row down (via `increase-row`)."
  [{:keys [db new-chars reset-factors]} _]
  (let [symbols (:symbols db)]
    {:db (assoc db :symbols (map update-symbol symbols new-chars reset-factors))}))

(re-frame/reg-cofx
 ::reset-factors
 (fn [cofx n]
   (assoc cofx :reset-factors (repeatedly n rand))))

(re-frame/reg-cofx
 ::new-chars
 (fn [cofx n]
   (assoc cofx :new-chars (repeatedly n character/random))))

(re-frame/reg-event-fx
 ::tick
 [(re-frame/inject-cofx ::new-chars (dimensions/cols))
  (re-frame/inject-cofx ::reset-factors (dimensions/cols))]
 update-symbols)

(re-frame/reg-event-fx
 ::initialize
 (fn [{:keys [db]} _]
   {:db                  (assoc db :symbols (initial-symbols! (dimensions/cols)))
    ::timer/new-interval [#(re-frame/dispatch [::tick]) 50]}))

(re-frame/reg-event-fx
 ::stop
 (fn [{db :db} _]
   {:db                   (dissoc db :symbols)
    ::timer/stop-interval (get db :timeout-id)}))
