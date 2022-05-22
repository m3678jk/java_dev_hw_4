package hw4.commDB;

import hw4.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperationOnDB {
    public static final String GET_SUM_OF_SALARY_BY_ID_PROJECT_QUERY = "select project_developer.id_project as id_pr, sum(developers.salary) as sum_of_salary\n" +
            "\tfrom developers \n" +
            "\tjoin project_developer on developers.id = project_developer.id_developer \n" +
            "\tgroup by id_pr\n" +
            "\thaving id_pr =?\n";

    protected PreparedStatement getSumOfSalarySt;
    public OperationOnDB(DatabaseConnector databaseConnector) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        getSumOfSalarySt = connection.prepareStatement(GET_SUM_OF_SALARY_BY_ID_PROJECT_QUERY);
    }

   public void getSumOfSalary(int projectId){
        int result = 0;
       try {
           getSumOfSalarySt.setLong(1, projectId);
       } catch (SQLException e) {
           e.printStackTrace();
       }
        try(ResultSet resultSet = getSumOfSalarySt.executeQuery()){
            if (!resultSet.next()) {
                System.out.println("incorrect id");
            }
                result = resultSet.getInt("sum_of_salary");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       System.out.println(result);
   }
   public List<Object> getListOfDevelopers(int projectId){
       return null;
   }
   public List<Object>getListOfJavaDev(){
       //TODO
       return null;
   }
    public List<Object>getListMidDev(){
       //TODO
        return null;
    }
    public List<String> getListOfProject(){
       //TODO
        return null;
    }
}
