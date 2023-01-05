/**
 * @author Pratham Patel
 * This is the Window Class that will create the interface for the program
 */

package ePortfolio;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;  
import java.io.File;  // Import the File clas
import java.lang.Object;
import java.awt.GridBagLayout;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.HashMap;   //Hashmap
import java.lang.*;
import java.awt.ComponentOrientation;
import java.awt.event.*;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.awt.Font;

import java.io.File;  // Import the File class
import java.lang.Object;

public class Window extends JFrame implements ActionListener /** Window Class */
{  

    JTextArea firstPage;

    final JComboBox<String> type = new JComboBox<String>();
    JTextArea symbolInput;
    JTextArea nameInput;
    JTextArea quantityInput;
    JTextArea priceInput;
    JTextArea outputArea;

    private int userInput = 0; 
    private int investmentType = 1; 

    static ArrayList<Investment> investmentList = new ArrayList<Investment>();    //Decleration of ArrayList
    static HashMap <String, ArrayList<Integer> > investmentMap = new HashMap <String, ArrayList<Integer> > ();  //Decleration of HashMap
    
    static int fileInput = 0;   
    static int numInvestments = 0;
    int indexOfStock = 0;

    static String fileNameFromInput;

    public class cbListener implements ActionListener       // Finds what the type of investment it is
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JComboBox type = (JComboBox)e.getSource();
            String typeString = (String)type.getSelectedItem();
            if (typeString.equals("Stock")) 
            {
                investmentType = 1;
            }
            if (typeString.equals("MutualFund")) 
            {
                investmentType = 2;
            }
        }
    }

    public static void main(String[] args) 
    {
        int i = 0;
        try         
        {       // If initial file exists
            File file = new File (args[0]);
            fileNameFromInput = new String(args[0]);

            Scanner scan = new Scanner (file);
            int numberInput = 0;

            String type = null; //Inserting from file
            String symbol = null;
            String name = null;
            double quant = 0;
            double price = 0;
            double bookVal = 0;
            
            while ( scan.hasNextLine() ) 
            {  
                StringBuffer nameString = new StringBuffer();   //creating name with multiple words
                String eachLine = null;  //Save each line then use split, deleate data in previous arrayList, the input the data into arrayList
                eachLine = scan.nextLine();

                if (eachLine == "") 
                {
                    numberInput = 0;
                }

                else if (numberInput == 0) 
                { //type
                    String words[] = eachLine.split("[, ]+");
                    String output = words[2].replace("\"", "");
                    type = output;
                    numberInput++;
                }

                else if (numberInput == 1) 
                { //symbol
                    String words[] = eachLine.split("[, ]+");
                    String output = words[2].replace("\"", "");
                    symbol = output;
                    numberInput++;
                }

                else if (numberInput == 2) 
                { //name
                    String words[] = eachLine.split("[, ]+");
                    for (i = 0; i < words.length; i++) 
                    {
                        String output = words[i].replace("\"", "");
                      
                        if (i == words.length-1) 
                        {
                            nameString.append(output);
                        }
                        else if (i > 1) 
                        {
                            nameString.append(output + " ");
                        }
                    }
                    name = nameString.toString();
                    numberInput++;
                }

                else if (numberInput == 3) 
                { //quant
                    String words[] = eachLine.split("[, ]+");
                    String output = words[2].replace("\"", "");
                    quant = Double.parseDouble(output);
                    numberInput++;
                }

                else if (numberInput == 4) 
                { //price
                    String words[] = eachLine.split("[, ]+");
                    String output = words[2].replace("\"", "");
                    price = Double.parseDouble(output);
                    numberInput++;
                }

                else if (numberInput == 5) 
                { //book val, set and reset
                    String words[] = eachLine.split("[, ]+");
                    String output = words[2].replace("\"", "");
                    bookVal = Double.parseDouble(output);

                    if ( type.equals("stock") ) 
                    {   //input stock into ArrayList stock
                        Stock newStock = new Stock();
                        newStock.set(type, symbol, name, quant, price, bookVal);
                        investmentList.add(newStock); 

                        String []eachWordOfName = name.split("[ ]+");   //Hashmap Input

                        for (i = 0; i < eachWordOfName.length; i++) 
                        {
                            String hashInput = eachWordOfName[i].toLowerCase();

                            if (investmentMap.containsKey(hashInput)) 
                            { //update int array
                                ArrayList<Integer> intArrayFromHash = new ArrayList<Integer>();
                                intArrayFromHash = investmentMap.get(hashInput);
                        
                                intArrayFromHash.add(numInvestments); //Adds stock number to hashMap
                                investmentMap.remove(hashInput);    //Gets rid of HashInput
                                investmentMap.put(hashInput, intArrayFromHash);
                            }

                            else 
                            {
                                ArrayList<Integer> intArrayForHash = new ArrayList<Integer>();
                                intArrayForHash.add(numInvestments);  //Assuming new word
                                
                                investmentMap.put(hashInput, intArrayForHash);
                            }
                        }
                    }

                    else if ( type.equals("mutualfund") ) 
                    { //input mutualFund in ArrayList mutualFund
                        MutualFund newMF = new MutualFund();
                        newMF.set(type, symbol, name, quant, price, bookVal);
                        investmentList.add(newMF);

                        String []eachWordOfName = name.split("[ ]+");   //Hashmap Input

                        for (i = 0; i < eachWordOfName.length; i++) 
                        {
                            String hashInput = eachWordOfName[i].toLowerCase();
                            if (investmentMap.containsKey(hashInput)) 
                            { //update int array
                                ArrayList<Integer> intArrayFromHash = new ArrayList<Integer>();
                                intArrayFromHash = investmentMap.get(hashInput);
                        
                                intArrayFromHash.add(numInvestments); //Adds stock number to hashMap
                                investmentMap.remove(hashInput);    //Gets rid of HashInput
                                investmentMap.put(hashInput, intArrayFromHash);
                            }

                            else 
                            {
                                ArrayList<Integer> intArrayForHash = new ArrayList<Integer>();
                                intArrayForHash.add(numInvestments);  //Assuming new word
                                investmentMap.put(hashInput, intArrayForHash);
                            }
                        }
                
                    }
                    name = null;
                    numInvestments++;
                    numberInput = 0;
                }
            }

            scan.close();
        } 

        catch (Exception y) {   //Error checking
            //System.out.println("Could not open file. Or no file inputed. Please Enter File folderName/fileName.txt\n");
            fileInput = 1;
        }

        Window demoGui = new Window( );
        demoGui.setBackground(Color.GREEN);
        demoGui.setVisible(true);
    }

    public Window() 
    {

        addWindowListener(new java.awt.event.WindowAdapter() 
        {  //Allows to output Stock back ontoFile when closed
            public void windowClosing(java.awt.event.WindowEvent r) 
            {
                if (fileInput == 0) 
                {
                    PrintWriter writer = null;
                    try 
                    {
                        writer = new PrintWriter (fileNameFromInput);
                    } 
                    catch (Exception s) 
                    { 
                        System.out.println("Failed to write.");
                    }
                    for ( Investment investment: investmentList ) 
                    {
                        writer.println(investment.toString());
                    }
                    writer.close();
                }
                System.exit(0);
            }
        });

        setSize(705, 530);
        setTitle("ePortfolio Manager");
        setDefaultCloseOperation(Window.DO_NOTHING_ON_CLOSE);

        setLayout(null); //Set Layout

        JMenu choiceMenu = new JMenu("Commands");       // Adds menu
        choiceMenu.setFont(new Font("Calibri", Font.PLAIN, 20));

        firstPage = new JTextArea();
        firstPage.setEditable(false);
        firstPage.setBounds(20, 40, 666, 400);
        firstPage.setFont(new Font("Calibri", Font.PLAIN, 20));
        firstPage.setText("Welcome to ePortfolio.\n\n\nChoose a command from the “Commands” menu to buy or sell\nan investment, update prices for all investmentList, get gain for the\nportfolio, search for relevant investmentList, or quit the program.");
        add(firstPage);

        JTextArea label = new JTextArea();
        label.setEditable(false);
        label.setFont(new Font("Calibri", Font.PLAIN, 20));
        label.setVisible(false);
        add(label);
        
        JButton resetButton = new JButton();
        resetButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        resetButton.addActionListener(this);
        resetButton.setVisible(false);
        add(resetButton);

        JButton bottomButton = new JButton();
        bottomButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        bottomButton.addActionListener(this);
        bottomButton.setVisible(false);
        add(bottomButton);

        JButton nextButton = new JButton();
        nextButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        nextButton.addActionListener(this);
        nextButton.setVisible(false);
        add(nextButton);

        JTextArea typeLabel = new JTextArea ();
        typeLabel.setEditable(false);
        typeLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        typeLabel.setVisible(false);
        add(typeLabel);

        String[] stockOrFund = {"Stock", "MutualFund"}; //Combo Box
        JComboBox<String> type = new JComboBox<String>(stockOrFund);
        type.setFont(new Font("Calibri", Font.PLAIN, 20));
        type.addActionListener(new cbListener());
        add(type);
        type.setVisible(false);

        JTextArea symbolLabel = new JTextArea();    //Symbol Label
        symbolLabel.setEditable(false);
        symbolLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        symbolLabel.setVisible(false);
        add(symbolLabel);

        symbolInput = new JTextArea();
        symbolInput.setEditable(true);
        symbolInput.setFont(new Font("Calibri", Font.PLAIN, 20));
        symbolInput.setVisible(false);
        add(symbolInput);

        JTextArea nameLabel = new JTextArea();    //Name Label
        nameLabel.setEditable(false);
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameLabel.setVisible(false);
        add(nameLabel);

        nameInput = new JTextArea();
        nameInput.setEditable(true);
        nameInput.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameInput.setVisible(false);
        add(nameInput);

        JTextArea quantityLabel = new JTextArea();    //Name Label
        quantityLabel.setEditable(false);
        quantityLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        quantityLabel.setVisible(false);
        add(quantityLabel);

        quantityInput = new JTextArea();
        quantityInput.setEditable(true);
        quantityInput.setFont(new Font("Calibri", Font.PLAIN, 20));
        quantityInput.setVisible(false);
        add(quantityInput);

        JTextArea priceLabel = new JTextArea();    //Name Label
        priceLabel.setEditable(false);
        priceLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        priceLabel.setVisible(false);
        add(priceLabel);

        priceInput = new JTextArea();
        priceInput.setEditable(true);
        priceInput.setFont(new Font("Calibri", Font.PLAIN, 20));
        priceInput.setVisible(false);
        add(priceInput);

        JTextArea bottomLabel = new JTextArea();  //Messages Label
        bottomLabel.setEditable(false);
        bottomLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        bottomLabel.setVisible(false);
        add(bottomLabel);

        outputArea = new JTextArea();  //Output Messages Label
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Calibri", Font.PLAIN, 20));
        outputArea.setVisible(false);
        add(outputArea);

        JScrollPane scrollPane = new JScrollPane(outputArea);    //Allowing Scroll Bars visable
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(false);
        add(scrollPane);

        JMenuItem buyInvestmentChoice = new JMenuItem("Buy");
        buyInvestmentChoice.setFont(new Font("Calibri", Font.PLAIN, 20));

        buyInvestmentChoice.addActionListener((ActionEvent e) ->
        { 
            userInput = 1;
            nextButton.setVisible(false);
            firstPage.setVisible(false);
            priceInput.setEditable(true);
            outputArea.setText("");
            outputArea.setVisible(true);
            symbolInput.setEditable(true);
            nameInput.setEditable(true);
            priceInput.setEditable(true);

            label.setText("Buying an investment");
            label.setBounds(20, 20, 215, 30);
            label.setVisible(true);
            
            resetButton.setText("Reset");
            resetButton.setBounds(550, 70, 100, 38);
            resetButton.setVisible(true);

            bottomButton.setText("Buy");
            bottomButton.setBounds(550, 175, 100, 38);
            bottomButton.setVisible(true);

            typeLabel.setText("Type");
            typeLabel.setBounds(35, 60, 54, 30);
            typeLabel.setVisible(true);
            
            type.setBounds(132, 60, 240, 30);
            type.setVisible(true);

            symbolLabel.setText("Symbol");
            symbolLabel.setBounds(35, 100, 75, 30);
            symbolLabel.setVisible(true);
            
            symbolInput.setText("");
            symbolInput.setBounds(132, 100, 160, 30);
            symbolInput.setVisible(true);

            nameLabel.setText("Name");
            nameLabel.setBounds(35, 140, 63, 30);
            nameLabel.setVisible(true);
            
            nameInput.setText("");
            nameInput.setBounds(132, 140, 320, 30);
            nameInput.setVisible(true);

            quantityLabel.setText("Quantity");
            quantityLabel.setBounds(35, 180, 82, 30);
            quantityLabel.setVisible(true);
            
            quantityInput.setText("");
            quantityInput.setBounds(132, 180, 320, 30);
            quantityInput.setVisible(true);

            priceLabel.setText("Price");
            priceLabel.setBounds(35, 220, 54, 30);
            priceLabel.setVisible(true);
            
            priceInput.setText("");
            priceInput.setBounds(132, 220, 100, 30);
            priceInput.setVisible(true);


            bottomLabel.setText("Messages");
            bottomLabel.setBounds(13, 279, 100, 30);
            bottomLabel.setVisible(true);

            outputArea.setVisible(true);
            scrollPane.setBounds(13, 313, 676, 145);
            scrollPane.setVisible(true);
        });

        choiceMenu.add(buyInvestmentChoice);

        JMenuItem sellInvestmentChoice = new JMenuItem("Sell");
        sellInvestmentChoice.setFont(new Font("Calibri", Font.PLAIN, 20));

        sellInvestmentChoice.addActionListener((ActionEvent e) ->
        { 
            userInput = 2;
            nextButton.setVisible(false);
            firstPage.setVisible(false);
            priceInput.setEditable(true);
            outputArea.setText("");
            outputArea.setVisible(true);
            symbolInput.setEditable(true);
            nameInput.setEditable(true);
            priceInput.setEditable(true);

            label.setText("Selling an investment");
            label.setBounds(20, 20, 215, 30);
            label.setVisible(true);
            
            resetButton.setText("Reset");
            resetButton.setBounds(550, 70, 100, 38);
            resetButton.setVisible(true);

            bottomButton.setText("Sell");
            bottomButton.setBounds(550, 175, 100, 38);
            bottomButton.setVisible(true);

            typeLabel.setVisible(false);
            
            type.setVisible(false);

            symbolLabel.setText("Symbol");
            symbolLabel.setBounds(35, 100, 75, 30);
            symbolLabel.setVisible(true);
            
            symbolInput.setText("");
            symbolInput.setBounds(132, 100, 160, 30);
            symbolInput.setVisible(true);

            nameLabel.setVisible(false);
    
            nameInput.setVisible(false);

            quantityLabel.setText("Quantity");
            quantityLabel.setBounds(35, 160, 82, 30);
            quantityLabel.setVisible(true);
            
            quantityInput.setText("");
            quantityInput.setBounds(132, 160, 100, 30);
            quantityInput.setVisible(true);

            priceLabel.setText("Price");
            priceLabel.setBounds(35, 220, 54, 30);
            priceLabel.setVisible(true);
            
            priceInput.setText("");
            priceInput.setBounds(132, 220, 100, 30);
            priceInput.setVisible(true);


            bottomLabel.setText("Messages");
            bottomLabel.setBounds(13, 279, 100, 30);
            bottomLabel.setVisible(true);

            outputArea.setVisible(true);
            scrollPane.setBounds(13, 313, 676, 145);
            scrollPane.setVisible(true);
        });
        choiceMenu.add(sellInvestmentChoice);

        JMenuItem updateInvestmentChoice = new JMenuItem("Update");
        updateInvestmentChoice.setFont(new Font("Calibri", Font.PLAIN, 20));

        updateInvestmentChoice.addActionListener((ActionEvent e) ->
        { 
            userInput = 3;
            firstPage.setVisible(false);
            priceInput.setEditable(true);
            symbolInput.setEditable(false);
            nameInput.setEditable(false);
            indexOfStock = 0;
            priceInput.setEditable(true);

            label.setText("Updating investmentList");
            label.setBounds(20, 20, 215, 30);
            label.setVisible(true);
            
            resetButton.setText("Prev");
            resetButton.setBounds(550, 60, 100, 38);
            resetButton.setVisible(true);

            nextButton.setText("Next");
            nextButton.setBounds(550, 122, 100, 38);
            nextButton.setVisible(true);

            bottomButton.setText("Save");
            bottomButton.setBounds(550, 185, 100, 38);
            bottomButton.setVisible(true);

            typeLabel.setVisible(false);
            
            type.setVisible(false);

            symbolLabel.setText("Symbol");
            symbolLabel.setBounds(35, 100, 75, 30);
            symbolLabel.setVisible(true);
            
            symbolInput.setText("");
            symbolInput.setBounds(132, 100, 160, 30);
            symbolInput.setVisible(true);

            nameLabel.setText("Name");
            nameLabel.setBounds(35, 160, 63, 30);
            nameLabel.setVisible(true);
            
            nameInput.setText("");
            nameInput.setBounds(132, 160, 320, 30);
            nameInput.setVisible(true);

            quantityLabel.setVisible(false);
            
            quantityInput.setVisible(false);

            priceLabel.setText("Price");
            priceLabel.setBounds(35, 220, 54, 30);
            priceLabel.setVisible(true);
            
            priceInput.setText("");
            priceInput.setBounds(132, 220, 100, 30);
            priceInput.setVisible(true);


            bottomLabel.setText("Messages");
            bottomLabel.setBounds(13, 279, 100, 30);
            bottomLabel.setVisible(true);

            outputArea.setText("Please Change the Price to update the Investment then press\nthe Save button.");
            outputArea.setVisible(true);
            scrollPane.setBounds(13, 313, 676, 145);
            scrollPane.setVisible(true);

            Investment investment = investmentList.get(0);
            nameInput.setText(investment.getName());
            symbolInput.setText(investment.getSymbol());
            double outputPrice = investment.getPrice();
            priceInput.setText("" + outputPrice);
        });
        choiceMenu.add(updateInvestmentChoice);

        JMenuItem getGainChoice = new JMenuItem("Get Gain");
        getGainChoice.setFont(new Font("Calibri", Font.PLAIN, 20));

        getGainChoice.addActionListener((ActionEvent e) ->
        { 
            userInput = 4;
            firstPage.setVisible(false);
            nextButton.setVisible(false);
            outputArea.setText("");
            outputArea.setVisible(true);
            symbolInput.setEditable(true);
            nameInput.setEditable(true);

            label.setText("Getting total gain");
            label.setBounds(20, 20, 215, 30);
            label.setVisible(true);

            resetButton.setVisible(false);

            bottomButton.setVisible(false);

            typeLabel.setText("Total gain");
            typeLabel.setBounds(35, 60, 110, 30);
            typeLabel.setVisible(true);
            
            type.setVisible(false);

            symbolLabel.setVisible(false);
            
            symbolInput.setVisible(false);

            nameLabel.setVisible(false);
            
            nameInput.setVisible(false);

            quantityLabel.setVisible(false);

            quantityInput.setVisible(false);

            priceLabel.setVisible(false);
            
            priceInput.setText("");
            priceInput.setEditable(false);
            priceInput.setBounds(162, 60, 150, 30);
            priceInput.setVisible(true);

            bottomLabel.setText("Individual gains");
            bottomLabel.setBounds(13, 100, 180, 30);
            bottomLabel.setVisible(true);

            outputArea.setText("");
            scrollPane.setBounds(13, 130, 676, 315);
            scrollPane.setVisible(true);

            double totalGain = 0;
            double IndividualGain = 0;

            priceInput.setEditable(false);

            StringBuilder stringOutput = new StringBuilder();
            for (Investment investment: investmentList)
            {    //Gain for Stocks
                if (investment.getType() == "stock") 
                {  //stocks has commision
                    IndividualGain = investment.getPrice() * investment.getQuant() - 10 - investment.getBookVal();
                }

                else 
                {  //mutual fund doesnt have commission
                    IndividualGain = investment.getPrice() * investment.getQuant() - investment.getBookVal();
                }

                double roundedIndGain = Math.round(IndividualGain*100.0)/100.0;
                stringOutput.append(investment.getSymbol() + " total profit/loss = " + roundedIndGain + "\n");
                
                totalGain += IndividualGain;
            }
            
            totalGain = Math.round(totalGain * 100.0) / 100.0;

            priceInput.setText("" + totalGain);
            outputArea.setText(stringOutput.toString());

        });

        choiceMenu.add(getGainChoice);

        JMenuItem searchChoice = new JMenuItem("Search");
        searchChoice.setFont(new Font("Calibri", Font.PLAIN, 20));

        searchChoice.addActionListener((ActionEvent e) ->
        { 
            userInput = 5;
            firstPage.setVisible(false);
            priceInput.setEditable(true);
            nextButton.setVisible(false);
            outputArea.setText("");
            outputArea.setVisible(true);
            symbolInput.setEditable(true);
            nameInput.setEditable(true);
            priceInput.setEditable(true);

            label.setText("Searching investmentList");
            label.setBounds(20, 20, 215, 30);
            label.setVisible(true);
            
            resetButton.setText("Reset");
            resetButton.setBounds(550, 70, 100, 38);
            resetButton.setVisible(true);

            bottomButton.setText("Search");
            bottomButton.setBounds(550, 175, 100, 38);
            bottomButton.setVisible(true);

            typeLabel.setVisible(false);
            
            type.setVisible(false);

            symbolLabel.setText("Symbol");
            symbolLabel.setBounds(35, 69, 75, 30);
            symbolLabel.setVisible(true);
            
            symbolInput.setText("");
            symbolInput.setBounds(132, 69, 160, 30);
            symbolInput.setVisible(true);

            nameLabel.setText("Name\nkeywords");
            nameLabel.setBounds(35, 109, 90, 60);
            nameLabel.setVisible(true);
            
            nameInput.setText("");
            nameInput.setBounds(132, 115, 320, 30);
            nameInput.setVisible(true);

            quantityLabel.setText("Low price");
            quantityLabel.setBounds(35, 180, 100, 30);
            quantityLabel.setVisible(true);
            
            quantityInput.setText("");
            quantityInput.setBounds(140, 180, 100, 30);
            quantityInput.setVisible(true);

            priceLabel.setText("High price");
            priceLabel.setBounds(35, 220, 100, 30);
            priceLabel.setVisible(true);
            
            priceInput.setText("");
            priceInput.setBounds(140, 220, 100, 30);
            priceInput.setVisible(true);

            bottomLabel.setText("Search results");
            bottomLabel.setBounds(13, 279, 140, 30);
            bottomLabel.setVisible(true);

            outputArea.setVisible(true);
            scrollPane.setBounds(13, 313, 676, 145);
            scrollPane.setVisible(true);
        });

        choiceMenu.add(searchChoice);

        JMenuItem quitChoice = new JMenuItem("Quit");
        quitChoice.setFont(new Font("Calibri", Font.PLAIN, 20));    //Quits Program and outputs to file if avaible

        quitChoice.addActionListener((ActionEvent e) ->
        { 
            if (fileInput == 0) 
            {
                PrintWriter writer = null;
                try 
                {
                    writer = new PrintWriter (fileNameFromInput);
                } 

                catch (Exception x)
                {
                    System.out.println("Failed to write.");
                }
                
                for ( Investment investment: investmentList ) 
                {
                    writer.println(investment.toString());
                }
                writer.close();
            }
 
            System.exit(0);
        });

        choiceMenu.add(quitChoice);

        JMenuBar bar = new JMenuBar();
        bar.add(choiceMenu);
        setJMenuBar(bar);
    }

    public void actionPerformed(ActionEvent e)
    {       
        int i = 0;

        String actionCommand = e.getActionCommand();


        if (actionCommand.equals("Reset")) 
        {    //User Activates Reset it Clears TextAreas
            symbolInput.setText("");
            nameInput.setText("");
            quantityInput.setText("");
            priceInput.setText("");
            outputArea.setText("");
        }

        if ((userInput == 1) && (actionCommand.equals("Buy"))) 
        {  //Input information for Stock Buy Method
            String type = null;
            String symbol = null;
            String name = null;
            double quant = 0;
            double price = 0;
            double bookVal = 0;
            

            try 
            {
                if (investmentType == 1) 
                {
                    type = "stock";
                }
                else 
                {
                    type = "mutualfund";
                }

                symbol = symbolInput.getText();

                name = nameInput.getText();
                quant = Double.parseDouble(quantityInput.getText());
                price = Double.parseDouble(priceInput.getText());

                int b = 0;
                
                if (quant <= 0) 
                {
                    outputArea.setText("Error! quantity is < 0.");
                    throw new Exception();
                }
                if (price <= 0) 
                {
                    outputArea.setText("Error! price is < 0");
                    throw new Exception();
                }
                String other;
                    
                if (type.equals("stock")) 
                { 
                    other = "mutualfund";
                }
                else 
                {
                    other = "stock";
                }

                for (Investment investment: investmentList) 
                {    //Check if there is overlap
                    String sym = investment.getSymbol();
                    String typ = investment.getType();

                    if ( sym.equals(symbol) && typ.equals(other)) 
                    {
                        throw new Exception();
                    }
                }

                for (Investment investment: investmentList) 
                {    //Update Stocks
                    String sym = investment.getSymbol();
                    if (sym.equals(symbol) && type.equals("stock"))
                    {
                        investment.updateBuy( price, quant );
                        outputArea.setText("Stock Updated");
                        b = 1;  //Stop from creating new stock
                        break;
                    }
                    if (sym.equals(symbol) && type.equals("mutualfund"))
                    {
                        investment.updateBuy( price, quant );
                        outputArea.setText("MutualFund Updated");
                        b = 1;  //Stop from creating new stock
                        break;
                    }
                }
                if (b != 1) 
                {
                    if ( type.equals("stock") ) 
                    {   //input stock into ArrayList stock
                        bookVal = quant * price + 9.99;
                        Stock newStock = new Stock();
                        newStock.set(type, symbol, name, quant, price, bookVal);
                        investmentList.add(newStock); 
                        outputArea.setText("Stock inserted into list");

                        String []eachWordOfName = name.split("[ ]+");   //Hashmap Input
                        
                        for (i = 0; i < eachWordOfName.length; i++) 
                        {
                            String hashInput = eachWordOfName[i].toLowerCase();
                            if (investmentMap.containsKey(hashInput)) 
                            { //update int array
                                ArrayList<Integer> intArrayFromHash = new ArrayList<Integer>();
                                intArrayFromHash = investmentMap.get(hashInput);
                        
                                intArrayFromHash.add(numInvestments); //Adds stock number to hashMap

                                investmentMap.remove(hashInput);    //Gets rid of HashInput

                                investmentMap.put(hashInput, intArrayFromHash);
                            }
                            else 
                            {
                                ArrayList<Integer> intArrayForHash = new ArrayList<Integer>();
                                intArrayForHash.add(numInvestments);  //Assuming new word
                                
                                investmentMap.put(hashInput, intArrayForHash);
                                }
                            }
                        }
                        if ( type.equals("mutualfund") ) 
                        { //input mutualFund in ArrayList mutualFund
                            bookVal = quant * price;
                            MutualFund newMF = new MutualFund();
                            newMF.set(type, symbol, name, quant, price, bookVal);
                            investmentList.add(newMF);
                            outputArea.setText("Fund inserted into list");

                            String []thing = name.split("[ ]+");   //Hashmap Input

                            for (i = 0; i < thing.length; i++) 
                            {
                                String hashInput = thing[i].toLowerCase();
                                if (investmentMap.containsKey(hashInput)) 
                                { //update int array
                                    ArrayList<Integer> intArrayFromHash = new ArrayList<Integer>();
                                    intArrayFromHash = investmentMap.get(hashInput);
                            
                                    intArrayFromHash.add(numInvestments); //Adds stock number to hashMap

                                    investmentMap.remove(hashInput);    //Gets rid of HashInput

                                    investmentMap.put(hashInput, intArrayFromHash);
                                }
                                else 
                                {
                                    ArrayList<Integer> intArrayForHash = new ArrayList<Integer>();
                                    intArrayForHash.add(numInvestments);  //Assuming new word
                                    //System.out.println(intArrayForHash);   //TestCase
                                    investmentMap.put(hashInput, intArrayForHash);
                                }
                            }
                        }   
                        numInvestments++;   //Adds to the number of investmentList
                    }
                
            } 
            catch (Exception a) 
            {
                //type, symbol, name, quant, price, bookVal
                outputArea.setText("Error! Please Follow Guidelines.\nAll prices and Quant > 0.\nNo Mutual Funds or Stocks the same symbol and eachother.");
            }
           
        }

        if ((userInput == 2) && (actionCommand.equals("Sell"))) 
        {

            try
            {

                String symbol = symbolInput.getText();
                double price = Double.parseDouble(priceInput.getText());
                double quant = Double.parseDouble(quantityInput.getText());
                
                int numStockOrFund = 0;
                for (Investment investment: investmentList) 
                {    //Check Investments
                    String sym = investment.getSymbol();
                    if ( sym.equals( symbol )) {
                        if ( quant > investment.getQuant() ) 
                        {
                            throw new Exception();
                        }
                        if (price < 0) {
                            throw new Exception();
                        }
                        investment.updateSell(price, quant);
                        if ( investment.getQuant() == 0 ) 
                        {  //all quant sold
                            //Update HashMap
                            String name = investment.getName();

                            String []eachWordOfName = name.split("[ ]+");
                            for (i = 0; i < eachWordOfName.length; i++) 
                            {
                                String hashInput = eachWordOfName[i].toLowerCase();
                                ArrayList<Integer> intArrayFromHash = new ArrayList<Integer>();
                                intArrayFromHash = investmentMap.get(hashInput);
                                
                                for (int j = 0; j < intArrayFromHash.size(); j++) 
                                {
                                    if (intArrayFromHash.get(j) == numStockOrFund) 
                                    {
                                        intArrayFromHash.remove(j);
                                    }
                                    else if (intArrayFromHash.get(j) > numStockOrFund)
                                    {
                                        intArrayFromHash.set(j, intArrayFromHash.get(j) - 1);   //replaces element
                                    }
                                }

                            } 
                            investmentList.remove (numStockOrFund);
                            break;
                        }
                        break;
                    }
                    numStockOrFund--;
                }
                double roundedGain = Investment.gain;
                roundedGain = Math.round(roundedGain * 100.0) / 100.0;
                outputArea.setText("Sold " + quant + " of " + symbol + " at " + price + "\nTotal Gain = " + roundedGain);
            } 
            catch (Exception k) 
            {
                outputArea.setText("Error! Be Sure to have Quant equal or less\nthan what is avaible. Be sure that all numbers are > 0.\nAnd Investment Exsits");
            }
        }

        if ((userInput == 3)) 
        {    //indexOfStock is what we are currently on 
            if (numInvestments == 0) 
            {
                outputArea.setText("No Investments Currently");
            }
            else 
            {
                try
                {
                    if (actionCommand.equals("Prev")) 
                    { //changes output
                        if (indexOfStock > 0) 
                        {
                            indexOfStock--;
                            outputArea.setText("");
                            Investment investment = investmentList.get(indexOfStock);
                            nameInput.setText(investment.getName());
                            symbolInput.setText(investment.getSymbol());
                            double outputPrice = investment.getPrice();
                            priceInput.setText("" + outputPrice);
                        }
                        else 
                        {
                            outputArea.setText("You have Reached the first stock in list");
                        }
                    }
                    if (actionCommand.equals("Next")) 
                    { //changes output
                        if (indexOfStock < numInvestments - 1) 
                        {
                            indexOfStock++;
                            outputArea.setText("");
                            Investment investment = investmentList.get(indexOfStock);
                            nameInput.setText(investment.getName());
                            symbolInput.setText(investment.getSymbol());
                            double outputPrice = investment.getPrice();
                            priceInput.setText("" + outputPrice);
                        }
                        else 
                        {
                            outputArea.setText("You have Reached the last stock in list");
                        }
                    }
                    if (actionCommand.equals("Save")) 
                    {
                        double theInputedPrice = Double.parseDouble(priceInput.getText());
                        if (theInputedPrice < 0) 
                        {
                            outputArea.setText("Error! Price Must be greater than or equal to 0");
                            throw new Exception();
                        }
                        Investment investment = investmentList.get(indexOfStock);
                        investment.update(theInputedPrice);
                        outputArea.setText("Updated: " + investment.getSymbol() + " to " + theInputedPrice);
                    }
                } 
                catch (Exception t) 
                {
                    outputArea.setText("Error! Please make sure all prices are greater than 0.");
                }
            }
        }

        if ((userInput == 5) && (actionCommand.equals("Search"))) 
        {
            try 
            {
                int nameIsEmpty = 0;
                
                ArrayList <Integer> outputSearchName = new ArrayList <Integer> ();
                if (nameInput.getText().equals("")) 
                {   //No Input for Name
                    nameIsEmpty = 1;
                }
                else 
                {
                    String []var = nameInput.getText().split("[ ]+");
                    for (i = 0; i < var.length; i++)
                    {
                        if (investmentMap.containsKey(var[i])) 
                        {
                            if (outputSearchName.size() == 0) 
                            {
                                outputSearchName = investmentMap.get(var[i]);
                            }
                            else 
                            {
                                ArrayList <Integer> compare = new ArrayList <Integer> ();
                                compare = investmentMap.get(var[i]);
                                outputSearchName.retainAll(compare);    //retains like elements
                            }
                        }
                    }
                }

                int symbolEmpty = 0;
                ArrayList <Integer> outputSearchSymbol = new ArrayList <Integer> ();
                String symbolInputG = symbolInput.getText();
                if ( symbolInputG.equals(""))
                {   //No Input
                    symbolEmpty = 1;
                }
                else 
                {
                    int numThing = 0;
                    for (Investment investment: investmentList) 
                    {
                        if (investment.getSymbol().equals(symbolInputG)) 
                        {
                            outputSearchSymbol.add(numThing);
                        }
                        numThing++;
                    }
                }

                int rangePriceEmpty = 0;
                ArrayList <Integer> outputSearchPrice = new ArrayList <Integer> ();

                if ( priceInput.getText().equals("") && quantityInput.getText().equals("") ) 
                {   //No Input
                    rangePriceEmpty = 1;
                }
                else {   
                    int lowPriceThere = 0;
                    ArrayList <Integer> lowPriceList = new ArrayList <Integer> ();
                    ArrayList <Integer> highPriceList = new ArrayList <Integer> ();
                    int highPriceThere = 0;
                    if (priceInput.getText().equals("") == false) 
                    {  //High Price Input
                        highPriceThere = 1;
                        double highPrice = Double.parseDouble(priceInput.getText());
                        if (highPrice <= 0) 
                        {
                            throw new Exception();
                        }
                        
                        int numStock = 0;
                        for (Investment investment: investmentList) 
                        {
                            if (investment.getPrice() <= highPrice) 
                            {
                                highPriceList.add(numStock);
                                numStock++;
                            }
                        }
                    }

                    
                    if (((quantityInput.getText()).equals("")) == false) 
                    { 
                    
                        lowPriceThere = 1;
                        double lowPrice = Double.parseDouble(quantityInput.getText());
                        if (lowPrice <= 0) 
                        {
                            throw new Exception();
                        }
                        int numStock = 0;
                        for (Investment investment: investmentList) 
                        {

                            if (investment.getPrice() >= lowPrice) 
                            {
                                lowPriceList.add(numStock);
                                numStock++;
                            }
                        }
                    }
                    if (lowPriceThere == 1 && highPriceThere == 1) 
                    {  // Range
                        lowPriceList.retainAll(highPriceList);
                        outputSearchPrice = lowPriceList;
                    }
                    
                    if (lowPriceThere == 1 && highPriceThere == 0) 
                    {  // Lowe Limit
                        outputSearchPrice = lowPriceList;
                    }

                    if (lowPriceThere == 0 && highPriceThere == 1) 
                    {  //Upper Limit
                        outputSearchPrice = highPriceList;
                    }
                }

                StringBuilder stringOutputFromBuilder = new StringBuilder();
                if ((nameIsEmpty == 1) && (symbolEmpty == 1) && (rangePriceEmpty == 1)) 
                {
                    for (Investment investment: investmentList) 
                    {
                        stringOutputFromBuilder.append(investment.toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }
                else if ((nameIsEmpty == 1) && (symbolEmpty == 1)) 
                {    //prints rangePrices
                    for (i = 0; i < outputSearchPrice.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchPrice.get(i)).toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }

                else if ((symbolEmpty == 1) && (rangePriceEmpty == 1)) 
                {    //prints names

                    for (i = 0; i < outputSearchName.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchName.get(i)).toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }

                else if ((nameIsEmpty == 1) && (rangePriceEmpty == 1)) 
                {    //prints symbols
                    for (i = 0; i < outputSearchSymbol.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchSymbol.get(i)).toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }

                else if (nameIsEmpty == 1) 
                {    //compares symbol and rangePrice
                    outputSearchSymbol.retainAll(outputSearchPrice);

                    for (i = 0; i < outputSearchSymbol.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchSymbol.get(i)).toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }
                else if (symbolEmpty == 1) 
                {    //compares name and rangePrice
                    outputSearchName.retainAll(outputSearchPrice);

                    for (i = 0; i < outputSearchName.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchName.get(i)).toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }

                else if (rangePriceEmpty == 1) 
                {    //compares name and symbol
                    outputSearchName.retainAll(outputSearchSymbol);

                    for (i = 0; i < outputSearchName.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchName.get(i)).toString()+ "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }

                else if ((nameIsEmpty == 0) && (rangePriceEmpty == 0) && (symbolEmpty == 0)) 
                {  //compares all parameters
                    outputSearchName.retainAll(outputSearchSymbol);
                    outputSearchName.retainAll(outputSearchPrice);
                    for (i = 0; i < outputSearchName.size(); i++) 
                    {
                        stringOutputFromBuilder.append(investmentList.get(outputSearchName.get(i)).toString() + "\n");
                    }
                    outputArea.setText(stringOutputFromBuilder.toString());
                }

                else 
                {
                    outputArea.setText("No Stocks met crietera :(");
                }
               //if symbol is entered there is only 1 possible option
            } 
            catch (Exception l) 
            {
                outputArea.setText("Error! Please be sure that all prices are > 0\n");
            }

        }
    }
}
