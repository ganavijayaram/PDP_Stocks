Setup:
Run "java -jar pdpassignment6.jar" in terminal to run jar file from any location.

Requirement:
Java jdk must be installed correctly.

In the terminal

CHOOSE 1: to use the command line interface to trade the stocks
CHOOSE 2: To use the GUI to trade the stocks


GUI INTERFACE

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
1. Click on ÒEvaluate ValueÓ
2. Click on submit
3. Choose an existing portfolio
4. Enter a date
5. Click on submit to view the value of the portfolio
6. Click home to return to the main menu


STEPS TO QUERY THE COST BASIS OF THE PORTFOLIO
1. Click on ÒEvaluate cost BasisÓ
2. Click on submit
3. Choose an existing portfolio
4. Enter a date
5. Click on submit to view the Cost-Basis of the portfolio
6. Click home to return to the main menu


STEPS TO SAVE THE PORTFOLIO
1. Click on ÒSave PortfolioÓ
2. Click on Submit
3. Choose a portfolio
4. Click on submit
5. Choose the path
6. Click on save
7. Click on home to return to the main menu

STEPS TO LOAD THE PORTFOLIO
1. Click on ÒLoad PortfolioÓ
2. Click on Submit
3. Choose a file
4. Click on submit
5. Click on home to return to the main menu


STEPS TO THE BAR CHART OF THE PORTFOLIO
1. Click on ÒShow bar ChartÓ
2. Click on Submit
3. Choose a portfolio
4. Choose the from and to date
5. Click on submit
6. Click on home to return to the main menu



STEPS TO CREATE ONE TIME FIXED INVESTMENT STRATEGY
1. Click on ÒCreate One-time Fixed StrategyÓ
2. Click on Submit
1. Enter a name for this strategy (you can use this strategy again, by using this name in the apply strategy)
3. 
4. Enter the Amount you want to invest
5. Enter the number of Stocks
6. Enter the commission for this strategy
7. Enter the date on which you want to make this investment
8. Now for the number of stocks entered, enter the corresponding Ticker symbol and the percentage (summing up to 100%)
9. Click on submit
10. Click on home to return to the main menu



STEPS TO CREATE RECURRING INVESTMENT STRATEGY
2. Click on ÒCreate Recurring Fixed StrategyÓ
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
1. Click ÒApply strategyÓ
2. Click Submit
3. Choose a portfolio
4. Choose the strategy you want to apply
5. Click Submit
6. Click on home to return to the main menu




COMMAND LINE INTERFACE


 Steps to create a Simulated (inflexible) portfolio 
1. Enter 1
2. Enter name of the portfolio-> sample1
3. You will be prompted to input the number of stocks for the portfolio, Enter->3.
4. Enter the stock symbol you wish to buy-> AAPL
5. Enter the number of shares you wish to buy->1
6. A message saying portfolio has successfully created will be displayed on your screen.


Steps to create a transactional portfolio

1. Enter 2. 
2. Enter name of the portfolio-> sample1
3. A message will be displayed saying Portfolio is created sucessfully.
[2->sample1]

Steps to add more stocks in the portfolio
1. Enter->3. 
2. Enter name of the portfolio you created in Step 1 -> sample1
3. You will be prompted to input the stock you wish to buy, Enter->IBM.
4. Next enter the number of shares you wish to buy-> 5
5. Enter the date of the transaction -> 2022-11-09
6. Enter the commission fee associated with the transaction -> 10
7.A message saying the purchase was successful will be displayed on your screen.
[3->sample1->IBM->5->2022-11-09->10]

Steps to query value of Portfolio created in Step1(i.e., sample1) on a date

1. You will see the menu again, now Enter->8.
2. Enter name of the portfolio for which you want the evaluation. Here ->sample1
3. Enter the date in YYYY-MM-DD format-> 2022-11-02. 
4. This will retrieve the value of the portfolio on the asked date. 
[8->sample1->2022-11-02]


Steps to query cost basis of Portfolio created in Step1(i.e., sample1) on a date

1. You will see the menu again, now Enter->9.
2. Enter name of the portfolio for which you want the evaluation. Here ->sample1
3. Enter the date in YYYY-MM-DD format-> 2022-11-02. 
4. This will retrieve the cost basis of the portfolio on the asked date. 
[9->sample1->2022-11-02]



Steps to Composition of Portfolio created in Step1(i.e., sample1) on a date

1. You will see the menu again, now Enter->86
2. Enter name of the portfolio for which you want the evaluation. Here ->sample1
3. Enter the date in YYYY-MM-DD format-> 2022-11-02. 
4. This will retrieve the value of the portfolio on the asked date. 
[8->sample1->2022-11-02]


Steps to sell stocks in transactional portfolio
1. Choose option 4
2. Enter the name of the portfolio
3. Enter the ticker symbol of the stock you want to sell
4. Enter the number of shares
5. Enter the date
6. Enter the commission

Steps to get all available portfolios
1. Choose option 5


Steps to get the performance of the portfolio
1. Choose option 10
2. Enter the name of the existing portfolio
3. Enter the start date
4. Enter the end date

Steps to save the portfolio
1. Choose the option 11
2. Enter the name of the existing portfolio


Steps to load the portfolio
1. Choose the option 12
2. The portfolio.txt will be loaded (this is the file which will be saved in last procedure, if not default one will be loaded)


Steps to create one time investment strategy
1. Choose the option 13
2. Enter the name of the strategy (this can be applied later again)
3. Enter the amount you wish to invest
4. Enter the number of stocks you want to buy
5. Enter the corresponding ticker symbols and the percentage (summing up to 100%)
6. Enter the date you wish to make this transaction
7. Enter the commission


Steps to create recurring investment strategy
1. Enter the name of the strategy (this can be applied later again)
2. Enter the amount you wish to invest
3. Enter the number of stocks you want to buy
4. Enter the corresponding ticker symbols and the percentage (summing up to 100%)
5. Enter the start date you wish to make this transaction
6. Choose if you want to enter the end date
7. If end date is chosen, then enter the end date
8. Enter the frequency in days
9. Enter the commission

Steps to apply strategy to the existing portfolio
1.Choose option 16
2.Enter the name of the portfolio
3.Enter the name of the existing strategy


To Exit the application
Enter any key other than menu option and press enter to exit.



NOTE :
The program supports all the stocks supported by Alpha-Vantage API
The program supports all the dates for portfolio value evaluation
The program does not support purchase/sale transactions to be made on days when Stock Market is closed.

References Used :
1. Nicenum algorithm to understand the algorithm for the scale of bar chart

Library Used :
1. https://mvnrepository.com/artifact/org.json/json - Using org.json for Parsing string to JSON
2. https://jar-download.com/artifacts/org.jdatepicker/jdatepicker/1.3.4/source-code Using the jdatepicker-1.3.4.jarFor Choosing the date

Setting up in IntelliJ
1. Click setting on the right side in IntelliJ
2. Click on Project Structure in settings
3. From the left hand, select the Library module
4. You will see a + sign
5. Click on the + sign and select "From Maven" option
6. You will see a dialog box with search
7. Enter "org.json:json:20220924" in search and select it.
8. Repeat the above steps for jdatepicker JAR.
9. Click on Save, the library will get added to the project.
10. Click Apply and close the settings.
