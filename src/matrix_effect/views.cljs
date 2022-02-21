(ns matrix-effect.views
  (:require
   [matrix-effect.canvas.util :as canvas.util]
   [matrix-effect.dimensions :as dimensions]
   [matrix-effect.events :as events]
   [matrix-effect.subs :as subs]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [reagent.dom :as rdom]))

(defn canvas
  [{:keys [width height]}]
  (reagent/create-class
    {:display-name "canv"
     :reagent-render (fn [{:keys [width height]}]
                       [:canvas {:width width, :height height}])
     :component-did-mount (fn [comp]
                            (let [canvas (rdom/dom-node comp)]
                              (canvas.util/draw-rectangle! canvas "#000" width height)))
     :component-did-update (fn [comp]
                             (let [canvas   (rdom/dom-node comp)
                                   elements (get (reagent/props comp) :elements)]
                               (canvas.util/draw-rectangle! canvas "#0001" width height)
                               (run! (partial apply canvas.util/draw-character! canvas "#0F0")
                                     elements)))}))

(defn main-panel []
  [:div
   [:h1 "The matrix-effect"]
   (let [new-elements (re-frame/subscribe [::subs/elements])]
     [canvas {:width dimensions/width, :height dimensions/height
              :elements @new-elements}])])
