(ns recursion.invalid-parens)

; Input  : str = “()())()” -
; Output : ()()() (())()
; There are two possible solutions
; "()()()" and "(())()"

; Input  : str = (v)())()
; Output : (v)()()  (v())()


(defn val [x]
  (cond (= x \() 1
        (= x \)) -1
        :else 0))

(defn valid? [x]
  (let [v (->> (map val x) (reductions +))]
    (and (not (empty? x))
         (= 0 (last v))
         (->> (map (partial <= 0) v)
              (every? true?)))))

(defn remove-at [xs n]
  (concat (take n xs)
          (drop (inc n) xs)))

(defn rem [xs]
  (map (partial remove-at xs) (range (count xs))))


(defn dump-sol [xs]
  (map #(apply str %) xs))

(defn fix [xs]
  (cond (empty? xs) nil
        (valid? xs) (list xs)
        :else (let [sol (filter valid? (rem xs))]
                (println "xxx" sol)
                (if (empty? sol)
                  (mapcat fix (rem xs))
                  sol))))
;;;;

(def p1 "()())()")
(def p2 "()()()")
(def p3 "(())()")
(def p4 "((")
(def p5 "((()")

(def l1 [1 2 3 4 5])

(valid? '())
;; 

(reverse (sort-by count (fix p5)))

(empty? "")

(fix p5)