(ns recursion.core)

;; https://www.mathsisfun.com/combinatorics/combinations-permutations.html

;; When the order doesn't matter, it is a Combination.
;; When the order does matter it is a Permutation.
;; A Permutation is an ordered Combination.

;; Permutation with repetition

(defn perm-repe
  ([xs n] (perm-repe xs n '()))
  ([xs n acc] (lazy-seq
               (cond (empty? xs) nil
                     (= n (count acc)) (list acc)
                     :else (mapcat (fn [x] (perm-repe xs n (cons x acc))) xs)))))

(defn perm-repe2
  ([xs n] (perm-repe xs n '()))
  ([xs n acc] (lazy-seq
               (cond (= n (count acc)) (list acc)
                     :else (mapcat (fn [x] (perm-repe2 xs n (cons x acc))) xs)))))


;; Permutation without repetition

(defn perm-no-repe [xs n acc]
  (cond (empty? xs) nil
        (= n (count acc)) (list acc)
        :else (mapcat (fn [x] (perm-no-repe (remove #{x} xs) n (conj acc x))) xs)))

;;;;

(def l1 [1 2 3 4 5])
(def l2 [1 2 3 4 5 6 7 8])
