(ns recursion.workbench1)

(defn- ->>pick [f xs]
  (map (partial f xs) (range (count xs))))

(defn- ->pick-and-keep [xs n]
  (list
   (first (drop n xs))
   (concat (take n xs) (drop (inc n) xs))))

(def ->>pick-and-keep (partial ->>pick ->pick-and-keep))
;; => ((1 (2 3 4)) (2 (1 3 4)) (3 (1 2 4)) (4 (1 2 3)))

(defn ->pick-and-keep-all [xs n]
  (list
   (first (drop n xs))
   (apply list  xs)))

(def ->>pick-and-keep-all (partial ->>pick ->pick-and-keep-all))
;; => ((1 (1 2 3 4)) (2 (1 2 3 4)) (3 (1 2 3 4)) (4 (1 2 3 4)))

(defn ->pick-and-consume [xs n]
  (list
   (first (drop n xs))
   (concat (drop (inc n) xs))))

(def ->>pick-and-consume (partial ->>pick ->pick-and-consume))
;; => ((1 (2 3 4)) (2 (3 4)) (3 (4)) (4 ()))

(defn  ->pick-and-keep-first-and-consume [xs n]
  (let [head (drop n xs)]
    (list
     (first head)
     (concat (take 1 head) (drop (inc n) xs)))))

(def ->>pick-and-keep-first-and-consume (partial ->>pick ->pick-and-keep-first-and-consume))
;; => ((1 (1 2 3 4)) (2 (2 3 4)) (3 (3 4)) (4 (4)))


;; Permutation

(defn permutation 
  ([xs n] (permutation xs n []))
  ([xs n acc] (cond (= n (count acc)) (list acc)
                    (empty? xs) nil
                    :else (mapcat (fn [[x x-rest]] (permutation x-rest n (conj acc x))) (->>pick-and-keep xs)))))

;; Permutation with repetition

(defn permutation-with-repe 
  ([xs n] (permutation-with-repe xs n []))
  ([xs n acc] (cond (= n (count acc)) (list acc)
         (empty? xs) nil
         :else (mapcat (fn [[x x-rest]] (permutation-with-repe x-rest n (conj acc x))) (->>pick-and-keep-all xs)))))

;; Combination

(defn combination 
  ([xs n] (combination xs n []))
  ([xs n acc]
   (cond (= n (count acc)) (list acc)
         (empty? xs) nil
         :else (mapcat (fn [[x x-rest]] (combination x-rest n (conj acc x))) (->>pick-and-consume xs)))))

;; Combination without repetition

(defn combination-with-repe 
  ([xs n] (combination-with-repe xs n []))
  ([xs n acc]
   (cond (= n (count acc)) (list acc)
         (empty? xs) nil
         :else (mapcat (fn [[x x-rest]] (combination-with-repe x-rest n (conj acc x))) (->>pick-and-keep-first-and-consume xs)))))

;; Combinator

(defn combinator
  ([->>f xs n] (combinator ->>f xs n []))
  ([->>f xs n acc]
   (-> (cond (= n (count acc)) (list acc)
             (empty? xs) nil
             :else (mapcat (fn [[x x-rest]] (combinator ->>f x-rest n (conj acc x))) (->>f xs)))
       (lazy-seq))))


(def permutation2 (partial combinator ->>pick-and-keep))
(def permutation-with-repe2 (partial combinator ->>pick-and-keep-all))
(def combination2 (partial combinator ->>pick-and-consume))
(def combination-with-repe2 (partial combinator ->>pick-and-keep-first-and-consume))

;;;;

(def l1 [1 2 3 4])
(def l2 [1 2 3])

(assert (= (permutation l2 3)
           (permutation2 l2 3)))

(assert (= (permutation-with-repe l2 3)
           (permutation-with-repe2 l2 3)))

(assert (= (combination l1 3)
           (combination2 l1 3)))

(assert (= (combination-with-repe l1 3)
           (combination-with-repe2 l1 3)))