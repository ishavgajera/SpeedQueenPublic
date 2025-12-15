package src;
/**
 * Class that represents a load of laundry. 
 * 
 * @author Vishal Saravanan, Isha Gajera, Alexandra Domanski
 */
public class Laundry {
    
    /**
     * size: LoadSize
     * dryerTemp: DryerTemp
     */
    private LoadSize size;     
    private DryerTemp dryerTemp;

    /**
     * Constructor to initialize a Laundry object.
     */
    public Laundry(LoadSize size, DryerTemp temperature) {
        this.size = size; 
        this.dryerTemp = temperature;
    }

    /**
     * Gets the size of the load.
     *
     * @return The size of the load size.
     */
    public LoadSize getLoadSize(){
        return this.size;
    }

    /**
     * Sets the size of the load.
     */
    public void setLoadSize(LoadSize inSize){
        this.size = inSize;
    }

    /**
     * Gets the temperature of the dryer.
     *
     * @return The dryer temperature.
     */
    public DryerTemp getDryerTemp(){
        return this.dryerTemp;
    }

     /**
     * Sets the temperature of the dryer.
     */
    public void setDryerTemp(DryerTemp inDryerTemp){
        this.dryerTemp = inDryerTemp;
    }
}
