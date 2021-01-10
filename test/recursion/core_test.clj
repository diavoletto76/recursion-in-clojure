(ns recursion.core-test
  (:require [clojure.test :refer :all]
            [recursion.core :refer :all]))

(deftest a-test
  (testing "Testing identity"
    (is (identity 1) 1)))
