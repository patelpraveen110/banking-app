1. API to create an account
URL = localhost:8080/api/accounts/createAccount
HTTP Method = POST
Request Payload = {
    "accountHolderName": "Ishan",
    "balance":2000000
}
Response Payload = {
    "id": 7,
    "accountHolderName": "Ishan",
    "balance": 2000000.0
}
----------------------------------------------------------
2. API to get the account by ID
URL = localhost:8080/api/accounts/getAccountById/3
HTTP Method = GET
Response Payload = {
    "id": 3,
    "accountHolderName": "Sonu",
    "balance": 20000.0
}
