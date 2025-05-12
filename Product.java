import java.util.Scanner; 
import java.sql.*;
public class Product{
    //attributes 
    private String name; 
    private double price; 
    private int qty;  

    //getters 
    public String getName()
    {
        return this.name;
    }
     public double getPrice()
    {
        return this.price;
    }
     public int getQTY()
    {
        return this.qty;
    } 

    //setters

    public void setName(String name)
    {
        this.name = name;
    } 
    public void setPrice(double price)
    {
        this.price = price;
    } 
    public void setQTY(int qty)
    {
        this.qty = qty;
    } 

    //other methods 
    //create product 

    public void createProduct()
    {
       try{
         //connection 
        Scanner scan =  new Scanner(System.in);
        MyConnection conn = new MyConnection(); 
        //fetch user input  
        System.out.println("Enter Product Name");
        this.name = scan.nextLine(); 
         System.out.println("Enter Product Price");
        this.price = scan.nextDouble(); 
         System.out.println("Enter Product QTY");
        this.qty = scan.nextInt(); 
        scan.nextLine(); 
        //persist to db 
        //query 
        String query = "INSERT INTO products(name,price,qty) VALUES(?,?,?)"; 
        //prepare 
        PreparedStatement st = conn.connection.prepareStatement(query); 
        //placeholders 
        st.setString(1,this.name); 
        st.setDouble(2,this.price); 
        st.
        setInt(3,this.qty); 
        //execute 
        int rowsAffected = st.executeUpdate();
        //feedback 
        System.out.println("Affected Rows "+rowsAffected); 
       }
       catch(SQLException e)
       {
        System.out.println("Error"+e.getMessage());
       }
      
    } 
    //view product 

    public void viewProducts()
    {
       try{
 MyConnection conn = new MyConnection();
        //query 
        String query = "SELECT * FROM products"; 
        //prepare 
        prepareStatement st = conn.connection.prepareStatement(query); 
        //exec 
        resultSet rs = st.executeQuery(); 
        //display 
        while(rs.next())
        {
            System.out.println("______________________\n NAME: "+rs.getString("name")+" PRICE: "+rs.getDouble("price")+" QTY"+rs.getInt("qty"));
            
        }
       }
       catch(SQLException e)
       {
        System.out.println("Error "e.getMessage());

       }
    }

    //delete product 

    public void deleteProduct()
    {
        try{
            //take the id 
        Scanner scan =  new Scanner(System.in); 
        System.out.println("Enter ID of Product"); 
        int id = scan.nextInt();
        //connectiion 
        MyConnection conn = new MyConnection(); 
        //query 
        String query = "DELETE FROM products WHERE id = ?"; 
        //prepare 
        prepareStatement st = conn.connectiion.prepareStatement(query); 
        //placeholder 
        st.setInt(1,id); 
        //exe 
        int rowsDeleted = st.executeUpdate(); 
       System.out.println("Rows Deleted" +rowsDeleted);
        }
        catch(SQLException e)
        {
            System.out.println("Error");
        }
    }

}