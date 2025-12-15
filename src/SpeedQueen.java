package src;

/**
 * The submitted file. This is where the methods pertaining to simulation will be. 
 * 
 * @author Vishal Saravanan, Isha Gajera, Alexandra Domanski
 */
public class SpeedQueen {
    private double totalEarnings; // total earnings for the day
    private Customer[] waiting; // array of customers waiting for their turn
    private Customer[] washers; // array of customers currently using washers
    private Customer[] dryers; // array of customers currently using dryers
    private Customer[] finished; // array of customers who have finished their laundry
    private static final int TOTALCUSTOMERS = 20; // maximum number of customers
    private static final int WASHERSAMT = 6; // maximum number of washers
    private static final int DRYERSAMT = 6; // maximum number of dryers

    public SpeedQueen() {
        // Default constructor
    }   

    /**
     * @return the waiting array of Customers.
     */
    public Customer [] getWaitingArray() {
        return waiting;
    }

    /**
     * @return the washers array of Customers.
     */
    public Customer [] getWashersArray() {
        return washers;
    }

    /**
     * @return the dryers array of Customers.
     */
    public Customer [] getDryersArray() {
        return dryers;
    }

    /**
     * @return the finished array of Customers.
     */
    public Customer [] getFinishedArray() {
        return finished;
    }

    /**
     * @return the total Earnings.
     */
    public double getTotalEarnings() {
        return totalEarnings;
    }

    /**
     * Returns the total balance of a Customer based on their RUID.
     * 
     * @return the total balance of the Customer.
     */
    public double getTotalBalance (int RUID)
    {
        double balance = 0.0;
        Customer toSearch = null;
        //No stage is given, thus we must traverse all four stage arrays
        for(int i = 0; i < waiting.length; i++){
            if(waiting[i] != null && waiting[i].getRUID() == RUID){
                toSearch = waiting[i];
                return toSearch.getBalance();
            }
        }
        for(int i = 0; i < washers.length; i++){
            if(washers[i] != null && washers[i].getRUID() == RUID){
                toSearch = washers[i];
                return toSearch.getBalance();
            }
        }
        for(int i = 0; i < dryers.length; i++){
            if(dryers[i] != null && dryers[i].getRUID() == RUID){
                toSearch = dryers[i];
                return toSearch.getBalance();
            }
        }
        for(int i = 0; i < finished.length; i++){
            if(finished[i] != null && finished[i].getRUID() == RUID){
                toSearch = finished[i];
                return toSearch.getBalance();
            }
        }
        return balance;
    }

    /** 
     * Reads information from the input file and store in customer objects while initializing instance variables.
     * Students must enter the full name of the file including .csv at the end.
     * 
     * @param inputFile String representing the name of the input file
     */
    public void createSimulation( String inputFile ) {
        // DO NOT CHANGE OR MODIFY THESE LINES
        StdIn.setFile(inputFile); //opens the inputFile
        StdIn.readLine(); //reads and ignores the first line (header)
        totalEarnings = 0.0;
        waiting = new Customer[TOTALCUSTOMERS];
        washers = new Customer[WASHERSAMT]; 
        dryers = new Customer[DRYERSAMT];
        finished = new Customer[TOTALCUSTOMERS];

        // WRITE YOUR CODE HERE
                
    }
    
    /**
     * Calculates how much it will cost a student to move on to the next stage.
     * 
     * @param RUID         integer representing the RUID of the customer
     * @param currentStage the stage the customer is currently in
     * @param nextStage    the stage the customer will be in next
     * @return the price of washing or drying clothes only if the next stage is that of the washer or dryer. Else, return 0.0.
     */
    public double calculateCost(int RUID, Stage currentStage, Stage nextStage) { 
        //WRITE YOUR CODE HERE

        return 0.0; // TO BE REPLACED BY STUDENTS
    }

    /**
     * Calls calculate cost and checks if the customer’s balance is enough to 
     * Successfully move on to the next stage/ cover that cost.
     * 
     * @param RUID         integer representing the RUID of the customer
     * @param currentStage the stage the customer is currently in
     * @param nextStage    the stage the customer will be in next
     * @return boolean representing whether the balance is sufficient for the cost
     */
    public boolean checkBalance(int RUID, Stage currentStage, Stage nextStage) {
        // WRITE YOUR CODE HERE
        
        return false; // TO BE REPLACED BY STUDENTS
    }

    /** 
     * Updates customer's balance by adding the inputted amount.
     * The amount may be positive or negative.
     * 
     * @param RUID         integer representing the RUID of the customer
     * @param amount       double representing the amount of money that needs to be added to/ subtracted from the customer's balance.
     * @param currentStage the stage the customer is currently in
     * @assumes amount may be positive or negative.
     */
    public void updateBalance(int RUID, double amount, Stage currentStage){
        // WRITE YOUR CODE HERE

    }

    /** 
     * This method finds the first available spot for a Customer to use in the inputted Stage.
     * 
     * @param stage the desired stage to check.
     * @return j such that stage_array[j] = available spot. If no spots are available, return -1. 
     */
    public int findAvailableSpot(Stage stage){ 
        // WRITE YOUR CODE HERE

        return -1; // TO BE REPLACED BY STUDENTS
    } 

    /**
     * Moves a customer from one stage to another, if possible.
     * 
     * @param RUID of the customer to be moved
     * @param currentStage current stage of the customer
     * @param nextStage stage to which the customer is to be moved
     */
    public void moveCustomers(int RUID, Stage currentStage, Stage nextStage){
        // WRITE YOUR CODE HERE

    }

    /**
     * If the daily earnings reach $25, reimburse customers based on their attributes.
     * Customers with both ID and RUExpress get $2, those with only RUExpress get
     * $1, and those with only ID get $0.50.
     * 
     * @return boolean representing whether cashback was applied
     */
    public boolean scarletCashback() {
        // WRITE YOUR CODE HERE

        return false; // TO BE REPLACED BY STUDENTS
    }
}
