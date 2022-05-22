package hw4.commDB.commCustomers;

import hw4.commDB.commCompanies.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Customer {
    private String name;
    private String address;
    public static final Customer INCORRECT_QUERY = new Customer("error","error");

}
