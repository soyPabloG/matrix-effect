(ns matrix-effect.events
  (:require
   [matrix-effect.dimensions :as dimensions]
   [matrix-effect.timer.util :as timer]
   [re-frame.core :as re-frame]))

(re-frame/reg-cofx
 ::random-numbers
 (fn [cofx n]
   (assoc cofx :rand-nums (repeatedly n rand))))

(re-frame/reg-cofx
 ::random-chars
 (fn [cofx n]
   (let [random-char (fn [] (String/fromCharCode (* (rand) 128)))]
     (assoc cofx :rand-chars (repeatedly n random-char)))))


(defn next-y-pos
  "Takes a `y-pos` seq and generates `y`s values (moving them 20px down) for
   the next frame. According to `rand-nums`, randomly resets the end of the
   column if it's at least 60% full height."
  [y-pos rand-nums]
  (->> y-pos
       (map-indexed vector)
       (map (fn [[idx y]]
              (if (> y (+ (* dimensions/height 0.60) (* (nth rand-nums idx) dimensions/height)))
                0
                (+ y 20))))))

(re-frame/reg-event-fx
 ::next-frame
 [(re-frame/inject-cofx ::random-numbers (dimensions/cols dimensions/width))
  (re-frame/inject-cofx ::random-chars (dimensions/cols dimensions/width))]
 (fn [{:keys [db] :as cofx} _]
   {:db (-> db
            (update :y-pos next-y-pos (:rand-nums cofx))
            (assoc :chars (:rand-chars cofx)))}))


(re-frame/reg-event-fx
 ::initialize
 (fn [{:keys [db]} _]
   (let [cols (dimensions/cols dimensions/width)]
     {:db                  (assoc db :y-pos (repeat cols 0))
      ::timer/new-interval [#(re-frame/dispatch [::next-frame]) 50]})))

(re-frame/reg-event-fx
 ::stop
 (fn [{db :db} _]
   {:db                   (dissoc db :y-pos :chars)
    ::timer/stop-interval (get db :timeout-id)}))
