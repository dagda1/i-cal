(ns example.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as sab :include-macros true]
    [devcards.core :as dc :include-macros true]
    [ical.core :as ical])
  (:require-macros
   [devcards.core :refer [defcard]]))

(enable-console-print!)

(dc/start-devcard-ui!)

(dc/start-figwheel-reloader!)

(defcard omcard-ical
  (dc/om-root-card ical/ical {}))
