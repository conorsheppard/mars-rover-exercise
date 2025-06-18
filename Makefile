SHELL := /bin/bash

default: test

test:
	./shell/test

package:
	mvn package -DskipTests

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
