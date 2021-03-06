# Go Beyond Functional Testing

Selenium is built for exercising browsers in a similar manner to that of an end user, 
and it's great for functional testing. As many of us have realized, Selenium is not 
great at non-functional testing, whether that be layout, usability, performance, or 
other types of testing. But that doesn't mean that your tests themselves can't be 
repurposed to assist in these areas. Join Max in a hands on workshop to learn some 
techniques on how to expand the testing your Selenium scripts can accomplish.

Max will cover 3 techniques: how to get security results by running Selenium tests, 
how to retrieve accessibility results from pages you test, and how to transform 
your Selenium tests into performance tests. Come with a laptop as we will walk 
through examples, and leave with a better understanding how to expand your testing 
coverage, with minimal additional effort.

## Setup Instructions
The below tools and programs need to be installed on the machine before starting
the workshop
 * [Java JDK](#java-jdk)
 * [Maven](#maven)
 * [Git](#git)
 * [Chrome Browser](#chrome-browser)
 * [IntelliJ IDE](#intellij-ide)
 * [Zed Attack Proxy](#zed-attack-proxy)
 * [JMeter](#jmeter)
 
### Java JDK
#### Install
Note that in the following instructions, if a version other than Java 8u265 is 
installed (which is fine, download the latest Java 8 version), the file names 
below will differ in the version number (8u265 or 1.8.0_265).
##### Windows
You must have administrative permissions in order to install the JDK on Microsoft 
Windows.
1. Navigate to [RedHat's site](https://developers.redhat.com/products/openjdk/download)
to download the latest version of OpenJDK.
2. Find the correct `msi` for your Windows system. If you instead want to install
the `zip` follow instructions [here](https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows)
3. If you downloaded either file instead of running it directly from the website, 
double-click the installer's icon.
4. Follow the instructions the installer provides.
5. When finished with the installation, you can delete the downloaded file to 
recover disk space.
##### Linux - DEB
1. Open a terminal window
2. Install openjdk via `apt`
```shell script
sudo apt-get install openjdk-8-jdk
```
##### Linux - RPM
1. Open a terminal window
2. Install openjdk via `apt`
```shell script
su -c "yum install java-1.7.0-openjdk"
```
##### Mac OS
We will be using `brew` to install openjdk 8 from a terminal window
1. Open a terminal
2. Run the following command to checkout the correct openjdk 
version `brew tap AdoptOpenJDK/openjdk`
3. Run the following command to find the correct openjdk 
version `brew search /adoptopenjdk/`
4. Copy the displayed line that has openjdk8 listed, and run brew install
with it. For example: `brew install adoptopenjdk/openjdk/adoptopenjdk-openjdk8`
#### Verify
Open a new command prompt (in windows use Windows Key + R then type cmd) 
Run `javac -version` to verify that it is correctly installed. This command will 
display the currently installed version of Java.

### Maven
#### Download
Download Maven from the Apache website at 
[this link](https://maven.apache.org/download.cgi). At time of 
this writing, the current version is 3.6.3, but feel free to use a later version. 
Note that if a later version is installed, then the version numbers in the file 
names below will need to use that version.
#### Install
##### Windows
1. Unzip the distribution archive to a directory easy to find on the file system. 
These instructions assume this directory will be `C:\Program Files\`. The 
subdirectory `apache-maven-3.6.3` will be created from the archive.
2. Add the `M2_HOME` environment variable by opening up the system properties (this 
can be done through the Control Panel or by using the Windows Key + Pause), 
selecting the "Advanced" tab, and the "Environment Variables" button, then adding 
the `M2_HOME` variable in the user variables with the value 
`C:\Program Files\apache-maven-3.6.3`. Be sure to omit any quotation marks around 
the path even if it contains spaces.
3. In the same dialog, add the `M2` environment variable in the user variables 
with the value `%M2_HOME%\bin`.
4. In the same dialog, update/create the `Path` environment variable in the user 
variables and prepend the value `%M2%` to add Maven available in the command line.
5. In the same dialog, make sure that JAVA_HOME exists in your user variables or 
in the system variables and it is set to the location of your JDK, e.g. 
`C:\Program Files\Java\jdk1.8.0_261` and that `%JAVA_HOME%\bin` is in your `Path` 
environment variable.
##### Unix OSes (Linux, Solaris and Mac OS X)
1. Extract the distribution archive to the directory you wish to install Maven 3.6.3. 
These instructions assume you chose `/usr/local/`. The subdirectory 
`apache-maven-3.6.3` will be created from the archive.
2. In a command terminal, add the `M2_HOME` environment variable:
`export M2_HOME=/usr/local/apache-maven/apache-maven-3.6.3`
3. Add the `M2` environment variable:
`export M2=$M2_HOME/bin`
4. Add `M2` environment variable to your path:
`export PATH=$M2:$PATH`
5. Make sure that `JAVA_HOME` is set to the location of your JDK; this was done 
during the installation of Java above.
`export JAVA_HOME=/usr/java/jdk1.8.0_261` 
and that `$JAVA_HOME/bin` is in your `PATH` environment variable:
`echo $PATH`
#### Verify
Open a new command prompt (in Windows use Windows Key + R then type cmd) and run 
`mvn --version` to verify that it is correctly installed. This should display 
something like the following:
```
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 1.8.0_261, vendor: Private Build, runtime: /usr/lib/jvm/java-8-openjdk-amd64/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-47-generic", arch: "amd64", family: "unix"
```

### Git
#### Download and Install
1. Navigate to [git downloads](https://git-scm.com/downloads)
2. Select the operating system you are running
3. If Windows or Mac, run the installer
4. If Linux, follow the provided instructions
#### Verify
Open a new command prompt (in windows use Windows Key + R then type cmd) 
Run `git --version` to verify that it is correctly installed. This command will 
display the currently installed version of Git.

### Chrome Browser
1. Navigate to [chrome downloads](https://www.google.com/chrome/)
2. Click on the “Download Chrome” button. The appropriate installation package for 
your operating system will be automatically downloaded
3. Once the download is done, run the installer and, unless there is a known reason,
accept all defaults.

*Note that Chrome is not required for this workshop, but a modern browser is. The 
exercises will need to be modified slightly for usage with other browsers.*

### IntelliJ IDE
*Note that any Java capable IDE can work. If you have Eclipse, NetBeans, or some 
other Java friendly IDE, IntelliJ does not have to be installed. That said, be sure
to be familiar with your IDE, as the instructor might have limited support based on
the chosen IDE.*
#### Download
1. Navigate to [Intellij](https://www.jetbrains.com/idea/download/)
2. You should be forwarded to the section for your current system setup
3. Download the Community version (Free, open source)
#### Install
##### Windows or Mac OS
Simply launch the provided installer. Default settings are almost always appropriate.
##### Linux
Extract the distribution archive to the directory you wish to install IntelliJ. 
These instructions assume you chose `/usr/local/`. The subdirectory `idea-IC-VERSION`
will be created from the archive.

### Zed Attack Proxy
#### Download
Download the latest Zed Attack Proxy version from ZAP’s website at 
[this link](https://www.zaproxy.org/download/). At time of this 
writing, the current version is 2.9, but feel free to use a later version. Click 
the download link for the computer system you are running.
#### Install
Launch the installer you downloaded, either the .exe, .dmg, or shell script, 
depending on the download you selected based on your computer system (you might need
to add execute permissions to the file for Linux systems). Follow the installation
instructions. Once the installation is complete, launch ZAP and read the license 
terms. Click Agree if you accept the terms, and ZAP will finish installing, then 
ZAP will automatically start.

### JMeter
#### Download and Install
Download the latest JMeter version from apache’s website at 
[this link](https://jmeter.apache.org/download_jmeter.cgi). Select 
the link for either the tgz or zip, depending on whichever compressed file type you are 
comfortable handling. Unzip the zip/tar file into the directory where you want 
JMeter to be installed.

## Exercises
* Exercise 1: Functional Testing
* Exercise 2: Security Testing
* Exercise 3: Accessibility Testing
* Exercise 4: Performance Testing
