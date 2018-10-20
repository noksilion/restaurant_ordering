package Entities;

public abstract class User {
    static String firstName ;
    static String lastName;
    static Position position;


    public User (String firstName,String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }
}
