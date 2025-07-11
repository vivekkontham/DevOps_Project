FROM openjdk
COPY ./target/java-kubernetes-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","java-kubernetes-0.0.1-SNAPSHOT.jar"]
