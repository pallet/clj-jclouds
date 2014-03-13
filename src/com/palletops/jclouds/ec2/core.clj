;;;; Licensed to the Apache Software Foundation (ASF) under one or more
;;;; contributor license agreements.  See the NOTICE file distributed with
;;;; this work for additional information regarding copyright ownership.
;;;; The ASF licenses this file to You under the Apache License, Version 2.0
;;;; (the "License"); you may not use this file except in compliance with
;;;; the License.  You may obtain a copy of the License at
;;;;
;;;;     http://www.apache.org/licenses/LICENSE-2.0

;;;; Unless required by applicable law or agreed to in writing, software
;;;; distributed under the License is distributed on an "AS IS" BASIS,
;;;; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;;;; See the License for the specific language governing permissions and
;;;; limitations under the License.

(ns com.palletops.jclouds.ec2.core
  "A clojure binding to core jclouds EC2 functions."
  (:require
   [com.palletops.jclouds.compute2 :refer [location]])
  (:import
   org.jclouds.aws.ec2.AWSEC2Api
   org.jclouds.compute.domain.NodeMetadata))

(defn ^AWSEC2Api
  aws-ec2-api
  "Returns the AWS EC2 Api associated with the specified compute service."
  [compute]
  (-> compute .getContext (.unwrapApi org.jclouds.aws.ec2.AWSEC2Api)))

(defn ^String get-region
  "Coerces the first parameter into a Region string; strings, keywords, and
   NodeMetadata instances are acceptable arguments. An optional second argument
   is returned if the first cannot be coerced into a region string.
   Returns nil otherwise."
  ([v] (get-region v nil))
  ([v default-region]
    (cond
      (string? v) v
      (keyword? v) (name v)
      (instance? NodeMetadata v) (let [zone (location v)]
      ; no easier way to go from zone -> region?
      (if (> (.indexOf zone "-") -1)
        (subs zone 0 (-> zone count dec))
        zone))
      :else default-region)))
