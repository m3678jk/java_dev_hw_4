package hw4.commandsDB;

import hw4.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationOnDB {
    protected PreparedStatement getSumOfSalarySt;
    protected PreparedStatement getListOfDevelopersSt;
    protected PreparedStatement getListOfJavaDevSt;
    protected PreparedStatement getListOfMidDevSt;
    protected PreparedStatement getListOfProjectSt;


    public OperationOnDB(DatabaseConnector databaseConnector) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        getSumOfSalarySt = connection.prepareStatement(GET_SUM_OF_SALARY_BY_ID_PROJECT_QUERY);
        getListOfDevelopersSt = connection.prepareStatement(GET_LIST_OF_DEV_BY_ID_PROJECT);
        getListOfJavaDevSt = connection.prepareStatement(GET_LIST_OF_JAVA_DEV);
        getListOfMidDevSt = connection.prepareStatement(GET_LIST_OF_MID_DEV);
        getListOfProjectSt = connection.prepareStatement((GET_LIST_OF_PROJECT_DATE_NAME_QTY_OF_DEV_FORMAT));
    }

    public int getSumOfSalary(int projectId) {
        int result = 0;
        try {
            getSumOfSalarySt.setLong(1, projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet resultSet = getSumOfSalarySt.executeQuery()) {
            if (!resultSet.next()) {
                System.out.println("incorrect id");
            }
            result = resultSet.getInt("sum_of_salary");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //is it ok to creating String? or it is better to create new object with fields: id, firstName, secondName?
    public List<String> getListOfDevelopers(int projectId) {
        List result = new ArrayList();
        try {
            getListOfDevelopersSt.setLong(1, projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet resultSet = getListOfDevelopersSt.executeQuery()) {
            while (resultSet.next()) {
                result.add("\ndev: " + resultSet.getString("id_d") + " " +
                        resultSet.getString("f_name") + " " +
                        resultSet.getString("s_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<String> getListOfJavaDev() {
        List<String> result = new ArrayList<>();
        try (ResultSet resultSet = getListOfJavaDevSt.executeQuery()) {
            while (resultSet.next()) {
                result.add("\nID: " + resultSet.getString("id_dev") + " " +
                        resultSet.getString("f_name") + " " +
                        resultSet.getString("s_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public List<String> getListMidDev() {
        List<String> result = new ArrayList<>();
        try (ResultSet resultSet = getListOfMidDevSt.executeQuery()) {
            while (resultSet.next()) {
                result.add("\nID: " + resultSet.getString("id_dev") + " " +
                        resultSet.getString("f_name") + " " +
                        resultSet.getString("s_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public List<String> getListOfProject() {
        List<String> result = new ArrayList<>();
        try (ResultSet resultSet = getListOfProjectSt.executeQuery()) {
            while (resultSet.next()) {
                result.add("\ndate: " + resultSet.getString("st_d") + " " +
                        resultSet.getString("name_pr") + " " +
                        resultSet.getString("total_dev"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return result;
    }

    // where should be stored such kind of static query?
    public static final String GET_SUM_OF_SALARY_BY_ID_PROJECT_QUERY = "select project_developer.id_project as id_pr, sum(developers.salary) as sum_of_salary\n" +
            "\tfrom developers \n" +
            "\tjoin project_developer on developers.id = project_developer.id_developer \n" +
            "\tgroup by id_pr\n" +
            "\thaving id_pr =?\n";

    public static final String GET_LIST_OF_DEV_BY_ID_PROJECT = "select project_developer.id_project as id_pr, developers.id as id_d," +
            "\tdevelopers.firstName as f_name,  developers.secondName as s_name\n" +
            "\tfrom developers \n" +
            "\tjoin project_developer on developers.id = project_developer.id_developer \n" +
            "\thaving id_pr = ?\n";

    public static final String GET_LIST_OF_JAVA_DEV = "select developers.id as id_dev, skills.java as java, \n" +
            "    developers.firstName as f_name,  developers.secondName as s_name\n" +
            "\tfrom developers \n" +
            "\tjoin skills on developers.id = skills.id_developer \n" +
            "    having java = true\n";
    public static final String GET_LIST_OF_MID_DEV = "select developers.id as id_dev, skills.levelOfPosition as java, \n" +
            "\tdevelopers.firstName as f_name,  developers.secondName as s_name\n" +
            "\tfrom developers \n" +
            "\tjoin skills on developers.id = skills.id_developer \n" +
            "\thaving levelOfPosition = \"middle\"";


    public static final String GET_LIST_OF_PROJECT_DATE_NAME_QTY_OF_DEV_FORMAT = "select projects.start_date as st_d, projects.name_of_project as name_pr, projects.id_project as id_pr,\n" +
            " count(*) as total_dev\n" +
            "\tfrom projects \n" +
            "\tjoin project_developer on projects.id_project = project_developer.id_project\n" +
            "\tgroup by id_pr\n";
}
