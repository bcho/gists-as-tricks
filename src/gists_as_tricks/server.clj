(ns gists-as-tricks.server
  "Jetty server container."
  (:gen-class)
  (:require [gists-as-tricks.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))


(defn -main [& args]
  ; FIXME
  (let [port (. Integer parseInt (or (System/getenv "GISTS_AS_TRICKS_SERVER_PORT")
                                     "9344"))]
    (run-jetty app {:port port})))
