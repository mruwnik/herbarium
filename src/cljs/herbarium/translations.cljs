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

             ;; Inflorescence
             :anthela :dichasium :spadix :botryoid :corymb-racemose
             :dreparium :spike :catkin :compound-spike :head :thyrse
             :cinicinnus :compound-triple-umbel :calathid :panicle :thyrsoid
             :compound-umbel :raceme :umbel :pleiochasium

             ;; Leaf types
             :bifoliolate :bipinnate :imparipinnate :paripinnate :trifoliolate :bigeminate
             :biternate :palmately-compound :simple :tripinnate

             ;; Leaf shapes
             :acicular :elliptic :flabelate :lobed :obcordate :orbicular :pedate :spear-shaped
             :cordate :ensiforme :hastate :lorate :oblanceolate :ovale :reniform :trullate
             :deltoid :falcate :lanceolate :lyrate :oblong :palmate :rhomboid :digitate :filiform
             :linear :multifide :obovate :pandurate :spatulate

             ;; Leaf edges
             :ciliate :crenate :dentate :denticulate :doubly-serrate :entire
             :lobate :serrate :serrulate :sinuate :spiny :undulate

             ;; Fruit
             :achene :capsule :caryopsis :drupe :follicle :legume :nut
             :samara :silique :siliqua :berry :achaenium :schizocarp
             ]
            (map #(vector % (key->name %)))
            (into {})
            (merge
             {:sub-application "Method of application"
              :amount-per-kg "Grams per body kg"
              :time-till-active "Minutes till active"
              :duration "Duration in minutes"

              :compound-heterothetic "Heterothetic compound raceme"
              :compound-homeothetic "Homeothetic compound raceme"
              :corymb-cymose "Cymose corymb"
              :cyme-double-curled "Double cyme"
              :cyme-double-straight "Double cyme"
              :compound-capitulum "Compound capitulum"
              :cyme-umbelliform "Umbelliform cyme"
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

        ;; Inflorescence
        :anthela "anthela"
        :compound-heterothetic "Heterothetic compound raceme"
        :compound-homeothetic "Homeothetic compound raceme"
        :corymb-cymose "Cymose corymb"
        :cyme-double-curled "Double cyme"
        :cyme-double-straight "Double cyme"
        :thyrse "Thyrse"
        :thyrsoid "Thyrsoid"
        :compound-capitulum "Compound capitulum"
        :cyme-umbelliform "Umbelliform cyme"

        :dichasium "wierzchotka dwuramienna"
        :pleiochasium "wierzchotka wieloramienna"
        :spadix "kolba"
        :botryoid "grono skończone (?)"
        :corymb-racemose "baldachogrono"
        :dreparium "sierpik"
        :spike "kłos"
        :catkin "kotka"
        :compound-spike "kłos złożony"
        :head "główka"
        :calathid "koszyczek"
        :cinicinnus "wachlarzyk"
        :compound-triple-umbel "baldach złożony (potrójnie)"
        :panicle "wiecha"
        :compound-umbel "baldach złożony"
        :raceme "grono"
        :umbel "baldach"

        ;; leaf types
        :bifoliolate "dwulistkowy"
        :bipinnate "podwójnie pierzasty"
        :imparipinnate "nieparzystopierzasty"
        :paripinnate "parzystopierzasty"
        :trifoliolate "trójlistkowy"
        :bigeminate "podwójnie dwulistkowy"
        :biternate "potrójnie trójlistkowy"
        :palmately-compound "dłoniasto złożony"
        :simple "prosty"
        :tripinnate "potrójnie pierzasty"

        ;; Leaf shapes
        :acicular "szpilkowy"
        :elliptic "eliptyczny"
        :flabelate "łopatkowaty"
        :lobed "pierzastowrębny"
        :obcordate "odwrotnie sercowaty"
        :orbicular "okrągły"
        :pedate "pierzastowrębny"
        :spear-shaped "strzałkowaty"
        :cordate "sercowaty"
        :ensiforme "igiełkowaty"
        :hastate "oszczepowaty"
        :lorate "równowąski"
        :oblanceolate "lancetowaty"
        :ovale "jajowaty"
        :reniform "nerkowaty"
        :trullate "romboidalny"
        :deltoid "trójkątny"
        :falcate "owalnie lancetowaty"
        :lanceolate "owalnie lancetowaty"
        :lyrate "pierzastowrębny"
        :oblong "równowąski"
        :palmate "pierzastowrębny"
        :rhomboid "romboidalny"
        :digitate "pierzastowrębny"
        :filiform "szpilkowy"
        :linear "spiczasto-jajowaty"
        :multifide "pierzastowrębny"
        :obovate "odwrotnie jajowaty"
        :pandurate "łopatkowaty"
        :spatulate "łopatkowaty"

        ;; Leaf edges
        :ciliate "ciliate"
        :crenate "karbowany"
        :dentate "ząbkowany"
        :denticulate "ząbkowany"
        :doubly-serrate "podwójnie piłkowany"
        :entire "całobrzegi"
        :lobate "falisto wcięty"
        :serrate "piłkowany"
        :serrulate "piłkowany"
        :sinuate "falisto"
        :spiny "z kolcami"
        :undulate "falisto wcięty"

        ;; Fruit
        :achene "niełupka"
        :capsule "Torebka"
        :caryopsis "Ziarniak"
        :drupe "pestkowiec"
        :follicle "Mieszek"
        :legume "strąk"
        :nut "orzech"
        :samara "skrzydlak"
        :silique "Łuszczynka"
        :siliqua "Łuszczyna"
        :berry "jagoda"
        :achaenium "Niełupka"
        :schizocarp "Rozłupnia"
        }

   :tongue/fallback :pl})

(def translate (tongue/build-translate dicts))
