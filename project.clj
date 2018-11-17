 (defproject issue-compojure-api "0.1.0-SNAPSHOT"
   :description "FIXME: write description"
   :dependencies [[org.clojure/clojure "1.9.0"]
                  [org.clojure/spec.alpha "0.2.168"]
                  [metosin/compojure-api "2.0.0-alpha27"]]
   :ring {:handler issue-compojure-api.handler/app}
   :uberjar-name "server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                    :plugins [[lein-ring "0.12.0"]]}})
