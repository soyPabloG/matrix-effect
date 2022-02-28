(ns matrix-effect.character)

(def ^:private letters "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz")
(def ^:private numbers "0123456789")
(def ^:private brackets-punctuation "(){}[]¡!¿?`'.:;<>=+-/*")
(def ^:private kana
  "Half-width kana from 0xFF66 to 0xFF9E."
  "ｦｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝ")
(def ^:private available (str letters numbers brackets-punctuation kana))

(defn random
  "Returns a random character."
  []
  (rand-nth available))
