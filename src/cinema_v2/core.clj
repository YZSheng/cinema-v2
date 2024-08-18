(ns cinema-v2.core
  (:gen-class)
  (:require [cinema-v2.input-parser :refer [parse-movie]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn setup-movie []
  (loop [input (read-line)]
    (when (not= "3" input) ;; TODO: only exit when it's in main menu
      (let [[[name row col] _] (parse-movie input)]
        (println (str "You have setup movie " name " with " row "x" col " seatmap.")))
      (recur (read-line)))))