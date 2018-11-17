(ns issue-compojure-api.handler
  (:require [clojure.spec.alpha :as s]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [spec-tools.spec :as spec]))


(s/def ::x spec/int?)
(s/def ::y spec/int?)
(s/def ::r spec/int?)

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Issue-compojure-api"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}
    (context "/api" []
      :tags ["api"]
      (context "/plus" []
        (resource
          {:summary "adds two numbers together"
           :coercion :spec
           :get
           {:parameters
            {:query-params
             (s/keys :req-un [::x ::y])}
            :responses
            {200 {:schema {:result ::r}}}
            :handler
            (fn [{{:keys [x y]} :query-params}]
              (ok {:result (+ x y)}))}})))))
