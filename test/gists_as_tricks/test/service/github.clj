(ns gists-as-tricks.test.service.github
  (:require [clojure.test :refer :all]
            [gists-as-tricks.service.github :refer :all]
            [clj-http.fake :refer [with-fake-routes]]))


(defn make-mock-list-gists-route
  [resp]
  { #"https://api.github.com/users/.*/gists" resp})

(deftest test-list-gists
  (testing "list user's gists"
    (with-fake-routes (make-mock-list-gists-route (fn [req] {:status 200
                                                             :body "[{}, {}]"}))
      (let [gists (list-gists "test")]
        (is (= (count gists) 2) "should return expected list for valid user"))))
  
  (testing "list invalid user's gists"
    (with-fake-routes (make-mock-list-gists-route (fn [req] {:status 404
                                                             :body "[{} {}]"}))
      (let [gists (list-gists "test")]
        (is (= (count gists) 0) "should return empty list for invalid user")))))
