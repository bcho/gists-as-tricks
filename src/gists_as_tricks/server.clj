(ns gists-as-tricks.server
  "Jetty server container."
  (:require [gists-as-tricks.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))


(defn manager->run
  "Run server."
  [config]
  (run-jetty app config))
