(ns recursion.combinator-test
  (:require [clojure.test :refer :all]
            [recursion.combinator :refer :all]))

(def l1 [1 2 3 4])
(def l2 [1 2 3])

(deftest test-permutation
  (testing "Permutation"
    (is (= (count (permutation l2 3))
           (permutation-count l2 3)))))

(deftest test-permutation-with-repe
  (testing "Permutation with repetition"
    (is (= (count (permutation-with-repe l2 3))
           (permutation-with-repe-count l2 3)))))

(deftest test-combination
  (testing "Combination"
    (is (= (count (combination l1 3))
           (combination-count l1 3)))))

(deftest test-combination-with-repe
  (testing "Combination with repetition"
    (is (= (count (combination-with-repe l1 3))
           (combination-with-repe-count l1 3)))))
