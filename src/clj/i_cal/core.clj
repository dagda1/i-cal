(ns i-cal.core
  (:require
            [liberator.core :refer [resource defresource by-method]]
            [liberator.representation :refer :all]
            [liberator.dev :refer (wrap-trace)]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.core :refer [defroutes ANY GET PUT]]
            [ring.util.response :as resp]
            [ring.adapter.jetty :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [clojure.java.io :as io]))

(defroutes app
  (ANY "/" [] (resp/redirect "/index.html")) 

  (route/resources "/"))

(def handler
  (-> app
      (wrap-trace :header :ui)))

(defn start [port]
  (ring/run-jetty #'handler {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt
       (or (System/getenv "PORT") "3000"))]
  (start port)))
