public class Person {
    //TODO: make Customers and Trainers a subclass of People so you can add end dates to customers, and specialties to trainers
    protected int customerId;
    String firstName;
    String lastName;
    String username;
    String password;


    class Customer extends Person{
        String startDate;
        String endDate;
        int membership;

    }
    class Staff extends Person{
        String specialty;
    }
}