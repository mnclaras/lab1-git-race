Complete me!



#Installing Gradle on Windows 10
1. Download the latest Gradlle distribution, in this case, version 4.2
2. Unpack the distribution.
	2.1. Create a new directory, named Gradle.
	2.2. Extract the the zip content in the newly created folder. You can unpack the distribution using an archiver tool of your choice.
3. Configure your system environment.
	3.1. In File Explorer right-click on the This PC (or Computer) icon, then click Properties -> Advanced System Settings -> Environmental Variables.
	3.2 Under System Variables select Path, then click Edit. Add an entry for C:\Gradle\gradle-4.2\bin and name it GRADLE_HOME. Click OK to save.

4. Verify your installation with the command gradle -v on your console system

Common error. tools.js not found....
To fix this, a new user variable must be created (like GRADLE_HOME one).
Name it JAVA_HOME, and refers to JDK installation folder (C:\Program Files\Java\jdk1.8... is a possible route).
Ultimately, edit the variable PATH adding ;%JAVA_HOME%\bin at the end.
#