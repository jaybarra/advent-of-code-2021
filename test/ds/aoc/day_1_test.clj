(ns ds.aoc.day-1-test
  (:require
   [clojure.edn :as edn]
   [clojure.test :refer [deftest is are]]
   [clojure.java.io :as io]
   [ds.aoc.day-1 :refer [depth-increased-count sliding-window]]))

(def sample-depths
  [199
   200
   208
   210
   200
   207
   240
   269
   260
   263])

(def depths
  (with-open [rdr (io/reader (io/resource "day_1/input.txt"))]
    (reduce #(conj %1 (edn/read-string %2)) [] (line-seq rdr))))

(deftest depth-increased-test
  (are [input expected] (= expected (depth-increased-count input))
    sample-depths 7
    depths 1446))

(deftest sliding-window-test
  (is (= [607
          618
          618
          617
          647
          716
          769
          792]
         (sliding-window sample-depths 3)))

  (are [input expected] (= expected (depth-increased-count (sliding-window input 3)))
    sample-depths 5
    depths 1486))
