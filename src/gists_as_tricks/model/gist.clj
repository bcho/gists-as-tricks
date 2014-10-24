(ns gists-as-tricks.model.gist
  "Gist model."
  (:require [gists-as-tricks.service.github :as github]
            [gists-as-tricks.service.database :as database]
            [cheshire.core :as json]
            [yesql.core :refer [defqueries]]))


; Gist query result cache time: 10 minutes
(def result-ttl-ms (* 10 60))

(defqueries "database/gist.sql")

(defn- trick-gist?
  "Check if a gist is trick gist."
  [gist]
  (re-find #"[tT][rR][iI][cC][kK]" (:description gist)))

(defn- get-by-user-from-database
  "Get user's trick gists from database."
  [user]
  (let [db-config (database/get-db)
        cached (first (sql-get-by-user-with-ttl db-config user result-ttl-ms))]
    (if cached
      (json/parse-string (:content cached) true)
      nil)))

(defn- write-gists-to-database
  "Write user's gists to database."
  [user gists]
  (let [cache-gists (json/generate-string gists)
        db-config (database/get-db)
        cache-count ((first (sql-get-user-cache-count db-config user)) :count)]
    (if (> cache-count 0)
      (sql-set-user-cache! db-config cache-gists user)
      (sql-create-user-cache! db-config cache-gists user))))

(defn- get-by-user-from-github
  "Get user's trick gists from github."
  [user]
  (let [gists (filter trick-gist? (github/list-gists user))]
    (write-gists-to-database user gists)
    gists))

(defn get-by-user
  "Get user's trick gists."
  [user]
  (let [gists (get-by-user-from-database user)]
    (if gists
      gists
      (get-by-user-from-github user))))
