import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing a generic Room in the hotel.
 * Defines common attributes and behavior.
 *
 * @author YourName
 * @version 1.0
 */
abstract class Room {

    private int beds;
    private double size;
    private double price;

    public Room(int beds, double size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public int getBeds() {
        return beds;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getRoomType();

    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: $" + price);
    }
}

/**
 * Single Room implementation
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 200, 100);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

/**
 * Double Room implementation
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 350, 180);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

/**
 * Suite Room implementation
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 600, 400);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

/**
 * Manages centralized room availability using HashMap.
 * Acts as the single source of truth.
 */
class RoomInventory {

    private Map<String, Integer> availabilityMap;

    public RoomInventory() {
        availabilityMap = new HashMap<>();

        // Initialize availability
        availabilityMap.put("Single Room", 5);
        availabilityMap.put("Double Room", 3);
        availabilityMap.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    // (Not used in search, but exists for future booking use case)
    public void updateAvailability(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("=== Room Inventory ===");
        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

/**
 * Handles read-only search operations.
 * Displays only available rooms without modifying inventory.
 */
class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void displayAvailableRooms() {

        System.out.println("=== Available Rooms ===");

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        for (Room room : rooms) {

            String roomType = room.getRoomType();
            int available = inventory.getAvailability(roomType);

            // Show only available rooms
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Units: " + available);
                System.out.println();
            }
        }
    }
}

/**
 * Application entry point.
 * Simulates a guest viewing available rooms.
 */
public class BookMyStayApp {
    public static void main(String[] args){
        // Initialize inventory (state holder)
        RoomInventory inventory = new RoomInventory();

        // Initialize search service (read-only)
        SearchService searchService = new SearchService(inventory);

        // Guest views available rooms
        searchService.displayAvailableRooms();

        System.out.println("Search completed. Application terminated.");
    }
}