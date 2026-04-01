import java.util.*;
public class BookMyStay {
    public static void main(String[] args) {



// Booking class
        class Booking {
            String bookingId;
            String roomId;
            String roomType;
            boolean isCancelled;

            Booking(String bookingId, String roomId, String roomType) {
                this.bookingId = bookingId;
                this.roomId = roomId;
                this.roomType = roomType;
                this.isCancelled = false;
            }
        }

// Main System
        class HotelSystem {

            // Store bookings
            private Map<String, Booking> bookings = new HashMap<>();

            // Inventory (roomType -> count)
            private Map<String, Integer> inventory = new HashMap<>();

            // Stack for rollback (released room IDs)
            private Stack<String> rollbackStack = new Stack<>();

            // Constructor to initialize inventory
            public HotelSystem() {
                inventory.put("Single", 2);
                inventory.put("Double", 2);
            }

            // Booking method
             void bookRoom(String bookingId, String roomType) {
                if (!inventory.containsKey(roomType) || inventory.get(roomType) == 0) {
                    System.out.println("No rooms available for type: " + roomType);
                    return;
                }

                String roomId = roomType + "-" + (inventory.get(roomType));
                inventory.put(roomType, inventory.get(roomType) - 1);

                Booking booking = new Booking(bookingId, roomId, roomType);
                bookings.put(bookingId, booking);

                System.out.println("Booking confirmed: " + bookingId + " Room: " + roomId);
            }

            // Cancellation method (core logic)
            void cancelBooking(String bookingId) {

                // Step 1: Validate booking
                if (!bookings.containsKey(bookingId)) {
                    System.out.println("Invalid booking ID!");
                    return;
                }

                Booking booking = bookings.get(bookingId);

                if (booking.isCancelled) {
                    System.out.println("Booking already cancelled!");
                    return;
                }

                // Step 2: Record rollback (push to stack)
                rollbackStack.push(booking.roomId);

                // Step 3: Restore inventory
                String roomType = booking.roomType;
                inventory.put(roomType, inventory.get(roomType) + 1);

                // Step 4: Update booking status
                booking.isCancelled = true;

                // Step 5: Confirm cancellation
                System.out.println("Booking cancelled successfully: " + bookingId);
                System.out.println("Room released: " + booking.roomId);
            }

            // Show inventory
            void showInventory() {
                System.out.println("Current Inventory: " + inventory);
            }

            // Show rollback stack
            void showRollbackStack() {
                System.out.println("Rollback Stack: " + rollbackStack);
            }
        }

                HotelSystem system = new HotelSystem();

                // Book rooms
                system.bookRoom("B101", "Single");
                system.bookRoom("B102", "Single");

                system.showInventory();

                // Cancel booking
                system.cancelBooking("B102");

                system.showInventory();

                // Try invalid cancellation
                system.cancelBooking("B999");

                // Try duplicate cancellation
                system.cancelBooking("B102");

                // View rollback stack
                system.showRollbackStack();
            }
        }