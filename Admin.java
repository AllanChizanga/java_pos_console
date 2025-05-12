import java.util.Scanner;
import java.sql.*;
public class Admin extends User{
    
    //methods 
    //create cashier 
    public void createCashier()
    {
        //launch register() 
        this.register();
        this.displayAdminMenu();
    }
    public void viewCashiers()
    {
        try{
            //fetch all cashiers 
        MyConnection conn = new MyConnection(); 
        //query 
        String query = "SELECT * FROM users WHERE role =  ?"; 
        //PREPARE
        PreparedStatement st = conn.connection.prepareStatement(query);
        //parameter 
        st.setString(1,"cashier");
        //execute 
        ResultSet resultSet = st.executeQuery(); 
        //display the resultset 
        while(resultSet.next())
        {
        System.out.println("ID : "+resultSet.getInt("id")+" FullName : "+resultSet.getString("name")+" Email : "+resultSet.getString("email")+"\n____________________________________________________");
        }
        }
        catch(SQLException e)
        {
            System.out.println("Error e"+e.getMessage());
        }
        finally{
            this.displayAdminMenu();
        }
    }
    //delete cashier method
    public void deleteCashier()
    { 
      try{
          Scanner scan =  new Scanner(System.in);
        //obtain ID of cashier 
        System.out.println("Enter the ID of Cashier");
        int id = scan.nextInt();
        //fetch and check if role is cashier 
        MyConnection conn = new MyConnection(); 
        //create query 
        String query =  "DELETE FROM users WHERE id = ? AND role = ?"; 
        //prepare 
        PreparedStatement st = conn.connection.prepareStatement(query); 
        //placeholders 
        st.setInt(1,id);
        st.setString(2,"cashier");
        //execute 
        int rowsDeleted = st.executeUpdate(); 
        //
        System.out.println("Records Deleted: "+rowsDeleted);
        if(rowsDeleted==0)
        {
            System.out.println("The ID Does Not Belong To Cashier");

        }
        this.displayAdminMenu();


      }catch(SQLException e)
      {
          System.out.println("Eror "+ e.getMessage());
      }

    } 
    //create product 
    public void createProduct()
    { 
        //product must be a seperate class
        Product product = new Product(); 
        product.createProduct();
        this.displayAdminMenu();
    }
    //view products 
    public void viewProducts()
    { 
        Product product = new Product(); 
        product.viewProducts();


    }
    //delete product 

    public void deleteProduct(int id)
    { 
        Product product = new Product(); 
        product.deleteProduct();

    }
    //view repors 
    public void viewReports()
    {

    }
    //export reports 
    public void exportReports()
    {

    }
    //logout defined in User

    //display menu
    public void displayAdminMenu()
    {   Scanner scan = new Scanner(System.in);
        System.out.println("***** ADMIN MAIN MENU *****\n");
        System.out.println("*****CASHIER MANAGEMENT*****\n1. Create Cashier 2. View Cashiers 3. Delete Cashiers\n*****PRODUCT MANAGEMENT *****\n 4.Create new Product 5. View Products 6. Delete Product \n*****REPORTS*****\n 7. View Sales reports 8. Export As PDF 9. Logout");                                                          
        try{
              int option =  scan.nextInt();
               scan.nextLine(); 
        //launch other methods 
        if(option==1)
        {
       this.createCashier();
        }//endof if 
        else if(option==2)
        { 
            this.viewCashiers();

        }//endof else if
                else if(option==3)
        {
            this.deleteCashier(); //need arguement

        }//endof else if
                else if(option==4)
        {
            this.createProduct();

        }//endof else if
                else if(option==5)
        {
            this.viewProducts();

        }//endof else if
                else if(option==6)
        {  
            System.out.println("Enter the ID of the Product to delete");
            int id = scan.nextInt();
            scan.nextLine();
            this.deleteProduct(id);//need arg

        }//endof else if
                else if(option==7)
        {
            this.viewReports();

        }//endof else if
                else if(option==8)
        {
            this.exportReports();

        }//endof else if
                else if(option==9)
        {
            this.logout();

        }//endof else if

        }//endof tryblock 
        catch(Exception e)
        {
            System.out.println("Error Occured:"+e.getMessage());
            this.displayAdminMenu();
        }

        
       
    }//end of method
}//endof class
