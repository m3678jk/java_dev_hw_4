package hw4.commDB.commDevelopers;


import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Developer {
    private String firstName;
    private String secondName;
    private int age;
    private String sex;
    private int salary;

    public static final Developer INCORRECT_QUERY = new Developer("error", "error", 0, "unknown", 0);


}