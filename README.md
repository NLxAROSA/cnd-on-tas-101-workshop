<img src="fortune-ui/src/main/resources/static/img/tanzu-black.svg" height=100/>

## Cloud Native Development on Tanzu Application Service 101 - Workshop
This workshop will focus on writing a few Spring Boot applications and will teach you how to deploy them to Tanzu Application Service, formerly known as Pivotal Application Service, formerly known as Pivotal Cloud Foundry. You will provision a database and connect to it, inject configuration at runtime and setup a service registry and a circuit breaker. All with little to no developer hassle or setup. While this workshop is executed using Tanzu Application Service, all of the exercises also work quite happily on other brands of Cloud Foundry or on OSS Cloud Foundry.

[Getting started with Tanzu Application Service](https://tanzu.vmware.com/tutorials/getting-started)


## Prerequisites

We expect you to have with you:

* A laptop with Windows, Linux or MacOS
* A working internet connection
* An IDE or code editor of your choice

We expect you to have installed:

* [JDK11 or higher](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
* [Cloud Foundry CLI](https://docs.run.pivotal.io/cf-cli/install-go-cli.html)

We expect you to be familiar with Java, but if you're not it's also ok as working source code for every exercise is provided.

Login credentials and access to the environment will be provided by the instructor(s).


## Verify the CF CLI is working

```bash
cf -v
```

Login to CF:

```bash
cf login -a <provided API url>
Email> <provided user>
Password> <provided password>
```


## Build the project

For every exercise we need to build the project: 

Using the embedded Maven on MacOS or Linux

```bash
./mvnw clean package
```

Using the embedded Maven on Windows

```bash
mvnw clean package
```

## Run the application locally

Before pushing the application to Cloud Foundry you can run the application locally first

```bash
./mvnw spring-boot:run
```

Or on Windows

```bash
mvnw spring-boot:run
```

Open the application in the browser: [http://localhost:8080/](http://localhost:8080/)


## Exercises

* Exercise 1: [start](exercises/exercise-1-start.md), [solution](exercises/exercise-1-solution.md)
* Exercise 2: [start](exercises/exercise-2-start.md), [solution](exercises/exercise-2-solution.md)
* Exercise 3: [start](exercises/exercise-3-start.md), [solution](exercises/exercise-3-solution.md)
* Exercise 4: [start](exercises/exercise-4-start.md), [solution](exercises/exercise-4-solution.md)


## Tips

* Stuck? The exercises have hints to help you!
* Stuck? Ask your neighbour to pair with you!
* Stuck? Every exercise has a working solution right here!
* Still Stuck? Ask one of the instructors!
