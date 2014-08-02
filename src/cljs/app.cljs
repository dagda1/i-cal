(ns i-cal.core
  (:require
    [figwheel.client :as fw :include-macros true]
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(def state (atom {:doc {} :saved? false}))

(enable-console-print!)

(defn log [s]
  (.log js/console (str s)))

(log "hoors droop")

(defn widget [data]
  (om/component
   (html [:h2 "This is an om component, " (:text data)])))

(om/root widget {:text "hoors droop"} {:target js/document.body})

(fw/watch-and-reload
  ;; :websocket-url "ws://localhost:3449/figwheel-ws" default
  :jsload-callback (fn [] (print "reloaded"))) ;; optional callback
