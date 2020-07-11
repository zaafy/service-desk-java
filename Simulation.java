import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Simulation.java
 * This class represents a Service Desk and simulates its customer service operation
 * using a queue.
 * @author zaafy
 */
public class Simulation {

    /**
     * The main method of the class.
     * @param args Command line arguments.
     */
    public static void main(String [] args) {
        // maximum size of queue
        int qCapacity = 100;

        // number of simulation minutes
        int simSeconds = 1000;

        int[] randProperties = new int[2];
        // Change these numbers to change the randomness of people coming in.
        // first is multiplier, second is range
        // i.e. multiplier = 10, range = 0 -> random is 0-10 /// multiplier = 20, range = 5 -> random is 5-25
        randProperties[0] = 10;
        randProperties[1] = 0;

        // Run simulation
        simulation(qCapacity, simSeconds, randProperties);
    }

    /**
     * This methods performs a simulation of a Service Desk operation using a queue and
     * prints the results.
     * @param qCapacity The fixed capacity of the queue to be used.
     * @param simSeconds The number of minutes that the simulation should run.
     */
    private static void simulation(int qCapacity, int simSeconds, int[] randProperties) {
        // A queue that will hold and manage objects of type Customer.
        Queue<Customer> line = new PriorityQueue<Customer>(qCapacity);

        Service_Desk desks[]= new Service_Desk[4];
        desks[0] = new Service_Desk();
        desks[1] = new Service_Desk();
        desks[2] = new Service_Desk();
        desks[3] = new Service_Desk();

        // Number of customers that arrived.
        int customers = 0;

        // Number of customers that were served.
        int served = 0;

        // Total time that all the customers waited in the line.
        int lineWait = 0;

        // Simulation
        for (int cycle = 0; cycle < simSeconds; cycle++) {
            for (int i = 0; i < desks.length; i++) {
                if (desks[i].showStatus(i, cycle) == "switch") {
                    int id = desks[i].getCustomerID();
                    int startedAt = desks[i].getStartWorkingTime();
                    System.out.println(ANSI_RED + "Desk number " + i + " finished serving customer at " + cycle  + " second" + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "Customer (arrived at " + id + " second) was served at desk " + i + ", duration of service: " + (cycle - startedAt) + " seconds" + ANSI_RESET);
                }
            }

            if (newCustomer((int)(Math.random() * randProperties[0] + randProperties[1]))) {
                System.out.println(ANSI_GREEN + "New customer came at: " + cycle + ANSI_RESET);
                customers++;
                Customer customer = new Customer();
                customer.set(cycle);
                line.add(customer);
            }

            if (!line.isEmpty()) {
                for (int i = 0; i < desks.length; i++) {
                    if (desks[i].isFree(cycle)) {
                        Customer customer = (Customer) line.remove();
                        int id = customer.when();
                        desks[i].set(cycle, id);
                        int customerWait = cycle - id;
                        lineWait += customerWait;
                        System.out.println(ANSI_PURPLE + "Customer no. " + id + " waited for " + customerWait + " seconds and came to desk " + i + " at time " + cycle + ANSI_RESET);
                        served++;
                        break;
                    }
                }
            }
        }

        // Print the simulation results.
        if (customers > 0) {
            System.out.println("\nTotal Customers: " + customers);
            System.out.println(" Customers served: " + served);
            System.out.println(" Customers waiting: " + line.size());
            System.out.println(" Average wait time: " + (float) lineWait / served + " seconds");
        } else {
            System.out.println("No customers!");
        }
    }

    /**
     * This method decides if a new customer has arrived at each time.
     * @param secPerCust Number of seconds between two customer arrivals.
     * @return true if a new customer has arrived, otherwise false.
     */
    private static boolean newCustomer(int secPerCust) {
        return (Math.random() * secPerCust < 1);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}