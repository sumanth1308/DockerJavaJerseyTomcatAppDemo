FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/myDockerDemo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
COPY ./config.txt /usr/local/tomcat/config.txt
CMD ["catalina.sh","run"]
