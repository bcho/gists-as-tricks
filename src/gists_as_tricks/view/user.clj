(ns gists-as-tricks.view.user
  "User views."
  (:require [gists-as-tricks.model.gist :as gist-model]
            [gists-as-tricks.render.gist :refer [render]]
            [clostache.parser :refer [render-resource]]))

(defn list-user-gists
  "List user's trick gists.
  
  A trick gist is where description contains ``trick`` word (case insensitive).
  "
  [user]
  (let [gists (gist-model/get-by-user user)
        rendered-gists (map render gists)]
    (str (render-resource "templates/list.html" {:gists rendered-gists}))))
