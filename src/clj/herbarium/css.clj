(ns herbarium.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "black"}]
  [:section {:margin-top "40px"}]

  [:li.species-item.selected {:color "red"}]

  [:.container {:display "flex"}]

  [:#species-container [:.title {:display "block" :font-size "200%"}]]

  [:.taxonomy :.clade {:width "315px"}]
  [:.taxonomy :.clade-type {:display "inline-block" :width "100px"}]

  [".symptoms .add-symptom" {:display "none"}]
  [".symptoms[open] .add-symptom" {:display "inline"}]

  [:details {:margin-left "15px"}]
)
