(ns herbarium.subs
  (:require
   [clojure.string :as str]
   [re-frame.core :as re-frame]))

(defn contains-name?
  "Check whether the given species definition contains `search-name` in any of its fields."
  [{:keys [name latin aliases] :or {aliases []}} search-name]
  (->> [name latin]
       (concat aliases)
       (map str/lower-case)
       (some #(str/includes? % (str/lower-case search-name)))))

(re-frame/reg-sub
 ::species
 (fn [{:keys [species-search species]}]
   (if-not species-search
     species
     (filter #(contains-name? % species-search) species))))

(re-frame/reg-sub ::lang (fn [db] (:lang db)))
(re-frame/reg-sub ::edit? (fn [db] (:edit? db)))
(re-frame/reg-sub ::species-search (fn [db] (:species-search db)))
(re-frame/reg-sub ::selected-species (fn [db] (:selected db)))
(re-frame/reg-sub ::selected-taxonomy (fn [db] (-> db :selected :taxonomy)))
