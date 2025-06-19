### Mars Rover Exercise

<table>
<tr>
<td>
Test Coverage
</td>
<td>
<img src="./badges/jacoco.svg" style="display: flex;" alt="jacoco-test-coverage-badge">
</td>
</tr>
</table>

### Tech Stack
- Java 24 (OpenJDK)
- Maven
- Lombok
- Jacoco (code test coverage analysis)
- Testing: JUnit 5, Mockito, MockMvc

### Instructions

Build with Docker
```shell
make build
```
Run with Docker
```shell
make
```
Build and run with Maven
```shell
make package
```
```shell
make java-run
```
Create rover request:
```shell
curl -w "\nStatus: %{http_code}\n" http://localhost:8080/api/v1 \                    
-H "Content-Type: application/json" \
-d '{"x": 0, "y": 2, "direction": "N"}'
```
Example response: 
```shell
d0c79900-5ee3-4a0c-84b1-0b809a88a50a
Status: 201
```

Get rover request:
```shell
curl -w "\nStatus: %{http_code}\n" http://localhost:8080/api/v1/{id}
```
Example response:
```shell
{
  "position": {
    "x": 0.0,
    "y": 0.0
  },
  "direction": "N"
}
Status: 200
```

Move rover request:
```shell
curl -X POST -w "\nStatus: %{http_code}\n" http://localhost:8080/api/v1/{id}/move
```
Example response:
```shell
{
  "moved": true
}
Status: 200
```

To execute all tests with Maven, run:
```shell
make test
```

### Notes on implementation
I've moved some of the logic out of the Rover class to make it a bit cleaner.  
The Board is now responsible for keeping track of the rovers' positions and we synchronise on it to avoid any race conditions.