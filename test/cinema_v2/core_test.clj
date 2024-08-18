(ns cinema-v2.core-test
  (:require [cinema-v2.core :refer :all]
            [clojure.test :refer :all]))


(defn capture-println-output [f]
  (let [out (java.io.StringWriter.)]
    (binding [*out* out]
      (f))
    (str out)))

(defn setup-mock-input [input]
  (let [inputs (atom input)]
    (fn []
      (let [input (first @inputs)]
        (swap! inputs rest)
        input))))

(deftest setup-movie-test
  (testing "Valid multi-line movie setup"
    (with-redefs [read-line (setup-mock-input ["Good morning, Vietnam! 10 10" "3"])]
      (is (= "You have setup movie Good morning, Vietnam! with 10x10 seatmap.\n" (capture-println-output setup-movie))))))