# spring_boot_test
simple spring boot Model-View-Controller example application

Working from: 
https://www.djamware.com/post/5d9896d3abbb349ea4003e94/spring-boot-tutorial-build-an-mvc-java-web-app-using-netbeans

Doesn't compile out of the box, had several modifications: 

**spring.main.allow-bean-definition-overriding=true** in *application.properties*

**mainClassName='springmvc.SpringmvcApplication'** in *build.gradle*

**@ComponentScan("springmvc")** in *SpringmvcApplication.main*

**Note :**
*https://netbeans.org/kb/docs/web/quickstart-webapps-spring.html*
needs to be updated because SimpleFormController is not used in Spring v4

