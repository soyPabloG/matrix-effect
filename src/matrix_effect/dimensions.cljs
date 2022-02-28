(ns matrix-effect.dimensions)

(def width 1500)
(def height 800)
(def char-size 20)
(def max-row (/ height char-size))

(defn cols
  ([] (cols width))
  ([width]
   (+ (Math/floor (/ width char-size)) 1)))
