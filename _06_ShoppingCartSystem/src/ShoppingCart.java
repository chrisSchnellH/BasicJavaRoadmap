import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Item> itemList;

    public ShoppingCart() {
        itemList = new ArrayList<>();
    }

    // Add item to shopping cart
    public void addItem(Item item) {
        itemList.add(item);
        System.out.println(item.getName() + " has been added to the shopping cart.");
    }

    // Calculate Price
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Item item : itemList) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    // Display Shoppingcart items
    public void displayItems() {
        if (itemList.isEmpty()) {
            System.out.println("The shopping cart is empty.");
        } else {
            System.out.println("\nItems in the shopping cart:");
            for (Item item : itemList) {
                System.out.println(item.getName() + " - " + item.getPrice() + " Euro");
            }
        }
    }
}
