#!/bin/bash
mvn exec:java -Dexec.mainClass="ServerMain" &
mvn exec:java -Dexec.mainClass="Client" &
mvn exec:java -Dexec.mainClass="Client" &
