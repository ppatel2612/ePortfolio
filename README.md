======================================================================================================================================================================================
Pratham Patel    
Student ID: 1140832
UoG CIS*2430 F2021
2021-11-30
Assignment 3
======================================================================================================================================================================================


(1): EXPLANATION
	This program allows users to keep track of investments thorugh a visula interface (GUI). They can add and remove investments and the program will automatically generate the 
	profit/loss depending on previous prices and updated prices ofsaid investments. The user can also search their investments using various search parametersand get detailed list 
	of all their investments and even output their current portflio to a *.txt file which will be formated and stored in the same file that was given to the program to initialize 
	the portfolio at the startS of the program. This is all done through the interface that makes it much easier to track and read as everything is more spaced out and properly 
	formatted. When the program is exited (using either method), if a file was inputed using a commandline argument all the stock will then be re uploaded to that file.

(2): Assumptions 
	The user will only input stock or mutualFund. The program is not made to handle any other type.
	The user will not upload any other information for a stock or mutual fund. The code can't store more information than the given values for a specific investment.
	If there is a given file at the start, it will be in the format specified below. The code has set rules it follows to extract the information from that file.

	Sample file content format: 
		type = "stock"
		symbol = "AAPL"
		name = "Apple Inc."
		quantity = "125"
		price = "125.78"
		bookValue = "150.00"

(3): Any user can run this file by using
	1) javac ePortfolio/*.java
	2) java ePortfolio.Window <foldername>/<filename>.txt   (file is optional)

(4): Testing
	When Testing this I tested all possible entrys the user could input. When there is a string input such as inputing names or symbols the program will allow any input since all 
	inputs are valid. When there is a number input such as Low Price, High Price, Price, and quantity the program will only accept positive amounts. And, when the user Inputs 
	quantity for selling it will check to make sure the input is less than what is owned already. If the user Inputs anything that is not acceptable such as a string for a number 
	input it will throw an exception and allow the user to retry without giving an error in the terminal. I also tested my code with a file called file.txt inside my ePortfolio 
	folder so my command line argument was ePortfolio/file.txt.

(4): Future Improvments: 
	I would like to make the interface more user friendly by adding an arrow on the drop down menu for example. This would help make the program simpler and easier to use. Different 
	colours and designs would also be helpful in the overall feel of the program, however due to lack of time and knowledge as of now, I was not able to add such additons. The final 
	thing that would really be beneficial to the program is for me to clean up the code. It works as it is, however, there could be bugs that I have not yet to discover, and 
	cleaning up the code will not only help me find them but also fix them.
======================================================================================================================================================================================
