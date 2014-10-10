(ns gists-as-tricks.model.gist
  "Gist model."
  (:require [gists-as-tricks.service.github :as github]))


(defn- trick-gist?
  "Check if a gist is trick gist."
  [gist]
  (re-find #"[tT][rR][iI][cC][kK]" (:description gist)))

(defn get-by-user
  "Get user's trick gists."
  [user]
  ; TODO caching
  (let [gists (github/list-gists user)]
    (filter trick-gist? gists)))
