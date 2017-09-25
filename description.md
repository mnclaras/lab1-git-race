# Installing Gradle on Windows 10
1. Download the latest Gradlle distribution, in this case, version 4.2
1. Unpack the distribution.
	1. Create a new directory, named Gradle.
	1. Extract the the zip content in the newly created folder. You can unpack the distribution using an archiver tool of your choice.
1. Configure your system environment.
	1. In File Explorer right-click on the This PC (or Computer)  icon, then click Properties -> Advanced System Settings -> Environmental Variables.
	1. Under System Variables select Path, then click Edit. Add an entry for `C:\Gradle\gradle-4.2\bin` and name it GRADLE_HOME. Click OK to save.
1. Verify your installation with the command `gradle -v` on your console system

#### Common error.
* `tools.js not found` when `gradle -v`

    To fix this, a new user variable must be created (like `GRADLE_HOME` one).
    Name it `JAVA_HOME`, and refers to JDK installation folder (`C:\Program Files\Java\jdk1.8`... is a possible route).
    Ultimately, edit the variable PATH adding `;%JAVA_HOME%\bin` at the end.


# Deployment with Heroku

### Procfile
A [Procfile](https://devcenter.heroku.com/articles/procfile) is a text file in the root directory of your application 
that defines process types and explicitly declares what command should be executed to start your app. 
It is optional, and you need to include it only if your application needs any special configuration to run.
The default configuration is the following:

```
web: java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/*.jar
```

The name `web` is important. It declares that this process type will be attached to the `HTTP routing` stack of Heroku,
and receive web traffic when deployed.

In our case, we will create a `war` file at `build\libs` path, which will be used as the web application. For avoiding 
errors when looking for the .war, it is necessary to specify its name in the `build.gradle` file:
```
war {
    baseName = 'webeng'
}
```

And last, we must specify the special behavior when starting the application. In this case, define the path to get the `.war` file.


```
web: java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/webeng.war
```

## Step-by-step deployment
1. Create a free account in [Heroku](https://signup.heroku.com/).
1. If you already had an account, the main page will show you your web applications.
1. Click on `New` at the right upper corner, and then click on `Create New App`.
1. Define a name for you web application and select `Europe` as region. Then click on `Create App`.
1. Go to `Deploy` tab and select `GitHub` as the deployment method. This will synchronize your GitHub account to Heroku.
Afterwards, Heroku will ask you which repository does you want to synchronize. Select `lab1-git-race`.
1. Now you will see a new functionality. We will focus on `Automatic deploys`. This allow us to select a branch and every push
will deploy a new version of the application. Also, you can tell Heroku to deploy the application only if `Travis CI` has 
been passed without errors. Select the `master` branch and `Wait for CI to pass before deploy` option, since is a need for us to
pass all tests on every push to master. Finally, click on `Enable automatic deploys`.
1. If you want to access to the web application, go to the right upper corner and click on `Open app`. If you are facing
with errors at the moment of visualize the web page, you can also see the log by clicking on `More ->  View logs` button
in the same place as the previous button. This will give you information in a console.