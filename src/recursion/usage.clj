(ns recursion.usage
  (:require [recursion.combination :refer [combination1 
                                           combination2 
                                           combination-count]]))

(def l1 [1 2 3 4 5])
(def l2 [1 2 3 4 5 6 7 8])

(assert (count (combination1 l1 2)) (combination-count l1 2))
(assert (count (combination2 l1 2)) (combination-count l1 2))
(assert true? (=  (sort  (combination1 l1 2))
                  (sort  (combination2 l1 2))))