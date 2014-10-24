(ns gists-as-tricks.server
  "Jetty server container."
  (:require [gists-as-tricks.handler :refer [app]]
            [gists-as-tricks.service.database :refer [set-db!]]
            [clojure.java.io :as io]
            [ring.adapter.jetty :refer [run-jetty]]))


(defn manager->run
  "Run server."
  [config]
  ; FIXME refine config
  (let [port (. Integer parseInt (or (System/getenv "GISTS_AS_TRICKS_SERVER_PORT")
                                     "9344"))
        db-config (:db config)]

    (if db-config
      (set-db! db-config))
    
    (run-jetty app {:port port})))
