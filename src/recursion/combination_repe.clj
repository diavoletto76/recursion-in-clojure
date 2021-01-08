(ns recursion.combination-repe)

(defn- pick [xs n]
  (list
   (first (drop n xs))
   (concat (drop n xs))))

(defn- picks [xs]
  (map (partial pick xs) (range (count xs))))


(defn combination-repe 
  ([xs n] (combination-repe xs n []))
  ([xs n sol]
   (lazy-seq 
    (cond (= n (count sol)) (list sol)
          (empty? xs) nil
          :else (mapcat (fn [[x x-rest]]
                          (combination-repe x-rest n (conj sol x))) (picks xs))))))


;;;;

(def l1 [1 2 3 4])
(count  (combination-repe l1 3))

(picks l1)