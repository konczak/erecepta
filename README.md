# erecepta

## Project purpose

Purpose of this project is to provide working solution consuming CSIOZ ObslugaReceptyWS (aka e-recepty WS).

## Engine

Project core uses:

- Spring boot as engine,
- Apache CXF as WebService client framework.


## Build

After checkout run in project root directory:

```bash
mvn clean install
``` 

it will build all modules what includes generation client source code from WSDL.

> Note: at current stage Maven wrapper is not included - use Maven from IDE or command line.

## Run

_TODO_
