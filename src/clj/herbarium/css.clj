(ns herbarium.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "black"}]

  [:.input-label {:width "140px" :display "inline-flex"}]
  [:summary {:font-size "20px" :font-weight "bold"}]

  [:section {:margin "20px 5px"}
   [:.header {:font-size "26px" :font-weight "bold"}]]

  [:.edit-species
   [:details {:margin-left "10px"}]

   [:.list-multi-checkbox {:margin "20px 0px"}]

   [:.multi-checkbox
    [:.type-select
     [:.fancy-checkbox-contents
      {:color "#444;"
       :border "1px solid #CCC"
       :margin "5px"
       :background "#DDD"
       :box-shadow "0 0 5px -1px rgba(0,0,0,0.2)"
       :cursor "pointer"}]

     [:input {:display "none"}]
     ["input[type=checkbox] + label .fancy-checkbox-contents" {:border "5px solid #DDD"}]
     ["input[type=checkbox]:checked + label .fancy-checkbox-contents"
      {:box-shadow "0 0 5px 1px rgba(0,0,200.6)"}]]]

   [:.part-types
    {:width "650px"
     :display "flex"
     :flex-wrap "wrap"}

    [:.type-select
     ["label .fancy-checkbox-contents" {:width "100px" :height "100px" :display "inline-block"}
      [:img {:width "100%" :height "100%" :object-fit "scale-down"}]]

     ["label .tooltiptext"
      {:display "none"
       :position "absolute"
       :z-index 1
       :padding "10px"
       :background-color "black"
       :color "white"}]
     ["label:hover .tooltiptext" {:display "inherit"}]]]

   [:.colour-picker
    {:width "700px"}
    [:.input-label {:position :relative :top "-40px"}]
    [:.type-select
     ["label .fancy-checkbox-contents"
      {:background :none :width "60px" :height "60px"}
      [:img {:object-fit :cover}]]
     ["input[type=checkbox] + label .fancy-checkbox-contents" {:border :none}]
     ]]
   ]
)
