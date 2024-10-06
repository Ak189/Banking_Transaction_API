# Banking_Transaction_API

### Overview

This Banking Transaction API handles requests like creating a new user account, transferring amount from one account 
to another, retrieving information of an account from its id, retrieving the transaction history for a given account and 
deleting an account.

### Features

- **Create Account**: This feature allows a user to create an account, with first name, middle name, last name, address details and identification details.
- **Retrieving Info**: This feature allows to retrieve information of an account from its id.
- **Transfer funds**: This feature allows a user to transfer funds to another user, the user can't send amount greater than the balance.
- **Transaction History**: This features fetches transaction history of a particular account.
- **Delete Account**: This feature deletes an account permanently.


#### Prerequisites
Make sure you have the following installed on your machine:
- [Java 17 or higher](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

#### Steps to Build the application

1. **Clone the Repository**  
   Open your terminal and run the following command to clone the repository to your local machine:
   ```bash
   git clone https://github.com/Ak189/Banking_Transaction_API
2. **Navigate to project directory**
    ```bash
    cd Banking_Transaction_API
3. **Build the project**
    ```bash
   mvn clean instal
4. **Run the Application**
    ```bash
   mvn spring-boot:run
   
### Example of Curl Commands

1. **Creating an Account**
    ```bash
   curl -X POST "http://localhost:8080/accounts" \
   -H "Content-Type: application/json" \
   -d '{
      "firstName": "Akash",
      "middleName": "",
      "lastName": "Gupta",
      "addressLine1": "89 tru",
      "addressLine2": "",
      "city": "Brampton",
      "state": "Ontario",
      "postalCode": "L7A 444",
      "drivingLicense": "A1234567",
      "sinNumber": "123456789",
      "initialBalance": 1000.0
   }'

2. **Retrieving information of an account**
    ```bash
   curl -X GET http://localhost:8080/accounts/1
3. **Transferring money**
    ```bash
   curl -X POST http://localhost:8080/accounts/1/transfer \-H "Content-Type: application/json" \-d '{"toAccountId": 2, "amount": 300.00}'
4. **Retrieving Transaction history**
    ```bash
   curl -X GET http://localhost:8080/accounts/1/transactions
5. **Deleting an Account**
    ```bash
   curl -X DELETE http://localhost:8080/api/accounts/12345
### Assumptions
- The Curl commands follow a general syntax /accounts/{id}, to get info, transaction history or delete an account just replace the id.
- User input for initial balance is always and integer without spaces.
- There is a single currency and a single account of a user.
- Postal code must have more than 5 characters and less than 10 chars.
- SIN number is 9-digit number without spaces and hyphen.
- API can't support multiple currencies and multiple accounts that is chequing and savings account.
- There can be transaction delay's in real life but the api assume it in real time.
- All potential errors will be handled gracefully with appropriate HTTP status codes.
- There are no types of transaction that is debit or credit.