(ns recursion.combinator)

(defn- ->>pick [f xs]
  (map (partial f xs) (range (count xs))))

(defn- ->pick-and-keep [xs n]
  (list
   (first (drop n xs))
   (concat (take n xs) (drop (inc n) xs))))

(def ->>pick-and-keep (partial ->>pick ->pick-and-keep))
;; => ((1 (2 3 4)) (2 (1 3 4)) (3 (1 2 4)) (4 (1 2 3)))

(defn- ->pick-and-keep-all [xs n]
  (list
   (first (drop n xs))
   (apply list  xs)))

(def ->>pick-and-keep-all (partial ->>pick ->pick-and-keep-all))
;; => ((1 (1 2 3 4)) (2 (1 2 3 4)) (3 (1 2 3 4)) (4 (1 2 3 4)))

(defn- ->pick-and-consume [xs n]
  (list
   (first (drop n xs))
   (concat (drop (inc n) xs))))

(def ->>pick-and-consume (partial ->>pick ->pick-and-consume))
;; => ((1 (2 3 4)) (2 (3 4)) (3 (4)) (4 ()))

(defn-  ->pick-and-keep-first-and-consume [xs n]
  (let [head (drop n xs)]
    (list
     (first head)
     (concat (take 1 head) (drop (inc n) xs)))))

(def ->>pick-and-keep-first-and-consume (partial ->>pick ->pick-and-keep-first-and-consume))
;; => ((1 (1 2 3 4)) (2 (2 3 4)) (3 (3 4)) (4 (4)))

(defn- combinator
  ([->>f xs n] (combinator ->>f xs n []))
  ([->>f xs n acc]
   (-> (cond (= n (count acc)) (list acc)
             (empty? xs) nil
             :else (mapcat (fn [[x x-rest]] (combinator ->>f x-rest n (conj acc x))) (->>f xs)))
       (lazy-seq))))


(def permutation (partial combinator ->>pick-and-keep))
(def permutation-with-repe (partial combinator ->>pick-and-keep-all))
(def combination (partial combinator ->>pick-and-consume))
(def combination-with-repe (partial combinator ->>pick-and-keep-first-and-consume))

;;;;

(defn factorial [n]
  (reduce * (range 1 (inc n))))

(defn permutation-count [xs r]
  (let [n (count xs)]
    (/ (factorial n)
       (factorial (- n r)))))

(defn permutation-with-repe-count [xs r]
  (let [n (count xs)]
    (int (Math/pow n r))))

(defn combination-count [xs r]
  (let [n (count xs)]
    (/ (factorial n)
       (* (factorial r)
          (factorial (- n r))))))

(defn combination-with-repe-count [xs r]
  (let [n (count xs)]
    (/ (factorial (- (+ r n) 1))
       (*  (factorial r)
           (factorial (- n 1))))))
