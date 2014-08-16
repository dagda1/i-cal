(ns ical.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def weekdays ["saturday" "monday" "tuesday" "wednesday" "thursday" "friday" "sunday"])

(defn log [s]
  (.log js/console (str s)))

; do this
; moment().add('month', -1).endOf('month').startOf('week').add('days',1).format()
(defn last-monday [current]
  (->
    current
    (.add "month" -1)
    (.endOf "month")
    (.startOf "week")
    (.add "days" 1)))

(defn first-day-of-month [current]
  (let [start-of-month (.date current 1)
        week-day (.weekday start-of-month)]
    (if (= 1 week-day)
      start-of-month
      (last-monday current))))

(defn ical [data]
  (reify
    om/IDisplayName
      (display-name [_]
        (or (:react-name opts) "calendar"))
    om/IRender
      (render [this]
        (let [today (js/moment (new js/Date))
              first-day-of-month (first-day-of-month today)]
          (log (.format first-day-of-month "DD/MM/YYYY"))
          (html/html [:div.calendar-toolbar
                      [:div.btn-group.pull-right
                       [:a.right {:href "#"} "Right"]
                       [:a.left {:href "#"} "Left"]]
                      [:h3 "August 2014"]
                      [:table.table.table-striped.table-bordered.table-hover
                       [:thead
                        [:tr (map #(identity [:th %]) weekdays)]]]])))))
