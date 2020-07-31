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

             :ecology :height
             :life-form
             :phanerophyte :epiphyte :chamaephyte :hemicryptophyte :geophyte
             :helophyte :hydrophyte :therophyte :aerophyte

             :life-span :summer-annual :winter-annual :biennial :perennial

             ;; Environment
             :light-requirements :heliophyte :sciophyte :facultative-sciophytes :facultative-heliophytes
             :stem-type :herbaceous :woody
             :growth-pattern :vertical :bushy :cover :vine
             :vegetive-reproduction :side-growth :clonal :stolon :rhizome

             ;; Soil
             :soil :high :low
             :soil-type :loess :peat :silt :clay :sandy :chalk :stony
             :soil-ph :very-acidic :acidic :slightly-acidic :neutral :alkaline
             :soil-moisture :dry :wet :water-logged :submerged
             :macronutrient-requirements :nitrogen :potasium :phosphorous :calcium :magnesium :sulfur

             ;; Inflorescence
             :inflorescence :floral-formula :blooms
             :anthela :dichasium :spadix :botryoid :corymb_racemose
             :dreparium :spike :catkin :compound_spike :head :thyrse
             :cinicinnus :compound_triple_umbel :calathid :panicle :thyrsoid
             :compound-umbel :raceme :umbel :pleiochasium

             :flower-colour :red :yellow :blue :white :green :orange :purple
             ;; Flower shape
             :flower-symmetry :flower-shape
             :actinomorphic :calceolate :campanulate :coronate :crateriform :cruciform
             :cyanthiform :funnelform :galeate :ligulate :papilionaceous :rotate :saccate
             :salverform :stellate :tubulate :urceolate :zygomorphi

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
        :ecology "Ekologia"
        :height "Wysokość"

        ;; Environment
        :light-requirements "Wymagania świetlne"
        :heliophyte "Heliofit"
        :sciophyte "Skiofit"
        :facultative-heliophytes "Heliofit facultatywny"
        :facultative-sciophytes "Skiofit facultatywny"

        :stem-type "Typ łodygi"
        :herbaceous "Zielna"
        :woody "Drzewiasta"

        :vegetive-reproduction "Rozmnażanie wegetatywne"
        :growth-pattern "Sposób wrzostu"
        :vertical "Pionowy"
        :bushy "Krzaczasty"
        :cover "Płożący"
        :side-growth "Gałęże boczne"
        :clonal "Klonalny"
        :stolon "Stolony"
        :rhizome "Kłącze"
        :vine "Pnącze"

        ;; Soil
        :soil "Gleba"
        :soil-type "Rodzaj gleby"
        :loess "lessowa"
        :peat "torfowa"
        :silt "ilasta"
        :chalk "wapienna"
        :clay "gliniasta"
        :stony "kamienista"
        :sandy "piaszczysta"

        :soil-ph "pH gleby"
        :very-acidic "bardzo kwaśne"    ; < 4.5
        :acidic "kwaśne"                ; 4.6 - 5.5
        :slightly-acidic "lekko kwaśne" ; 5.6 - 6.5
        :neutral "obojętne"             ; 6.6 - 7.2
        :alkaline "zasadowe"            ; > 7.3

        :high "wysokie" :low "niskie"
        :macronutrient-requirements "Wymagane makroelementy"
        :nitrogen "azot"
        :potasium "potas"
        :phosphorous "fosfor"
        :calcium "wapń"
        :magnesium "magnez"
        :sulfur "siarka"

        :soil-moisture "Wilgotność gleby"
        :dry "sucha"
        :wet "wilgotna"
        :water-logged "podmokła"
        :submerged "podwodna"

        ;; life forms
        :life-form "Forma życiowa"
        :phanerophyte "Fanerofit"
        :epiphyte "Epifit"
        :chamaephyte "Chamefit"
        :hemicryptophyte "Hemikryptofit"
        :geophyte "Geofit"
        :helophyte "Helofit"
        :hydrophyte "Hydrofit"
        :therophyte "Terofit"
        :aerophyte "Aerofit"

        ;; life span
        :life-span "Czas życia"
        :summer-annual "Jara"
        :winter-annual "Ozima"
        :biennial "Dwuletnia"
        :perennial "Bylina"

        ;; Flower
        :flower-colour "Kolor okwiatu"
        :red "Czerwony"
        :yellow "Żółty"
        :blue "Niebieski"
        :white "Biały"
        :green "Zielony"
        :orange "Pomarańczowy"
        :purple "Fioletowy"

        :flower-symmetry "Symetria"
        :flower-shape "Kształt okwiatu"
        :tubulate "trąbkowate"
        :campanulate "dzwonkowate"
        :funnelform "lejkowate"
        :ligulate "wargowate"
        :papilionaceous "motylkowate"

        ;;;;;; Unknown
        :actinomorphic "actinomorphic"
        :calceolate "calceolate"
        :coronate "coronate"
        :crateriform "crateriform"
        :cruciform "cruciform"
        :cyanthiform "cyanthiform"
        :galeate "galeate"
        :rotate "rotate"
        :saccate "saccate"
        :salverform "salverform"
        :stellate "stellate"
        :urceolate "urceolate"
        :zygomorphic "zygomorphic"

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
