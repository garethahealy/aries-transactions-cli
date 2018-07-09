[![Build Status](https://travis-ci.org/garethahealy/aries-transactions-cli.svg?branch=master)](https://travis-ci.org/garethahealy/aries-transactions-cli)
[![Coverage](https://sonarqube.com/api/badges/measure?key=com.garethahealy.aries-transactions-cli:aries-transactions-cli-parent&metric=coverage)](https://sonarcloud.io/dashboard?id=com.garethahealy.aries-transactions-cli%3Aaries-transactions-cli-parent)
[![Release Version](https://img.shields.io/maven-central/v/com.garethahealy.aries-transactions-cli/aries-transactions-cli-parent.svg?maxAge=2592000)](https://mvnrepository.com/artifact/com.garethahealy.aries-transactions-cli/aries-transactions-cli-parent)
[![License](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)]()

# aries-transactions-cli
Command line tool to show extra info about transactions via the aries project, if you are hitting problems recovering a transaction.

# How to build and run
- mvn clean install
- java -jar target/aries-transactions-cli-parent-1.0.0-SNAPSHOT.jar -db username=gareth,password=healy,database=products,host=localhost
