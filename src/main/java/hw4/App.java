package hw4;

import hw4.commDB.Commands;
import hw4.commDB.OperationOnDB;
import hw4.commDB.commDevelopers.CommDevelopers;
import hw4.commDB.commDevelopers.Developer;
import hw4.commDB.commProjectDeveloper.CommProjectDeveloper;
import hw4.commDB.commProjects.CommProject;
import hw4.commDB.commProjects.Project;
import hw4.commDB.commSkills.CommSkills;
import hw4.commDB.commSkills.Skills;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector();
        Connection connection = databaseConnector.getConnection();


        CommDevelopers commDevelopers = new CommDevelopers(databaseConnector,
                CommDevelopers.INSERT, CommDevelopers.SELECT, CommDevelopers.SELECT_ALL, CommDevelopers.DELETE, CommDevelopers.UPDATE);
        CommSkills commSkills = new CommSkills(databaseConnector,
                CommSkills.INSERT,CommSkills.SELECT, CommSkills.SELECT_ALL, CommSkills.DELETE, CommSkills.UPDATE);

        CommProject commProject = new CommProject(databaseConnector,
                CommProject.INSERT, CommProject.SELECT, CommProject.SELECT_ALL, CommProject.DELETE, CommProject.UPDATE);


        //--All operation on developers table
        //System.out.println("commDevelopers.insertData(new Developer(\"name1\", \"secondName2\", 30, \"male\", 3000)) = " + commDevelopers.insertData(new Developer("name1", "secondName2", 30, "male", 3000)));
        //System.out.println("commDevelopers.selectData(6) = " + commDevelopers.selectData(6));
        //System.out.println("commDevelopers.selectAllData() = " + commDevelopers.selectAllData("id"));
        //System.out.println("commDevelopers.delete(7) = " + commDevelopers.delete(6));
        //System.out.println("commDevelopers.updateData(7,new Developer(\"name1\", \"secondName2\", 36, \"male\",4000)) = " + commDevelopers.update(8, new Developer("ch2", "ch", 36, "male")));
        //System.out.println("commDevelopers.selectAllData() = " + commDevelopers.selectAllData());

        //--All operation on skills table
//        System.out.println("commSkills.insertData(new Skills(7,true, true, false, false, \"middle\")) = "
//                       + commSkills.insertData(
//                                new Skills(7, true, true, false, false, "middle")));
//        System.out.println("commSkills.selectData(4) = " + commSkills.selectData(4));
        //System.out.println("commSkills.selectAllData() = " + commSkills.selectAllData());
//        System.out.println("commSkills.delete(7) = " + commSkills.delete(5));
//        System.out.println("commSkills.delete(8) = " + commSkills.delete(8));
//        System.out.println("commSkills.updateData(4, new Skills(4,true, true, true, false, \"Middle\")) = " +
//                commSkills.update(4, new Skills(
//                        4, true, true, true, false, "Middle")));


        // -- All operation with Projects table
//        System.out.println("commProject.selectAllData() = " + commProject.selectAllData("id_project"));
//        System.out.println("commProject.selectData(6) = " + commProject.selectData(5));
//        commProject.update(6,new Project("warehouse","testUpdate", LocalDate.parse("2021-07-07")));
//       System.out.println("commProject.selectData(6) = " + commProject.selectData(6));
//        //System.out.println("commProject.selectAllData() = " + commProject.selectAllData("id_project"));
       // System.out.println("commProject.delete(6) = " + commProject.delete(6));
       // System.out.println("commProject.selectAllData() = " + commProject.selectAllData("id_project"));

        //System.out.println("commPD.selectAllData(\"id_pr_dev\") = " + commPD.selectAllData("id_pr_dev"));


        // -- Here is sum od salary of all dev specific project
        CommProjectDeveloper commPD = new CommProjectDeveloper(databaseConnector,
                CommProjectDeveloper.INSERT,CommProjectDeveloper.SELECT,
                CommProjectDeveloper.SELECT_ALL,CommProjectDeveloper.DELETE,
                CommProjectDeveloper.UPDATE);

        OperationOnDB op = new OperationOnDB(databaseConnector);
        op.getSumOfSalary(3);

    }
}
