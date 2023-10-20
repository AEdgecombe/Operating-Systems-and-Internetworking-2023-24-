package Practical1;

public class Hello {

    public static void main(String[] args) {
        String firstName = args[0];
        String lastName = args[1];

        String greeting = "Hello " + firstName + " " + lastName + "!";
        System.out.println(greeting);
    }
}
