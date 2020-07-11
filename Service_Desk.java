/**
 * Service_Desk.java
 * This class represents a service desk.
 *
 * @author zaafy
 */
public class Service_Desk {
//    boolean isFree; // Flag that shows if desk is busy.
    int processTime; // Time duration that the desk will need to serve the customer.
    int startWorkingTime; // Time at which desk started servicing customer.
    String currentStatus = "free";
    int customerID = 0;

    /**
     * Constructor
     */
    public Service_Desk() {
        startWorkingTime = processTime = -1;
    }

    /**
     * Set the start time of serving a customer.
     * @param when Time point
     */
    public void set(int when, int customerArrival) {
        startWorkingTime = when;
        customerID = customerArrival;

        // We set the processing time as a random integer between 5 and 15.
        processTime = (int)(Math.random() * 10 + 10);
        currentStatus = "busy";
    }

    public boolean isFree(int time) {
        return (startWorkingTime + processTime < time);
    }

    public boolean justFinished(int time) {
        return (startWorkingTime + processTime == time);
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getStartWorkingTime() {
        return startWorkingTime;
    }


    public String showStatus(int id, int cycle) {
        if (isFree(cycle)) {
            currentStatus = "free";
        }
        if (justFinished(cycle)) {
            currentStatus = "switch";
        }
        return currentStatus;
//        System.out.println("Desk nr. " + id + " is currently " + currentStatus);
    }
}