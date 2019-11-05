(ns herbarium.translations
  (:require [tongue.core :as tongue]
            [clojure.string :as str]
            [re-frame.core :as re-frame]))

(defn key->name [value]
  (-> value name str/capitalize (str/replace "-" " ")))

(def dicts
  {:en (->> [:search-species
             :kingdom :class :order :family :genus :species
             :usages :whole-plant :leaves :stem :bark :root
             :symptom :symptoms :active-substances
             :raw :cooked :dried :infusion :poultice :oil
             ]
            (map #(vector % (key->name %)))
            (into {})
            (merge
             {:sub-application "Method of application"
              :amount-per-kg "Grams per body kg"
              :time-till-active "Minutes till active"
              :duration "Duration in minutes"
              }))

   :pl {:search-species "Wyszukaj gatunek"
        :kingdom "Królestwo"
        :class "Klasa"
        :order "Rząd"
        :family "Rodzina"
        :genus "Rodzaj"
        :species "Gatunek"

        :usages "Zastosowania"
        :whole-plant "Cała roślina"
        :leaves "Liście"
        :stem "Łodyga"
        :bark "Kora"
        :root "Korzenie"

        :symptom "Objaw"
        :symptoms "Objawy"
        :sub-application "Sposób stosowania"
        :amount-per-kg "Gramy na kg masy ciała"
        :time-till-active "Minut do aktywacji"
        :duration "Czas trwania w minutach"
        :active-substances "Substancje czynne"

        :raw "Surowo"
        :cooked "Ugotowano"
        :dried "Wysuszono"
        :infusion "Wywar"
        :poultice "Kompres"
        :oil "Olej"
        }

   :tongue/fallback :pl})

(def translate (tongue/build-translate dicts))
