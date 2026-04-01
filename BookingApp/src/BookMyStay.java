import java.util.*;
public class BookMyStay {
    public static void main(String[] args) {


        // ---------------------- UNSAFE SYSTEM ----------------------
        class UnsafeBookingSystem {
            int availableRooms = 1; // shared resource

            public void book(String guestName) {
                if (availableRooms > 0) {
                    System.out.println(guestName + " is trying to book...");

                    // Simulate delay → causes race condition
                    try { Thread.sleep(100); } catch (InterruptedException e) {}

                    availableRooms--;
                    System.out.println(guestName + " booked successfully!");
                } else {
                    System.out.println(guestName + " failed - No rooms available");
                }
            }
        }

// ---------------------- SAFE SYSTEM ----------------------
        class SafeBookingSystem {
            int availableRooms = 1;

            // synchronized → critical section protected
            public synchronized void book(String guestName) {
                if (availableRooms > 0) {
                    System.out.println(guestName + " is trying to book...");

                    try { Thread.sleep(100); } catch (InterruptedException e) {}

                    availableRooms--;
                    System.out.println(guestName + " booked successfully!");
                } else {
                    System.out.println(guestName + " failed - No rooms available");
                }
            }
        }

// ---------------------- THREAD CLASS ----------------------
        class BookingThread extends Thread {
            String guestName;
            Object system;

            BookingThread(String guestName, Object system) {
                this.guestName = guestName;
                this.system = system;
            }

            public void run() {
                if (system instanceof UnsafeBookingSystem) {
                    ((UnsafeBookingSystem) system).book(guestName);
                } else if (system instanceof SafeBookingSystem) {
                    ((SafeBookingSystem) system).book(guestName);
                }
            }
        }

// ---------------------- MAIN CLASS ----------------------

                // ----------- WITHOUT SYNCHRONIZATION -----------
                System.out.println("=== Without Synchronization (Race Condition) ===");

                UnsafeBookingSystem unsafe = new UnsafeBookingSystem();

                Thread t1 = new BookingThread("Guest 1", unsafe);
                Thread t2 = new BookingThread("Guest 2", unsafe);

                t1.start();
                t2.start();

                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException e) {}

                // ----------- WITH SYNCHRONIZATION -----------
                System.out.println("\n=== With Synchronization (Thread-Safe) ===");

                SafeBookingSystem safe = new SafeBookingSystem();

                Thread t3 = new BookingThread("Guest 1", safe);
                Thread t4 = new BookingThread("Guest 2", safe);

                t3.start();
                t4.start();

                try {
                    t3.join();
                    t4.join();
                } catch (InterruptedException e) {}
            }
        }