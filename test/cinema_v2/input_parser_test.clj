(ns cinema-v2.input-parser-test
  (:require [clojure.test :refer [deftest testing is]]
            [cinema-v2.input-parser :as sut]))

(deftest movie-parser-test
  (testing "single word movie with valid row and columns"
    (is (= [["Inception" 10 10] nil] (sut/parse-movie "Inception 10 10"))))
  (testing "multi word movie with valid row and columns"
    (is (= [["Good morning, Vietnam!" 10 10] nil] (sut/parse-movie "Good morning, Vietnam! 10 10"))))
  (testing "Invalid input with ingering input after row and column"
    (is (= [nil "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]"] (sut/parse-movie "Good morning, Vietnam! 10 10 abc"))))
  (testing "single word movie with invalid 0 row and columns"
    (is (= [nil "Row must be between 1 and 26 inclusive"] (sut/parse-movie "Inception 0 10"))))
  (testing "single word movie with invalid 27 row and columns"
    (is (= [nil "Row must be between 1 and 26 inclusive"] (sut/parse-movie "Inception 27 10"))))
  (testing "single word movie with invalid row and columns"
    (is (= [nil "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]"] (sut/parse-movie "Inception a 10"))))
  (testing "single word movie with row and invalid 0 column"
    (is (= [nil "Column must be between 1 and 50 inclusive"] (sut/parse-movie "Inception 10 0"))))
  (testing "single word movie with row and invalid 51 columns"
    (is (= [nil "Column must be between 1 and 50 inclusive"] (sut/parse-movie "Inception 10 51"))))
  (testing "single word movie with row and invalid columns"
    (is (= [nil "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]"] (sut/parse-movie "Inception 10 b"))))
  (testing "empty string returns only error messages"
    (is (= [nil "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]"] (sut/parse-movie ""))))
  (testing "single string returns only error messages"
    (is (= [nil "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]"] (sut/parse-movie "hello"))))
  (testing "single string returns only error messages"
    (is (= [nil "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]"] (sut/parse-movie "hello")))))
