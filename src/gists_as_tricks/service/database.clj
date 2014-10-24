(ns gists-as-tricks.service.database
  "Database service."
  (:require [clojure.java.io :as io]))


; TODO runtime config
(defn get-db
  "Get database connection settings."
  []
  {:subprotocol "sqlite"
   :subname (.getFile (io/resource "database/db.sqlite3"))})
