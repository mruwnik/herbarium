(ns herbarium.views
  (:require
   [re-frame.core :as re-frame]
   [herbarium.subs :as subs]
   [herbarium.translations :refer [translate]]))

(defn tr
  "Translate the given phrase to the current language."
  ([phrase] (tr phrase @(re-frame/subscribe [::subs/lang])))
  ([phrase lang] (translate lang phrase)))

(defn text-input
  ([name] (text-input name (tr name)))
  ([name label]
   [:div {:class "datum-input"}
    [:label {:for name} label]
    [:input {:name name :id name :type "text"}]]))

(defn radio-input [name values]
  (into [:div {:class "radio-buttons"} [:label (tr name)]]
        (for [type values]
          [:span
           [:input {:type "radio" :name name :id type :value type}]
           [:label {:for type} (tr type)]])))

(defn fancy-checkbox [name value display]
  [:span {:class "type-select" :key value}
   [:input {:type "checkbox" :name name :id value :value value}]
   [:label {:for value} [:span {:class "fancy-checkbox-contents"} display]]])

(defn multi-checkbox [name items]
   (into [:div {:class "multi-checkbox"}]
         (for [[value display] items] (fancy-checkbox name value display))))


;; Generates a group of selectable images
(defn part-type-selector [base-path part type]
  (let [name (subs type 0 (- (count type) 4))]
    [name
     [:span
      [:span {:class "tooltiptext"} (tr (keyword name))]
      [:img {:src (str base-path type) :alt type}]]]))

(defn part-types [base-path part types]
  [:details {:class "part-types-container" :open true}
   [:summary (tr part)]
   [:div {:class "part-types"}
    (multi-checkbox part (into {} (map (partial part-type-selector base-path part) types)))]])


(defn life-forms []
  [:div
   (radio-input :life-form
                [:phanerophyte :epiphyte :chamaephyte :hemicryptophyte :geophyte
                 :helophyte :hydrophyte :therophyte :aerophyte])
   (radio-input :life-span [:summer-annual :winter-annual :biennial :perennial])])

(defn main-panel []
  [:div {:class "container"}
   [:form {:method "post" :class "edit-species"}
    (text-input :latin-name)
    (text-input :name)
    (text-input :order)
    (text-input :family)

    (life-forms)

    [:details {:open true}
     [:summary (tr :flowers)]
     (text-input :floral-formula)

     [:div {:class "bloom-times"}
      [:label {:for :blooms} (tr :blooms)]
      (multi-checkbox :blooms (into {} (for [i (range 1 13)] [i i])))]

     (part-types "img/inflorescence/" :inflorescence
                 ["botryoid.svg" "raceme.svg" "spike.svg" "catkin.svg" "corymb_racemose.svg"
                  "umbel.svg" "spadix.svg" "head.svg" "calathid.png" "compound_capitulum.svg"
                  "cyme_double_curled.svg" "cyme_double_straight.svg" "dreparium.svg"
                  "cinicinnus.svg" "dichasium.svg" "compound_heterothetic.svg" "corymb_cymose.svg"
                  "compound_umbel.svg" "compound_homeothetic.svg" "compound_spike.svg"
                  "anthela.svg" "thyrse.svg" "compound_triple_umbel.svg" "panicle.svg" "thyrsoid.svg"])
     (radio-input :fruit
                  [:achene :capsule :caryopsis :drupe :follicle :legume :nut
                   :samara :silique :siliqua :berry :schizocarp])]

    [:details {:open true}
     [:summary (tr :leaves)]
     (part-types "img/leaf/edge/" :leaf-edge
                 ["entire.png", "ciliate.png", "denticulate.png", "spiny.png",
                  "serrulate.png", "dentate.png", "serrate.png", "doubly_serrate.png",
                  "crenate.png", "sinuate.png", "undulate.png", "lobate.png"])
     (part-types "img/leaf/structure/" :leaf-structure
                 ["simple.png" "bifoliolate.png" "bigeminate.png" "trifoliolate.png"
                  "palmately_compound.png" "biternate.png" "imparipinnate.png" "paripinnate.png"
                  "tripinnate.png" "bipinnate.png"])
     (part-types "img/leaf/shape/" :leaf-shape
                 ["falcate.png" "lanceolate.png" "linear.png" "oblanceolate.png"
                  "hastate.png" "spear-shaped.png" "rhomboid.png" "trullate.png"
                  "elliptic.png" "ovale.png" "orbicular.png" "reniform.png"
                  "obcordate.png" "spatulate.png" "pandurate.png" "lyrate.png"
                  "deltoid.png" "cordate.png" "flabelate.png" "obovate.png"
                  "palmate.png" "digitate.png" "pedate.png"  "lobed.png"
                  "multifide.png" "ensiforme.png" "filiform.png"  "acicular.png"
                  "lorate.png" "oblong.png"])]
    [:input {:type :submit :value (tr :save)}]
    ]
   @(re-frame/subscribe [::subs/name])
   ])
