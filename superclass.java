public class Property {
    // Common fields
    private String ownerName;
    private String contactNumber;
    private int months;

    // Default constructor
    public Property() {
        this.ownerName = "";
        this.contactNumber = "";
        this.months = 0;
    }

    // Parameterized constructor
    public Property(String ownerName, String contactNumber, int months) {
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
        this.months = months;
    }

    // Getters
    public String getOwnerName() { return ownerName; }
    public String getContactNumber() { return contactNumber; }
    public int getMonths() { return months; }

    // toString
    public String toString() {
        return ownerName + " " + contactNumber + " " + months;
    }
}
