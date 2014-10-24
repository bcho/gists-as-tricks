(ns gists-as-tricks.main
  "Application entry."
  (:gen-class)
  (:require [gists-as-tricks.cli :as cli]
            [gists-as-tricks.database :as db]
            [gists-as-tricks.server :as server]))


(defn -main [& args]
  (let [{:keys [command configs]} (cli/parse args)]
    (cond
      (= command "init") (db/manager->run configs)
      (= command "run") (server/manager->run configs))))
