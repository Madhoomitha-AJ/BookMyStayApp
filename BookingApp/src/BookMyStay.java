import java.util.*;

import java.io.*;
public class BookMyStay {
    public static void main(String[] args) {



// ---------------------- BOOKING CLASS ----------------------
        class Booking implements Serializable {
            String bookingId;
            String roomType;

            Booking(String bookingId, String roomType) {
                this.bookingId = bookingId;
                this.roomType = roomType;
            }

            public String toString() {
                return bookingId + " (" + roomType + ")";
            }
        }

// ---------------------- SYSTEM STATE ----------------------
        class SystemState implements Serializable {
            Map<String, Booking> bookings;
            Map<String, Integer> inventory;

            SystemState(Map<String, Booking> bookings, Map<String, Integer> inventory) {
                this.bookings = bookings;
                this.inventory = inventory;
            }
        }

// ---------------------- HOTEL SYSTEM ----------------------
        class HotelSystem {

            private Map<String, Booking> bookings = new HashMap<>();
            private Map<String, Integer> inventory = new HashMap<>();
            private final String FILE_NAME = "system_state.ser";

            public HotelSystem() {
                // Default inventory
                inventory.put("Single", 2);
                inventory.put("Double", 2);
            }

            // ---------------- BOOK ROOM ----------------
            public void bookRoom(String bookingId, String roomType) {
                if (!inventory.containsKey(roomType) || inventory.get(roomType) == 0) {
                    System.out.println("No rooms available for " + roomType);
                    return;
                }

                inventory.put(roomType, inventory.get(roomType) - 1);
                bookings.put(bookingId, new Booking(bookingId, roomType));

                System.out.println("Booked: " + bookingId);
            }

            // ---------------- SHOW DATA ----------------
            public void showData() {
                System.out.println("Bookings: " + bookings.values());
                System.out.println("Inventory: " + inventory);
            }

            // ---------------- SAVE STATE ----------------
            public void saveState() {
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(FILE_NAME))) {

                    SystemState state = new SystemState(bookings, inventory);
                    oos.writeObject(state);

                    System.out.println("System state saved successfully!");

                } catch (IOException e) {
                    System.out.println("Error saving state: " + e.getMessage());
                }
            }

            // ---------------- LOAD STATE ----------------
            public void loadState() {
                File file = new File(FILE_NAME);

                // Handle missing file (failure tolerance)
                if (!file.exists()) {
                    System.out.println("No saved state found. Starting fresh.");
                    return;
                }

                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(FILE_NAME))) {

                    SystemState state = (SystemState) ois.readObject();

                    bookings = state.bookings;
                    inventory = state.inventory;

                    System.out.println("System state restored successfully!");

                } catch (Exception e) {
                    System.out.println("Error loading state. Starting fresh.");
                }
            }
        }

// ---------------------- MAIN CLASS ----------------------


                HotelSystem system = new HotelSystem();

                // Load previous state (Recovery)
                system.loadState();

                // Show current state
                System.out.println("\n--- Current System State ---");
                system.showData();

                // Perform operations
                system.bookRoom("B101", "Single");
                system.bookRoom("B102", "Double");

                System.out.println("\n--- After Booking ---");
                system.showData();

                // Save state before shutdown (Persistence)
                system.saveState();

                System.out.println("\nRestart the program to see recovery in action!");
            }
        }