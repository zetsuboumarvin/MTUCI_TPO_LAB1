FROM openjdk:11-jdk

COPY /target/parking-1.0.jar /parking-1.0.jar
ENTRYPOINT ["java"]
CMD ["-jar", "parking-1.0.jar"]
EXPOSE 8080