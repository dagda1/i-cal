(ns example.core
  (:require
   [devcards.core :as dc :include-macros true])
  (:require-macros
   [devcards.core :refer [defcard]]))

(dc/start-devcard-ui!)

;; optional
(dc/start-figwheel-reloader!)

;; required ;)
(defcard my-first-card
  (dc/sab-card [:h1 "Devcards is freaking awesome!"]))
