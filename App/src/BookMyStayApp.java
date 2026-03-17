import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    // Centralized data structure for availability
    private Map<String, Integer> availabilityMap;

    /**
     * Constructor initializes room availability
     */
    public RoomInventory() {
        availabilityMap = new HashMap<>();

        // Initial inventory setup
        availabilityMap.put("Single Room", 5);
        availabilityMap.put("Double Room", 3);
        availabilityMap.put("Suite Room", 2);
    }

    /**
     * Get availability for a specific room type
     */
    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    /**
     * Update availability for a specific room type
     */
    public void updateAvailability(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    /**
     * Display full inventory state
     */
    public void displayInventory() {
        System.out.println("=== Room Inventory ===");

        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}


public class BookMyStayApp {
    public static void main(String[] args){
        // Initialize inventory (Single Source of Truth)
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        System.out.println();

        // Retrieve availability
        int singleRooms = inventory.getAvailability("Single Room");
        System.out.println("Single Room Availability: " + singleRooms);

        System.out.println();

        // Update availability (simulate booking)
        System.out.println("Booking 1 Single Room...");
        inventory.updateAvailability("Single Room", singleRooms - 1);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication terminated successfully.");
    }
}
