# Card game API (WIP)

This is a RESTful API for a very fun card game created in Java. Disclaimer: this work is currently in progress. 

## Setup

Maven is used to build this project. This was develop and built using VS Code on Windows.

## Dependencies

This project uses Spring framework to provide the REST operations. Also it uses GemFire to persist the data to a in-memory database.

## Philosophy

The code concerns are separated in domains (e.g. game, players, etc) then into multiple layers. The API is fronted by a controller so that the business logic can be decoupled and re-use for an other interface. The business logic is wrapped in a Service class and depends upon an interface of a repository to persist the data.
