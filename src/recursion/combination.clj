(ns recursion.combination
  (:require [recursion.utils :refer [factorial pick-rest]]))

;;;; Combination
;;;; https://tinyurl.com/y6dvv7dv

(defn combination-count [xs r]
  (let [n (count xs)]
    (/ (factorial n)
       (* (factorial r)
          (factorial (- n r))))))

;; Combination without repetition - Algorithm 1
;; Inclusion and Exclusion of every element
;; 
;; We use the idea similar to the subset sum problem for creating possible 
;; combinations of K numbers from n ;; numbers— We select each number from 1 to
;; n and recur for two possible cases:
;; 
;; 1 The selected number is the part of the solution or included in the set
;; 2 The selected number is not part of the solution or not included in the set
;; 
;; Suppose we have n=4 and K=2 i.e: Given Range : [1,2,3,4]. 
;; The following tree diagram explains the generation of all combinations of size 2.
;; We are going left if we are including the number and going right if we are 
;; not including the number. At each level, we are including or excluding one 
;; number at a time. We have a combination of size 2 at each leaf node.

(defn combination1 ([xs n] (combination1 xs n []))
  ([xs n sol]
   (lazy-cat
    (if (empty? xs) nil
        (let [sa (conj sol (first xs))
              sb sol
              next-sa (combination1 (rest xs) n sa)
              next-sb (combination1 (rest xs) n sb)]
          (cond
            (= n (count sa)) (concat (list sa) next-sb)
            (= n (count sb)) (concat (list sb) next-sa)
            :else (concat next-sa next-sb)))))))


;; Combination without repetition - Algorithm 2
;; Fix elements and recur for creating a combination of K numbers
;; 
;; The idea is to generate a combination tree where we fix each number from 1 
;; to n and recursively build the combination of K numbers. Suppose we have 
;; n = 5 and K=3 i.e: Given Range : [1,2,3,4, 5].
;; We first fix number 1 and recursively generate the all unique combination 
;; of size 3 starting with number 1 i.e. {1,2,3}, {1,2,4}, {1,2,5}, {1,3,4}, 
;; {1,4,5}.
;; Then we first fix number 2 and recursively generate the all unique 
;; combination of size 3 starting with number 2 i.e. {2,3,4}, {2,3,5} and 
;; {2,4,5}.
;; Then we first fix number 3 and recursively generate the all unique 
;; combination of size 3 starting with number 3 i.e. {3,4, 5}. 
;; There would be no unique combination of size 3 starting from 4 and 5.


(defn combination2
  ([xs n] (combination2 xs n []))
  ([xs n sol]
   (lazy-seq
    (cond (= n (count sol)) (list sol)
          (empty? xs) nil
          :else (mapcat (fn [[[x] x-rest]] (combination2 x-rest n (conj sol x)))
                        (pick-rest xs))))))

;; Combination without repetition - Algorithm 3
;; 
;; Recursive Backtracking — DFS Approach
;; 
;; In this approach, we are using the power of DFS to recursively iterate 
;; through the range to generate all possible combinations. The iteration 
;; steps of the DFS approach is similar to the second approach we discussed 
;; above. Here we iterate until we get a set consisting of K elements and store
;; that subset in our resultant vector and then we backtrack and remove the 
;; previous element inserted in our temporary vector and consider further 
;; elements from the range which are not considered. This way all combinations 
;; are generated.
;; 
;; The functional implementation seems to be identical to combination2

(def combination3 combination2)
