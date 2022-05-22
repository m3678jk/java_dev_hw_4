package hw4.commDB.commSkills;

import hw4.DatabaseConnector;
import hw4.commDB.Commands;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommSkills extends Commands {

    public static final String INSERT = "insert into skills (id_developer, java, c_plus_plus, c_sharp, js, levelOfPosition) values  (?,?,?,?,?,?)";
    public static final String SELECT = "select * from skills where id_skills =?";
    public static final String SELECT_ALL = "select * from skills";
    public static final String DELETE = "delete from skills where id_skills = ?";
    public static final String UPDATE = "update skills set id_developer = ?,java= ?, c_plus_plus =?, c_sharp=?, js=?,levelOfPosition=? where id_skills = ?";

    public CommSkills(DatabaseConnector databaseConnector, String insert, String select, String selectAll, String delete, String update) throws SQLException {
        super(databaseConnector, insert, select, selectAll, delete, update);
    }

    @Override
    public boolean insertData(Object object) {
        Skills skills = (Skills) object;
        try {
            insertSt.setInt(1, skills.getIdDev());
            insertSt.setBoolean(2, skills.isHasJava());
            insertSt.setBoolean(3, skills.isHasCPlusPlus());
            insertSt.setBoolean(4, skills.isHasCSharp());
            insertSt.setBoolean(5, skills.isHasJS());
            insertSt.setString(6, skills.getLevelOfPosition());

            return insertSt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object selectData(int id) {
        try {
            selectSt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet resultSet = selectSt.executeQuery()) {
            if (!resultSet.next()) {
                System.out.println("incorrect id");
            }
            return new Skills(resultSet.getInt("id_developer"),
                    resultSet.getBoolean("java"),
                    resultSet.getBoolean("c_plus_plus"),
                    resultSet.getBoolean("c_sharp"),
                    resultSet.getBoolean("js"),
                    resultSet.getString("levelOfPosition"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Skills.INCORRECT_QUERY;
    }

    @Override
    public boolean updateData(int id, Object object) {
        Skills skills = (Skills) object;
        if(!selectData(id).equals(Skills.INCORRECT_QUERY)){
            try {
                updateSt.setInt(1, skills.getIdDev());
                updateSt.setBoolean(2, skills.isHasJava());
                updateSt.setBoolean(3, skills.isHasCPlusPlus());
                updateSt.setBoolean(4, skills.isHasCSharp());
                updateSt.setBoolean(5, skills.isHasJS());
                updateSt.setString(6, skills.getLevelOfPosition());
                updateSt.setInt(7, id);

                return updateSt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Incorrect id");
        return false;
    }
}
