(ns gists-as-tricks.database
  "Database service manager."
  (:require [gists-as-tricks.model.schema :refer [build]]
            [gists-as-tricks.service.database :refer [set-db!]]))


(defn manager->run
  "Init database schema."
  [config]
  (let [db-config (:db config)]
    (println "Init database...")

    (if db-config
      (set-db! db-config))
    (build)

    (println "Database is ready.")))
