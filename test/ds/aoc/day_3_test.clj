(ns ds.aoc.day-3-test
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.test :refer [deftest is are testing]]
   [ds.aoc.day-3 :refer [gamma-rate epsilon-rate]]))

(def bits [2r00100
           2r11110
           2r10110
           2r10111
           2r10101
           2r01111
           2r00111
           2r11100
           2r10000
           2r11001
           2r00010
           2r01010])

(def readings
  (with-open [rdr (io/reader (io/resource "day_3/input.txt"))]
    (reduce (fn [readings line]
              (let [line (Integer/parseInt line 2)]
                (conj readings line)))
            []
            (line-seq rdr))))

(deftest gamma-rate-test
  (is (= 2r10110 (gamma-rate bits))))

(deftest epsilon-rate-test
  (is (= 2r01001 (epsilon-rate bits))))

(comment
  (* (gamma-rate readings) (epsilon-rate readings)))
