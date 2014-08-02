(ns app.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [ical.core :as ical]
    [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(om/root ical/ical {} {:target js/document.body})
