# Authorization Server Project

## Declaring the Datasource on the Application Container
We’ll declare our datasource in <tomcat_home>/conf/server.xml file inside the <GlobalNamingResources> element.

Assuming that the database server is running on the same machine as the application container, and that the intended database is named authserver, and that the username is root with empty password, a resource would look like this:

### conf/server.xml
```
<Resource name="jdbc/AuthServerDB"
	auth="Container"
	type="javax.sql.DataSource"
	driverClassName="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/authserver?createDatabaseIfNotExist=true"
	username="root"
	password=""
	maxActive="20"
	maxIdle="10"
	maxWait="-1"/>
```   
 
Take note that we’ve named our resource jdbc/AuthServerDB. This will be the name to be used when referencing this datasource.

We’ve also had to specify its type and database driver’s class name. For it to work, you must also place the corresponding jar in <tomcat_home>/lib/ (in this case, mysql-connector-java-5.1.28.jar).

Remaining configuration parameters are:

auth=”Container” – means that the container will be signing on to the resource manager on behalf of the application
maxActive, maxIdle, and maxWait – are pool connection’s configuration parameters
We must also define a ResourceLink inside the <Context> element in <tomcat_home>/conf/context.xml, which would look like:

### conf/context.xml
```
<ResourceLink
        name="jdbc/AuthServerDB"
        global="jdbc/AuthServerDB"
        type="javax.sql.DataSource"/>
```
  
Note that we are using the name we defined in our Resource in server.xml.
