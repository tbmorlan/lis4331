public class Product {
    // private data members
    private String code;
    private String description;
    private double price;
    
    // no param constructor
    public Product() {
        code = "abc123";
        description = "My Widget";
        price = 49.99;
        System.out.println("Inside product default constructor.\n");
    }
    
    // param constructor
    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
        System.out.println("\nInside product constructor with parameters.\n");
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getPrice() {
        return String.format("$%.2f", price);
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void print() {
        System.out.println("Code: " + code + ", Description: " + description + ", Price: " + getPrice());
    }
}