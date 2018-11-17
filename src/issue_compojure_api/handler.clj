(ns issue-compojure-api.handler
  (:require [clojure.spec.alpha :as s]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [spec-tools.spec :as spec]))

(s/def ::a spec/int?)
(s/def ::b spec/int?)
(s/def ::c spec/int?)
(s/def ::d spec/int?)
(s/def ::total spec/int?)
(s/def ::total-body (s/keys ::req-un [::total]))

(s/def ::x spec/int?)
(s/def ::y spec/int?)

(def app
  (api
    {:coercion :spec
     :swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Issue-compojure-api"
                    :description "Compojure Api example"}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/math/:a" []
      :path-params [a :- ::a]

      (POST "/plus" []
        :query-params [b :- ::b, {c :- ::c 0}]
        :body [numbers (s/keys :req-un [::d])]
        :return (s/keys :req-un [::total])
        (ok {:total (+ a b c (:d numbers))})))

    (context "/data-math" []
      (resource
        ;; to make coercion explicit
        {:coercion :spec
         :get {:parameters {:query-params (s/keys :req-un [::x ::y])}
               :responses {200 {:schema ::total-body}}
               :handler (fn [{{:keys [x y]} :query-params}]
                          (ok {:total (+ x y)}))}}))))
