(defproject gists-as-tricks "0.0.1-SNAPSHOT"
  :description "Gists as tricks!"
  :url "http://tricks.youknowmymind.com"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.9"]
                 [cheshire "5.3.1"]
                 [clj-http "1.0.0"]
                 [hiccup "1.0.5"]]

  :source-paths ["src"]

  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler gists-as-tricks.handler/app}

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [clj-http-fake "0.7.8"]]}})
