(ns gists-as-tricks.service.database
  "Database service.")


(def db-connection (atom {:subprotocol "sqlite"
                          :subname ":memory:"}))


(defn get-db
  "Get database connection settings."
  []
  @db-connection)


(defn set-db!
  "Set database connection settings."
  [settings]
  (reset! db-connection settings))
