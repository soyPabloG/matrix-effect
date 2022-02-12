(ns matrix-effect.canvas.util
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-cofx
 ::get-canvas
 (fn [cofx]
   (assoc cofx :canvas (.getElementById js/document "canv"))))

(defn draw-rectangle
  [canvas color width height]
  (let [ctx (.getContext canvas "2d")]
    (aset ctx "fillStyle" color)
    (.fillRect ctx 0 0 width height)))

(re-frame/reg-fx
 ::draw-rectangle
 (fn [[canvas color width height]]
   (draw-rectangle canvas color width height)))

(defn draw-character
  [canvas color char width height]
  (let [ctx (.getContext canvas "2d")]
    (aset ctx "fillStyle" color)
    (aset ctx "font" "15pt monospace")
    (.fillText ctx char width height)))
