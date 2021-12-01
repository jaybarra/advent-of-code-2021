(ns ds.aoc.day-1-test
  (:require
   [clojure.edn :as edn]
   [clojure.test :refer [deftest is]]
   [clojure.java.io :as io]
   [ds.aoc.day-1 :refer [depth-increased-count]]))

(def sample-depths [199
                    200
                    208
                    210
                    200
                    207
                    240
                    269
                    260
                    263])

(deftest depth-increased-test
  (is (= 7 (depth-increased-count sample-depths)))
  (let [depths
        (with-open [rdr (io/reader (io/resource "day_1/input.txt"))]
          (reduce #(conj %1 (edn/read-string %2)) [] (line-seq rdr)))]
    (is (= 1446 (depth-increased-count depths)))))
