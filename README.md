# BankTransferSystem

## Description
The API is built using Java Spring Boot with PostgreSQL as its database. It's connected with pgAdmin, 
which is installed using Docker via Docker Compose. The Spring Boot application is installed on the
local computer and connected with PostgreSQL.

## Geting started
first clone the repository, afeter repo cloned at your local computer, you must build the docker container
it for install the posgresql 
```bash
docker-compose up -d
```
Docker will pull PostgreSQL and pgAdmin 4. PgAdmin 4 is for easy user management of PostgreSQL. If you don't need it, just ignore it.
**The important thing is PostgreSQL, and the application should be cloned to your local computer.**

After the container finishes building, open the application directory and run the command below.
```bash
mvn spring-boot:run
```

## Api End Point
"It has three parts for API endpoints:
- Admin: Used for managing admin functionalities.
- Nasabah: Used for managing customer-related operations.
- Transaction: This endpoint is specifically for transferring balances between customers."

# Customer API
This api is for register a new cutomer, the admin is can add,edit,views all cutomer data
| Method | Endpoint                 | Description                          |
|--------|--------------------------|--------------------------------------|
| POST   | http://localhost:8098/api/nasabah | Create a new customer               |

**Example Request Body:**

```json
{
    "username": "alhuzwiri2s",
    "password": "password123",
    "nama": "John Doe",
    "saldo": 0
}
```
| Method | Endpoint                          | Description               |
|--------|-----------------------------------|---------------------------|
| GET    | http://localhost:8098/api/nasabah | Retrieve all customer details|


| Method | Endpoint                                | Description                         |
|--------|-----------------------------------------|-------------------------------------|
| GET    | http://localhost:8098/api/nasabah/{id} | Retrieve details of a specific customer by ID |


| Method | Endpoint                                           | Description                                 |
|--------|----------------------------------------------------|---------------------------------------------|
| PUT    | http://localhost:8098/api/nasabah/update-saldo/{id}?newSaldo=500000 | Update the balance of a customer with ID 2 to 500000 |



# Transaction API
The transaction endpoint facilitates transferring balances from one customer to another.
if the balance is less, it will fail the balance trasefre, and if the customer's account maintains a sufficient balance, the specified transfer 
amount will be deducted,reducing their balance accordingly, while the recipient's balance will increase by the transferred amount

| Method | Endpoint                          | Description                          |
|--------|-----------------------------------|--------------------------------------|
| POST   | http://localhost:8098/api/transaksi | Create a new transaction             |

**Example Request Body:**

```json
{
  "pengirimId": 4,
  "penerimaId": 2,
  "jumlah": 3400.0,
  "tanggal": "2024-04-25T08:00:00Z"
}
```

| Method | Endpoint                          | Description                          |
|--------|-----------------------------------|--------------------------------------|
| GET    | http://localhost:8098/api/transaksi | Retrieve all transaction data        |


| Method | Endpoint                          | Description                               |
|--------|-----------------------------------|-------------------------------------------|
| GET    | http://localhost:8098/api/transaksi/{id} | Retrieve transaction details for a specific transaction with ID 2 |

| Method | Endpoint                                | Description                                 |
|--------|-----------------------------------------|---------------------------------------------|
| DELETE | http://localhost:8098/api/transaksi/1   | Delete a specific transaction with ID 1     |


# Admin Api
the api is for manage user admin, the admin can manage all data transaction and Customer 
| Method | Endpoint                             | Description                     |
|--------|--------------------------------------|---------------------------------|
| POST   | http://localhost:8098/api/admins     | Create a new admin user         |

**Example Request Body:**

```json
{
    "username": "admin",
    "password": "Admin"
}
```
| Method | Endpoint                           | Description                           |
|--------|------------------------------------|---------------------------------------|
| GET    | http://localhost:8946/api/admins/3 | Retrieve details of admin user with ID 3 |


| Method | Endpoint                           | Description                    |
|--------|------------------------------------|--------------------------------|
| GET    | http://localhost:8028/api/admins   | Retrieve all admin users      |

| Method | Endpoint                             | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| DELETE | http://localhost:8028/api/admins/id  | Delete a specific admin user by ID               |

| Method | Endpoint                             | Description                                      |
|--------|--------------------------------------|--------------------------------------------------|
| PUT    | http://localhost:8946/api/admins/id  | Update details of a specific admin user by ID    |

**Example Request Body:**

```json
{
    "username": "admin12",
    "password": "Admin123"
}
```
