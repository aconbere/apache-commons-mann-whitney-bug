(ns bug.core
  (:require [clojure.java.io :as io])
  (:import (org.apache.commons.math3.stat.inference MannWhitneyUTest))
  (:gen-class :main true))

(defn read-number-file
  [path]
  (map #(Integer/parseInt %) (clojure.string/split (-> path io/resource slurp) #"\n")))

(defn u-test
  [group-a group-b]
  (-> (MannWhitneyUTest.)
      (.mannWhitneyUTest (double-array group-a) (double-array group-b))))

(defn random-list
  [length max-value]
  (take length (repeatedly #(rand-int max-value))))

(defn -main
  []
  (let [group-a (read-number-file "1.tsv")
        group-b (read-number-file "default.tsv")]
    (println "with full data set" (u-test group-a group-b))
    (println "with smaller data set"
             (u-test (take 30000 group-a) (take 30000 group-b))))
  (let [group-a (random-list 50000 300)
        group-b (random-list 50000 3000)]
    (println "large random set" (u-test group-a group-b)))

  (let [group-a (random-list 2000 300)
        group-b (random-list 2000 300)]
    (println "smaller random set" (u-test group-a group-b))))
