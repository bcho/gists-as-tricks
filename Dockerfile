FROM dockerfile/java
MAINTAINER hbc "bcxxxxxx@gmail.com"

RUN sudo apt-get update

ADD target/gists-as-tricks-0.0.2-SNAPSHOT-standalone.jar /srv/gists-as-tricks.jar
ADD config.docker.clj /srv/config.clj

EXPOSE 9344

RUN ["touch", "/srv/db.sqlite3"]
RUN ["java", "-jar", "/srv/gists-as-tricks.jar", "-c", "/srv/config.clj", "init"]
CMD ["java", "-jar", "/srv/gists-as-tricks.jar", "-c", "/srv/config.clj", "run"]
