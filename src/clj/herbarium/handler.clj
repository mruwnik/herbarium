(ns herbarium.handler
  (:require
   [clojure.java.io :as io]
   [clojure.pprint :as pprint]
   [compojure.core :refer [GET POST defroutes]]
   [compojure.route :refer [resources]]
   [ring.util.response :refer [resource-response]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.middleware.params :refer [wrap-params]]
   [shadow.http.push-state :as push-state]))

(defn map-kv [f coll] (reduce-kv #(assoc %1 %2 (f %3)) {} coll))

(defn parse-int [s] (some->> s (re-find #"\d+") (Integer.)))
(defn list-parser [parser]
  (fn [coll]
    (if (coll? coll)
      (map parser coll)
      [(parser coll)])))

(def keyword-list (list-parser keyword))
(def not-empty (comp not empty?))

;;;; Hackity hack - save incoming species to a EDN file
(def species-parsers
  {:latin-name str
   :name str
   :order str
   :family str

   :height parse-int
   :blooms (list-parser parse-int)
   :stem-type keyword
   :light-requirements keyword
   :life-form keyword
   :life-span keyword
   :growth-pattern keyword-list
   :vegetive-reproduction keyword-list
   :thorns not-empty
   :prickles not-empty

   :soil-type keyword-list
   :soil-ph keyword-list
   :soil-moisture keyword-list
   :nitrogen keyword
   :potasium keyword
   :phosphorous keyword
   :calcium keyword
   :magnesium keyword
   :sulfur keyword

   :floral-formula str
   :fruit keyword
   :inflorescence keyword-list
   :flower-symmetry keyword-list
   :flower-shape keyword-list
   :flower-colour keyword-list

   :stipule not-empty
   :leaf-bottom-different not-empty
   :leaf-base keyword
   :phyllotaxis keyword
   :leaf-edge keyword-list
   :leaf-structure keyword-list
   :leaf-shape keyword-list})

(defn parse-value [key val]
  (when (and val (not= "" val))
    ((key species-parsers) val)))

(defn parse-species [species]
  (->> species-parsers
       keys
       (map (fn [key] [key (parse-value key (species (name key)))]))
       (filter second)
       (into {})))

(defn save-species [{latin-name :latin-name :as species}]
  (let [filename (str "resources/species/" latin-name "/data.edn")]
    (io/make-parents filename)
    (->> filename
         clojure.java.io/writer
         (pprint/pprint species))))
;;;;;;;;;;;;;;;

(defn new-species [{params :params}]
  (->> params parse-species save-species)
  (resource-response "index.html" {:root "public"}))


(defroutes routes
  (POST "/add" [] new-species)
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/"))

(def dev-handler (-> #'routes wrap-params wrap-reload push-state/handle))

(def handler (wrap-params routes))
