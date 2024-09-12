
import java.util.*;

public class HotelMgmt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        int choice;
        do {
            System.out.println("1. Add Room");
            System.out.println("2. Assign Room to Customer");
            System.out.println("3. Show All Rooms");
            System.out.println("4. Remove User");
            System.out.println("5. Remove All users");
            System.err.println("6. Show Total Income");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Room ID: ");
                    int roomId = sc.nextInt();
                    System.out.print("Enter Price: ");
                    float price = sc.nextFloat();
                    Room newRoom = new Room();
                    newRoom.setRoomid(roomId);
                    newRoom.setPrice(price);
                    newRoom.setStatus("Available");
                    admin.addRoom(roomId, newRoom);
                    break;
                case 2:
                    System.out.print("Enter Room ID to assign: ");
                    int assignRoomId = sc.nextInt();
                    if (admin.checkRoomAvailability(assignRoomId)) {
                        System.out.print("Enter User ID: ");
                        int userId = sc.nextInt();
                        System.out.print("Enter Price: ");
                        price = sc.nextFloat();

                        Room assignedRoom = new Room();
                        assignedRoom.setRoomid(assignRoomId);
                        assignedRoom.setUid(userId);
                        assignedRoom.setStatus("Unavailable");
                        assignedRoom.setPrice(price);
                        admin.assignCustomer(assignRoomId, assignedRoom);
                        System.out.println("Room assigned to customer.");
                    } else {
                        System.err.println("Room is unavailable.");
                    }
                    break;
                case 3:
                    admin.showAll();
                    break;
                case 4:
                    int UserId = sc.nextInt();
                    admin.removeUser(UserId);
                case 5:
                    System.out.println("Are you sure you want to remove all rooms? (y/n): ");
                    char confirm = sc.next().charAt(0);
                    if (confirm == 'y') {
                        admin.removeAll();

                    } else {
                        System.out.println("No rooms removed.");
                    }
                case 6:
                    admin.calculateTotalIncome();
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.err.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
    }
}

class Room {

    private int r_id;
    private int u_id;
    private float price;
    private String status;
    private int income;

    public void setRoomid(int r_id) {
        this.r_id = r_id;
    }

    public int getRoomid() {
        return r_id;
    }

    public void setUid(int u_id) {
        this.u_id = u_id;
    }

    public int getUid() {
        return u_id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Room ID: " + r_id + " User ID: " + u_id + " Price: " + price + " Status: " + status;
    }
}

class Admin {

    Map<Integer, Room> rooms = new HashMap<>();

    public void addRoom(int r_id, Room room) {
        rooms.put(r_id, room);
        System.out.println("Room " + r_id + " added.");
    }

    public void showAll() {
        if (rooms.isEmpty()) {
            System.err.println("No rooms available.");

        } else {
            for (Room room : rooms.values()) {
                System.out.println(room);
            }
        }
    }

    public boolean checkRoomAvailability(int r_id) {
        Room room = rooms.get(r_id);
        return room != null && room.getStatus().equalsIgnoreCase("Available");
    }

    public void assignCustomer(int r_id, Room room) {
        rooms.put(r_id, room);
    }

    public void removeAll() {
        rooms.clear();
        System.out.println("All rooms removed.");
    }

    public void removeUser(int userId) {

        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            if (entry.getValue().getUid() == userId) {
                entry.getValue().setUid(0);
                entry.getValue().setStatus("Available");
                System.out.println("User removed from room " + entry.getKey());
            }
        }
    }

    public void calculateTotalIncome() {
        int totalIncome = 0;
        for (Room room : rooms.values()) {
            if (room.getStatus().equalsIgnoreCase("unAvailable")) {
                totalIncome += room.getPrice();
            }
        }
        System.out.println("Total Income: " + totalIncome);
    }
}
