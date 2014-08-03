(ns ical.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def weekdays ["saturday" "monday" "tuesday" "wednesday" "thursday" "friday" "sunday"])

(defn log [s]
  (.log js/console (str s)))

; (log (.format (js/moment)))
; (log (. (. (js/moment ) subtract "days" offset) startOf "day"))

(defn ical [data]
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
                      [:tr (map #(identity [:th %]) weekdays)]]]]))))
