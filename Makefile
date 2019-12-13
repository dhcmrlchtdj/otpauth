SHELL := /bin/bash

build:
	gradle shadowJar

native: build
	native-image --no-server -jar build/libs/otpauth-all.jar otpauth.exe

test:
	gradle test

clean:
	gradle clean
	rm ./*.exe

fmt:
	ktlint --format --relative './src/**/*.kt'

doc:
	gradle dokka

init:
	gradle wrapper

.PHONY: run build test clean fmt doc
.PHONY: init
