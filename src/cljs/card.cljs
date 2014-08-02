(ns example.core
  (:require
    [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [sablono.core :as sab :include-macros true]
    [devcards.core :as dc :include-macros true])
  (:require-macros
   [devcards.core :refer [defcard]]))

(enable-console-print!)

(dc/start-devcard-ui!)

(dc/start-figwheel-reloader!)

(defonce om-test-atom (atom {:count 20}))

(defn counter [owner data f s]
  (om/component
   (sab/html
    [:div
     [:h1 (om/get-shared owner :title) (:count data)]
     [:div [:a {:onClick #(om/transact! data :count f)} s]]
     (dc/edn->html data)])))

(defn om-counter-inc [data owner] (counter owner data inc "inc"))

(defcard omcard-shared-ex-1
  (dc/om-root-card om-counter-inc om-test-atom {:shared {:title "First counter "}}))
