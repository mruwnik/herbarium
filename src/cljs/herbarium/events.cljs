(ns herbarium.events
  (:require
   [re-frame.core :as re-frame]
   [herbarium.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

;; (re-frame/reg-fx
;;  :fetch-species
;;  (fn [{latin :latin}]
;;    (re-frame/dispatch [::set-species (get-species latin)])))
