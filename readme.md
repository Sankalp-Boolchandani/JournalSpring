# Self Notes:
* priority of properties that spring accepts:
  * First - command line args
  * Second - application.properties
  * Third - application.yml
* For logging we are using "logback" which is a part of SLF4J framework. There are other frameworks as well like JUL and LOG4J but we use logback from SLF4J because it is provided as a default by spring and is easy yet flexible framework.
  * by default, only warn, info and error logs are added in logback. We add DEBUG and TRACE manually using logback.xml or application.properties/yml
* We can use different environments like dev and prod using spring. to do so we create different files namely application-dev.yml/properties and application-prod.yml/properties consisting of dev or prod properties respectively and we can set the env in the main application.properties/yml file   