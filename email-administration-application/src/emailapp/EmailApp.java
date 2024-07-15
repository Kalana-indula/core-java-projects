package emailapp;

public class EmailApp {

    public static void main(String[] args) {
        System.out.println("Application is started");

        Email email=new Email("kalana","indula");

        email.setAlternativeEmail("ka@gmail.com");
        email.changePassword("awet!@4DF");

        System.out.println("Alternative email : "+email.getAlternativeEmail());

    }
}
