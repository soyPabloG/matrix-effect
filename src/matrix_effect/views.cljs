(ns matrix-effect.views
  (:require
   [matrix-effect.dimensions :as dimensions]
   [matrix-effect.events :as events]
   [re-frame.core :as re-frame]))

(defn main-panel []
  [:div
   [:h1 "The matrix-effect"]
   [:canvas {:id     "canv"
             :width  dimensions/width
             :height dimensions/height}]])
