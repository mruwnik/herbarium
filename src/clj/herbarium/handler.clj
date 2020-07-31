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

(defn parse-int [s] (some->> s (re-find #"\d+") (Integer.)))
(defn list-parser [parser]
  (fn [coll]
    (if (coll? coll)
      (map parser coll)
      [(parser coll)])))

;;;; Hackity hack - save incoming species to a EDN file
(def species-parsers
  {str [:latin-name :name :order :family :floral-formula]
   parse-int [:height]
   (list-parser parse-int) [:blooms]
   keyword [:stem-type :light-requirements :life-form :life-span :fruit
            :nitrogen :potasium :phosphorous :calcium :magnesium :sulfur]
   (list-parser keyword) [:growth-pattern :vegetive-reproduction :soil-type :soil-ph :soil-moisture
                          :inflorescence :flower-symmetry :flower-shape :flower-colour
                          :leaf-edge :leaf-structure :leaf-shape]})

(defn parse-species [params]
  (into {}
        (for [[formatter keys] species-parsers k keys]
          [k (some-> k name params formatter)])))

(defn save-species [{latin-name :latin-name :as species}]
  (let [filename (str "resources/species/" latin-name "/data.edn")]
    (io/make-parents filename)
    (->> filename
         clojure.java.io/writer
         (pprint/pprint species))))
;;;;;;;;;;;;;;;


(defn new-species [{params :params}]
  (-> params parse-species save-species)
  (resource-response "index.html" {:root "public"}))


(defroutes routes
  (POST "/add" [] new-species)
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/"))

(def dev-handler (-> #'routes wrap-params wrap-reload push-state/handle))

(def handler (wrap-params routes))
