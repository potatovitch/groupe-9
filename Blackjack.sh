#!/bin/bash

# Compile et joue au jeu

javac -d bin -cp src src/main/Blackjack.java
java -cp bin:src/* main.Blackjack 