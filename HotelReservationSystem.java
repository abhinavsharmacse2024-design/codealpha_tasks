import java.io.*;
import java.util.*;

class Room implements Serializable {
    int roomNumber;
    String category;
    double price;
    boolean isBooked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }
}

class Reservation implements Serializable {
    String guestName;
    Room room;

    public Reservation(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
    }
}

public class HotelReservationSystem {
    private static final String FILE_NAME = "reservations.dat";
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        loadReservations();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View Active Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\n--- Available Rooms ---");
                for (Room r : rooms) {
                    if (!r.isBooked) {
                        System.out.println("Room " + r.roomNumber + " [" + r.category + "] - $" + r.price);
                    }
                }
            } else if (choice == 2) {
                System.out.print("Enter guest name: ");
                String name = scanner.nextLine();
                System.out.print("Enter room number to book: ");
                int roomNum = scanner.nextInt();
                
                Room selectedRoom = null;
                for (Room r : rooms) {
                    if (r.roomNumber == roomNum && !r.isBooked) {
                        selectedRoom = r;
                        break;
                    }
                }

                if (selectedRoom != null) {
                    selectedRoom.isBooked = true;
                    reservations.add(new Reservation(name, selectedRoom));
                    saveReservations();
                    System.out.println("\n[SUCCESS] Reservation successful! Payment simulation processed for $" + selectedRoom.price);
                } else {
                    System.out.println("\n[ERROR] Room not available or invalid room number.");
                }
            } else if (choice == 3) {
                System.out.print("Enter guest name to cancel reservation: ");
                String name = scanner.nextLine();
                Reservation toCancel = null;
                
                for (Reservation res : reservations) {
                    if (res.guestName.equalsIgnoreCase(name)) {
                        toCancel = res;
                        break;
                    }
                }

                if (toCancel != null) {
                    toCancel.room.isBooked = false;
                    reservations.remove(toCancel);
                    saveReservations();
                    System.out.println("\n[SUCCESS] Reservation cancelled successfully for " + name);
                } else {
                    System.out.println("\n[ERROR] No active reservation found for this name.");
                }
            } else if (choice == 4) {
                System.out.println("\n--- Active Reservations ---");
                if (reservations.isEmpty()) {
                    System.out.println("No active bookings.");
                } else {
                    for (Reservation res : reservations) {
                        System.out.println("Guest: " + res.guestName + " | Room: " + res.room.roomNumber + " [" + res.room.category + "]");
                    }
                }
            } else if (choice == 5) {
                System.out.println("Saving data and exiting system...");
                break;
            }
        }
        scanner.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100.0));
        rooms.add(new Room(102, "Standard", 100.0));
        rooms.add(new Room(201, "Deluxe", 200.0));
        rooms.add(new Room(301, "Suite", 500.0));
    }

    private static void saveReservations() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reservations);
            oos.writeObject(rooms);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadReservations() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            reservations = (List<Reservation>) ois.readObject();
            rooms = (List<Room>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File doesn't exist yet, which is fine for first run
        }
    }
}