(ns matrix-effect.core
  (:require
   [matrix-effect.config :as config]
   [matrix-effect.events :as events]
   [matrix-effect.views :as views]
   [re-frame.core :as re-frame]
   [reagent.dom :as rdom]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (dev-setup)
  (mount-root)
  (re-frame/dispatch-sync [::events/initialize]))
