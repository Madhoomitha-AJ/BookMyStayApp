import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
public class BookMyStay {
    public static void main(String[] args) {
        class InventoryService {
            private final Map<String, Integer> inventory = new HashMap<>();
            private final Map<String, Set<String>> allocatedRooms = new HashMap<>();
            private final Lock lock = new ReentrantLock();

             public InventoryService() {
                inventory.put("DELUXE", 2);
                inventory.put("STANDARD", 1);

                allocatedRooms.put("DELUXE", new HashSet<>());
                allocatedRooms.put("STANDARD", new HashSet<>());
            }

            String allocateRoom(String roomType) {
                lock.lock(); // 🔒 ensures atomic operation
                try {
                    int available = inventory.getOrDefault(roomType, 0);

                    if (available <= 0) {
                        return null;
                    }

                    String roomId = generateRoomId(roomType);

                    // Ensure uniqueness
                    Set<String> rooms = allocatedRooms.get(roomType);
                    while (rooms.contains(roomId)) {
                        roomId = generateRoomId(roomType);
                    }

                    rooms.add(roomId);
                    inventory.put(roomType, available - 1);

                    return roomId;

                } finally {
                    lock.unlock();
                }
            }

            private String generateRoomId(String roomType) {
                return roomType + "-" + UUID.randomUUID().toString().substring(0, 5);
            }

            public void printStatus() {
                System.out.println("\nFinal Inventory:");
                for (String type : inventory.keySet()) {
                    System.out.println(type + " Available: " + inventory.get(type));
                    System.out.println(type + " Allocated: " + allocatedRooms.get(type));
                }
            }
        }

        class BookingService implements Runnable {
            private final InventoryService inventoryService;
            private final String roomType;
            private final int requestId;

            public BookingService(InventoryService inventoryService, String roomType, int requestId) {
                this.inventoryService = inventoryService;
                this.roomType = roomType;
                this.requestId = requestId;
            }

            @Override
            public void run() {
                String roomId = inventoryService.allocateRoom(roomType);

                if (roomId != null) {
                    System.out.println("Request " + requestId + " CONFIRMED → Room: " + roomId);
                } else {
                    System.out.println("Request " + requestId + " FAILED → No rooms available");
                }
            }
        }

    }
}

