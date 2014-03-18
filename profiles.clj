{:dev {:dependencies [[ch.qos.logback/logback-classic "1.0.9"]]}
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
 :release
 {:plugins [[lein-set-version "0.3.0"]
            [lein-shell "0.4.0"]]
  :set-version {:updates [{:path "README.md" :no-snapshot true}]}
  :aliases
  {"release" ;; can't work because no arg handling
   ["do"
    "with-profile" "+no-checkouts" "test,"
    "shell" "git" "flow" "release" "start" "$$$$,"
    "set-version" "$$$$,"
    "shell" "git" "add" "-u" "project.clj" "README.md,"
    "shell" "git" "commit" "-m"
    "\"Updated project.clj, release notes and readme for $$$$\","
    "clean,"
    "with-profile" "+no-checkouts" "test,"
    "deploy" "clojars,"
    "shell" "rm" "pom.xml,"
    "shell" "git" "flow" "release" "finish" "$$$$,"
    "lein" "set-version" "${next_version},"
    "shell" "git" "add" "project.clj," \
    "shell" "git" "commit" "-m" "\"Updated version for next release cycle\""
    ]}}}
