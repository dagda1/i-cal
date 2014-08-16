(defproject i-cal "0.1.0-SNAPSHOT"
  :description "A ClojureScript and om full-sized drag & drop event calendar"
  :url "https://github.com/dagda1/i-cal"

  :dependencies
  [
    [org.clojure/clojure "1.6.0"]
    [org.clojure/clojurescript "0.0-2268" :scope "provided"]
    [org.clojure/core.async "0.1.267.0-0d7780-alpha" :scope "provided"]
    [com.facebook/react "0.11.1"]
    [com.momentjs/moment "2.7.0"]
    [devcards "0.1.1-SNAPSHOT"]
    [figwheel "0.1.3-SNAPSHOT"]

    [liberator "0.11.0"]

    [compojure "1.1.8"] ; Web routing https://github.com/weavejester/compojure
    [liberator "0.11.0"] ; WebMachine (REST state machine) port to Clojure https://github.com/clojure-liberator/liberator
    [cljs-http "0.1.2"]

    [sablono "0.2.16"]
    [om "0.6.2"]
  ]

  :uberjar-name "i-cal.jar"

  :jvm-opts ["-Xmx1G"]

  :profiles {
    :dev {
      :dependencies [
                      [ring/ring-devel "1.2.1" :exclusions [joda-time]]
                      [print-foo "0.4.6"] ; Old school print debugging https://github.com/danielribeiro/print-foo
                      [org.clojure/tools.trace "0.7.6"] ; Tracing macros/fns https://github.com/clojure/tools.trace
                      [com.cemerick/piggieback "0.1.3"]
                      [clj-ns-browser "1.3.1"] ; Doc browser https://github.com/franks42/clj-ns-browser
                      [cider/cider-nrepl "0.8.0-SNAPSHOT"]
                      [ring-mock "0.1.5"]
                    ]
      :injections [
          (require '[clojure.pprint :refer :all]
                   '[clojure.stacktrace :refer (print-stack-trace)]
                   '[clojure.test :refer :all]
                   '[print.foo :refer :all]
                   '[clj-time.format :as t]
                   '[clojure.string :as s]
                   '[cljs.repl.browser :as b-repl]
                   '[cemerick.piggieback :as pb])
          (defn brepl [] (pb/cljs-repl :repl-env (b-repl/repl-env :port 9000)))
                  ]
    }
    :test {
      :dependencies [
                     [ring-mock "0.1.5"]
                     [org.clojure/tools.nrepl "0.2.3"]
                    ]
      :plugins [
                  [com.cemerick/austin "0.1.3"]
                  [com.cemerick/clojurescript.test "0.3.0"]
                ]
      :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
    }
  }

  :plugins
  [
    [lein-cljsbuild "1.0.3"]
    [lein-ring "0.8.10"]
    [lein-figwheel "0.1.3-SNAPSHOT"]
  ]

  ; :source-paths ["src/clj"]

  ; :test-paths ["test/cljs/ic_cal"]

  :cljsbuild {
    :test-commands {"unit-tests" ["runners/phantomjs.js" :runner
                                      "test/cljs/polyfills/bind.js"
                                      "test/cljs/polyfills/requestanimationframe.js"
                                      "target/test/ical-test.js"]}
    :builds [
      {:id "dev"
        :source-paths ["src/cljs"]
        :compiler
          {:preamble ["react/react.min.js"]
           :optimizations :none
           :output-to "resources/public/js/app.js"
           :output-dir "resources/public/js/"
           :pretty-print true
           :source-map true}}
      {:id "devcards"
        :source-paths ["src/cljs"]
        :compiler
          {:optimizations :none
           :output-to "resources/public/devcards/js/example.js"
           :output-dir "resources/public/devcards/js/"
           :pretty-print true
           :source-map true}}
      {:id "test"
         :source-paths ["src"
                 "src/clj"
                 "src/cljs"
                 "test/clj"
                 "test/cljs"
                 "test/cljs/ic_cal"]
         :compiler {
           :pretty-print true
           :preamble ["react/react.min.js", "moment/moment.min.js"]
           :externs ["react/react.min.js", "moment/moment.min.js"]
           :output-dir "target/test"
           :output-to "target/test/ical-test.js"
           :optimizations :simple}}]}

  :ring {:handler i-cal.core/app
         :init    i-cal.core/init}

  :figwheel {
   :http-server-root "public" ;; this will be in resources/
   :port 3449                 ;; default

   ;; CSS reloading
   ;; :css-dirs has no default value
   ;; if :css-dirs is set figwheel will detect css file changes and
   ;; send them to the browser
   :css-dirs ["resources/public/css"]
  }

  :aliases {"dev-cards" ["do" "clean," "figwheel" "devcards"]
            "auto-test" ["do" "clean," "cljsbuild" "auto" "test"]}


  :min-lein-version "2.0.0"
  :main ^:skip-aot i-cal.core
  :homemin-lein-version "2.0.0"
)
