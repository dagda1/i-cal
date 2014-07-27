(ns test.ical
  (:require-macros
    [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])
  (:require cemerick.cljs.test))

(enable-console-print!)

(deftest somewhat-less-wat
  (is (= 1 1)))
