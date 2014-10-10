(ns gists-as-tricks.service.github
  "GitHub API service."
  (:require [clj-http.client :as http]
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
  
  Path is string likes:

    /orgs/octokit/repos
  "
  [path method & [opts]]
  (let [req-opts (merge {:method method
                         :url (make-real-endpoint path)
                         :throw-exceptions false} opts)
        {:keys [body status]} (http/request req-opts)]
    {:status status
     :body (json/parse-string body true)}))

(defn list-gists
  "List a user's gists.
  
  https://developer.github.com/v3/gists/#list-gists
  "
  [username]
  (let [endpoint (str "/users/" username "/gists")
        {:keys [body status]} (make-request endpoint :get)]
    (if (= (quot status 100) 2)
      body
      [])))
