(ns recursion.subset-sum
  (:require [recursion.combinator :refer [->>pick-and-consume]]))

; Subset sum problem is to find subset of elements that are selected from a 
; given set whose sum adds up to a given number K. We are considering the 
; set contains non-negative values. It is assumed that the input set is 
; unique (no duplicates are presented).

; Combination without repetition

(defn subset-sum
  ([xs target] (subset-sum xs target []))
  ([xs target sol]
   (cond (= target (reduce + sol)) (list sol)
         (empty? xs) nil
         :else (mapcat (fn [[x x-rest]] (subset-sum x-rest target (conj sol x))) (->>pick-and-consume xs)))))

;;;;

(def l1 [1 3 2 4 8 2])
(def t1 12)

(->> (subset-sum l1 12)
     (map (partial reduce +))
     (every? (partial = 12))
     (assert))
