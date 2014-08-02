(ns ical.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(defn log [s]
  (.log js/console (str s)))

(defn ical [data]
  (om/component
   (html [:h2 "Render calendar"])))
