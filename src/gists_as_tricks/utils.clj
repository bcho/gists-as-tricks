(ns gists-as-tricks.utils
  "Utilities.")


(defn- read-from-file-with-trusted-contents
  [file-name]
  (with-open [r (java.io.PushbackReader.
                  (clojure.java.io/reader file-name))]
    (binding [*read-eval* false]
      (read r))))

(defn parse-config
  "Read & execute config script."
  [config-file-name]
  (read-from-file-with-trusted-contents config-file-name))
