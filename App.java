import java.util.Scanner;
public class App
{ 

    public static void mainMenu()
    {
        Scanner scan = new Scanner(System.in);
        Admin admin = new Admin(); 
        Cashier cashier = new Cashier();
        int option;
        System.out.println("****WELCOME TO ZOMAC POINT OF SALE****\n****CHOOSE OPTION****\n\n1=> Login\n2=>Register");
        try{
           option = scan.nextInt(); 
           if(option==1)
           {
            //ask for the role , user name and password
           }
           else if(option==2)
           {
            //register as admin 
            admin.register(); //calling the register function 
            mainMenu();

           }

           else{
            System.out.println("Invalid Option");
           }
      
        }
        catch(Exception e)
        {
            System.out.println("Please Enter A Number");

        }

    }//endof mainMenu

    public static void main(String[] args)
    {    
        mainMenu(); 

    }//endof main
  
}//endof class