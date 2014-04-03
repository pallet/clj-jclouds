{:dev {:dependencies [[ch.qos.logback/logback-classic "1.0.9"]]
       :plugins [[lein-pallet-release "RELEASE"]],
       :pallet-release {:url "https://pbors:${GH_TOKEN}@github.com/palletops/clj-jclouds.git"
                        :branch "master"}}
 :no-checkouts {:checkout-deps-shares ^:replace []} ; disable checkouts
 :doc {:dependencies [[com.palletops/pallet-codox "0.1.0"]]
       :plugins [[codox/codox.leiningen "0.6.4"]
                 [lein-marginalia "0.7.1"]]
       :codox {:writer codox-md.writer/write-docs
               :output-dir "doc/api/0.1"
               :src-dir-uri "https://github.com/pallet/clj-jclouds/blob/develop"
               :src-linenum-anchor-prefix "L"}
       :aliases {"marg" ["marg" "-d" "doc/annotated/0.1/"]
                 "codox" ["doc"]
                 "doc" ["do" "codox," "marg"]}}
 :release {:set-version {:updates [{:path "README.md" :no-snapshot true}]}}}


