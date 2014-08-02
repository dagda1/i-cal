(ns app.core
  (:require
    [figwheel.client :as fw :include-macros true]
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [ical.core :as ical]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(om/root ical/ical {} {:target js/document.body})

(fw/watch-and-reload
  ;; :websocket-url "ws://localhost:3449/figwheel-ws" default
  :jsload-callback (fn [] (print "reloaded"))) ;; optional callback
