public class BussProperty extends Property implements Propable {
    private int size; // square meters

    // Default constructor
    public BussProperty() {
        super();
        this.size = 0;
    }

    // Parameterized constructor
    public BussProperty(String ownerName, String contactNumber, int months, int size) {
        super(ownerName, contactNumber, months);
        this.size = size;
    }

    // Getter
    public int getSize() { return size; }

    // Rent calculation
    public double calcMonthRent() {
        return size * 250.0;
    }

    // Insurance calculation
    public double calcMonthInsurance() {
        return size * 25.0; // Example: 25 per 10m² → adjust if needed
    }

    // toString
    public String toString() {
        return super.toString() + " " + size + " R" + calcMonthInsurance() + 
               " R" + calcMonthRent();
    }
}
