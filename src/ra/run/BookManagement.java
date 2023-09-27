package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    Book[] arrBook = new Book[100];
    int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManagement bookManagement = new BookManagement();
        do {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1: Add books");
            System.out.println("2: Show library");
            System.out.println("3: Sort book by interest");
            System.out.println("4: Remove book by ID");
            System.out.println("5: Search book by name or description");
            System.out.println("6: Change book information by ID");
            System.out.println("7: Exits");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bookManagement.addBook(scanner);
                    break;

                case 2:
                    bookManagement.displayBook();
                    break;

                case 3:
                    bookManagement.sortBookByInterest();
                    break;

                case 4:
                    bookManagement.removeBookById(scanner);
                    break;

                case 5:
                    bookManagement.searchBook(scanner);
                    break;

                case 6:
                    bookManagement.changeBookById(scanner);
                    break;

                case 7:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Please select 1 to 7");
                    break;
            }
        } while (true);
    }

    public void addBook(Scanner scanner) {
        System.out.printf("Amount of book want to add(less than %d):\n", 100 - bookCount);
        int amount = Integer.parseInt(scanner.nextLine());
        if (amount + bookCount < 100) {
            for (int i = 0; i < amount; i++) {
                arrBook[bookCount + i] = new Book();
                arrBook[bookCount + i].inputData(scanner, bookCount++);

            }
            System.out.println(bookCount);
        } else {
            System.out.println("Library can't handle more than 100 book");
        }
    }

    public void displayBook() {
        for (int i = 0; i < bookCount; i++) {
            arrBook[i].displayData();
        }
    }

    public void sortBookByInterest() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = i + 1; j < bookCount; j++) {
                if (arrBook[i].getInterest() > arrBook[j].getInterest()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[j];
                    arrBook[j] = temp;

                }
            }
        }
        System.out.println("Book sort by interest:");
        displayBook();
    }

    public int getIndexById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (arrBook[i].getBookId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void removeBookById(Scanner scanner) {
        System.out.println("Enter ID book want remove:");
        int idDelete = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexById(idDelete);
        if (indexDelete >= 0) {
            for (int i = indexDelete; i < bookCount; i++) {
                arrBook[i] = arrBook[i + 1];
            }
            bookCount--;
        }
    }

    public void searchBook(Scanner scanner) {
        System.out.println("Enter Book name or book description:");
        String searchIp = scanner.nextLine();
        if (!searchIp.isEmpty()) {
            System.out.println("Book Find");
            for (int i = 0; i < bookCount; i++) {
                if (arrBook[i].getBookName().toLowerCase().contains(searchIp.toLowerCase())) {
                    arrBook[i].displayData();
                }
            }
        } else {
            System.out.println("input can't be empty");
        }
    }

    public void changeBookById(Scanner scanner) {
        System.out.println("Enter ID book want to change:");
        int idChange = Integer.parseInt(scanner.nextLine());
        int indexChange = getIndexById(idChange);
        if (indexChange >= 0) {
            System.out.println("Update Book Name:");
            String nameC = scanner.nextLine();
            if (!nameC.trim().isEmpty()) {
                arrBook[indexChange].setBookName(nameC);
            }
            System.out.println("Update Book Author:");
            String authorC = scanner.nextLine();
            if (!authorC.trim().isEmpty()) {
                arrBook[indexChange].setAuthor(authorC);
            }
            while (true) {
                System.out.println("Update Book Description:");
                String descC = scanner.nextLine();
                System.out.println(descC.trim().isEmpty());
                if (!descC.trim().isEmpty()) {
                    if ((descC.length() >= 10)) {
                        arrBook[indexChange].setDesc(descC);
                        break;
                    } else {
                        System.out.println("Description must more than 10 character");
                    }

                }else {
                    break;
                }
            }
            while (true) {

                System.out.println("Change Import Price:");
                String importC = scanner.nextLine();
                if (!importC.trim().isEmpty()) {
                    if (Double.parseDouble(importC) > 0) {
                        arrBook[indexChange].setImportPrice(Double.parseDouble(importC));
                        break;
                    } else {
                        System.out.println("Import Price can't small than 0");
                    }
                }
            }
            System.out.println("Change Export Price:");
            String exportC = scanner.nextLine();
            if (!exportC.trim().isEmpty()) {
                if (!(Double.parseDouble(exportC) < arrBook[indexChange].getImportPrice() * 1.2)) {
                    arrBook[indexChange].setExportPrice(Double.parseDouble(exportC));
                } else {
                    System.out.println("Export Price must more than Import price * 1.2");
                }
            }
            arrBook[indexChange].setInterest((float) (arrBook[indexChange].getExportPrice() - arrBook[indexChange].getImportPrice()));
            System.out.println("Change Status:(true/false)");
            String statusC = scanner.nextLine();
            if (!statusC.trim().isEmpty()) {
                if (statusC.equalsIgnoreCase("true")) {
                    arrBook[indexChange].setBookStatus(true);
                } else if (statusC.equalsIgnoreCase("false")) {
                    arrBook[indexChange].setBookStatus(false);
                } else {
                    System.out.println("Status must be true or false");
                }
            }
        } else {
            System.out.println("Book not find");
        }
    }
}
