(ns matrix-effect.canvas.util)

(defn draw-rectangle!
  [canvas color width height]
  (let [ctx (.getContext canvas "2d")]
    (aset ctx "fillStyle" color)
    (.fillRect ctx 0 0 width height)))

(defn draw-character!
  [canvas color char width height]
  (let [ctx (.getContext canvas "2d")]
    (aset ctx "fillStyle" color)
    (aset ctx "font" "15pt monospace")
    (.fillText ctx char width height)))
