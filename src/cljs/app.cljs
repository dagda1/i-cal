(ns i-cal.core
  (:require
    [figwheel.client :as fw :include-macros true]
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]))

(def state (atom {:doc {} :saved? false}))

(enable-console-print!)

(defn log [s]
  (.log js/console (str s)))

(log "hoors droop")

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/h1 nil (:text data)))))

(om/root widget {:text "Hello world!"}
  {:target (. js/document (getElementById "app"))})

(fw/watch-and-reload
  ;; :websocket-url "ws://localhost:3449/figwheel-ws" default
  :jsload-callback (fn [] (print "reloaded"))) ;; optional callback
