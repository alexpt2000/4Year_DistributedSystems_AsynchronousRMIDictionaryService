javac -cp servlet-api.jar src/ie/gmit/sw/*.java

jar -cf dictionary-service.jar src/ie/gmit/sw/*.class

java -cp dictionary-service.jar ie.gmit.sw.ServiceSetup

