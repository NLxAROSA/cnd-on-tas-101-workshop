# Exercise 1 - CF Push a simple Spring Boot application onto Cloud Foundry

## Goal

* Build A Spring Boot 2 micro service that Expose a REST API on the root and return a 
Fortune Cookie message (String) and deploy it to Cloud Foundry.
* Learn about the functionality of the CF CLI.
* Learn about the functionality of the Apps Manager.

## Steps 

* Go to [https://start.spring.io](https://start.spring.io) to create a project with Spring Initializr
* Create a (Maven) Spring Boot 2 project with starters `Web` and `Actuator`
* Use 'workshop-fortune-service' for the artifact id
* Use the latest and greatest GA release of Spring Boot
* Download the project and open it in your favourite IDE
* Introduce a ‘Fortune Cookie’ controller (`@RestController`, `@GetMapping`) that returns a simple String message
* Build the project using Maven and run it locally first
* Call the REST endpoint from the browser or curl/http to show the static Fortune text
* If the application is working fine push the application to Cloud Foundry
* After pushing the application, there's a lot of console output, can you figure out what it's doing?
* Call the REST endpoint of the deployed application from the browser or curl/http to show the static Fortune text
* Take a look at the [manifest.yml](../01-cfpush/manifest.yml), can you spot differences with other popular deployment platforms (e.g. k8s or WebSphere or other?)
* Using the CLI, show your applications most recent log lines
* Using the CLI, stop and start your application
* Using the CLI, can you scale the application's instances up to 3 instances? And how about back to 1?
* Now do the same via the Apps Manager
* The Apps Manager has more nice functions, most of which are available in the CLI as well, but a lot of them are available because of Spring Boot Actuator integration. Check out the Logs, Trace, Threads and Settings tabs of your application and play around with the features in there.
* Can you figure out how to use the Autoscaler? (Don't go above 3 instances please)

## Tips

* Many IDEs have out-of-the-box integration with Spring Initializr (e.g. IntelliJ IDEA or VS Code), so no need to visit the website
* The CF CLI has a --help parameter that will list all the available commands and parameters
* [HTTPie](https://httpie.org/) is a great replacement for curl with great support for both GET and POST/PUT
* Check the [solution](exercise-1-solution.md) for valuable information and whether you missed anything