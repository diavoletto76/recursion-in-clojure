(ns recursion.coin-exchnge
  (:require [recursion.combinator :refer [->>pick-and-keep-first-and-consume]]))

; Given a value N, if we want to make change for N cents, and we have infinite 
; supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can 
; we make the change? The order of coins doesn’t matter.

; For example, for N = 4 and S = {1,2,3}, there are four solutions: 
; {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. 
; For N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
; {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. 
; So the output should be 5.

; Combination with repetition

(defn change 
  ([coins target] (change coins target []))
  ([coins target acc]
   (cond (= target (reduce + acc)) (list acc)
         (> (reduce + acc) target) nil
         :else (mapcat (fn [[x x-rest]] (change x-rest target (conj acc x))) 
                       (->>pick-and-keep-first-and-consume coins)))))

;;;;

(def c1 [1 2 3])
(def t1 4)

(assert (= 4 (count (change c1 t1))))
