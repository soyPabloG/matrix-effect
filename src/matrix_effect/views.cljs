(ns matrix-effect.views
  (:require
   [matrix-effect.subs :as subs]
   [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     [:canvas {:id     "canv"
               :width  1000
               :height 10000}]]))
