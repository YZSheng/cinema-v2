(ns cinema-v2.input-parser
  (:require [clojure.string :as string]))

(def ^:private movie-input-format-error-message "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]")
(def ^:private movie-input-row-error-message "Row must be between 1 and 26 inclusive")
(def ^:private movie-input-col-error-message "Column must be between 1 and 50 inclusive")

(defn ^:private is-row-valid [row]
  (and row (< 0 row 27)))

(defn ^:private is-column-valid [col]
  (and col (< 0 col 51)))

(defn parse-movie [input]
  (let [[name row col :as parts] (string/split input #"\s")]
    (cond
      (< (count parts) 3)
      [nil movie-input-format-error-message]

      (not (is-row-valid (parse-long row)))
      [nil movie-input-row-error-message]

      (not (is-column-valid (parse-long col)))
      [nil movie-input-col-error-message]

      :else
      [[name (parse-long row) (parse-long col)] nil])))
