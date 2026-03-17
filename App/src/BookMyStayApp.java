
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
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 200, 100);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 350, 180);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 600, 400);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}


public class BookMyStayApp {
    public static void main(String[] args){
        // Create room objects (Polymorphism in action)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability (simple variables)
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("=== Hotel Room Availability ===");

        // Display Single Room
        singleRoom.displayDetails();
        System.out.println("Available Units: " + singleAvailability);
        System.out.println();

        // Display Double Room
        doubleRoom.displayDetails();
        System.out.println("Available Units: " + doubleAvailability);
        System.out.println();

        // Display Suite Room
        suiteRoom.displayDetails();
        System.out.println("Available Units: " + suiteAvailability);
        System.out.println();

        System.out.println("Application terminated successfully.");
    }
}
