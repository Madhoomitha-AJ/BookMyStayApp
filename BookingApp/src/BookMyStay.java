import java.util.*;
public class BookMyStay {
    public static void main(String[] args) {


// Reservation Class (Core - unchanged)
        class Reservation {
            private String reservationId;
            private String guestName;
            private double amount;

            public Reservation(String reservationId, String guestName, double amount) {
                this.reservationId = reservationId;
                this.guestName = guestName;
                this.amount = amount;
            }

            public String getReservationId() {
                return reservationId;
            }

            public String getGuestName() {
                return guestName;
            }

            public double getAmount() {
                return amount;
            }
        }

// Booking History (stores confirmed bookings)
        class BookingHistory {
            private List<Reservation> history = new ArrayList<>();

            // Add confirmed reservation
            public void addReservation(Reservation r) {
                history.add(r); // maintains insertion order
            }

            // Retrieve all reservations
            public List<Reservation> getAllReservations() {
                return history;
            }
        }

// Reporting Service
        class BookingReportService {

            // Display all bookings
            public void displayAllBookings(List<Reservation> reservations) {
                System.out.println("Booking History:");
                for (Reservation r : reservations) {
                    System.out.println(
                            r.getReservationId() + " | " +
                                    r.getGuestName() + " | ₹" +
                                    r.getAmount()
                    );
                }
            }

            // Generate total revenue report
            public double calculateTotalRevenue(List<Reservation> reservations) {
                double total = 0;
                for (Reservation r : reservations) {
                    total += r.getAmount();
                }
                return total;
            }

            // Count total bookings
            public int totalBookings(List<Reservation> reservations) {
                return reservations.size();
            }
        }

// Main Class (Simulation)
                // Create booking history
                BookingHistory history = new BookingHistory();

                // Simulate confirmed bookings
                history.addReservation(new Reservation("R101", "Alice", 2500));
                history.addReservation(new Reservation("R102", "Bob", 3000));
                history.addReservation(new Reservation("R103", "Charlie", 2000));

                // Admin uses report service
                BookingReportService report = new BookingReportService();

                // Display bookings
                report.displayAllBookings(history.getAllReservations());

                // Generate reports
                System.out.println("\nTotal Bookings: " +
                        report.totalBookings(history.getAllReservations()));

                System.out.println("Total Revenue: ₹" +
                        report.calculateTotalRevenue(history.getAllReservations()));
            }
        }