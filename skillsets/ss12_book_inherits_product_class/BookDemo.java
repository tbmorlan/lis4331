public class BookDemo {
    public static void main(String[] args) {
        // create product objects
        System.out.println("/////Below are \"base class default constructor\" values (instantiating p1, then using getter methods)://///");
        Product p1 = new Product();
        System.out.println("Code = " + p1.getCode());
        System.out.println("Description = " + p1.getDescription());
        System.out.println("Price = " + p1.getPrice());
        
        System.out.println("\n/////Below are \"base class\" user-entered values (instantiating p2, then using getter methods)://///");
        Product p2 = new Product("def456", "Your Widget", 59.99);
        System.out.println("Code: " + p2.getCode());
        System.out.println("Description: " + p2.getDescription());
        System.out.println("Price: " + p2.getPrice());
        
        System.out.println("\n/////Below using setter methods to pass literal values to p2, then print() method to display values://///");
        p2.setCode("xyz789");
        p2.setDescription("Test Widget");
        p2.setPrice(89.99);
        p2.print();
        
        // create book objects
        System.out.println("\n/////Below are \"derived class default constructor\" values (instantiating b1, then using getter methods)://///");
        Book b1 = new Book();
        System.out.println("Code = " + b1.getCode());
        System.out.println("Description = " + b1.getDescription());
        System.out.println("Price = " + b1.getPrice());
        System.out.println("Author = " + b1.getAuthor());
        
        System.out.println("\nOr using overridden derived class print() method...");
        b1.print();
        
        System.out.println("\n/////Below are \"derived class\" user-entered values (instantiating b2, then using getter methods)://///");
        Book b2 = new Book("jk1987", "Another Widget", 99.99, "Jane Smith");
        System.out.println("Code: " + b2.getCode());
        System.out.println("Description: " + b2.getDescription());
        System.out.println("Price: " + b2.getPrice());
        System.out.println("Author: " + b2.getAuthor());
        
        System.out.println("\nOr using derived class print() method...");
        b2.print();
    }
}