package src;
/**
 * Represents the stages of a laundry process in a washing machine simulation.
 * This enum class defines the different stages that a customer goes through
 * during their laundry experience at SpeedQueen.
 * 
 * @author Vishal Saravanan, Isha Gajera, Alexandra Domanski
 */
public enum Stage {
    
    /**
     * Enum constants representing the stages of the laundry process.
     */
    WAITING(1), // Customer is waiting for their turn
    WASHER(2), // Customer is currently using a washing machine
    DRYER(3), // Customer is currently using a dryer
    FINISHED(4) // Customer has completed their laundry process
    ;
    private final int stageNumber;

    /**
     * Constructor to initialize the stage with a stage number.
     *
     * @param stageNumber The number representing the stage.
     */
    Stage(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    /**
     * Gets the stage number of the current stage.
     *
     * @return The stage number.
     */
    public int getStageNumber() {
        return stageNumber;
    }
   
    /**
     * Gets the name of the stage.
     *
     * @return The name of the stage.
     */
    @Override
    public String toString() {
        switch (this) {
            case WAITING:
                return "Waiting";
            case WASHER:
                return "Washer";
            case DRYER:
                return "Dryer";
            case FINISHED:
                return "Finished";
            default:
                return "Unknown Stage";
        }
    }
}
