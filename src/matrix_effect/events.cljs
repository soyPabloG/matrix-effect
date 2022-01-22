(ns matrix-effect.events
  (:require
   [matrix-effect.canvas.util :as canvas]
   [matrix-effect.db :as db]
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

(re-frame/reg-event-fx
 ::initialize
 [(re-frame/inject-cofx ::canvas/get-canvas)]
 (fn [{:keys [db canvas]} _]
   (let [cols (+ (Math/floor (/ 1000 20)) 1)]
     {:db                     (assoc db :y-pos (repeat cols 0))
      ::canvas/draw-rectangle [canvas "#000" 1000 10000]})))
