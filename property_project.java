//Super class

public class Property {
  // Common fields
  private String ownerName;
  private String contactNumber;
  private int months;

  public Property() {
      this.ownerName = "";
      this.contactNumber = "";
      this.months = 0;
  }

  public Property(String ownerName, String contactNumber, int months) {
      this.ownerName = ownerName;
      this.contactNumber = contactNumber;
      this.months = months;
  }

  // Getters
  public String getOwnerName() { return ownerName; }
  public String getContactNumber() { return contactNumber; }
  public int getMonths() { return months; }


  public String toString() {
      return ownerName + " " + contactNumber + " " + months;
  }
}

//Res Property class

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

//================== Buss property class ===========================

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

//===========================Interface - propable===========================

public interface Propable {
    double calcMonthRent();
}
 //==========================file class======================================

import java.io.*;
import java.util.*;

public class FileClass {
    private ArrayList<Propable> properties = new ArrayList<>();

    public void readFile() {
        try {
            Scanner sc = new Scanner(new File("properties.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("#");

                if (parts[0].equals("R")) {
                    properties.add(new ResProperty(parts[1], parts[2],
                        Integer.parseInt(parts[3]), parts[4],
                        Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
                } else if (parts[0].equals("B")) {
                    properties.add(new BussProperty(parts[1], parts[2],
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public ArrayList<Propable> getProperties() {
        return properties;
    }

    public void writeTownHToFile() {
        try {
            PrintWriter pw = new PrintWriter("townhouses.txt");
            for (Propable p : properties) {
                if (p instanceof ResProperty) {
                    ResProperty r = (ResProperty) p;
                    if (r.getType().equals("T")) {
                        pw.println(r.getOwnerName() + " " + r.getContactNumber() + 
                                   " R" + r.calcMonthRent());
                    }
                }
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

 //============================test class (main)================================

import java.util.*;

public class TestProperty {
    public static void displayResidential(ArrayList<Propable> list) {
        System.out.println("=== Residential Properties ===");
        for (Propable p : list) {
            if (p instanceof ResProperty) {
                System.out.println(p.toString());
            }
        }
    }

    public static void displayBusiness(ArrayList<Propable> list) {
        System.out.println("=== Business Properties ===");
        for (Propable p : list) {
            if (p instanceof BussProperty) {
                System.out.println(p.toString());
            }
        }
    }

    public static void main(String[] args) {
        FileClass fc = new FileClass();
        fc.readFile();
        ArrayList<Propable> props = fc.getProperties();

        displayResidential(props);
        displayBusiness(props);

        fc.writeTownHToFile();
    }
}


