(ns ical.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def date-format "DD/MM/YYYY")

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
  (let [cloned-date (.clone current)
        start-of-month (.startOf cloned-date "month")
        week-day (.weekday start-of-month)]
    (if (= 1 week-day)
      start-of-month
      (last-monday cloned-date))))

(defn last-day-of-month [current]
  (let [cloned-date (.clone current)
        end-of-month (->
                        cloned-date
                        (.endOf "month"))
        week-day (.weekday end-of-month)]
   (if (= 0 week-day)
     end-of-month
     end-of-month)))

(defn ical [data]
  (reify
    om/IDisplayName
      (display-name [_]
        (or (:react-name opts) "calendar"))
    om/IRender
      (render [this]
        (let [today (js/moment (new js/Date))
              first-day-of-month (first-day-of-month today)
              last-day-of-month (last-day-of-month today)]
          (log (str "start-date = " (.format first-day-of-month date-format)))
          (log (str "end-date = " (.format last-day-of-month date-format)))
          (html/html [:div.calendar-toolbar
                      [:div.btn-group.pull-right
                       [:a.right {:href "#"} "Right"]
                       [:a.left {:href "#"} "Left"]]
                      [:h3 "August 2014"]
                      [:table.table.table-striped.table-bordered.table-hover
                       [:thead
                        [:tr (map #(identity [:th %]) weekdays)]]]])))))
