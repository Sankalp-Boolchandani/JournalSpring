# Self Notes:
* priority of properties that spring accepts:
  * First - command line args
  * Second - application.properties
  * Third - application.yml
* For logging we are using "logback" which is a part of SLF4J framework. There are other frameworks as well like JUL and LOG4J but we use logback from SLF4J because it is provided as a default by spring and is easy yet flexible framework.
  * by default, only warn, info and error logs are added in logback. We add DEBUG and TRACE manually using logback.xml or application.properties/yml
* We can use different environments like dev and prod using spring. to do so we create different files namely application-dev.yml/properties and application-prod.yml/properties consisting of dev or prod properties respectively and we can set the env in the main application.properties/yml file
* **Query and Criteria**: query is an od school way of executing db queries. Apart from using MongoRepository, this is used to write and create complex DB queries using different criteria.
  * it is done using **MongoTemplate** which helps us create queries and executing them.
  * Criteria is a class which is passed as a param to queries and it can be customized as per our requirements.

# Resume Stuff:
* SonarQube sonarCloud and sonarLint
* mongo - DB and atlas cloud
* logging

# Todo:
* implement sonarCloud and sonarLint again in the system
* check how to get coverage files for when adding tests