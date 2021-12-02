(ns ds.aoc.day-2-test
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.test :refer [deftest is are testing]]
   [ds.aoc.day-2 :refer [navigate]]))

(def sample-directions
  (with-open [rdr (io/reader (io/resource "day_2/input.txt"))]
    (reduce (fn [instructions line]
    (let [line (str/split line #" ")
          direction (keyword (first line))
          amount (edn/read-string (second line))]
          (conj instructions [direction amount]))) [] (line-seq rdr))))

(deftest navigate-test
  (let [start {:depth 0 :position 0}]
    (is (= {:depth 1 :position 0}
           (navigate start :down 1)))
    (are [start direction amount new-pos]
         (= new-pos (navigate start direction amount))

      {:depth 0 :position 0}
      :forward 3
      {:depth 0 :position 3}

      {:depth 5 :position 1}
      :up 3
      {:depth 2 :position 1}))

  (testing "and computing the product of depth and position"
    (let [final-pos (reduce #(navigate %1 (first %2) (second %2))
                            {:position 0 :depth 0}
                            sample-directions)
          {:keys [position depth]} final-pos]

      (is (= {:position 1991 :depth 911}
             final-pos))
      (is (= 1813801 (* position depth))))))
