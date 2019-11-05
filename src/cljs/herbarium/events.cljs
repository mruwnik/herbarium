(ns herbarium.events
  (:require
   [re-frame.core :as re-frame]
   [herbarium.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(defn register-replacer
  "Register an event handler that will replace the given key with whatever comes."
  ([key] (register-replacer key [key]))
  ([key path] (re-frame/reg-event-db key (fn [db [_ val]] (assoc-in db path val)))))

(defn register-merger
  "Register an event handler that will merge the given key with whatever comes."
  ([key] (register-merger key [key]))
  ([key path] (re-frame/reg-event-db key (fn [db [_ val]] (update-in db path merge val)))))

(register-replacer :species-search)
(register-merger ::set-species [:selected])

(re-frame/reg-event-db :set-value (fn [db [_ [path val]]] (assoc-in db path val)))
(re-frame/reg-event-db :dissoc-value (fn [db [_ path]] (update-in db (butlast path) dissoc (last path))))

(re-frame/reg-event-db
 :add-symptom
 (fn [db [_ part]]
   (doall (map println db))
   (update-in db [:selected :parts part :symptoms]
              #(assoc (or % (sorted-map)) (gensym) {}))))

(defn get-species
  "Query the backend for the given species."
  [latin-name]
  ({"Thymus vulgaris" {:taxonomy {:kingdom "Plantae"
                                  :class "Magnoliopsida"
                                  :order "Lamiales"
                                  :family "Lamiaceae"
                                  :genus "Thymus"
                                  :species "T. vulgaris"
                                  :clades ["Tracheophytes" "Euphyllophyta" "Asterids"]}
                       }
    } latin-name))

(re-frame/reg-fx
 :fetch-species
 (fn [{latin :latin}]
   (re-frame/dispatch [::set-species (get-species latin)])))

(re-frame/reg-event-fx
 :selected-species
 (fn [{db :db} [_ species]]
   {:db (assoc db :selected species)
    :fetch-species species}))
