(ns matrix-effect.canvas.util
  (:require [matrix-effect.dimensions :as dimensions]))

(defn ^:private draw-rectangle!
  [canvas color width height]
  (let [ctx (.getContext canvas "2d")]
    (aset ctx "fillStyle" color)
    (.fillRect ctx 0 0 width height)))

(defn draw-background!
  [canvas width height]
  (draw-rectangle! canvas "#000" width height))

(defn fade-canvas!
  [canvas width height]
  (draw-rectangle! canvas "#0001" width height))

(defn draw-symbol!
  [canvas symbol]
  (let [ctx (.getContext canvas "2d")
        {:keys [char col row]} symbol]
    (aset ctx "fillStyle" "#0F0")
    (aset ctx "font" "15pt monospace")
    (.fillText ctx char (* dimensions/char-size col) (* dimensions/char-size row))))
