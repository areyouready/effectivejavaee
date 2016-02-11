#!/usr/bin/jjs -fv
/**
 * Created by sebastianbasner on 11.02.16.
 */
//Nashorn script environment

var uri = "http://localhost:8080/doit_war_exploded/api/todos"; //needs to be modified for different deployments
var command = "curl ${uri}";
print(command);
$EXEC(command);
var result = $OUT;
print(result);
var resultAsArray = JSON.parse(result)
print(resultAsArray);
for (todo in resultAsArray) {
    print(resultAsArray[todo].caption + "-" + resultAsArray[todo].description)
}