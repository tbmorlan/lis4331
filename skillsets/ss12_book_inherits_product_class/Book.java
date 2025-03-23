public class Book extends Product {
    // additional field for book class
    private String author;
    
    // default constructor
    public Book() {
        super(); // calls default constructor of product
        author = "John Doe";
        System.out.println("Inside book default constructor.\n");
    }
    
    // param constructor
    public Book(String code, String description, double price, String author) {
        super(code, description, price); // calls product param constructor
        this.author = author;
        System.out.println("Inside book constructor with parameters.\n");
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    // override the print method from product
    @Override
    public void print() {
        System.out.println("\nCode: " + getCode() + ", Description: " + getDescription() + 
                          ", Price: " + getPrice() + ", Author: " + author);
    }
}