(ns gists-as-tricks.handler
  "Application router."
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [gists-as-tricks.view.user :refer [list-user-gists]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/resources "/")

  ; List user's trick gists.
  (GET ["/:user", :user #"[0-9a-zA-Z]+"] [user] (list-user-gists user))

  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
