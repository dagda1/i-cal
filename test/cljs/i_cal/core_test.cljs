;;; This namespace is used for testing purpose. It use the
;;; clojurescript.test lib.
(ns i-cal.core-test
  (:require-macros [cemerick.cljs.test :as m :refer (deftest testing are)])
  (:require [cemerick.cljs.test :as t]
            [i-cal.core :refer (foo)]))

(deftest foo-test
  (testing "I don't do a lot\n"
    (testing "Edge cases\n"
      (testing "(foo str)"
        (are [expected actual] (= expected actual)
             "ClojureScript!" (foo "")
             "Hello, ClojureScript!" (foo nil))))))
