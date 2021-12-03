(ns ds.aoc.day-2)

(defn navigate
  "Direct translation of the sub"
  [current direction amount]
  (let [delta (case direction
                :forward {:position amount}
                :down {:depth amount}
                :up {:depth (- amount)})]
    (merge-with + current delta)))

(defn navigate-with-aim
  "Move the sub taking pitch into account"
  [current direction amount]
  (let [delta (case direction
                :forward {:position amount
                          :depth (* (:aim current) amount)}
                :down {:aim amount}
                :up {:aim (- amount)})]
    (merge-with + current delta)))

(comment
  (-> {:depth 0 :position 0}
      (navigate :forward 3)
      (navigate :down 2)
      (navigate :forward 5)
      (navigate :down 5)
      (navigate :up 4)))
