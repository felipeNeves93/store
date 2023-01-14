* **Simple Store Microservice**

 
* **Objective**
  * This microservice aims to implement the concepts of a store microservice, that will allow a customer to choose products from
  an stock, and create an order, sending it to the [Order Microservice](https://github.com/felipeNeves93/order) Microservice to persist and follow the status updates.


* **Running the System**
    * There is a docker-compose file inside the docker folder containing the configurations to run a postgres database called *store*.
    * To run it, run the following command: *docker-compose -f postgres-compose.yml up -d*
    * To run locally, you have to specify the spring local profile, adding the following configuration as vm parameter: *-Dspring.profiles.active=local*