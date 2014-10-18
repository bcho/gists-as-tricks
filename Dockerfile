FROM bcho/gists-as-tricks
MAINTAINER hbc "bcxxxxxx@gmail.com"

RUN sudo apt-get update

ADD target/gists-as-tricks-0.0.1-SNAPSHOT-standalone.jar /srv/gists-as-tricks.jar

EXPOSE 9344

CMD ["java", "-jar", "/srv/gists-as-tricks.jar"]
