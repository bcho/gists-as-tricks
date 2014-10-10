(ns gists-as-tricks.test.model.gist
  (:require [clojure.test :refer :all]
            [gists-as-tricks.service.github :as github]
            [gists-as-tricks.model.gist :refer :all]))


(defn make-test-gist [trick-gist?]
  {:url "test"
   :html_url "http://test/test"
   :id "test"
   :description (if trick-gist? "trick" "test")})

(defn github-list-gists-mock [user]
  (for [i (range 10)]
    (make-test-gist (= 0 (mod i 2)))))

(deftest test-get-by-user
  (with-redefs-fn {#'github/list-gists github-list-gists-mock}
    #(let [gists (get-by-user "test")]
       (is (= (count gists) 5)))))
