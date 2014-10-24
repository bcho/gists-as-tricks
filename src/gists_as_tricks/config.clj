(ns gists-as-tricks.config
  "Application configuration manager."
  (:require [gists-as-tricks.service.database :refer [set-db!]]
            [clojure.tools.logging :as log]))


(defn- get-from-envvar
  "Get environment variable value from name."
  [cate]
  (->> cate (str "gists_as_tricks_")
       clojure.string/upper-case
       System/getenv))


(def default-server-configs {:port 9344})

(defn- collect-server-configs-from-env
  "Collect server configs from env."
  []
  (let [port (or (get-from-envvar "server_port") "9344")]
    {:port (. Integer parseInt port)}))

(defn- collect-server-configs
  "Collect server configs."
  [cli-configs]
  (let [env-configs (collect-server-configs-from-env)]
    (merge default-server-configs env-configs cli-configs)))


(def default-db-configs {:subprotocol "sqlite"
                         :subname ":memory:"})

(defn- collect-db-configs
  "Collect database configs."
  [cli-configs]
  (merge default-db-configs cli-configs))


(defn manager->collect
  "Collect configs from command line & environment variables."
  [cli-config]
  (let [{:keys [db server]} cli-config]
    {:db (collect-db-configs db)
     :server (collect-server-configs server)}))


(defn manager->run
  "Apply configs. (Setup database etc.)"
  [configs]
  (let [{:keys [db]} configs]

    ;; Setup database connection.
    (if db
      (->> (:subname db) (str "Using database connection: ") log/info)
      (set-db! db))))
