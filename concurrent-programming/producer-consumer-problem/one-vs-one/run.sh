#!/bin/sh

javac Buffer.java
javac Producer.java
javac Consumer.java
javac Main.java
java Main

rm Buffer.class
rm Producer.class
rm Consumer.class
rm Main.class
