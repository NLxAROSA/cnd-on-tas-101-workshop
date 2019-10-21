# Exercise 1 - solution

* Required dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

* Introduce a REST controller

```java
package io.pivotal.workshops.cnd.cfpush;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {

    @GetMapping
    public String fortune() {
        return "No fortune today, sorry...";
    }
}

```

* Push the application to Cloud Foundry (use from your project folder)

```bash
cf push
```

If all is well you'll get output that's similar to the output below:

```bash
Pushing app fortune-service to org your-org / space development as your.email@pivotal.io
Getting app info...
Updating app with these attributes...
  name:                fortune-service
  path:                /path/to/projects/github.com/workshop-fortune-service/target/workshop-fortune-service-0.0.1-SNAPSHOT.jar
  disk quota:          1G
  health check type:   port
  instances:           1
  memory:              1G
  stack:               cflinuxfs2
  routes:
    fortune-service-rested-springhare.cfapps.io

Updating app fortune-service...
Mapping routes...
Comparing local files to remote cache...
Packaging files to upload...
Uploading files...
 308.81 KiB / 308.81 KiB [======================================================================================================================================================================================================] 100.00% 1s

Waiting for API to complete processing files...

Staging app and tracing logs...
   Downloading binary_buildpack...
   Downloading staticfile_buildpack...
   Downloading java_buildpack...
   Downloading ruby_buildpack...
   Downloading nodejs_buildpack...
   Downloaded ruby_buildpack
   Downloading go_buildpack...
   Downloaded java_buildpack
   Downloading python_buildpack...
   Downloaded staticfile_buildpack
   Downloading php_buildpack...
   Downloaded nodejs_buildpack
   Downloading dotnet_core_buildpack...
   Downloaded binary_buildpack
   Downloading dotnet_core_buildpack_beta...
   Downloaded php_buildpack
   Downloading hwc_buildpack...
   Downloaded go_buildpack
   Downloaded python_buildpack
   Downloaded dotnet_core_buildpack
   Downloaded hwc_buildpack
   Downloaded dotnet_core_buildpack_beta
   Cell cd3a6a7e-8bb3-4631-a839-296095130133 creating container for instance 4190131e-e329-417c-9137-0d4d4e7c6b7d
   Cell cd3a6a7e-8bb3-4631-a839-296095130133 successfully created container for instance 4190131e-e329-417c-9137-0d4d4e7c6b7d
   Downloading app package...
   Downloaded app package (14.9M)
   -----> Java Buildpack v4.15 (offline) | https://github.com/cloudfoundry/java-buildpack.git#553f2c6
   -----> Downloading Jvmkill Agent 1.16.0_RELEASE from https://java-buildpack.cloudfoundry.org/jvmkill/trusty/x86_64/jvmkill-1.16.0_RELEASE.so (found in cache)
   -----> Downloading Open Jdk JRE 1.8.0_181 from https://java-buildpack.cloudfoundry.org/openjdk/trusty/x86_64/openjdk-1.8.0_181.tar.gz (found in cache)
          Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (2.0s)
          JVM DNS caching disabled in lieu of BOSH DNS caching
   -----> Downloading Open JDK Like Memory Calculator 3.13.0_RELEASE from https://java-buildpack.cloudfoundry.org/memory-calculator/trusty/x86_64/memory-calculator-3.13.0_RELEASE.tar.gz (found in cache)
          Loaded Classes: 13597, Threads: 250
   -----> Downloading Client Certificate Mapper 1.8.0_RELEASE from https://java-buildpack.cloudfoundry.org/client-certificate-mapper/client-certificate-mapper-1.8.0_RELEASE.jar (found in cache)
   -----> Downloading Container Security Provider 1.14.0_RELEASE from https://java-buildpack.cloudfoundry.org/container-security-provider/container-security-provider-1.14.0_RELEASE.jar (found in cache)
   -----> Downloading Spring Auto Reconfiguration 2.4.0_RELEASE from https://java-buildpack.cloudfoundry.org/auto-reconfiguration/auto-reconfiguration-2.4.0_RELEASE.jar (found in cache)
   Exit status 0
   Uploading droplet, build artifacts cache...
   Uploading droplet...
   Uploading build artifacts cache...
   Uploaded build artifacts cache (129B)
   Uploaded droplet (61M)
   Uploading complete
   Cell cd3a6a7e-8bb3-4631-a839-296095130133 stopping instance 4190131e-e329-417c-9137-0d4d4e7c6b7d
   Cell cd3a6a7e-8bb3-4631-a839-296095130133 destroying container for instance 4190131e-e329-417c-9137-0d4d4e7c6b7d
   Cell cd3a6a7e-8bb3-4631-a839-296095130133 successfully destroyed container for instance 4190131e-e329-417c-9137-0d4d4e7c6b7d

Waiting for app to start...

name:              fortune-service
requested state:   started
instances:         1/1
usage:             1G x 1 instances
routes:            fortune-service-rested-springhare.cfapps.io
last uploaded:     Wed 22 Aug 22:16:31 CEST 2018
stack:             cflinuxfs2
buildpack:         client-certificate-mapper=1.8.0_RELEASE container-security-provider=1.14.0_RELEASE java-buildpack=v4.15-offline-https://github.com/cloudfoundry/java-buildpack.git#553f2c6 java-main java-opts java-security
                   jvmkill-agent=1.16.0_RELEASE open-jdk-...
start command:     JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.16.0_RELEASE=printHeapHistogram=1 -Djava.io.tmpdir=$TMPDIR
                   -Djava.ext.dirs=$PWD/.java-buildpack/container_security_provider:$PWD/.java-buildpack/open_jdk_jre/lib/ext -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security $JAVA_OPTS" &&
                   CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE -totMemory=$MEMORY_LIMIT -loadedClasses=14307 -poolType=metaspace -stackThreads=250 -vmOptions="$JAVA_OPTS") &&
                   echo JVM Memory Configuration: $CALCULATED_MEMORY && JAVA_OPTS="$JAVA_OPTS $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2 SERVER_PORT=$PORT eval exec $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp $PWD/.
                   org.springframework.boot.loader.JarLauncher

     state     since                  cpu      memory         disk           details
#0   running   2018-08-22T20:17:30Z   126.6%   322.1M of 1G   141.6M of 1G   
```

In the output you'll see that your application is uploaded after which CF will decided which [Buildpack](https://buildpacks.io/) it needs to use in order to build a container image from your application. Most of the times it's correct, but in case it's not you can always explicitly select a buildpack in the manifest file. After the container image is built your application will be scheduled for running. Since buildpacks are curated and managed this means that as a developer you don't need to do anything if there's a critical CVE in a library somewhere, the platform will do that automatically for you!

* Lookup the created (dynamic) route from the console
* Copy the route 
* Open the application in the browser (e.g. in specific example the route was: [https://workshop-fortune-service-relaxed-grysbok.apps.lropcf.pushto.cf](https://workshop-fortune-service-relaxed-grysbok.apps.lropcf.pushto.cf))
* Show the logs for your running app

```bash
cf logs workshop-fortune-service --recent
```
* Check the status of the application

```bash
cf app workshop-fortune-service
```

* Show the deployed application in the Pivotal Cloud Foundry web console using the provided Apps Manager URL

* Check the CF CLI for other helpful commands

```bash
cf --help
```

* Scaling the application up to 3 instances and verifying that there are now 3 instances up and running

```bash
cf scale workshop-fortune-service -i 3
```
```
cf app workshop-fortune-service
```

* Scaling the application back down to 1 instance and verifying that there is now 1 instance up and running

```bash
cf scale workshop-fortune-service -i 3
```
```
cf app workshop-fortune-service
```

* Using the Autoscaler

Click 'Enable Autoscaling' and then restage the application (follow the link or use the button in the same screen). Wait a bit for it to finish, then click 'manage autoscaling'. Try setting minimum instances to 2 and maximum instances to 4 and see what happens. Try adding a scaling rule and or scheduled limit.

* You can do the same in the Apps Manager UI by logging in there and navigating to your org, space and app by clicking on them, then click the 'Scale' button.
* The Apps Manager has many functions, most of which are available in the CLI as well, but several are available because of Spring Boot Actuator integration. Check out the Logs, Trace, Threads and Settings tabs of your application.