import java.util.Queue;
import java.util.LinkedList;
public class BookMyStay {
    public static void main() {
        class Reservation {
            private Queue<Reservation> queue = new LinkedList<>();

            // Add request to queue
             synchronized void enqueue(Reservation request) {
                queue.offer(request);
            }

            // Fetch next request (for allocation stage)
             synchronized Reservation dequeue() {
                return queue.poll();
            }

            // Peek without removing
             synchronized Reservation peek() {
                return queue.peek();
            }

            synchronized boolean isEmpty() {
                return queue.isEmpty();
            }
        }
    }
}
