//==============================super class======================================

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return name + " - R" + price + " (" + quantity + " in stock)";
    }
}


//==============================point of sale class==================================

import java.util.*;

public class PointOfSale {
    private ArrayList<Item> stock;
    private ArrayList<Item> soldItems;
    private double totalSales;

    public PointOfSale() {
        stock = new ArrayList<>();
        soldItems = new ArrayList<>();
        totalSales = 0.0;
    }

    // Add stock
    public void addStock(String name, double price, int quantity) {
        stock.add(new Item(name, price, quantity));
    }

    // Show items in stock
    public void showItems() {
        System.out.println("=== Stock Items ===");
        for (Item i : stock) {
            System.out.println(i);
        }
    }

    // Purchase item
    public void purchase(String name, int quantity) {
        for (Item i : stock) {
            if (i.getName().equalsIgnoreCase(name)) {
                if (i.getQuantity() >= quantity) {
                    double bill = i.getPrice() * quantity;
                    totalSales += bill;
                    i.setQuantity(i.getQuantity() - quantity);

                    soldItems.add(new Item(i.getName(), i.getPrice(), quantity));
                    System.out.println("Purchased " + quantity + " " + name + " for R" + bill);
                } else {
                    System.out.println("Not enough stock for " + name);
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }

    // Show sold items
    public void showSoldItems() {
        System.out.println("=== Sold Items ===");
        for (Item i : soldItems) {
            System.out.println(i);
        }
    }

    // Show total sales
    public void showTotalSales() {
        System.out.println("Total Sales: R" + totalSales);
    }

  public void showItems(JTextArea area) {
    for (Item i : stock) {
        area.append(i.toString() + "\n");
    }
}

public void showSoldItems(JTextArea area) {
    for (Item i : soldItems) {
        area.append(i.toString() + "\n");
    }
}

// Add delete functionality
public void deleteItem(String name) {
    stock.removeIf(i -> i.getName().equalsIgnoreCase(name));
}

// Getter for total sales
public double getTotalSales() {
    return totalSales;
}

}
//=====================POS GUI ===================================

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class POSGUI extends JFrame {
    private PointOfSale pos;
    private JTextArea displayArea;
    private JLabel totalLabel;

    public POSGUI() {
        pos = new PointOfSale();

        // Frame setup
        setTitle("Simple Point of Sale");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Total sales label
        totalLabel = new JLabel("Total Bill is: 0.0");
        add(totalLabel, BorderLayout.SOUTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        JButton addStockBtn = new JButton("Add Stock");
        JButton purchaseBtn = new JButton("Purchase");
        JButton totalSaleBtn = new JButton("Total Sale");
        JButton addItemBtn = new JButton("Add Item");
        JButton deleteItemBtn = new JButton("Delete Item");
        JButton showItemsBtn = new JButton("Show Items");
        JButton soldItemsBtn = new JButton("Sold Items");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addStockBtn);
        buttonPanel.add(purchaseBtn);
        buttonPanel.add(totalSaleBtn);
        buttonPanel.add(addItemBtn);
        buttonPanel.add(deleteItemBtn);
        buttonPanel.add(showItemsBtn);
        buttonPanel.add(soldItemsBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.NORTH);

        // Button actions
        addStockBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter item name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
            int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
            pos.addStock(name, price, qty);
            displayArea.append("Added stock: " + name + "\n");
        });

        purchaseBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter item name to purchase:");
            int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
            pos.purchase(name, qty);
            totalLabel.setText("Total Bill is: " + pos.getTotalSales());
        });

        totalSaleBtn.addActionListener(e -> {
            displayArea.append("Total Sales: R" + pos.getTotalSales() + "\n");
        });

        addItemBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter new item name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
            int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
            pos.addStock(name, price, qty);
            displayArea.append("Item added: " + name + "\n");
        });

        deleteItemBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter item name to delete:");
            pos.deleteItem(name);
            displayArea.append("Deleted item: " + name + "\n");
        });

        showItemsBtn.addActionListener(e -> {
            displayArea.append("=== Stock Items ===\n");
            pos.showItems(displayArea);
        });

        soldItemsBtn.addActionListener(e -> {
            displayArea.append("=== Sold Items ===\n");
            pos.showSoldItems(displayArea);
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new POSGUI().setVisible(true);
        });
    }
}

//==================test class===================================
public class TestPOS {
    public static void main(String[] args) {
        PointOfSale pos = new PointOfSale();

        // Add initial stock
        pos.addStock("Bread", 15.0, 50);
        pos.addStock("Milk", 20.0, 30);
        pos.addStock("Eggs", 2.5, 100);

        // Show stock
        pos.showItems();

        // Simulate purchases
        pos.purchase("Bread", 5);
        pos.purchase("Milk", 2);
        pos.purchase("Eggs", 12);

        // Show sold items
        pos.showSoldItems();

        // Show updated stock
        pos.showItems();

        // Show total sales
        pos.showTotalSales();
    }
}
