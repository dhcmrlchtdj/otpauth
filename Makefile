SHELL := /bin/bash

run: build
	java -jar build/libs/otpauth-all.jar

build:
	gradle shadowJar

test:
	gradle test

clean:
	gradle clean

fmt:
	ktlint --format --relative './src/**/*.kt'

doc:
	gradle dokka

init:
	gradle wrapper

.PHONY: run build test clean fmt doc
.PHONY: init
