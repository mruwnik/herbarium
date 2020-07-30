(ns herbarium.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "black"}]

  [:.edit-species
   [:.datum-input
    [:label {:width "140px" :display "inline-flex"}]]

   [:details {:margin-left "10px"}]

   [:.part-types
    {:width "650px"
     :display "flex"
     :flex-wrap "wrap"}

    [:.type-select
     [:label {:width "100px" :height "100px" :display "inline-block"}
      [:img {:width "100%" :height "100%" :object-fit "scale-down"}]]

     ["label .tooltiptext"
      {:display "none"
       :position "absolute"
       :z-index 1
       :padding "10px"
       :background-color "black"
       :color "white"}]
     ["label:hover .tooltiptext" {:display "inherit"}]

     [:input {:display "none"}]
     ["input[type=checkbox] + label img" {:border "5px solid white"}]
     ["input[type=checkbox]:checked + label img" {:border "5px solid red"}]]]]
)
