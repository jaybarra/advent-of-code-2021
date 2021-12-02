(ns ds.aoc.day-2-test
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.test :refer [deftest is are testing]]
   [ds.aoc.day-2 :refer [navigate navigate-with-aim]]))

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

(deftest navigate-with-aim-test
  (are [start direction dist end]
       (= end (navigate-with-aim start direction dist))
    {:position 0 :depth 0 :aim 0}
    :forward 5
    {:position 5 :depth 0 :aim 0}

    {:position 0 :depth 0 :aim 0}
    :down 2
    {:position 0 :depth 0 :aim 2}

    {:position 0 :depth 0 :aim 2}
    :up 2
    {:position 0 :depth 0 :aim 0}

    {:position 0 :depth 0 :aim 2}
    :forward 2
    {:position 2 :depth 4 :aim 2})

  (testing "simple case"
    (let [steps [[:forward 5]
                 [:down 5]
                 [:forward 8]
                 [:up 3]
                 [:down 8]
                 [:forward 2]]]
          (is (= {:depth 60 :position 15 :aim 10}
                 (reduce #(navigate-with-aim %1 (first %2) (second %2))
                         {:position 0 :depth 0 :aim 0}
                         steps)))))

  (testing "and computing the product of depth and position"
    (let [final (reduce #(navigate-with-aim %1 (first %2) (second %2))
                        {:position 0 :depth 0 :aim 0}
                        sample-directions)]
      (is (= {:position 1991 :depth 984716}
             (select-keys final [:position :depth])))
      (is (= 1960569556 (* (:depth final) (:position final)))))))
