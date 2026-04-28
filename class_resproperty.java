public class ResProperty extends Property implements Propable {
    private String type; // F, T, H
    private int bedrooms;
    private int bathrooms;

    // Default constructor
    public ResProperty() {
        super();
        this.type = "";
        this.bedrooms = 0;
        this.bathrooms = 0;
    }

    // Parameterized constructor
    public ResProperty(String ownerName, String contactNumber, int months,
                       String type, int bedrooms, int bathrooms) {
        super(ownerName, contactNumber, months);
        this.type = type;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    // Getters
    public String getType() { return type; }
    public int getBedrooms() { return bedrooms; }
    public int getBathrooms() { return bathrooms; }

    // Rent calculation
    public double calcMonthRent() {
        if (type.equals("F")) return 8000 + (500 * bedrooms);
        else if (type.equals("T")) return 15000 + (1000 * bedrooms);
        else if (type.equals("H")) return 12000 + (800 * bedrooms);
        else return 0;
    }

    // toString
    public String toString() {
        return super.toString() + " " + type + " " + bedrooms + " " + bathrooms + 
               " R" + calcMonthRent();
    }
}
