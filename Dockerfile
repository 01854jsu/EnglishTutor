# Use Tomcat with Java runtime
FROM tomcat:9-jdk17-openjdk-slim

# Remove default Tomcat apps to save space/security
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file from the target directory into Tomcat's deployment folder
# Replace 'my-app.war' with your actual generated WAR filename
COPY target/EnglishTutor*.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat's default web access port
EXPOSE 8080

CMD ["catalina.sh", "run"]