(ns matrix-effect.events
  (:require
   [matrix-effect.db :as db]
   [re-frame.core :as re-frame]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
