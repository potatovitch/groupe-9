#!/bin/bash

# Compile et joue au jeu

javac -d bin -cp src src/main/Launch.java
java -cp bin:src/* main.Launch 