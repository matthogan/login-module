# loginmodule

Example of how to login to a tomcat application securely using the JAAS login facility.

Login Modules

* SimpleLoginModule - just wants the username and password to be equals
* SignonUserLoginModule - uses the signon service facility that calls the ldap/directory server.

# Demo - Personal Lending Admin Console

Uses form-based login and the SignonUserLoginModule.

Take down the [personal lending admin branch](http://<server name>/process-reinvention/personal-lending-back-end/tree/admin).

See the comments in src/main/webapp/login.xhtml to secure the tomcat server.

* Front-end code: src/main/webapp/admin
* Application code: src/main/java/
* Properties: jaas.config (links to the server.xml realms, which in turn links to the LoginModule classes in this module).

# Deployment

Deployment is vendor specific.

Apache Tomcat - 

* copy all the files into the server/lib directory
* copy the loginmodule.properties into the server/lib directory

# Warning - Dependencies

All the dependencies must be available to the class loader that loads the loginmodule jar.

# Reference URLs

![Form based login](http://docs.oracle.com/cd/E19226-01/820-7627/images/security-formBasedLogin.gif)

*[Jee6 Tutorial](http://docs.oracle.com/cd/E19226-01/820-7627/6nisfjn89/index.html)
*[Tomcat logout bug](https://issues.apache.org/bugzilla/show_bug.cgi?id=39231)

