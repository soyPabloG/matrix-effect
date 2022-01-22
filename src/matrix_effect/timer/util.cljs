(ns matrix-effect.timer.util
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-event-db
 ::cleanup-timeout-id
 (fn [db _]
   (dissoc db :timeout-id)))

(re-frame/reg-event-db
 ::store-timeout-id
 (fn [db [_ id]]
   (assoc db :timeout-id id)))

(re-frame/reg-fx
 ::new-interval
 (fn [[func delay]]
   (re-frame/dispatch [::store-timeout-id (js/setInterval func delay)])))

(re-frame/reg-fx
 ::stop-interval
 (fn [id]
   (js/clearTimeout id)
   (re-frame/dispatch [::cleanup-timeout-id])))
