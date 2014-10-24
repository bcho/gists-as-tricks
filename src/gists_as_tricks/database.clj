(ns gists-as-tricks.database
  "Database service manager."
  (:require [gists-as-tricks.model.schema :refer [build]]
            [clojure.tools.logging :as log]))


(defn manager->run
  "Init database schema."
  [config]
  (log/info "Init database...")
  (build)
  (log/info "Database is ready."))
