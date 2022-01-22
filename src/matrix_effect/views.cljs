(ns matrix-effect.views
  (:require
   [matrix-effect.events :as events]
   [matrix-effect.subs :as subs]
   [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:div
      [:h1 "The matrix-effect"]
      [:button {:on-click #(re-frame/dispatch [::events/next-frame])}
       "Next frame"]]
     [:canvas {:id     "canv"
               :width  1000
               :height 10000}]]))
