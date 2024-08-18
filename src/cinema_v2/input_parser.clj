(ns cinema-v2.input-parser
  (:require [clojure.string :as string]))

(def ^:private movie-input-format-error-message "Invalid input. Please enter movie input in this format: [Movie name] [row] [column]")
(def ^:private movie-input-row-error-message "Row must be between 1 and 26 inclusive")
(def ^:private movie-input-col-error-message "Column must be between 1 and 50 inclusive")

(defn ^:private is-row-valid [row]
  (< 0 row 27))

(defn ^:private is-column-valid [col]
  (< 0 col 51))

(defn match-movie-input [input]
  (re-matches #"^(.*)\s+(\d+)\s+(\d+)$" input))

(defn parse-movie [input]
  (let [[_ name row col :as parts] (match-movie-input input)]
    (cond
      (< (count parts) 4)
      [nil movie-input-format-error-message]

      (not (is-row-valid (parse-long row)))
      [nil movie-input-row-error-message]

      (not (is-column-valid (parse-long col)))
      [nil movie-input-col-error-message]

      :else
      [[name (parse-long row) (parse-long col)] nil])))
