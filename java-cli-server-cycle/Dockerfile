#Image maven
FROM maven:3.8.1-jdk-11

# Define and create direcotry
ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

# Add pom.xml
ADD pom.xml $HOME

#Download dependencies
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]

#Add code and compile maven
ADD . $HOME
RUN ["mvn", "package"]

# Add port
EXPOSE 4567

# Add path app and define entrypoint
ADD target/java-cli-server-cycle-1.0-SNAPSHOT-jar-with-dependencies.jar java-cli-server-cycle-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar", "java-cli-server-cycle-1.0-SNAPSHOT-jar-with-dependencies.jar"]