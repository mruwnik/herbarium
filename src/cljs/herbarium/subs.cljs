(ns herbarium.subs
  (:require
   [clojure.string :as str]
   [re-frame.core :as re-frame]))

(re-frame/reg-sub ::lang (fn [db] (:lang db)))
(re-frame/reg-sub ::backend-url (fn [db] (:backend-url db)))
