(ns ds.aoc.day-1)

(defn depth-increased-count
  "Returns the number of instances where the second value is greater than the first."
  [depths]
  (reduce + (map #(if (> %2 %1) 1 0) depths (rest depths))))

(defn sliding-window
  "Returns the condensed form of a list of values where the values are summed"
  [depths n]
  (loop [depths depths
         new-depths []]
    (if (> n (count depths))
      new-depths
      (recur (rest depths)
             (conj new-depths (reduce + (take n depths)))))))
