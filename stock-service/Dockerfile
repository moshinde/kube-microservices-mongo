FROM tomcat:8-jre8
ADD target/stock-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
RUN sh -c 'touch /usr/local/tomcat/webapps/stock-service-0.0.1-SNAPSHOT.war'
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/stock-service-0.0.1-SNAPSHOT.war"]
EXPOSE 8301