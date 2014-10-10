(ns gists-as-tricks.view.user
  "User views."
  (:require [gists-as-tricks.model.gist :as gist-model]
            [hiccup.core :refer [html]]))


(defn- embed-gist-script
  "Make embed script element from a gist."
  [gist]
  (html [:script {:src (str (:html_url gist) ".js")}]))

(defn list-user-gists
  "List user's trick gists.
  
  A trick gist is where description contains ``trick`` word (case insensitive).
  "
  [user]
  (let [gists (gist-model/get-by-user user)]
    (str (html (map embed-gist-script gists)))))
