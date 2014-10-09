(ns gists-as-tricks.model.gist
  "Gist model."
  (:require [gists-as-tricks.service.github :as github]))

(defn get-by-user
  "Get user's trick gists."
  [user]
  ; TODO caching
  (github/list-gists user))
