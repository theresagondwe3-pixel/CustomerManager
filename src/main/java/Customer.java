public class Customer {
    private final String name;
    private final String province;

    /**
     * Creates a new customer.
     *
     * @param name     the customer's full name
     * @param province the province where the customer lives
     */
    public Customer(String name, String province) {
        this.name = name;
        this.province = province;
    }

    /** @return the customer's name */
    public String getName() {
        return name;
    }

    /** @return the customer's province */
    public String getProvince() {
        return province;
    }
}