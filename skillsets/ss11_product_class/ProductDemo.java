import java.util.Scanner;

public class ProductDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String code, description;
        double price;
        
        System.out.println("/////Below are default constructor values://///\n");
        
        //create first product object (no params)
        Product product1 = new Product();
        
        // show default values
        System.out.println("Code = " + product1.getCode());
        System.out.println("Description = " + product1.getDescription());
        System.out.println("Price = " + product1.getPrice());
        

        System.out.println("\n/////Below are user-entered values://///\n");
        System.out.print("Code: ");
        code = sc.nextLine();
        
        System.out.print("Description: ");
        description = sc.nextLine();
        
        System.out.print("Price: ");
        price = sc.nextDouble();

        sc.nextLine();
        
        // create second product object (with params)
        Product product2 = new Product(code, description, price);
        
        // display the values from the second product object
        System.out.println("Code = " + product2.getCode());
        System.out.println("Description = " + product2.getDescription());
        System.out.println("Price = " + product2.getPrice());
        
        System.out.println("\n/////Below using setter methods to pass literal values, then print() method to display values://///\n");
        product1.setCode("xyz789");
        product1.setDescription("Test Widget");
        product1.setPrice(89.99);
        
        // display the modified values
        product1.print();
    }
}