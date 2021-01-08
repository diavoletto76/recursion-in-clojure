(ns recursion.utils)

(defn factorial [n]
  (reduce * (range 1 (inc n))))

(defn- pick-and-all-rest [xs n]
  (list
   (take 1 (drop n xs))
   (concat (take n xs) (drop (inc n) xs))))

(defn- pick-and-rest [xs n]
  (list
   (take 1 (drop n xs))
   (concat (drop (inc n) xs))))

(defn- pick-and-rest2 [xs n]
  (list
   (first (drop n xs))
   (concat (drop (inc n) xs))))

(defn- pick-with [pick-f xs]
  (map (partial pick-f xs) (range (count xs))))

;;;;

(def pick-all (partial pick-with pick-and-all-rest))

;; (pick-all [1 2 3 4])
;; => (((1) (2 3 4)) ((2) (1 3 4)) ((3) (1 2 4)) ((4) (1 2 3)))

(def pick-rest (partial pick-with pick-and-rest))

;; (pick-rest [1 2 3 4])
;; => (((1) (2 3 4)) ((2) (3 4)) ((3) (4)) ((4) ()))

(def pick-rest2 (partial pick-with pick-and-rest2))

;; (pick-rest2 [1 2 3 4])
;; => ((1 (2 3 4)) (2 (3 4)) (3 (4)) (4 ()))
