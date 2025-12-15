package src;
/**
 * enum class that represents the varying dryer heat temperatures
 * This class defines the different heat settings for the dryer in the SpeedQueen simulation.
 *
 *  @author Vishal Saravanan, Isha Gajera, Alexandra Domanski
 */
public enum DryerTemp {
    
    /**
     * Enum constants representing the dryer heat settings.
     */
    LOW("LOW", 1.00),
    MEDIUM("MEDIUM", 1.25),
    HIGH("HIGH", 1.50);
    private final String temp;
    private final double cost;

    /**
     * Constructor to initialize the dryer heat with a cost.
     * 
     * @param temp the temperature of the dryer.
     * @param cost The cost associated with the dryer heat setting.
     */
    DryerTemp(String temp, double cost) {
        this.temp = temp;
        this.cost = cost;
    }

    /**
     * Gets the temp of the dryer.
     * 
     * @return The temp of the dryer.
     */
    public String getTemp() {
        return temp;
    }

    /**
     * Gets the cost of the dryer heat setting.
     *
     * @return The cost of the dryer heat setting.
     */
    public double getCost() {
        return cost;
    }
}
