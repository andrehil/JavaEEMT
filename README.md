## What is this project?
This is the source code used to write an [article](https://www.toptal.com/hibernate/build-multitenant-java-hibernate) about how to build a multitenant application using Hybernate. <br>
It uses RESTEasy, Wildfly, Gradle, and PostgreSQL.

## Requirements
* PostgreSQL database with at least one schema containing a table called `car` ([creation.sql](https://github.com/andrehil/JavaEEMT/blob/master/src/main/resources/creation.sql))
* Wildfly configured with a datasource called `JavaEEMTDS` pointing to the PostgreSQL database of step 1 ([DataSource configuration](https://docs.jboss.org/author/display/WFLY10/DataSource+configuration))
* Gradle to build the project ([Gradle documentation](http://gradle.org/documentation/))
