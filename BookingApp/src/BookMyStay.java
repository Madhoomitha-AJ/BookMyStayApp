import java.util.*;
public class BookMyStay {
    public static void main(String[] args) {


// Add-On Service Class
        class AddOnService {
            private String name;
            private double cost;

            public AddOnService(String name, double cost) {
                this.name = name;
                this.cost = cost;
            }

            public double getCost() {
                return cost;
            }

            public String getName() {
                return name;
            }
        }

// Reservation Class (Core - NOT modified)
        class Reservation {
            private String reservationId;

            public Reservation(String reservationId) {
                this.reservationId = reservationId;
            }

            public String getReservationId() {
                return reservationId;
            }
        }

// Add-On Service Manager
        class AddOnServiceManager {

            // Map<ReservationID, List of Services>
            private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

            // Add service to reservation
            public void addService(String reservationId, AddOnService service) {
                serviceMap.putIfAbsent(reservationId, new ArrayList<>());
                serviceMap.get(reservationId).add(service);
            }

            // Get services for a reservation
            public List<AddOnService> getServices(String reservationId) {
                return serviceMap.getOrDefault(reservationId, new ArrayList<>());
            }

            // Calculate total additional cost
            public double calculateTotalCost(String reservationId) {
                double total = 0;
                List<AddOnService> services = getServices(reservationId);

                for (AddOnService s : services) {
                    total += s.getCost();
                }
                return total;
            }

            // Display services
            public void displayServices(String reservationId) {
                List<AddOnService> services = getServices(reservationId);

                if (services.isEmpty()) {
                    System.out.println("No add-on services selected.");
                    return;
                }

                System.out.println("Services for Reservation " + reservationId + ":");
                for (AddOnService s : services) {
                    System.out.println("- " + s.getName() + " : ₹" + s.getCost());
                }
            }
        }


                // Create reservation
                Reservation r1 = new Reservation("R101");

                // Create services
                AddOnService wifi = new AddOnService("WiFi", 200);
                AddOnService breakfast = new AddOnService("Breakfast", 500);
                AddOnService spa = new AddOnService("Spa", 1500);

                // Manager
                AddOnServiceManager manager = new AddOnServiceManager();

                // Guest selects services
                manager.addService(r1.getReservationId(), wifi);
                manager.addService(r1.getReservationId(), breakfast);
                manager.addService(r1.getReservationId(), spa);

                // Display services
                manager.displayServices(r1.getReservationId());

                // Calculate cost
                double total = manager.calculateTotalCost(r1.getReservationId());
                System.out.println("Total Add-On Cost: ₹" + total);
            }
        }