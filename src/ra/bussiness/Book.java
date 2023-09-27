package ra.bussiness;

import java.util.Scanner;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String desc;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus = true;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Book() {
    }

    public Book(int bookId, String bookName, String author, String desc, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.desc = desc;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public void inputData(Scanner scanner, int count) {
        this.bookId = count + 1;
        System.out.println("Enter book " + (count+1));
        boolean isName = false;
        while (!isName) {
            System.out.println("Enter Book Name:");
            this.bookName = scanner.nextLine();
            isName = validName();
            if (!isName) {
                System.out.println("Book name can't be empty");
            }
        }
        boolean isAuthor = false;
        while (!isAuthor) {
            System.out.println("Enter Author Name:");
            this.author = scanner.nextLine();
            isAuthor = validAuthor(scanner);
            if (!isAuthor) {
                System.out.println("Author can't be empty");
            }
        }
        boolean isImPrice = false;
        while (!isImPrice) {
            System.out.println("Enter Product Import Price:");
            this.importPrice = Float.parseFloat(scanner.nextLine());
            isImPrice = validImPrice();
            if (!isImPrice) {
                System.out.println("Import price must bigger than 0");
            }
        }
        boolean isExPrice = false;
        while (!isExPrice) {
            System.out.println("Enter Product Export Price:");
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            isExPrice = validExPrice();
            if (!isExPrice) {
                System.out.println("Export price must more than import price * 1.2");
            }
        }
        this.interest = (float) (this.exportPrice - this.importPrice);
        boolean isDesc = false;
        while (!isDesc) {
            System.out.println("Enter Product description:");
            this.desc = scanner.nextLine();
            isDesc = validDesc();
            if (!isDesc) {
                System.out.println("Description must more than 10 character");
            }
        }
        boolean ipStatus = false;
        while (!ipStatus) {
            System.out.println("Enter Product Status:(true - false),(default is true)");
            String statusIp = scanner.nextLine();
            ipStatus = validStatus(statusIp);
            if (!ipStatus) {
                System.out.println("Status must be true or false");
            }
        }
    }

    public void displayData() {
        System.out.printf("ID: %d - Book name: %s - Author: %s\n", this.bookId, this.bookName, this.author);
        System.out.printf("Import price : %.2f - Export price : %.2f - interest: %f\n", this.importPrice, this.exportPrice, this.interest);
        System.out.printf("Description: %s\n Status: %s\n", this.desc, this.bookStatus ? "Sale" : "Not in sale");
    }

    public boolean validName() {
        return !(this.bookName.isEmpty());
    }

    public boolean validAuthor(Scanner scanner) {
        return !(this.author.isEmpty());
    }

    public boolean validImPrice() {
        return (this.importPrice > 0);
    }

    public boolean validExPrice() {
        return (this.exportPrice >= this.importPrice * 1.2);
    }

    public boolean validDesc() {
        return this.desc.length() >= 10;
    }

    public boolean validStatus(String statusIp) {
        return statusIp.equalsIgnoreCase("true") || statusIp.equalsIgnoreCase("") || statusIp.equalsIgnoreCase("false");
    }
}
