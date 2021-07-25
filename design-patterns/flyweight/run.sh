#!/bin/sh

javac Dog.java
javac DogFactory.java
javac Main.java

java Main

rm Dog.class
rm DogFactory.class
rm Main.class
