/**
* Investment class"
* Contains all constructors and all functions needed for Investment Class
*/

package ePortfolio;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;  
import java.io.File;
import java.lang.Object;
import java.io.Writer;
import java.io.PrintWriter;

public class Investment /** Investment objectClass */
{
    
    static double gain;

    String type;
    String symbol;
    String name;
    double quantity;
    double price;
    double bookValue;

    /**
    * @param newType Type of Investment
    * @param newSymbol Symbol of Investment
    * @param newName Name of Investment
    * @param newQuantity Quantity of Investment
    * @param newPrice Price of Investment
    * @param newBookValue Bookval of Investment
    */
    public void set ( String newType, String newSymbol, String newName, double newQuantity, double newPrice, double newBookValue ) 
    {
        type = newType;
        symbol = newSymbol;
        name = newName;
        quantity = newQuantity;
        price = newPrice;
        bookValue = newBookValue;

    }

     /**
    * @return All attributes of Stock in a string
    */
    public String toString () 
    {
        char buffer = '"';
        return ("type = " + buffer + type + buffer + "\nsymbol = " + buffer + symbol + buffer + "\nname = " + buffer + name + buffer + "\nquantity = " + buffer + quantity + buffer + "\nprice = " + buffer + price + buffer + "\nbookValue = " + buffer + bookValue + buffer + "\n");
    }

     /**
    * @param tempObj Object to check if attributes the same as a Stock
    * @return The Boolean value true or false
    */
    public boolean equals ( Investment tempObj ) 
    {
        return ( (symbol.equals(tempObj.symbol)) && (name.equals(tempObj.name)) && (quantity == tempObj.quantity) && (price == tempObj.price) && (bookValue == tempObj.bookValue) );
    }

     /**
    * @return type Of Investment
    */
    public String getType () 
    {
        return type;
    }

     /**
    * @return name Of Investment
    */
    public String getName () 
    {
        return name;
    }

     /**
    * @return symbol of Investment
    */
    public String getSymbol () 
    {
        return symbol;
    }

     /**
    * @return quantity of Investment
    */
    public double getQuant () 
    {
        return quantity;
    }

     /**
    * @return price of Investment
    */
    public double getPrice () 
    {
        return price;
    }
    
     /**
    * @return bookVal Of Investment
    */
    public double getBookVal () 
    {
        return bookValue;
    }

     /**
    * @param price updated price of Investment
    * @param quant to buy and update values
    */
    public void updateBuy ( double price, double quant ) 
    {

        this.quantity = this.quantity + quant;
        this.price = price;
    }

     /**
    * @param newPrice updated price of Investmenet
    * @param newQuant, to sell investments and have the new price and how much they still have
    */
    public void updateSell (double newPrice, double newQuant ) 
    {
        this.price = newPrice;
        this.quantity = newQuant;
    }

     /**
    * @param price to update the price on Object Investment
    */
    public void update ( double price ) 
    {
        this.price = price;
    }
}
