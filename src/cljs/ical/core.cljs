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

; moment().add('month', -1).endOf('month').startOf('week').add('days',1).format()
(defn last-monday [current]
  (->
    current
    (.add "month" -1)
    (.endOf "month")
    (.startOf "week")
    (.add "days" 1)))

; moment().add("month", 1).startOf('month').endOf('week').add('days', 1).format("dddd/DD/MM/YYYY")
(defn first-sunday [current]
  (->
     current
     (.add "month" 1)
     (.startOf "month")
     (.endOf "week")
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
     (first-sunday end-of-month))))

(defn weeks [start-date]
  (vec (for [week-counter (range 0 35 7)]
    (vec (for [day-counter (range 7)]
      (->
       start-date
       (.clone)
       (.add "days" (+ week-counter day-counter))))))))

(defn render-weeks [week-counter]
  [:tr
   [:td "flappies"]])

(defn ical [data owner]
  (reify
    om/IDisplayName
      (display-name [_]
        (or (:react-name opts) "calendar"))
    om/IRender
      (render [this]
        (let [today (js/moment (new js/Date))
              first-day-of-month (first-day-of-month today)
              weeks (weeks first-day-of-month)]
          (log weeks)
          (html/html [:div.calendar-toolbar
                      [:div.btn-group.pull-right
                       [:a.right {:href "#"} "Right"]
                       [:a.left {:href "#"} "Left"]]
                      [:h3 "August 2014"]
                      [:table.table.table-striped.table-bordered.table-hover
                       [:thead
                        [:tr (map #(identity [:th %]) weekdays)]]
                       [:tbody
                        (map render-weeks (range 0 35 7))]]])))))
