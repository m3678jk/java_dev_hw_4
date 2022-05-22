package hw4.commDB;

import hw4.DatabaseConnector;
import hw4.commDB.commDevelopers.Developer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// insertData selectData and updateData methods should be overwritten in each class
public abstract class Commands<V>{
    protected V value;
    protected String insert;
    protected String select;
    protected String selectAll;
    protected String delete;
    protected String update;

    protected final PreparedStatement insertSt;
    protected final PreparedStatement selectSt;
    protected final PreparedStatement selectAllSt;
    protected final PreparedStatement deleteSt;
    protected final PreparedStatement updateSt;

    public Commands(DatabaseConnector databaseConnector,
                    String insert, String select, String selectAll, String delete, String update ) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        this.insert = insert;
        this.select = select;
        this.selectAll = selectAll;
        this.delete = delete;
        this.update = update;

        insertSt = connection.prepareStatement(insert);
        selectSt = connection.prepareStatement(select);
        selectAllSt = connection.prepareStatement(selectAll);
        deleteSt = connection.prepareStatement(delete);
        updateSt = connection.prepareStatement(update);
    }

    public abstract boolean insertData(V object);

    public abstract V selectData(int id) ;


    public Map<Integer,V> selectAllData(String nameOfColumnWithPrimaryKey) {
       Map <Integer,V> objects = new HashMap<>();

        try (ResultSet resultSet = selectAllSt.executeQuery()){
            while(resultSet.next()){
                objects.put(resultSet.getInt(nameOfColumnWithPrimaryKey),
                        selectData(resultSet.getInt(nameOfColumnWithPrimaryKey)));
            }
            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Map.of(0,null);
    }


    public boolean delete(int id) {
        try {
            deleteSt.setLong(1,id);
            return deleteSt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public abstract boolean updateData(int id, V object);

}
