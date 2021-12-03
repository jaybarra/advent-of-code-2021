(ns ds.aoc.day-3
(:require
[clojure.pprint :refer [cl-format]]))

(defn gamma-rate
  "Takes an array of values, takes the most common leading bit as the gamma-bit at each position"
  [bits]
  (loop [bits bits
         gamma 2r0
         idx 0]
    (if (every? zero? bits)
      gamma
      (let [most-common-bit (reduce #(if (bit-test %2 0) (inc %1) (dec %1))
                                    0
                                    bits)]
        (recur (map #(bit-shift-right % 1) bits)
               (if (pos? most-common-bit)
                 (bit-set gamma idx)
                 (bit-clear gamma idx))
               (inc idx))))))

(defn epsilon-rate
  "Takes an array of values, takes the most common leading bit as the gamma-bit at each position.
  
  Note: bit-not is not behaving quite as expected"
  [bits]
  (loop [bits bits
         gamma 2r0
         idx 0]
    (if (every? zero? bits)
      gamma
      (let [most-common-bit (reduce #(if (bit-test %2 0) (inc %1) (dec %1))
                                    0
                                    bits)]
        (recur (map #(bit-shift-right % 1) bits)
               (if (pos? most-common-bit)
                 (bit-clear gamma idx)
                 (bit-set gamma idx))
               (inc idx))))))

(comment
  (gamma-rate [2r000101])
  (cl-format nil "2r~3,'0',B" (gamma-rate [2r101]))
  (cl-format nil "2r~3,'0',B" (epsilon-rate [2r101])))
