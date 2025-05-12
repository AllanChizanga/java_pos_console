import java.util.Scanner;
import java.sql.*;
public class  User
{
    //attributes 
    private String name; 
    private String email;
    private String role; 
    private String password; 
    public String confirm;

    //setters 

    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
     public void setRole(String role)
    {
        this.role = role;
    }
     public void setPassword(String password)
    {
        this.password = password;
    } 

    //getters 
    public String getName()
    {
        return this.name;
    }
     public String getEmail()
    {
        return this.email;
    }
     public String getRole()
    {
        return this.role;
    }
     public String getPassword()
    {
        return this.password;
    }
    //login 
    public boolean login()
    { 
        //collect user email and passworc
        //fetch first record with such details 
        //if found then authenticate user 
        System.out.println("******LOGIN PAGE******\n\n");
        try{
        Scanner scan = new Scanner(System.in);
        System.out.println("Email :\n");
        this.email = scan.nextLine();
        System.out.println("Password :\n");
        this.password = scan.nextLine(); 
        //connection object 
        MyConnection conn = new MyConnection(); 
        //query
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        //prepare  
        PreparedStatement st = conn.connection.prepareStatement(query);
        //parameters 
        st.setString(1,this.email);
        st.setString(2,this.password);
        //execute 
        ResultSet rs = st.executeQuery();
        return rs.next(); //returns true if a matching row is found 
        }
        catch(Exception e)
        {
            System.out.println("Login Failed :"+e.getMessage()); 
            this.login();
            
        }
       return true;



    }
    //register
    public void register()
    { 
        //collects user input 
        //validates 
        //save to database 
        //authenticates user 
        Scanner scan =  new Scanner(System.in);
        try{ 
              //fullname
        System.out.println("FullName : ");
        this.name = scan.nextLine();
          //email
        System.out.println("Email : ");
        this.email = scan.nextLine();
          //role
        System.out.println("Role : ");
        this.role = scan.nextLine();
        if (!(this.role.equals("admin")|| this.role.equals("cashier")))
        {
            throw new Exception("Role Must either be admin or cashier");
        } 
        //password 
        System.out.println("Password : "); 
        this.password = scan.nextLine(); 
        //Confirm password 
        System.out.println("Confirm Passord : ");
        this.confirm =  scan.nextLine(); 
        if(!this.password.equals(this.confirm))
        {
            throw new Exception("Password Did Not Match");

        } 

        //validation 
        if(this.name!="" && this.email !=""&&this.role!=""&&this.password!="")
        { 
            //save to db 
            //connection
            
            MyConnection myconnection = new MyConnection(); //loads jdbc driver and establishes connection 
            //check if the email is unique 
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement st = myconnection.connection.prepareStatement(query);
            st.setString(1,this.email);
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count==0)
            { 

                           //query 
            query = "INSERT INTO users(name,email,role,password) VALUES(?,?,?,?)";
            st = myconnection.connection.prepareStatement(query);
            //arguements 
            st.setString(1,this.name);
            st.setString(2,this.email);
            st.setString(3,this.role);
            st.setString(4,this.password);
            //execute 
            int rowsAffected = st.executeUpdate();
            System.out.println("Rows Affected "+rowsAffected);
            //authenticate 

            }
            else{
                System.out.println("The email you provided is not unique, please try again");
                this.register();
            }

 
            

        }
        else{
            throw new Exception("All the fields are required");
        }
 
        } //endof try block 
        catch(Exception e)
        { 
            System.out.println("Error "+ e.getMessage());
            //recursive
            this.register();

        }


    }//end of register()
    public void logout()
    {
        
    }
}