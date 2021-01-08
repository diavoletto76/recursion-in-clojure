(ns recursion.core)



;; Permutation with repetition

(defn perm-repe
  ([xs n] (perm-repe xs n '()))
  ([xs n acc] (lazy-seq
               (cond (empty? xs) nil
                     (= n (count acc)) (list acc)
                     :else (mapcat (fn [x] (perm-repe xs n (cons x acc))) xs)))))

(defn perm-repe2
  ([xs n] (perm-repe2 xs n []))
  ([xs n acc] (lazy-seq
               (cond (= n (count acc)) (list acc)
                     :else (mapcat (fn [x] (perm-repe2 xs n (cons x acc))) xs)))))


;; Permutation without repetition
;; No Repetition: for example the first three people in a running race. 
;; You can't be first and second.

(defn perm-no-repe [xs n acc]
  (cond (empty? xs) nil
        (= n (count acc)) (list acc)
        :else (mapcat (fn [x] (perm-no-repe (remove #{x} xs) n (conj acc x))) xs)))

(def l1 [1 2 3 4])


(perm-repe [1 2 3] 3)