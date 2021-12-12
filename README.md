# SeleniumBDDFramework
Selenium BDD test automation framework for UI Automation

# Description
The BDD framework is built using Java programming language integrated with TestNG,Cucumber.
The framework is built using Page Object Model design pattern and implemented using Page Factory class.
The project dependencies are maintained using MAVEN dependency management.
The test scripst are writted using Gherkin syntax.
The common fields across multiple step definitions are maintained using Dependency Injection pattern.

# Installation
1) Install ORACLE JAVA JDK 1.8 version
2) Install APACHE Maven software
3) Install ECLIPSE IDE

# Folder Structure :
1) src/main/java - the page classes,UI utilities
2) src/test/java - test runner file,step definitions
3) src/test/resources - feature files,configuration files
4) the test results are generated under ExtentReports folder
    
# Usage
 To execute a test script,perform the following steps
 1) Open the feature file for execution.The files are located under \\src\test\resources\features\. Get the tag denoted by @ to execute a scenario
 2) Open the Test Runner file.The file is located at \\src\test\java\runners\TestRunner.java
 3) Provide the tag within double quotes .For ex : tags= {"@<name>"}
 4) Change the @DataProvider(parallel=true) for the scenarios() within TestRunner class to enable parallel test execution or false otherwise.
 5) Configure the project build path as jdk1.8 by right click project->Configure Build Path->Libraries->JDK 1.8
 6) Open/Right Click the pom.xml file.The file is located at apifmwk\pom.xml.Choose Run As Maven test 

# Author
Swathiraj Agaram Venkatavaradan
