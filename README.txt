README

# CS611-Final Assignment
## Stock Trading System

## Classes
---------------------------------------------------------------------------
### Controller
MainController: Main UI Controller
SignupController: Signup UI Controller
LoginController: Login UI Controller
AdminMainController: Admin Main UI Controller
StockController: View Stocks Page Controller
CustomerController: View Customers Page Controller
UserMainController: User Main UI Controller
UserInfoController: User Information Page Controller
UserStockController: User Stocks Page Controller
UserStockBuyController: Buying Stocks Page Controller
UserStockSellController: Selling Stocks Page Controller
BgColorController: Background color change controller

### View
Observer: Observer interface for ColorJFrame
ColorJFrame: JFrame which has a change color panel(subclass of JFrame, Observer)
BgColorPanel: Color selection panel
TransPanel: Panel that is transparent top show the background color of the JFrame(subclass of JPanel)
MainView: Main UI(subclass of ColorJFrame)
SignupView: Signup UI(subclass of TransPanel)
LoginView: Login UI(subclass of TransPanel)
AdminMainView: Admin Main UI(subclass of ColorJFrame)
StockView: View Stocks Page(subclass of TransPanel)
CustomerView: View Customers Page(subclass of TransPanel)
UserMainView: User Main UI(subclass of ColorJFrame)
UserInfoView: User Information Page(subclass of JPanel)
UserStockView: User Stocks Page(subclass of JPanel)
UserStockBuyView: Buying Stocks Page(subclass of ColorJFrame)
UserStockSellView: Selling Stocks Page(subclass of ColorJFrame)

### Model
DatabaseConnector: MySQL Database Connector
CustomerDatabase: Customer database functions for query  
StockDatabase: Stock database functions for query  
OwnDatabase: Own database functions for query  
Customer: Class for storing customer data
Stock: Class for storing stock data
OwnStock: Class for storing stock own data

### ETC
Main: Entry point of the application
SystemInitializer: Initialize a Program
IntFIlter: Utility class for filtering integer from inputs
StockTableModel: Utility class for limiting specific table data type


## Notes
---------------------------------------------------------------------------
1. Design Patterns
    - MVC Pattern: Whole Program
    - Singleton Pattern: DatabaseConnector, BgColorController
    - Observer Pattern: ColorJFrame

2. Advantage of our design
    - When new data types are needed, they can be easily extended by adding the relevant classes to the Models.
    - Almost all business logic can be changed and extended through Controllers.
    - Views have very few dependencies on other classes, making it easy to change the look of the UI.

3. Additional Features
    - UI Color can be changed by selection.
    - We have a logo for the application.
    - Automatically change stock price after selling/buying.


## How to compile and run
---------------------------------------------------------------------------
### For Windows
1. Enter the directory "src"
2. Run the following instructions:
javac -d . -sourcepath . *.java View/*.java Controller/*.java Model/*.java Util/*.java
java -cp ".;..\library\mysql-connector-j-8.2.0.jar" com.stock_test.Main

### For MacOS
1. Enter the directory "src"
2. Run the following instructions:
javac -d . -sourcepath . *.java View/*.java Controller/*.java Model/*.java Util/*.java
java -cp ".:../library/mysql-connector-j-8.2.0.jar" com.stock_test.Main

Or you can simply execute "build_and_run.bat"(Windows) or "build_and_run.sh"(MacOS).
