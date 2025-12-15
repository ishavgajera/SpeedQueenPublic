/**
 * enum class that represents size of load
 * This class defines the different sizes of laundry loads that can be processed in the SpeedQueen simulation.
 * 
 * @author Vishal Saravanan, Isha Gajera, Alexandra Domanski
 */
public enum LoadSize {
    
    /**
     * Enum constants representing the load sizes.
     */
    SMALL("SMALL", 1.00),
    MEDIUM("MEDIUM", 1.25),
    LARGE("LARGE", 1.50);
    private final String size;
    private final double cost;

    /**
     * Constructor for the price of the load.
     *
     * @param size the size of the load.
     * @param The price associated with the load size.
     */
    LoadSize(String size, double cost) {
        this.size = size;
        this.cost = cost;
    }

    /**
     * Gets the size of the load.
     * 
     * @return The size of the load.
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the price of the load size.
     *
     * @return The price of the load size.
     */
    public double getCost() {
        return cost;
    }
}
