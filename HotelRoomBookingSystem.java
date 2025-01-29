// Hotel Room Booking System Application

import java.util.*;

// Room class
class Room {
    private String roomId;
    private String type;
    private double rate;
    private boolean isBooked;

    public Room(String roomId, String type, double rate) {
        this.roomId = roomId;
        this.type = type;
        this.rate = rate;
        this.isBooked = false;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        isBooked = true;
    }

    public void checkout() {
        isBooked = false;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId + " | Type: " + type + " | Rate: " + rate + "/night" + (isBooked ? " | Booked" : " | Available");
    }
}

// Customer class
class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

// Booking class
class Booking {
    private Room room;
    private Customer customer;
    private int nights;

    public Booking(Room room, Customer customer, int nights) {
        this.room = room;
        this.customer = customer;
        this.nights = nights;
    }

    public double calculateCost() {
        return nights * room.getRate();
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + " | Room: " + room.getType() + " (" + room.getRoomId() + ") | Nights: " + nights + " | Total Cost: " + calculateCost();
    }
}

// Hotel Room Booking System
public class HotelRoomBookingSystem {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("R001", "Standard", 100));
        rooms.add(new Room("R002", "Deluxe", 200));
        rooms.add(new Room("R003", "Standard", 100));
        rooms.add(new Room("R004", "Deluxe", 200));

        List<Booking> bookings = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Room Booking System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Check Out from a Room");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Rooms:");
                    for (Room room : rooms) {
                        if (!room.isBooked()) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2:
                    System.out.println("\nEnter Customer Details:");
                    System.out.print("Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Customer Name: ");
                    String customerName = scanner.nextLine();

                    System.out.println("\nAvailable Rooms:");
                    for (int i = 0; i < rooms.size(); i++) {
                        if (!rooms.get(i).isBooked()) {
                            System.out.println((i + 1) + ". " + rooms.get(i));
                        }
                    }

                    System.out.print("Choose a room to book (by number): ");
                    int roomChoice = scanner.nextInt() - 1;
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();

                    if (roomChoice >= 0 && roomChoice < rooms.size() && !rooms.get(roomChoice).isBooked()) {
                        Room selectedRoom = rooms.get(roomChoice);
                        selectedRoom.book();
                        Customer customer = new Customer(customerId, customerName);
                        Booking booking = new Booking(selectedRoom, customer, nights);
                        bookings.add(booking);
                        System.out.println("\nRoom booked successfully!");
                    } else {
                        System.out.println("\nInvalid choice or room already booked.");
                    }
                    break;

                case 3:
                    System.out.println("\nEnter Room ID to check out: ");
                    String roomId = scanner.nextLine();
                    boolean found = false;

                    for (Room room : rooms) {
                        if (room.getRoomId().equals(roomId) && room.isBooked()) {
                            room.checkout();
                            System.out.println("\nChecked out successfully!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("\nRoom not found or not booked.");
                    }
                    break;

                case 4:
                    System.out.println("\nCurrent Bookings:");
                    for (Booking booking : bookings) {
                        System.out.println(booking);
                    }
                    break;

                case 5:
                    System.out.println("\nThank you for using the Hotel Room Booking System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid option. Try again.");
            }
        }
    }
}