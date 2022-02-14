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
   {:display-name         "canv"
    :reagent-render       (fn [{:keys [width height]}]
                            [:canvas {:width width :height height}])
    :component-did-mount  (fn [comp]
                            (let [canvas (rdom/dom-node comp)]
                              (canvas.util/draw-background! canvas width height)))
    :component-did-update (fn [comp]
                            (let [canvas  (rdom/dom-node comp)
                                  symbols (get (reagent/props comp) :symbols)]
                              (canvas.util/fade-canvas! canvas width height)
                              (run! #(canvas.util/draw-symbol! canvas %) symbols)))}))

(defn main-panel []
  [:div
   [:h1 "The matrix-effect"]
   [canvas {:width   dimensions/width
            :height  dimensions/height
            :symbols @(re-frame/subscribe [::subs/symbols])}]])
