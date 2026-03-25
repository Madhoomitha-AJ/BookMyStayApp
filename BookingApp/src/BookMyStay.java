import java.util.*;
public class BookMyStay {
    public static void main(String[] args) {


// Custom Exception
        class InvalidBookingException extends Exception {
            public InvalidBookingException(String message) {
                super(message);
            }
        }

// Reservation Class
        class Reservation {
            private String reservationId;
            private String roomType;
            private int roomsBooked;

            public Reservation(String reservationId, String roomType, int roomsBooked) {
                this.reservationId = reservationId;
                this.roomType = roomType;
                this.roomsBooked = roomsBooked;
            }

            public String getRoomType() {
                return roomType;
            }

            public int getRoomsBooked() {
                return roomsBooked;
            }

            public String getReservationId() {
                return reservationId;
            }
        }

// Validator Class
        class InvalidBookingValidator {

            // Simulated inventory
            private static Map<String, Integer> inventory = new HashMap<>();

            static {
                inventory.put("Single", 5);
                inventory.put("Double", 3);
                inventory.put("Suite", 2);
            }

            public static void validate(Reservation r) throws InvalidBookingException {

                // Validate room type
                if (!inventory.containsKey(r.getRoomType())) {
                    throw new InvalidBookingException("Invalid room type selected.");
                }

                // Validate booking count
                if (r.getRoomsBooked() <= 0) {
                    throw new InvalidBookingException("Number of rooms must be greater than zero.");
                }

                // Validate availability
                int available = inventory.get(r.getRoomType());
                if (r.getRoomsBooked() > available) {
                    throw new InvalidBookingException("Not enough rooms available.");
                }
            }

            // Update inventory only after validation
            public static void updateInventory(Reservation r) {
                int available = inventory.get(r.getRoomType());
                inventory.put(r.getRoomType(), available - r.getRoomsBooked());
            }
        }

// Main Class

                // Example booking
                Reservation r = new Reservation("R201", "Double", 2);

                try {
                    // Validate first (Fail-Fast)
                    InvalidBookingValidator.validate(r);

                    // Update inventory only if valid
                    InvalidBookingValidator.updateInventory(r);

                    System.out.println("Booking confirmed for Reservation ID: " + r.getReservationId());

                } catch (InvalidBookingException e) {
                    // Graceful failure handling
                    System.out.println("Booking Failed: " + e.getMessage());
                }

                // System continues running
                System.out.println("System is still running safely...");
            }
        }