(ns se.citerus.crazysnake.brain.clojure-brain
  (:import
    [se.citerus.crazysnake Movement])
  (:gen-class
    :name se.citerus.crazysnake.brain.ClojureBrain
    :implements [se.citerus.crazysnake.Brain]
    :main false))

;-- Implementation of Brain interface

(defn -init [this participants meta])

(defn -getName [this]
  "ClojureBrain")

(defn -getNextMove [this state]
  Movement/FORWARD)

