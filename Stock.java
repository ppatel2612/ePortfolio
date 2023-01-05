/**
* Stock class:
* Contains all children for Investment and all functions needed for stock that are not in Investment
*/

package ePortfolio;

public class Stock extends Investment /** Stock Class */
{ 
     /**
    * @param price updated price of Investmenet
    * @param quantity, to sell investments and have the new price and how much they still have
    */
    public void updateBuy ( double price, double quantity ) 
    {
        super.updateBuy(price, quantity);
        this.bookValue = this.bookValue + (price * quantity) + 9.99;   // Calcs 
    }

     /**
    * @param newPrice Updated price of Investmenet
    * @param quantity, To sell investments and have the new price and how much they sell for
    */
    public void updateSell (double newPrice, double quantity ) 
    {
        this.price = newPrice;
        double excessQuantity = this.quantity - quantity;       // Calcs remaining quantity
        double totalQuantity = this.quantity;

        double payment = quantity * newPrice - 9.99;        // Calcs payemnt amount
    
        if (this.quantity == quantity) 
        { 
            gain = payment - this.bookValue;
        }
        else 
        {
            gain = payment - (this.bookValue * (quantity/totalQuantity));
            
            this.bookValue = this.bookValue * (excessQuantity/totalQuantity);

            this.quantity = this.quantity - quantity;
        }
        
        super.updateSell(price, excessQuantity);
    }

}
