**This is the Mancala Game**

This is one project, which contains two different lifecycle, Spring Boot and JSF. 
So, this is a monolith project. 

While starting the application, you have to use MancalaApplication class. This is the configuration class from Spring boot.

There is three additional beans in configuration class. These beans provide to starting JSF lifecycle.

After starting the application, you can go to http://localhost:8080/mancala.xhtml.

The project has one web page. 

MancalaController class is managing the mancala web page.
In that class, there are board initialization, pit selection and validation methods.

In this project, there are three different design pattern;
 - Strategy Pattern
 - Observer Pattern
 - Prototype Pattern

The strategy pattern was the most appropriate option, as there are certain operations in the games that come with certain strategies.

The observer pattern has been added as a second pattern due to the situations that need to be observed after each move operation.

The operations in the strategy pattern are from the Context class. Since each operation requires creating a new context object, 
the necessary context objects are cahced with the prototype pattern.

You can find detailed information about each class and method in the java doc lines in the related classes.
