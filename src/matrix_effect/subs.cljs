(ns matrix-effect.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::symbols
 (fn [db]
   (:symbols db)))
