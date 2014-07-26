(ns i-cal.core
  (:require [reagent.core :as reagent :refer [atom]]))

(def state (atom {:doc {} :saved? false}))

(enable-console-print!)

(defn log [s]
  (.log js/console (str s)))

(log "hoors")

(defn some-component []
  [:div
   [:h3 "I am a component!"]
   [:p.someclass 
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red"]
    " text."]])

(reagent/render-component [some-component] (.getElementById js/document "app"))
