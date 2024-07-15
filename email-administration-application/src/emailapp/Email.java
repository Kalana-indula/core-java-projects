package emailapp;

import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private int passwordLength=10;
    private String department;
    private int mailboxCapacity;
    private String email;
    private String company="env";
    private String alternativeEmail;

    //constructor to recieve first name and last name
    public Email(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
        System.out.println("Name : "+this.firstName+this.lastName);

        //Call a method asking for department - return department
        this.department=setDepartment();

        if (this.department==" "){
            System.out.println("You didn't select a department");
        }

        //Call a method to generate a random password
        System.out.println("Your Password : "+randomPassword(passwordLength));

        //Generate an email
        this.email="Email : "+this.firstName+"."+this.lastName+"@"+this.department+"."+this.company+".com";
        System.out.println(this.email);
    }

    //ask for the department
    private String setDepartment(){
        System.out.println("DEPARTMENT CODES\n 1 for Sales\n 2 for Development\n 3 for Accounting\n 0 for none\n Enter department code : ");
        Scanner input= new Scanner(System.in);

        int depChoice=input.nextInt();

        if(depChoice==1){
            return "sales";
        }else if(depChoice==2){
            return "dev";
        }else if(depChoice==3){
            return "acct";
        }else{
            return " ";
        }
    }

    //Generate a random password
    private String randomPassword(int pwLength){
        String passwordSet="ABCDEFGHTIJKLMNOPQRSTUVWXYZ1234567890!#$@&*";

        char[] password=new char[pwLength];
        for(int i=0;i<pwLength;i++){
            int rand=(int)(Math.random()*passwordSet.length());
            password[i]=passwordSet.charAt(rand);
        }

        return new String(password);
    }

    //Set the mailbox capacity
    public void setMailboxCapacity(int capacity){
        this.mailboxCapacity=capacity;
    }

    //Set the alternative email
    public void setAlternativeEmail(String altEmail){
        this.alternativeEmail=altEmail;
    }

    //Change the password
    public void changePassword(String password){
        this.password=password;
    }

    //Accessing/values of fields
    public String getAlternativeEmail(){
        return this.alternativeEmail;
    }

    public int getMailboxCapacity(){
        return this.mailboxCapacity;
    }

    public String showInfo(){
        return "DISPLAY NAME : "+firstName+" "+lastName+
                "COMPANY EMAIL : "+email +
                "MAILBOX CAPACITY : "+mailboxCapacity+"mb";
    }

}


