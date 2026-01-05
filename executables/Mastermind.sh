#!/bin/bash

# Compile et joue au jeu

javac -d bin -cp src src/main/Mastermind.java
java -cp bin:src/* main.Mastermind 