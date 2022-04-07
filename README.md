# bank-api
Application that receives monthly deliveries of customer statement records. This information is delivered in JSON Format.

Example: [{"transactionReference": "123456", "accountNumber": "5314780", "startBalance": 10.0, "mutation": 5.0, "description": "test", "endBalance": 15.0}]

Notes:
1. Names for the fields in the json mesage was adapted ussing standar naming in java
2. To mock the validation of the IBAN we just validate if the IBAN starts with '53'
3. For design principles, I used the transactionReference as String
4. Security is partially implement; authentication server is not implment but there is the code for JWT validation (this is disabled because to be functional all authetication flow must be implement)
