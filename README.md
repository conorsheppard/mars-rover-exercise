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
curl -X POST http://localhost:8080/api/v1 \
-H "Content-Type: application/json" \
-d '{
  "x": 0,
  "y": 0,
  "direction": "N"
}'
```
Example response: `58a71ddf-b861-47e0-b060-a9dbf16dc575`

Get rover request:
```shell
curl http://localhost:8080/api/v1/{id}
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
```

Move rover request:
```shell
curl -X POST http://localhost:8080/api/v1/{id}/move
```
Example response:
```shell
{
  "moved": true
}
```

To execute all tests with Maven, run:
```shell
make test
```

### Notes on implementation
I've moved some of the logic out of the Rover class to make it a bit cleaner.  
The Board is now responsible for keeping track of the rovers' positions and we synchronise on it to avoid any race conditions.