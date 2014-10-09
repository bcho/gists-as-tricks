(ns gists-as-tricks.view.user
  "User views."
  (:require [gists-as-tricks.model.gist :as gist]))

(defn list-user-gists
  "List user's trick gists.
  
  A trick gist is where description contains ``trick`` word (case insensitive).
  "
  [user]
  (str (gist/get-by-user user)))
