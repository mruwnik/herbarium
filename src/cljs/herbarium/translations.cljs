(ns herbarium.translations
  (:require [tongue.core :as tongue]
            [clojure.string :as str]
            [re-frame.core :as re-frame]))

(defn key->name [value]
  (-> value name str/capitalize (str/replace "-" " ")))

(def dicts
  {:en (->> [:save :latin-name :name
             :search-species
             :kingdom :class :order :family :genus :species
             :usages :whole-plant :leaves :stem :bark :root :flowers :fruit

             :life-form
             :phanerophyte :epiphyte :chamaephyte :hemicryptophyte :geophyte
             :helophyte :hydrophyte :therophyte :aerophyte

             :life-span :summer-annual :winter-annual :biennial :perennial

             ;; Inflorescence
             :inflorescence :floral-formula :blooms
             :anthela :dichasium :spadix :botryoid :corymb_racemose
             :dreparium :spike :catkin :compound_spike :head :thyrse
             :cinicinnus :compound_triple_umbel :calathid :panicle :thyrsoid
             :compound-umbel :raceme :umbel :pleiochasium

             ;; Leaf types
             :leaf-structure
             :bifoliolate :bipinnate :imparipinnate :paripinnate :trifoliolate :bigeminate
             :biternate :palmately_compound :simple :tripinnate

             ;; Leaf shapes
             :leaf-shape
             :acicular :elliptic :flabelate :lobed :obcordate :orbicular :pedate :spear_shaped
             :cordate :ensiforme :hastate :lorate :oblanceolate :ovale :reniform :trullate
             :deltoid :falcate :lanceolate :lyrate :oblong :palmate :rhomboid :digitate :filiform
             :linear :multifide :obovate :pandurate :spatulate

             ;; Leaf edges
             :leaf-edge
             :ciliate :crenate :dentate :denticulate :doubly_serrate :entire
             :lobate :serrate :serrulate :sinuate :spiny :undulate

             ;; Fruit
             :achene :capsule :caryopsis :drupe :follicle :legume :nut
             :samara :silique :siliqua :berry :schizocarp
             ]
            (map #(vector % (key->name %)))
            (into {})
            (merge
             {:compound-heterothetic "Heterothetic compound raceme"
              :compound-homeothetic "Homeothetic compound raceme"
              :corymb-cymose "Cymose corymb"
              :cyme-double-curled "Double cyme"
              :cyme-double-straight "Double cyme"
              :compound-capitulum "Compound capitulum"
              :cyme-umbelliform "Umbelliform cyme"
              }))

   :pl {:save "zapisz"
        :latin-name "Nazwa łacinska"
        :name "Nazwa zwyczajowa"

        :search-species "Wyszukaj gatunek"
        :kingdom "Królestwo"
        :class "Klasa"
        :order "Rząd"
        :family "Rodzina"
        :genus "Rodzaj"
        :species "Gatunek"

        :usages "Zastosowania"
        :whole-plant "Cała roślina"
        :leaves "Liście"
        :flowers "Kwiaty"
        :stem "Łodyga"
        :bark "Kora"
        :root "Korzenie"
        :fruit "Owoc"

        ;; life forms
        :life-form "Forma życiowa"
        :phanerophyte "Fanerofit"
        :epiphyte "Epifit"
        :chamaephyte "Chamefit"
        :hemicryptophyte "Hemikryptofit"
        :geophyte "Geofit"
        :helophyte "Helofit"
        :hydrophyte "Hydrofit"
        :therophyte "terofit"
        :aerophyte "aerofit"

        ;; life span
        :life-span "Czas życia"
        :summer-annual "Jara"
        :winter-annual "Ozima"
        :biennial "Dwuletnia"
        :perennial "Bylina"

        ;; Inflorescence
        :floral-formula "Wzór kwiatowy"
        :blooms "Kwitnie"
        :inflorescence "Kwiatostan"
        :anthela "anthela"
        :compound_heterothetic "Heterothetic compound raceme"
        :compound_homeothetic "Homeothetic compound raceme"
        :corymb_cymose "Cymose corymb"
        :cyme_double_curled "Double cyme"
        :cyme_double_straight "Double cyme"
        :thyrse "Thyrse"
        :thyrsoid "Thyrsoid"
        :compound_capitulum "Compound capitulum"
        :cyme_umbelliform "Umbelliform cyme"

        :dichasium "wierzchotka dwuramienna"
        :pleiochasium "wierzchotka wieloramienna"
        :spadix "kolba"
        :botryoid "grono skończone (?)"
        :corymb_racemose "baldachogrono"
        :dreparium "sierpik"
        :spike "kłos"
        :catkin "kotka"
        :compound_spike "kłos złożony"
        :head "główka"
        :calathid "koszyczek"
        :cinicinnus "wachlarzyk"
        :compound_triple_umbel "baldach złożony (potrójnie)"
        :panicle "wiecha"
        :compound_umbel "baldach złożony"
        :raceme "grono"
        :umbel "baldach"

        ;; leaf types
        :leaf-structure "Kształ blaszki"
        :bifoliolate "dwulistkowy"
        :bipinnate "podwójnie pierzasty"
        :imparipinnate "nieparzystopierzasty"
        :paripinnate "parzystopierzasty"
        :trifoliolate "trójlistkowy"
        :bigeminate "podwójnie dwulistkowy"
        :biternate "potrójnie trójlistkowy"
        :palmately_compound "dłoniasto złożony"
        :simple "prosty"
        :tripinnate "potrójnie pierzasty"

        ;; Leaf shapes
        :leaf-shape "Kształt blaski liściowej"
        :acicular "szpilkowy"
        :elliptic "eliptyczny"
        :flabelate "łopatkowaty"
        :lobed "pierzastowrębny"
        :obcordate "odwrotnie sercowaty"
        :orbicular "okrągły"
        :pedate "pierzastowrębny"
        :spear_shaped "strzałkowaty"
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
        :leaf-edge "Brzeg blaszki"
        :ciliate "ciliate"
        :crenate "karbowany"
        :dentate "ząbkowany"
        :denticulate "ząbkowany"
        :doubly_serrate "podwójnie piłkowany"
        :entire "całobrzegi"
        :lobate "falisto wcięty"
        :serrate "piłkowany"
        :serrulate "piłkowany"
        :sinuate "falisto"
        :spiny "z kolcami"
        :undulate "falisto wcięty"

        ;; Fruit
        :achene "niełupka"
        :capsule "torebka"
        :caryopsis "ziarniak"
        :drupe "pestkowiec"
        :follicle "mieszek"
        :legume "strąk"
        :nut "orzech"
        :samara "skrzydlak"
        :silique "łuszczynka"
        :siliqua "łuszczyna"
        :berry "jagoda"
        :schizocarp "rozłupnia"
        }

   :tongue/fallback :pl})

(def translate (tongue/build-translate dicts))
