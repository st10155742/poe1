public class Account
{

    private String firstName;
    private String lastName;
    private String userName;
    private String userPassword;
    
    //SET METHODS
    public void setfirstName(String name){
        this.firstName = name;
    }
    public void setLastName(String last_name){
        this.lastName = last_name;
    }
    public void setUserName(String user_name){
        this.userName = user_name;
    }
    public void setPassword(String user_password){
        this.userPassword = user_password;
    }
    
    //GET METHODS
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.userPassword;
    }
    
    public String toString(){
        return "Username" + this.userName + ", Password: " + this.userPassword;
    }
    
}
