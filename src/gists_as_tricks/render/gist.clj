(ns gists-as-tricks.render.gist
  "Gist renderer."
  (:require [hiccup.core :refer [html]]
            [clj-time.core :as t]
            [clj-time.format :as f]))


(defn- embed-url
  "Get gist's embed url"
  [gist]
  (str (:html_url gist) ".js"))

(defn- render-date
  "Render a date string."
  [date]
  (let [parsed-date (f/parse (:date-time-no-ms f/formatters) date)]
    (f/unparse (:year-month-day f/formatters) parsed-date)))

(defn- render-meta
  "Render gist meta part."
  [gist]
  (let [file-name (name (first (keys (:files gist))))
        date (render-date (:created_at gist))]
    (html [:header.gist-meta
           [:h1.gist-title file-name]
           [:h3.gist-date date]])))

(defn- render-source
  "Render gist source."
  [gist]
  (html [:div.gist-source [:script {:src (embed-url gist)}]]))

(defn render
  "Render a gist."
  [gist]
  (html [:div.gist-wrapper (render-meta gist) (render-source gist)]))
