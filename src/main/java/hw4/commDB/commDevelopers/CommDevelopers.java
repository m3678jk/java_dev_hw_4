package hw4.commDB;

import hw4.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommDevelopers implements Commands {
    private final static String INSERT_DATA = "insert into developers (firstName, secondName, age, sex) values  (?,?,?,?)";
    private final static String SELECT_DEV_BY_ID = "select * from developers where id =?";
    private static final String SELECT_ALL_DEV = "select * from developers";
    private static final String DELETE_DEV = "delete from developers where id = ?";
    private static final String UPDATE_DEV = "update developers set firstName = ?,secondName= ?, age =?, sex=? where id = ?";

    private final PreparedStatement insertSt;
    private final PreparedStatement selectSt;
    private final PreparedStatement selectAllSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;

    public CommDevelopers(DatabaseConnector databaseConnector) throws SQLException {
        Connection connection = databaseConnector.getConnection();
                insertSt = connection.prepareStatement(INSERT_DATA);
                selectSt = connection.prepareStatement(SELECT_DEV_BY_ID);
                selectAllSt = connection.prepareStatement(SELECT_ALL_DEV);
                deleteSt = connection.prepareStatement(DELETE_DEV);
                updateSt = connection.prepareStatement(UPDATE_DEV);
    }

   @Override
    public boolean insertData(Object object) {
       Developer dev = (Developer) object;
       try {
           insertSt.setString(1,dev.getFirstName());
           insertSt.setString(2,dev.getSecondName());
           insertSt.setInt(3,dev.getAge());
           insertSt.setString(4, dev.getSex());

           return insertSt.executeUpdate()==1;
       } catch (SQLException e) {
           e.printStackTrace();
       }
    return false;


    }

    @Override
    public Object selectData(int id) {
        try {
            selectSt.setLong(1,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet resultSet = selectSt.executeQuery()){
            if( !resultSet.next()){
                System.out.println("incorrect id");
            }
            return new Developer(resultSet.getString("firstName"),
                    resultSet.getString("secondName"),
                    resultSet.getInt("age"),
                    resultSet.getString("sex")); // to fix String/enum convert
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object> selectAllData() {
        List <Object> developers = new ArrayList<>();

        try (ResultSet resultSet = selectAllSt.executeQuery()){
            while(resultSet.next()){
                developers.add(new Developer(resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getInt("age"),
                        resultSet.getString("sex")));
            }
            return developers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            deleteSt.setLong(1,id);
            return deleteSt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, Object object) {
        Developer dev = (Developer) object;
        if(selectData(id)!=null){
            try {
                updateSt.setString(1, dev.getFirstName());
                updateSt.setString(2, dev.getSecondName());
                updateSt.setInt(3, dev.getAge());
                updateSt.setString(4, String.valueOf(dev.getSex()));
                updateSt.setInt(5, id);

                return updateSt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Incorrect id");
        return false;
    }
}



