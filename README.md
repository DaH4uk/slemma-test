# slemma-test

1. To build the archive, you need to use maven 3.1+.
"mvn clean install" to build war archive.
2. Then move the war file into the "webapps" folder of Tomcat;
3. And run {tomcat App folder}/bin/startup.sh if you use Unix system or startup.bin if you use windows.
4. Then open in browser localhost:8080/Users.csv to see the result.
> jdk 8+
> maven 3.1
> tomcat 8+
