
# Shooping Cart Assesstment

## 💡 Synopsis

This repository contains a simple Spring Boot backend and AngularJS as frontend.

The primary input end-point of this API is in charge of calculating the final price of a list of items. Is called every time items are added/removed to the shopping cart.

## Prerequisites

* Apache Maven
* JDK 8+

## 💾 Building the project

To build the project, just use:
```
	mvn clean package
```

## ⚙️ Execution
The command compiles the project and runs the tests, then  it launches the application, so you can check by yourself.

```
	mvn clean install spring-boot:run
```

## ▶️ Usage

Once the server is running, go to your favorite browser and in the address bar go to [localhost:8080](http://localhost:8080)

### POST /transactions
This end-point calculates the total amount of the given shopping cart. 

Request example:

```json
POST /cart/total
Host: localhost:8080
Content-Type: application/json
[
    {
        "sku": "A",
        "quantity": 2
    },
    {
        "sku": "B",
        "quantity": 2
    }
]
```

| Filed     | Description                                          |
|-----------|------------------------------------------------------|
| sku       | Item code ('A': Apples, 'O': Oranges, 'B':Bananas, 'P':Papayas)                                   |
| quantity | Amount of items per order line |

**Returns**

Response of code 200 

| Code   | Condition          | Data                    |
|--------|--------------------|-------------------------|
|   200  | In case of success | String with total amount|



## 🌡 Unit tests
Run this command to tests your application components:

```bash
mvn clean test
```
