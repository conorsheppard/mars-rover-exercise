SHELL := /bin/bash

default: run

test:
	./shell/test

package:
	mvn package -DskipTests

build: package
	docker build --no-cache . -t conorsheppard/mars-rover-exercise

run:
	docker run -p 8080:8080 --rm --name mars-rover-exercise conorsheppard/mars-rover-exercise

java-run:
	java -jar target/mars-rover-exercise-1.0.0.jar

jshell:
	./jsh/jshell-run

test-coverage:
	mvn clean test jacoco:report verify

check-coverage:
	open -a Google\ Chrome target/site/jacoco/index.html

coverage-badge-gen:
	python3 -m jacoco_badge_generator -j target/jacoco-report/jacoco.csv

test-suite: test-coverage check-coverage coverage-badge-gen

.SILENT:
.PHONY: default test jshell test-coverage check-coverage coverage-badge-gen test-suite
