(ns gists-as-tricks.model.schema
  "Database schema."
  (:require [gists-as-tricks.service.database :as database]
            [yesql.core :refer [defqueries]]))

(defqueries "database/schema.sql")

(def tables [create-user-cache-table!])

(defn build
  "Build schema."
  []
  (let [db-config (database/get-db)
        create-table (fn [t] (t db-config))]
    (doall (map create-table tables))))
