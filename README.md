# Payroll Processing springboot application

## Overview

Springboot java app which exposes a REST API to process payroll file. 
The API takes file location as input, parses and processes the file and logs results in console/log file.
Currently, the API is synchronous, but as per the requirement it can be changed to an asynchronous API.

## Pre-requisite

- Eclipse or Intellij IDE. If using Elipse IDE, then install Spring Tool Suite 4.
- Mysql server

## How to build and run

- Import the project (import as maven project, if using eclipse) in IDE.
- Project > Maven > Update Project
- Run > Run as Spring boot App

## Running tests

From eclipse, right click on Project > Coverage As > Junit

## Code coverage
Code coverage report can be viewed after running tests. Code coverage plugin comes pre-installed, so once the tests are run, 
coverage report can be viewed. To run tests with code coverage report: Project > Coverage As > Junit
Code coverage report view can be open from Window > Show View > Other > Search for 'coverage'

## DB setup
- Create new schema with name emp_payroll
- Update JDBC connection string, mysql username and db password in application.properties
- Tables will get automatically created

## API request

### Curl:
curl --location 'http://localhost:8080/api/v1/payroll/process' \
--header 'Content-Type: application/json' \
--data '{
    "payrollFilePath" : "C:\\Personal\\Projects\\PPCFile.csv"
}'

### Postman/Any other REST client:
POST http://localhost:8080/api/v1/payroll/process
Request body: 
{
    "payrollFilePath" : "C:\\Personal\\Projects\\PPCFile.csv"
}


## V2 Enhancements
The application simply parses the supplied file (in argument), validates each record and logs it. It also logs few stats such as number of invalid records and total number of records
Payroll file processing API is currently synchronous. In v2 version following enhancements can be done:

- Change /payroll/process API to asynchronous and return back requestId in response with 202 Accepted status
- Expose another API to return status of running process. Takes input as requestId passed in first API.
- A new API which takes entire file content as input as Multipart file
