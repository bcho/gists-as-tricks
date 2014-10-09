(ns gists-as-tricks.service.github
  "GitHub API service."
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json]))


; GitHub API root endpoint.
(def github-api "https://api.github.com")

(defn- make-real-endpoint
  "Concat sub part with root endpoint."
  [sub]
  (str github-api sub))

(defn- make-request
  "Request an API endpoint and parse json body.

  Returns :status, :error and :body
  
  Endpoint is string likes:

    /orgs/octokit/repos
  "
  [endpoint method]
  (let [req-opts {:method method
                  :url (make-real-endpoint endpoint)}
        {:keys [body status error]} @(http/request req-opts)]
    {:status status
     :error error
     :body (json/parse-string body)}))

(defn list-gists
  "List a user's gists.
  
  https://developer.github.com/v3/gists/#list-gists
  "
  [username]
  (let [endpoint (str "/users/" username "/gists")
        {:keys [body error]} (make-request endpoint :get)]
    (if error
      []
      body)))
