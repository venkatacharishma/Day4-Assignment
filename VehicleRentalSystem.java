// Vehicle Rental System Application

import java.util.*;

// Abstract Vehicle class
abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private double rentalRate;
    private boolean isRented;

    public Vehicle(String vehicleId, String brand, double rentalRate) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.rentalRate = rentalRate;
        this.isRented = false;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rent() {
        isRented = true;
    }

    public void returnVehicle() {
        isRented = false;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " | ID: " + vehicleId + " | Brand: " + brand + " | Rate: " + rentalRate + "/day" + (isRented ? " | Currently Rented" : "");
    }
}

// Car class
class Car extends Vehicle {
    public Car(String vehicleId, String brand, double rentalRate) {
        super(vehicleId, brand, rentalRate);
    }

    @Override
    public String getType() {
        return "Car";
    }
}

// Bike class
class Bike extends Vehicle {
    public Bike(String vehicleId, String brand, double rentalRate) {
        super(vehicleId, brand, rentalRate);
    }

    @Override
    public String getType() {
        return "Bike";
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

// Rental class
class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int daysRented;

    public Rental(Vehicle vehicle, Customer customer, int daysRented) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.daysRented = daysRented;
    }

    public double calculateCost() {
        return daysRented * vehicle.getRentalRate();
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + " | Vehicle: " + vehicle.getBrand() + " (" + vehicle.getType() + ") | Days: " + daysRented + " | Total Cost: " + calculateCost();
    }
}

// Vehicle Rental System
public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car("C001", "Toyota", 50));
        vehicles.add(new Bike("B001", "Yamaha", 20));
        vehicles.add(new Car("C002", "Ford", 60));
        vehicles.add(new Bike("B002", "Honda", 25));

        List<Rental> rentals = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Vehicle Rental System!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. View Rentals");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Vehicles:");
                    for (Vehicle vehicle : vehicles) {
                        if (!vehicle.isRented()) {
                            System.out.println(vehicle);
                        }
                    }
                    break;

                case 2:
                    System.out.println("\nEnter Customer Details:");
                    System.out.print("Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Customer Name: ");
                    String customerName = scanner.nextLine();

                    System.out.println("\nAvailable Vehicles:");
                    for (int i = 0; i < vehicles.size(); i++) {
                        if (!vehicles.get(i).isRented()) {
                            System.out.println((i + 1) + ". " + vehicles.get(i));
                        }
                    }

                    System.out.print("Choose a vehicle to rent (by number): ");
                    int vehicleChoice = scanner.nextInt() - 1;
                    System.out.print("Enter number of days to rent: ");
                    int days = scanner.nextInt();

                    if (vehicleChoice >= 0 && vehicleChoice < vehicles.size() && !vehicles.get(vehicleChoice).isRented()) {
                        Vehicle selectedVehicle = vehicles.get(vehicleChoice);
                        selectedVehicle.rent();
                        Customer customer = new Customer(customerId, customerName);
                        Rental rental = new Rental(selectedVehicle, customer, days);
                        rentals.add(rental);
                        System.out.println("\nVehicle rented successfully!");
                    } else {
                        System.out.println("\nInvalid choice or vehicle already rented.");
                    }
                    break;

                case 3:
                    System.out.println("\nEnter Vehicle ID to return: ");
                    String vehicleId = scanner.nextLine();
                    boolean found = false;

                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getVehicleId().equals(vehicleId) && vehicle.isRented()) {
                            vehicle.returnVehicle();
                            System.out.println("\nVehicle returned successfully!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("\nVehicle not found or not rented.");
                    }
                    break;

                case 4:
                    System.out.println("\nCurrent Rentals:");
                    for (Rental rental : rentals) {
                        System.out.println(rental);
                    }
                    break;

                case 5:
                    System.out.println("\nThank you for using the Vehicle Rental System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid option. Try again.");
            }
        }
    }
}