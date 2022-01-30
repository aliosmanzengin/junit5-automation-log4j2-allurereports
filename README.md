# amazon-automation

This is a Junit5 framework with Selenium and Java, which contains amazon ui tests.

Selenium Webdriver is created with singleton design pattern. This way, a class has only one instance of the driver and provides a global point of access to it.
Page Object Model is used for storing the web elements.
You can run tests through command line to get test reports and logs.
Used Allure Report for creating clear test results.
In the utility package, there are ReusableMethods class and JSUtils class which have useful methods in it.
***
>NOTE: **You should provide your email and password in configration.properties file** 
to run the tests you can type: mvn clean test
***
to get reports:

 mvn allure:serve

 mvn allure:report  
***
you can find more information about allure reporting: 
 <https://docs.qameta.io/allure-report/reporting/maven>
