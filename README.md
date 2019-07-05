# DockerJavaJerseyTomcatAppDemo

prerequisite:

Java 1.8 - to compile

Maven - to build java

Docker - to run container

port 8080 should be free or open.

How to run:

if you have the WAR file in /target/ 

docker build -t Image_Name

docker run -d -p 8080:8080 Image_Name

in the browser open localhost:8080/mydockerapp/ you will see futher instructions 
