#!/bin/bash

# Compile et joue au jeu

javac -d bin -cp src src/main/Roulette.java
java -cp bin:src/* main.Roulette 