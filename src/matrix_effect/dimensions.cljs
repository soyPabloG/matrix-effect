(ns matrix-effect.dimensions)

(def width 1500)
(def height 1500)

(defn cols
  [width]
  (+ (Math/floor (/ width 20)) 1))
