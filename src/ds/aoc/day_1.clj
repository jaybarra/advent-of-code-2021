(ns ds.aoc.day-1)

(defn depth-increased-count
  [depths]
  (reduce + (map #(if (> %2 %1) 1 0) depths (rest depths))))
