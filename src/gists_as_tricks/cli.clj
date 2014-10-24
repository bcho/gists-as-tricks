(ns gists-as-tricks.cli
  "Commaindline interface."
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string]
            [gists-as-tricks.utils :refer [parse-config]]))


; Commandline options.
(def options
  [["-c" "--config CONFIG.clj" "Config file."
    :parse-fn parse-config]
   ["-h" "--help" "Display this messages."]])

; Available commands
(def available-commands ["init", "run"])


(defn- error-msg
  "Generate error message retrieved from clojure.tools.cli."
  [errors]
  (str "The following errors occurred while parsing command:\n\n"
       (string/join \newline errors)))


(defn- usage
  "Generate program usage."
  [options-summary]
  (->> ["Gists as Tricks"
        ""
        "Usage: <program> [options] command"
        ""
        "Options:"
        options-summary
        ""
        "Commands:"
        "    init: Initialize application (database etc)."
        "    run:  Run application."]
       (string/join \newline)))


(defn exit
  "Exit program."
  [status msg]
  (println msg)
  (System/exit status))


(defn parse
  "Parse commandline input, return command & cli-configs.
  Exit on help or errors occurred."
  [args]
  (let [{:keys [options arguments errors summary]} (parse-opts args options)
        command (first arguments)
        configs (:config options)]
    (cond
      ;; Handle errors & help.
      (:help options) (exit 0 (usage summary))
      errors (exit 1 (error-msg errors))
      ;; Check command.
      (not (some #(= command %) available-commands))
           (exit 1 (error-msg [(str "Unknown command: " command)])))
    
    {:command command :cli-configs configs}))
