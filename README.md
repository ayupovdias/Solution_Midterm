# Solution_Midterm
Coffee Shop
1. Coffee interface

Defines methods for getting the price (getCost()) and description (getDescription()) of coffee.

2. Coffee classes (Espresso, Cappuccino, Latte, Americano, Raf)

Each class represents a specific type of coffee, indicating its price and description

3. CoffeeFactory

Creates a coffee object depending on the passed name. If the type is incorrect, throws an error.

4. Main

Prompts the user to select a coffee.

Allows you to add ingredients (milk, syrup, etc.).

Displays the final description and cost.

5. CoffeeDecorator

Base class for additives. Allows you to change the description and price of the drink.

 6. Decorator classes (Milk, CaramelSyrup, WhippedCream, ChocolateSyrup, Sugar)

Add ingredients to coffee, increasing its price and changing the description.

This code uses the Factory Method pattern to create coffee and the Decorator pattern to add ingredients.



Online Payment Gateway 

1. The PaymentMethod interface

Defines the processPayment(amount) method, which performs the payment and returns a status message.

2. API classes for payments (CreditCardAPI, PayPalAPI, CryptoPaymentAPI)

These classes represent external payment systems.

Each has a data verification method (checkCard(), checkPayPal(), checkCrypto()).

The processPayment() method returns a payment message.


3. Adapters (CreditCardAdapter, PayPalAdapter, CryptoPaymentAdapter)

They allow you to use the payment API through the PaymentMethod interface.

They check the correctness of the data before making the payment.

If the data is incorrect, an error message is returned.


4. Payment Factory (PaymentFactory)

Creates a payment object depending on the type: credit card, PayPal, or crypto

5. Transaction History (TransactionHistory)

Stores a list of all transactions.

addTransaction() method adds an entry.

showHistory() method outputs the payment history.


6. Main Class

Requests a payment method from the user.

Checks the entered data.

Performs the payment and saves the status in the history.

Allows you to complete the work by entering quit.

At the end, it shows all transactions.


Patterns used

Adapter — allows you to integrate external APIs through a common interface.

Factory method — creates the required payment type on request.