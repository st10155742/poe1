import javax.swing.JOptionPane;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Login
{
    
    public static void main(String[] args){
         Login login = new Login(); 
         login.app();
    }
    
    public void app(){
        
       
        Account account = new Account();
        
        int action = 0;
        
        while(action != 2){
          action = askWhatUserWantsToDo();
          if(action == 0){
              String userName = JOptionPane.showInputDialog(null, "Enter Username");
              String passWord = JOptionPane.showInputDialog(null, "Enter Password");
        
              boolean response = loginUser(account, userName, passWord);
              String loginStatus = returnLoginStatus(response, account);
              
              JOptionPane.showMessageDialog(null, loginStatus);
          }else if(action == 1){
              String registerMessage = registerUser(account);
              JOptionPane.showMessageDialog(null, registerMessage);
          }else{
              System.exit(0);
          }
        }
        
        
    }
    
    private int askWhatUserWantsToDo(){
         String[] options = {"login", "register", "exit"};
         // 0 login, 1 for registration ,2 for exit
        int action = 0;
        do{
            
          action = JOptionPane.showOptionDialog(
          null,"What would you like to do today?", 
          "Welcome", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]); 
          
        }while(action < 0);
        
        return action;
    }
    
    private boolean loginUser(Account account, String userName, String passWord){
        
        
        return userName.equals(account.getUserName()) && passWord.equals(account.getPassword());
    }
    
    private String registerUser(Account account){
        
        int tries = 5;
        
        //prompt user for First Name
        String firstName = JOptionPane.showInputDialog(null, "Enter your First Name");
        while(firstName.isEmpty()){
            if(tries == 0){
               return "You entered invalid First Name, try again"; 
            }
            firstName = JOptionPane.showInputDialog(null, "Enter a valid First Name");
            tries--;
        }
        tries = 5; //reset tries
        
        
        String lastName = JOptionPane.showInputDialog(null, "Enter your Last Name");
        while(lastName.isEmpty()){
            if(tries == 0){
               return "You entered invalid Last Name, try again"; 
            }
            lastName = JOptionPane.showInputDialog(null, "Enter a valid Last Name");
            tries--;
        }
        
        //prompt user for UserName
        String userName = JOptionPane.showInputDialog(null, "Enter New Username");
        while(!checkUserName(userName)){
            if(tries == 0){
               return "The username is incorrectly formatted"; 
            }
            JOptionPane.showMessageDialog(
            null, 
            "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length ."
            );
            userName = JOptionPane.showInputDialog(null, "Enter New Username");
            tries--;
        }
      
        //show captured username
        JOptionPane.showMessageDialog(null, "Username successfully captured");
        tries = 5;
        
        String userPass = JOptionPane.showInputDialog(null, "Enter New Password");
        while(!checkPasswordComplexity(userPass)){
            if(tries == 0){
               return "The password does not meet the complexity requirements."; 
            }
            JOptionPane.showMessageDialog(
            null, 
            "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character."
            );
            userPass = JOptionPane.showInputDialog(null, "Enter a valid Password");
            tries--;
        }
         //show captured password
        JOptionPane.showMessageDialog(null, "Password successfully captured");
        
        
        account.setfirstName(firstName);
        account.setLastName(lastName);
        account.setUserName(userName);
        account.setPassword(userPass);
        
        return "The two above conditions have been met and the user has been registered successfully.";
    }
    
    private boolean checkUserName(String user_name){
        
        //Username contains an underscore and is no more than 5 characters long
        boolean hasUnderScore = false; // flag
        boolean lengthPass = false; // flag
        
        if(user_name.contains("_")){
            hasUnderScore = true;
        }
        
        if(user_name.length() <= 5){
            lengthPass = true;
        }
        
        return hasUnderScore && lengthPass;
    }
    
    private boolean checkPasswordComplexity(String user_password){
        
        boolean lengthPass = false; //flag
        boolean containsNumber = false; //flag
        boolean containsCapitalLetter = false; // flag
        boolean containsSpecialChar = false;
        
        //At least 8 characters long
        if(user_password.length() >= 8){
            lengthPass = true;
        }
     
       
        for(int i=0; i < user_password.length(); i++) {
            char ch = user_password.charAt(i);
            
             //contains Number
            if( Character.isDigit(ch)) {
                containsNumber = true;
            }
            // contains capital letter
            else if (Character.isUpperCase(ch)) {
                containsCapitalLetter = true;
            }
            // contains special character
            String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
            if(specialCharactersString.contains(Character.toString(ch))) {
                containsSpecialChar = true;
            }
        }
  
        return lengthPass && containsNumber && containsCapitalLetter && containsSpecialChar;
    }
    
    private String returnLoginStatus(boolean isLoggedIn, Account account){
        
        if(isLoggedIn){
            return "Welcome "+account.getFirstName()+", "+account.getLastName()+" it is great to see you again";
        }
        
        return "Username or password incorrect, please try again";
    }

    @Test
    public void testloginUser(){
        Account account = new Account();
        account.setUserName("_test");
        account.setPassword("test");
        
        assertTrue("Login Successful",loginUser(account, "_test", "test"));
        assertFalse("Login Failed",loginUser(account, "_test", "test_1"));
    }

    @Test
    public void testUserName(){
        assertTrue("Username correctly formatted", checkUserName("_test"));
        assertFalse("Username incorrectly formatted", checkUserName("username"));
    }

    @Test
    public void testPassword(){
        assertTrue("Password meets complexity requirements", checkPasswordComplexity("NETgrpsvr@1"));
        assertFalse("Password does not meet complexity requirements", checkPasswordComplexity("password"));
    }
    
    
}
