(ns gists-as-tricks.main
  "Application entry."
  (:gen-class)
  (:require [gists-as-tricks.cli :as cli]
            [gists-as-tricks.config :as config]
            [gists-as-tricks.database :as db]
            [gists-as-tricks.server :as server]))


(defn -main [& args]
  (let [{:keys [command cli-configs]} (cli/parse args)
        configs (config/manager->collect cli-configs)]
    (config/manager->run configs)
    (cond
      (= command "init") (db/manager->run (:db configs))
      (= command "run") (server/manager->run (:server configs)))))
