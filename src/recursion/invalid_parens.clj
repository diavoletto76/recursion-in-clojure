(ns recursion.invalid-parens)

; An expression will be given which can contain open and close parentheses and
; optionally some characters, No other operator will be there in string. We 
; need to remove minimum number of parentheses to make the input string valid. 
; 
; If more than one valid output are possible removing same number of parentheses 
; then print all such output.
; 
; Input  : str = “()())()” -
; Output : ()()() (())()
; There are two possible solutions
; "()()()" and "(())()"
;
; Input  : str = (v)())()
; Output : (v)()()  (v())()

(defn parens? [x]
  (or (= x \() (= x \))))

(defn value [x]
  (cond (= x \() 1
        (= x \)) -1
        :else 0))

(defn valid? [xs]
  (let [parens (filter parens? xs)
        values (->> (map value parens) (reductions +))]
    (and (seq parens)
         (= 0 (last values))
         (->> (map (partial <= 0) values)
              (every? true?)))))

(defn remove-at [xs n]
  (concat (take n xs)
          (drop (inc n) xs)))

(defn remove-paren [xs]
  (->> (map #(if (or (= (nth xs %) \() (= (nth xs %) \)))
               (remove-at xs %)
               nil) (range (count xs)))
       (filter seq)))

(defn fix-parens [xs]
  (cond (valid? xs) (list xs)
        :else (let [sol-all   (remove-paren xs)
                    sol-valid (filter valid? sol-all)]
                (if (empty? sol-valid)
                  (mapcat fix-parens sol-all)
                  sol-valid))))

(defn find-solutions [xs]
  (->> (fix-parens xs)
       (distinct)
       (map #(apply str %))))

;;;;

(def p1 "()())()")
(def p2 "()()()")
(def p3 "(())()")
(def p4 "((b")
(def p5 "(((a)")
(def p6 "(v)())()")

