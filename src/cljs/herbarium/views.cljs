(ns herbarium.views
  (:require
   [re-frame.core :as re-frame]
   [herbarium.subs :as subs]
   [herbarium.translations :refer [translate]]
   ))

(def taxonomy-types [:kingdom :class :order :family :genus :species])
(def plant-parts [:whole-plant :leaves :stem :bark :root])
(def application-methods [:raw :cooked :dried :infusion :poultice :oil])

(defn tr
  "Translate the given phrase to the current language."
  ([phrase] (tr phrase @(re-frame/subscribe [::subs/lang])))
  ([phrase lang] (translate lang phrase)))

(defn dispatch-key
  ([key] (fn [event] (re-frame/dispatch [key (-> event .-target .-value)])))
  ([key val] (fn [_] (re-frame/dispatch [key val]))))

(defn stop-propagation [f]
  (fn [e] (.preventDefault e) (f e)))

(defn update-value
  "Dispatch an event to update the value at the given path in the selected species."
  [path]
  (fn [event]
    (re-frame/dispatch
     [:set-value [(concat [:selected] path)
                  (-> event .-target .-value)]])))

(defn get-in-species [& path]
  (get-in @(re-frame/subscribe [::subs/selected-species]) path))

(defn edit? [] @(re-frame/subscribe [::subs/edit?]))

(defn option-elem [item] [:option {:value item :key item} (tr item)])
(defn select-elem [props items]
  [:select props
   (->> items
        (sort-by tr)
        (map option-elem)
        doall)])

(defn value
  ([path] (value {} path))
  ([params path]
   (let [val (apply get-in-species path)]
     (if (edit?)
       [:input (merge {:type "text"
                       :value (or val "")
                       :on-change (update-value path)}
                      params)]
       [:span params val]))))

(defn species-list-item [{:keys [latin name] :as species}]
  (let [selected (re-frame/subscribe [::subs/selected-species])]
    [:li {:on-click (dispatch-key :selected-species species)
          :key latin
          :class ["species-item" (when (= latin @selected) "selected")]}
     name]))

(defn species-list
  "Generate a side bar contining all species, that can be searched over."
  []
  (let [species (re-frame/subscribe [::subs/species])
        species-selector (re-frame/subscribe [::subs/species-search])]
    [:div {:id "species-list-container" :class "side-bar"}
     [:input {:id "species-search" :type "text" :name "species-search"
              :placeholder (tr :search-species)
              :value (str @species-selector)
              :on-change (dispatch-key :species-search)}]
      [:ul {:id "species-list"} (doall (map species-list-item @species))]]))

(defn clade [clade]
  [:div {:class "clade" :key clade}
   [:span {:class "clade-type"} (tr clade)]
   [:span {:class "clade-name"} (value [:taxonomy clade])]])

(defn taxonomy
  "Return the taxonomy section."
  []
  [:div {:class "taxonomy"}
   (doall (map clade taxonomy-types))])

(defn symptom [part symptom]
  [:div {:class "symptom"}
   (value {:type "checkbox" :class "is-positive"} [:parts part :symptoms symptom :positive])
   (value {:class "symptom-name" :placeholder (tr :symptom)} [:parts part :symptoms symptom :name])
   (if (edit?)
     (select-elem {:class "application" :placeholder (tr :sub-application)} application-methods)
     (value {:class "application" :placeholder (tr :sub-application)} [:parts part :symptoms symptom :application]))
   (value {:class "amount-needed" :placeholder (tr :amount-per-kg)} [:parts part :symptoms symptom :amount-per-kg])
   (value {:class "t-start" :placeholder (tr :time-till-active)} [:parts part :symptoms symptom :time-till-active])
   (value {:class "t-end" :placeholder (tr :duration)} [:parts part :symptoms symptom :duration])
   (when (edit?)
     [:span {:class "actions"}
      [:button {:on-click (dispatch-key :dissoc-value [:selected :parts part :symptoms symptom])} "delete"]])
   ])

(defn add-symptom [part]
  [:button {:class "add-symptom"
            :on-click (stop-propagation (dispatch-key :add-symptom part))}
   "+"])

(defn part-symptoms [part]
  (let [symptoms (get-in-species :parts part :symptoms)]
    [:details {:class "symptoms"}
     [:summary (tr :symptoms) (add-symptom part)]
     (map (partial symptom part) (keys symptoms))]))

(defn part-active-subs [part]
  [:details {:class "active-subs"}
   [:summary (tr :active-substances)]
   [:textarea (get-in-species :parts part :active-substances)]])


(defn plant-part [part]
  [:details {:class "plant-part" :id part :key part}
   [:summary (tr part)]
   (part-symptoms part)
   (part-active-subs part)
   ])

(defn details []
  (vec (concat [:div {:id "species-container" :class "content"}]
   (when-let [species @(re-frame/subscribe [::subs/selected-species])]
     [[:div {:id "title"}
       [value {:class "title"} [:name]]
       [value {:class "title"} [:latin]]]
      [:section {:id "general-info"}
       (taxonomy)
       [:div [:br][:br] "(tu by trza dać jakieś inne rzeczy, typu odnośniki do wikipedii itp.)"]]
      [:section {:id "usages"}
       (tr :usages)
       (doall (map plant-part plant-parts))]
      ]))))

(defn main-panel []
  [:div {:class "container"}
   (species-list)
   (details)
   ])
