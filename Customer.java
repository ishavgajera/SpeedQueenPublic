/**
 * Takes the data from the input CSV files and places it into one object, with a class attribute for each read-in value. Represents the customer.
 * 
 * @author Vishal Saravanan, Isha Gajera, Alexandra Domanski
 */

public class Customer {
    
    private String name; // Customer's name
    private int RUID; // Customer's unique ID
    private double balance; // Customer's balance
    private Laundry laundObj; // Customer's laundry object
    private Stage stage; // Current stage of the customer in the laundry process
    private boolean idCard; // Whether the customer has their ID card
    private boolean RUExpress; // Whether the customer has RU Express
    private boolean washed;  // Whether the customer has washed their clothes
    private boolean dried;   // Whether the customer has dried their clothes
    

    /**
     * Constructor to initialize a Customer object with a unique ID, initial balance, ID card, and RUExpress.
     * 
     * @param name The customer's name.
     * @param RUID The unique ID for the customer.
     * @param balance The initial balance of the customer.
     * @param size The size of the customer's load of laundry.
     * @param temp The dryer temperature setting for the customer's laundry.
     * @param idCard Whether the customer has their ID card.
     * @param RUExpress Whether the customer has RU Express.
     */
    public Customer(String name, int RUID, double balance, String size, 
    String temp, boolean idCard, boolean RUExpress) {
        this.name = name;
        this.RUID = RUID;
        this.balance = balance;
        LoadSize loadSize = null; 
        if(size.equals("SMALL")) {
            loadSize = LoadSize.SMALL;
         } else if (size.equals("MEDIUM")) {
            loadSize = LoadSize.MEDIUM;
         } else {
            loadSize = LoadSize.LARGE;
        }
        DryerTemp dryerTemp = null;
        if(temp.equals("LOW")) {
            dryerTemp = DryerTemp.LOW;
         } else if (temp.equals("MEDIUM")) {
            dryerTemp = DryerTemp.MEDIUM;
         } else {
            dryerTemp = DryerTemp.HIGH;
        }
        this.laundObj = new Laundry(loadSize, dryerTemp);
        this.stage = Stage.WAITING; // Initial stage is waiting
        this.idCard = idCard; 
        this.RUExpress = RUExpress;
        this.washed = false; // Initially not washed
        this.dried = false; // Initially not dried
    }

    /**
     * @return customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return customer's RUID
     */ 
    public int getRUID() {
        return RUID;
    }

    /**
     * @return customer's balance
     */ 
    public double getBalance() {
        return balance;
    }

     /**
     * @return customer's laundryObj
     */ 
    public Laundry getLaundry() {
        return laundObj;
    }

     /**
     * @return customer's stage
     */ 
    public Stage getStage() {
        return stage;
    }

     /**
     * @return whether customer has their ID card
     */ 
    public boolean hasID() {
        return idCard;
    }

     /**
     * @return whether customer has RU Express
     */ 
    public boolean hasRUExpress() {
        return RUExpress;
    }

     /**
     * @return whether customer has already washed their clothes
     */ 
    public boolean hasWashed() {
        return washed;
    }

     /**
     * @return whether customer has already dried their clothes
     */ 
    public boolean hasDried() {
        return dried;
    }

    /**
     * changes new balance
     * 
     * @param new balance
     */ 
    public void changeBalance(double newBalance) {
        this.balance += newBalance;
    }

    /**
     * Sets/updates stage
     * 
     * @param new stage
     */
    public void setStage(Stage newStage) {
        this.stage = newStage;
    }

     /**
     * Sets/updates whether customer has washed their laundry
     * 
     * @param whether washed
     */ 
    public void setWashed(boolean updateWashed) {
        this.washed = updateWashed;
    }

     /**
     * Sets/updates whether customer has dried their laundry
     * 
     * @param whether dried
     */ 
    public void setDried(boolean updateDried) {
        this.dried = updateDried;
    }

}
