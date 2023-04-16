Assignment 6

The application supports both Text based View and GUI which are described below in detail :

In the terminal

CHOOSE 1: to use the command line interface to trade the stocks
CHOOSE 2: To use the GUI to trade the stocks


GUI Interface supports following features :

1. Create a Flexible Portfolio
2. View all the portfolio
3. Buy a stock and add to portfolio
4. Sell a stock from the portfolio
5. Evaluate Portfolio Composition at a date
6. Evaluate Portfolio Value at a date
7. Evaluate Portfolio Cost Basis at a date
8. Saving the portfolio to File
9. Loading the portfolios from file
10. Creating a Recurring Investment Strategy
11. Creating a Fixed One Time Investment Strategy
12. Applying a Strategy to a Portfolio
13. Creating a Dollar-Cost Averaging Portfolio


STEPS TO CREATE A PORTFOLIO
1. Click on Create Portfolio menu option
2. Enter the name of non-existing portfolio
3. Click submit to create the portfolio
4. Click home to return to the main menu

STEPS TO VIEW ALL PORTFOLIOS
1. Click on show all portfolio menu option
2. Click submit to view all the portfolios
3. Click home to return to the main menu


STEPS TO BUY STOCKS
1. Click on Buy Stocks menu option
2. Choose an existing portfolio
3. Enter valid name of the stock
4. Enter number of stocks greater than 0
5. Enter a valid date on which you want to buy the stocks
6. Enter the commission for the entire transaction
7. Click submit to buy the stock
8. You can click on the submit to execute the same transaction again.
9. Click home to return to the main menu

STEPS TO SELL STOCKS
1. Click on Sell Stocks
2. Choose an existing portfolio
3. Enter valid name of the stock
4. Enter number of stocks greater than 0
5. Enter a valid date on which you want to sell the stocks
6. Enter the commission for the entire transaction
7. Click submit to buy the stock
8. Click home to return to the main menu


STEPS TO GET COMPOSITION OF THE PORTFOLIO
1. Click on Portfolio Composition
2. Click on submit
3. Choose an existing portfolio
4. Enter a date
5. Click on submit to view the composition of the portfolio
6. Click home to return to the main menu

STEPS TO QUERY THE VALUE OF THE PORTFOLIO
1. Click on Evaluate Value
2. Click on submit
3. Choose an existing portfolio
4. Enter a date
5. Click on submit to view the value of the portfolio
6. Click home to return to the main menu


STEPS TO QUERY THE COST BASIS OF THE PORTFOLIO
1. Click on Evaluate cost Basis
2. Click on submit
3. Choose an existing portfolio
4. Enter a date
5. Click on submit to view the Cost-Basis of the portfolio
6. Click home to return to the main menu


STEPS TO SAVE THE PORTFOLIO
1. Click on Save Portfolio
2. Click on Submit
3. Choose a portfolio
4. Click on submit
5. Choose the path
6. Click on save
7. Click on home to return to the main menu

STEPS TO LOAD THE PORTFOLIO
1. Click on Load Portfolio
2. Click on Submit
3. Choose a file
4. Click on submit
5. Click on home to return to the main menu


STEPS TO THE BAR CHART OF THE PORTFOLIO
1. Click on Show bar Chart
2. Click on Submit
3. Choose a portfolio
4. Choose the from and to date
5. Click on submit
6. Click on home to return to the main menu



STEPS TO CREATE ONE TIME FIXED INVESTMENT STRATEGY
1. Click on Create One-time Fixed Strategy
2. Click on Submit
3. Enter a name for this strategy (you can use this strategy again, by using this name in the apply strategy)
4. Enter the Amount you want to invest
5. Enter the number of Stocks
6. Enter the commission for this strategy
7. Enter the date on which you want to make this investment
8. Now for the number of stocks entered, enter the corresponding Ticker symbol and the percentage (summing up to 100%)
9. Click on submit
10. Click on home to return to the main menu



STEPS TO CREATE RECURRING INVESTMENT STRATEGY
2. Click on Create Recurring Fixed Strategy
3. Click on Submit
4. Enter a name for this strategy (you can use this strategy again, by using this name in the apply strategy)
5. Enter the Amount you want to invest
6. Enter the number of Stocks
7. Enter the commission for this strategy
8. Enter the from date on which you want to make this investment
9. Enter the to date on till you want to make this investment or click check box for never ending
10. Enter the frequency in days
11. Now for the number of stocks entered, enter the corresponding Ticker symbol and the percentage (summing up to 100%)
12. Click on submit
13. Click on home to return to the main menu


STEPS TO APPLY THE GIVEN STRATEGY
1. Click Apply strategy
2. Click Submit
3. Choose a portfolio
4. Choose the strategy you want to apply
5. Click Submit
6. Click on home to return to the main menu

STEPS TO CREATE A DOLLAR COST AVERAGING PORTFOLIO
1. The application display the option to create this type of portfolio
2. Click on Create Recurring Fixed Strategy
3. Click on Submit
4. Enter a name for this strategy (you can use this strategy again, by using this name in the apply strategy)
5. Enter the Amount you want to invest
6. Enter the number of Stocks
7. Enter the commission for this strategy
8. Enter the from date on which you want to make this investment
9. Enter the to date on till you want to make this investment or click check box for never ending
10. Enter the frequency in days
11. Enter the name of the portfolio
12. Now for the number of stocks entered, enter the corresponding Ticker symbol and the percentage (summing up to 100%)
13. Click on submit
14. Click on home to return to the main menu

This following features are supported by the Text based View :

1.  Ability to create simulated (inflexible) portfolios with multiple stocks(i.e stock and the number of stock).
2.  Ability to create transactional (flexible) portfolios with multiple stock trades(i.e stock, number of stock, date of transaction, commission fee           associated with the transaction).
3.  Ability to purchase a stock and add it to transactional portfolio
4.  Ability to sell a Stock from the transactional portfolio
5.  Ability to get all the portfolio names that exist in the application.
6.  Ability to get the Final Portfolio Composition i.e the name of the portfolio along with the stock ( stock and its quantity) it is comprised of after       all the transactions has been completed.
7.  Ability to get the Portfolio Composition at a specific date.
8.  Ability to get the total value of the Portfolio at a specific date.
9.  Ability to get cost basis of the transactional portfolio
10. Ability to fetch the portfolio performance over a period of time.
11. Ability to save the Portfolio on disk.
12. Ability to load all the portfolios in the application from the disk.
13. Create a one time investment strategy
14. Create a recurring investment strategy
15. Get all the existing strategies
16. Apply a strategy to a portfolio
17. Create a Dollar Cost Averaging Portfolio

Description of the functionalities :

1. Ability to create simulated (inflexible) portfolios with multiple stocks(i.e stock and the number of stock)
-- The program starts by displaying the menu options.
-- To create a Simulated Portfolio, user must input 1.
-- The user must then enter the name of the portfolio that it needs to create.
-- Next the user is prompted to enter the number of shares that the portfolio is going to be comprised of
-- Based on the number of shares input by user above the program will ask for the stock symbol and amount/quantity of share of each stock.
-- Once the input is complete, The application will successfully create a Simulated Portfolio.

2. Ability to create transactional (flexible) portfolio
-- The program starts by displaying the menu options.
-- To create a Transactional Portfolio, user must input 2.
-- The user must then enter the name of the portfolio that it needs to create.
-- The application will successfully create a Transactional Portfolio.
NOTE : this step will create a empty portfolio, user can add/sell the stocks in and from the portfolio
to support trading.

3. Ability to purchase a stock and add it to transactional portfolio
-- The program starts by displaying the menu options.
-- To purchase a stock and add it to transactional portfolio, user must input 3.
-- Enter the name of the exisiting transactional portfolio the user wishes to add stock to.
-- Enter the stock that you wish to purchase
-- Enter the number of shares of the stock that you wish to purchase
-- Enter the date of transaction
-- Enter the commission fee associated with the transaction
-- Once the input is completed, the application will successfully add the stock to the portfolio.

4.  Ability to sell a Stock from the transactional portfolio
-- The program starts by displaying the menu options.
-- To sell a stock from the transactional portfolio, user must input 4.
-- Enter the name of the exisiting transactional portfolio the user wishes to sell from.
-- Enter the stock that you wish to sell
-- Enter the number of shares of the stock that you wish to sell
-- Enter the date of transaction
-- Enter the commission fee associated with the transaction
-- Once the input is completed, the application will successfully sell the stock from the portfolio.

5. Ability to get all the portfolio names that exist in the application :
-- The program starts by displaying the menu options.
-- To display all the existing Portfolios in the system, user must input 5.
-- The application displays all the portfolios existing in the application.


6. Ability to get the Portfolio Composition i.e the name of the portfolio along with the stock ( stock and its quantity) it is comprised of :
-- The program starts by displaying the menu options.
-- To display the composition of an existing Portfolios in the system, user must input 6.
-- The program will then prompt the user to enter the portfolio name
-- Once the portfolio name is entered, the application will display the portfolio composition including all transactions.

7.  Ability to get the Portfolio Composition at a specific date.
-- The program starts by displaying the menu options.
-- To display the composition of an existing Portfolios in the system at a specific date, user must input 7.
-- The program will then prompt the user to enter the portfolio name
-- The program will then prompt the user to enter the date
-- Once the portfolio name is entered, the application will display the portfolio composition at the specified date.

8. Ability to get the total value of the Portfolio at a specific date :
-- The program starts by displaying the menu options.
-- To get the total value of an existing Portfolios in the system, user must input 8.
-- The application will then prompt the user to enter the portfolio name for which the evaluation is asked
-- Once the user has input portfolio name, the program prompts the user to input the date at which the evaluation needs to be performed
-- Once the date is input, the application will display the total value of the portfolio at the specified date.

9.  Ability to get cost basis of the transactional portfolio
-- The program starts by displaying the menu options.
-- To get the cost basis an existing transactional Portfolio in the system, user must input 9.
-- The application will then prompt the user to enter the portfolio name for which the evaluation is asked
-- Once the user has input portfolio name, the program prompts the user to input the date at which the cost basis needs to be evaluated
-- Once the date is input, the application will display the cost basis of the portfolio at the specified date.

10. Ability to fetch the portfolio performance over a period of time.
-- The program starts by displaying the menu options.
-- To get the performance chart of Portfolio in the system, user must input 10.
-- The application will then prompt the user to enter the portfolio name for which the performance needs to be evaluated
-- Once the user has input portfolio name, the program prompts the user to input the start date for performance evaluation period
-- The program prompts the user to input the end date for performance evaluation period
-- The application will display the portfolio performance over the specified range.

11. Ability to save the Portolio on disk :
-- The program starts by displaying a menu option.
-- To save an existing Portfolios in the file system, user must input 11.
-- The program then prompts the user to input the portfolio name that needs to be saved in the system.
-- Once the user inputs, the program saves the portfolio in the file system.

12. Ability to load all the portfolios in the application from the disk :
-- The program starts by displaying a menu option.
-- To load Portfolios in the file system, user must input 12.
-- The user will then load the portfolios in the program from /res/portfolio.txt

13. Create a One Time Investment Strategy :
-- The program starts by displaying a menu option.
-- To create this type of investment strategy, user must input 13
-- Enter the name of the strategy that you wish to create.
-- Enter the principal amount for the strategy.
-- The program prompts to Enter the number of stocks you wish to purchase
-- Input Stock symbol and weight for each stock
-- Enter the date of the transaction (in YYYY-MM-DD format)
-- Enter the commission fee for the strategy
-- The strategy will be successfully created.

14. Create a Recurring Investment Strategy :
-- The program starts by displaying a menu option.
-- To create this type of investment strategy, user must input 14
-- Enter the name of the strategy that you wish to create.
-- Enter the principal amount for the strategy.
-- The program prompts to Enter the number of stocks you wish to purchase
-- Input Stock symbol and weight for each stock
-- Enter the start date of the transaction (in YYYY-MM-DD format)
-- The program displays a option to select if user wishes to enter end date(select y/n)
-- If y : then the application prompt for end date else if it does not.
-- Enter the recurring frequency of the strategy (in days)
-- Enter the commission fee for the strategy
-- The strategy will be successfully created.

15. Get all the existing strategies
-- The program starts by displaying a menu option.
-- To view all the existing strategies, user must input 15
-- The application displays all the strategies.

16. Apply a strategy to a portfolio
-- To apply a strategy to a portfolio, user must select 16 from the menu
-- Enter the name of the portfolio to wish the strategy must be applied
-- Enter the name of the strategy to be applied
-- The application will apply the specified strategy on the portfolio.

17. Create Dollar Cost Averaging Portfolio
-- The program starts by displaying a menu option.
-- To create this type of portfolio, user must input 17
-- Enter the name of the portfolio.
-- Enter the name of the strategy that you wish to create.
-- Enter the principal amount for the strategy.
-- The program prompts to Enter the number of stocks you wish to purchase
-- Input Stock symbol and weight for each stock
-- Enter the start date of the transaction (in YYYY-MM-DD format)
-- The program displays a option to select if user wishes to enter end date(select y/n)
-- If y : then the application prompt for end date else if it does not.
-- Enter the recurring frequency of the strategy (in days)
-- Enter the commission fee for the strategy
-- The strategy will be successfully created.