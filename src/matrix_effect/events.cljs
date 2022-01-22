(ns matrix-effect.events
  (:require
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

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
