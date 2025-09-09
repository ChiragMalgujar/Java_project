import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();

        while (true) {
            System.out.println("\n====== Library Menu =====");
            System.out.println("1. Show All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> book.displayBooks();
                case 2 -> book.addbook();
                case 3 -> book.issuebook();
                case 4 -> book.returnbook();
                case 0 -> {
                    System.out.println("Thank you!");
                    return;
                }
                default -> System.out.println("Enter a valid option.");
            }
        }
    }
}
