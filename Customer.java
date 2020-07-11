/**
 * Customer.java
 * This class represents a service desk customer.
 *
 * @author zaafy
 */
public class Customer implements Comparable<Customer> {
    int arrive; // Time point that the customer arrived.

    /**
     * Constructor
     */
    public Customer() {
        arrive = 0;
    }

    /**
     * Set the arrival time point of the customer.
     * @param when Time point
     */
    public void set(int when) {
        arrive = when;
    }

    /**
     * @return the arrival time point of the customer.
     */
    public int when() {
        return arrive;
    }

    @Override
    public int compareTo(Customer other) {
        return Integer.compare(this.arrive, other.arrive);
    }
}