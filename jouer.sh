#!/bin/bash

# Compile et joue au jeu

javac -d bin -cp src src/main/Main.java
java -cp bin:src/* main.Main
