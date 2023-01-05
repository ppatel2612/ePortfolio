/**
* MutualFund class
* Contains all children for Investment and all functions needed for mutualFund that are not in Investment
*/

package ePortfolio;

public class MutualFund extends Investment /** MutualFund objectClass */
{    

     /**
    * @param newPrice Updated new Price of Investmenet
    * @param quantity, To sell investments and have the new newPrice and how much they sell
    */
    public void updateBuy ( double newPrice, double quantity ) 
    {
        super.updateBuy(newPrice, quantity);
        this.bookValue = this.bookValue + (newPrice * quantity); //Funds
    }
     
     /**
    * @param newPrice updated newPrice of Investmenet
    * @param quantity, to sell investments and have the new newPrice and how much they sell
    */
    public void updateSell (double newPrice, double quantity ) 
    {
        super.updateSell(newPrice, quantity);
        double excessQuantity = this.quantity - quantity;
        double totalQuantity = this.quantity;
        
        double payment = quantity * newPrice - 45;
        if (this.quantity == quantity) 
        { //Selling everything
            gain = payment - this.bookValue;
        }
        else 
        {
            gain = payment - (this.bookValue * (quantity/totalQuantity));
            this.bookValue = this.bookValue * (excessQuantity/totalQuantity);
            this.quantity = this.quantity - quantity;
        }
    }
}