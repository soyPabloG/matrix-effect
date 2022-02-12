(ns matrix-effect.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::y-pos
 (fn [db]
   (get db :y-pos)))

(re-frame/reg-sub
 ::chars
 (fn [db]
   (get db :chars)))


(re-frame/reg-sub
 ::elements
 :<- [::y-pos]
 :<- [::chars]
 (fn [[y-pos chars] _]
   (->> (interleave chars (iterate (partial + 20) 0) y-pos)
        (partition 3))))
