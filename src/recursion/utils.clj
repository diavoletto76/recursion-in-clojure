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

(defn- pick-with [pick-f xs]
  (map (partial pick-f xs) (range (count xs))))

(def pick-all (partial pick-with pick-and-all-rest))
(def pick-rest (partial pick-with pick-and-rest))
