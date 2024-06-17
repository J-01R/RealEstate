import java.util.Scanner;
import java.util.Random;


/**
 * Name: Jonatan Rassekhnia
 * IDE : Eclipse 
 * Exam 2022-06-02
 * Professor Sandeep
 * Lulea University 
 * 
 * @author JohnnyDev
 *
 */
public class RealEstateRasjon0 
{
	   static Scanner userInput = new Scanner(System.in);
	   static String[][] bid = new String[200][5]; //Name, Address, Bid Amount, Quit Y/N  
	   static String[][] object = new String[100][5]; //Name, Amount, Bidding ID 
	   final static String ENDED = "Y"; 
	   final static String ONGOING = "N"; 

	   public static void main(String[] args)
	   {
	      while (true)
	      {
	         showMenu();
	         chooseFromMenu();

	      }
	   }

	   /**
	    * Prints out the Menu
	    */
	   private static void showMenu()
	   {
	      System.out.printf("#LTU REAL ESTATE");
	      System.out.printf("Choose from the following menu option: \n");
	      System.out.printf("1. Register new object\n");
	      System.out.printf("2. Register Bid/User\n");
	      System.out.printf("3. End bidding process\n");
	      System.out.printf("4. Print bidding history for object\n");
	      System.out.printf("5. Print all sold Objects\n");
	      System.out.printf("6. Print all sold Objects(by price)\n");
	      System.out.printf("q. End program\n");
	   }

	   /**
	    * 
	    * Switch Board for declaring different options in the menu
	    */
	   private static void chooseFromMenu()
	   {
	      String choice = stringInput("Type your Menu Choice: ");
	      switch (choice)
	      {
	      case "1":
	         String address = stringInput("Enter property's address: ");
	         String type = stringInput("Enter property's type (Apartment, House or Commerical: ");
	         String bidAmount = String.valueOf(intInput("Type in Starting bid: "));
	         String price = String.valueOf(1000000000);
	         String propertyName = stringInput("Enter Bidder User Name:");
	    	 String reBidinput = stringInput("Enter Higest Amount:");
	         if (Integer.valueOf(bidAmount) > 1 && Integer.valueOf(bidAmount) <= 100000000 && !(address.equals("")))
	         {
	            addNewRegister(address, type, bidAmount,price, propertyName, reBidinput);
	         } else
	         {
	            System.out.printf("We need the correct information to be typed");
	         }
	         break;
	      case "2":
	    	
	         printBiddingHistoryInfo();
	         break;
	      case "3":
	         startAbid();
	         break;
	      case "4":
	         printBiddersInfo();
	         break;
	      case "5":
	         String soldObjects = stringInput("print all Sold Objects ");
	         endAuction(soldObjects);
	         break;
	      case "6":
	         printBidList();
	         break;
	      case "q":
	         System.exit(0);
	      default:
	         System.out.printf("Menu Option Does not Exist\n");
	         break;
	      }
	   }

	   /**
	    * Prints Bid List, with every other print information
	    * from the switch board to every object created
	    * 
	    */
	   private static void printBiddersInfo()
	   {
		   System.out.printf("Bidder Price Accepted \n");
	
		   for (int i = 0; i < object.length; i++)
		      {
		         if (!(object[i][0] == null))
		         {
		            System.out.printf("%-5s %-10s %-3s %-5s\n", object[i][3], object[i][1], object[i][1], object[i][2]);
		         }
		      }
		   }
	   
		   
	   

	   private static void printBidList()
	   {
		   {
		      System.out.printf("ID   Address    Type  Asking-price Sold-For  \n");
		      for (int i = 0; i < object.length; i++)
		      {
		         if (!(object[i][0] == null))
		         {
		            System.out.printf("%-5s %-10s %-3s %-5s\n", object[i][2], object[i][0], object[i][1], object[i][4]);
		         }
		      }
		   }
	   }

	   /**
	    * Closes the Auction
	    * 
	    * @param projectToEnd
	    */
	   private static void endAuction(String auctionToEnd)
	   {
		      System.out.printf("IDNr   Address Type Asking price Highest Price \n");
		      for (int i = 0; i < object.length; i++)
		      {
		         if (!(object[i][0] == null))
		         {
		            System.out.printf("%-5s %-10s %-3s %-5s %-4s\n", object[i][2], object[i][0], object[i][0], object[i][1], object[i][2], object[i][4]);
		         }
		      }
		   }

	   /**
	    *Method 
	    *for the slots to be filled
	    *the amount 
	    *checks if the price exists 
	    */
	   private static void startAbid()
	   {
	      int SlotToFill = findNextEmptySlot();
	      System.out.printf("Starting Price: ");
	      String registerBid = stringInput();
	      boolean Exists = checkIfPriceExists(registerBid);
	      String amount = "";
	      String userID = "";
	      String address = "";

	      if (!registerBid.equals("q") && !Exists)
	      {
	         while (!amount.equalsIgnoreCase("q") && !userID.equalsIgnoreCase("q"))
	         {
	            userID = stringInput("User ID: ");
	            if (!userID.equalsIgnoreCase("q"))
	            {
	               amount = stringInput("Amount: ");
	            }
	            if (!amount.equalsIgnoreCase("q"))
	            {
	               bid[SlotToFill][0] = registerBid; 
	               bid[SlotToFill][1] = bid[SlotToFill][1] + " " + userID + " " + amount + " " + address;
	               bid[SlotToFill][2] = "";
	               bid[SlotToFill][3] = ONGOING;
	            }
	         }
	      } else
	      {
	         System.out.printf("No A\n");
	      }
	   }

	   /**
	    * Checks if the price exists
	    * suppose not be a duplicate 
	    * 
	    * @param Price
	    * @return Duplication
	    */
	   private static boolean checkIfPriceExists(String price)
	   {
	      for (int i = 0; i < bid.length; i++)
	      {
	         if (!(bid[i][0] == null))
	         {
	            if (bid[i][0].equalsIgnoreCase(price))
	            {
	               System.out.printf("Price already exists\n");
	               return true;
	            }
	         }
	      }
	      return false;
	   }

	   /**
	    * Finds the next empty slot
	    * 
	    * @return slot counter/200 open
	    */
	   private static int findNextEmptySlot()
	   {
	      int usedSlotsCounter = 0;
	      for (int i = 0; i < bid.length; i++)
	      {
	         if (bid[i][0] == null)
	         {
	            return usedSlotsCounter;
	         }
	         usedSlotsCounter++;
	      }
	      // 200 slots open
	      return 0;
	   }
	   

	   /**
	    *Adding information
	    *a loop with the slots to fill
	    *name
	    *amount
	    *address
	 * @param reBidinput 
	 * @param name2 
	    * 
	    * @param name,id, address, amount
	    * @param slots,Info
	    */
	   private static void addNewRegister(String name, String address, String amount, String price, String propertyName, String reBidinput)
	   {
	      int slotToFill = findNextEmptySlots();
	      boolean uniqueId = false;
	      object[slotToFill][0] = name;
	      object[slotToFill][0] = address;
	      object[slotToFill][1] = amount;
	      object[slotToFill][2] = price;
	      object[slotToFill][3] = propertyName;
	      object[slotToFill][4] = reBidinput;
	      
	      String randomNumberAsInt = "";
	      int IdCounter;
	      while (!uniqueId)
	      {
	         IdCounter = 0;
	         randomNumberAsInt = String.valueOf(randomNumber(1, 10000));
	         for (int i = 0; i < object.length; i++)
	         {
	            if (!(object[i][2] == null))
	            {
	               if (object[i][2].equals(randomNumberAsInt))
	               {
	                  IdCounter++;
	               }
	            }
	         }
	         if (IdCounter == 0)
	         {
	            uniqueId = true;
	         }
	      }
	      object[slotToFill][2] = randomNumberAsInt;
	     
	      
	   }

	   /**
	    * looking for empty slots
	    * 
	    * @return
	    */
	   private static int findNextEmptySlots()
	   {
	      int usedSlotsCounter = 0;
	      for (int i = 0; i < object.length; i++)
	      {
	         if (object[i][0] == null)
	         {
	            return usedSlotsCounter;
	         }
	         usedSlotsCounter++;
	      }
	      // if we use 100 slots
	      return 0;
	   }

	   /**
	    * Type your name first
	    * Prints out the information
	    * Ids
	    * property type
	    * Amount
	 * @param name 
	 * @param reBidinput 
	    * 
	    */
	   private static void printBiddingHistoryInfo()
	   {
	      System.out.printf("IDNr   Namn    Amount  \n");
	      for (int i = 0; i < object.length; i++)
	      {
	         if (!(object[i][0] == null))
	         {
	            System.out.printf("%-5s %-10s %-3s\n", object[i][2], object[i][0], object[i][1]);
	         }
	      }
	   }

		   
	   

	   /**
	    * Recieves a text string
	    * prints the load input 
	    * from the user 
	    * pretty much the info 
	    * of the return on the result 
	    * 
	    * @param textToPrint
	    * @return
	    */
	   public static String stringInput(String textToPrint)
	   {
	      System.out.printf("%s", textToPrint);
	      String tempString = "";
	      userInput = new Scanner(System.in);
	      tempString = userInput.nextLine();
	      return tempString;
	   }

	   /**
	    *Receives input from the user
	    * @param String input
	    * @return from user 
	    */
	   public static String stringInput()
	   {
	      String tempString = "";
	      userInput = new Scanner(System.in);
	      tempString = userInput.nextLine();
	      return tempString;
	   }

	   /**
	    * Receives a txt string 
	    * and prints it
	    * you do not have to write in
	    * 
	    * @param textToPrint
	    * @return
	    */
	   public static int intInput(String textToPrint)
	   {
	      System.out.printf("%s", textToPrint);
	      int tempInt = 0;
	      userInput = new Scanner(System.in);
	      while (tempInt > -1)
	      {
	         if (userInput.hasNextInt())
	         {
	            tempInt = userInput.nextInt();
	            return Math.abs(tempInt);

	         } else
	         {
	            userInput.next();
	            System.out.printf("Please type a full Amount");
	         }
	      }
	      
	      return -1;
	   }

	   /**
	    * creation of Random Number
	    * (inkluderande).
	    * 
	    * @param lowestNumber
	    * @param highestNumber
	    * @return
	    */
	   public static int randomNumber(int lowestNumber, int highestNumber)
	   {
	      Random number = new Random();
	      return number.nextInt(highestNumber - lowestNumber + 1) + lowestNumber;
	   }
	}
