(ns ical.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def weekdays ["saturday" "monday" "tuesday" "wednesday" "thursday" "friday" "sunday"])

(defn log [s]
  (.log js/console (str s)))

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

(defn render-days [week-counter day-counter]
  (let [first-day-of-month (first-day-of-month (js/moment (new js/Date)))]
    [:td
     (->
       first-day-of-month
       (.clone)
       (.add "days" (+ week-counter day-counter))
       (.format "DD"))]))

(defn render-weeks [week-counter]
  [:tr
   (map (partial render-days week-counter) (range 7))])

(defn ical [data owner]
  (reify
    om/IDisplayName
      (display-name [_]
        (or (:react-name opts) "calendar"))
    om/IRender
      (render [this]
          (html/html [:div.calendar-toolbar
            [:div.btn-group.pull-right
             [:a.right {:href "#"} "Right"]
             [:a.left {:href "#"} "Left"]]
            [:h3 "August 2014"]
            [:table.table.table-striped.table-bordered.table-hover
             [:thead
              [:tr (map #(identity [:th %]) weekdays)]]
             [:tbody
              (map render-weeks (range 0 35 7))]]]))))
