(ns ds.aoc.day-2
  (:require
   [clojure.spec.alpha :as spec]))

(def directions #{:forward :down :up})

(spec/def ::direction directions)
(spec/def ::number number?)
(spec/def ::command (spec/cat :direction ::direction
                              :amount ::number))
(spec/def ::depth ::number)
(spec/def ::position ::number)

(spec/def ::sub-position (spec/keys :req-un [::depth
                                             ::position]))

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
