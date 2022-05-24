package hw4;

import hw4.DatabaseConnector;
import hw4.commandsDB.OperationOnDB;
import hw4.commandsDB.commands.*;
import lombok.Data;

import java.sql.SQLException;

@Data
public class ServiceDB {
    private CommandsDevelopers commandsDevelopers;
    private CommandsSkills commandsSkills;
    private CommandsProject commandsProject;
    private CommandsCompanies commandsCompanies;
    private CommandsCustomers commandsCustomers;
    private CommandsCompanyProject commandsCompanyProject;
    private CommandsCustomerProject commandsCustomerProject;
    private CommandsProjectDeveloper commandsProjectDeveloper;
    private OperationOnDB operationOnDB;


    public ServiceDB() throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector();

        commandsDevelopers = new CommandsDevelopers(databaseConnector,
                CommandsDevelopers.INSERT, CommandsDevelopers.SELECT,
                CommandsDevelopers.SELECT_ALL, CommandsDevelopers.DELETE,
                CommandsDevelopers.UPDATE);

        commandsSkills = new CommandsSkills(databaseConnector,
                CommandsSkills.INSERT, CommandsSkills.SELECT,
                CommandsSkills.SELECT_ALL, CommandsSkills.DELETE,
                CommandsSkills.UPDATE);

        commandsProject = new CommandsProject(databaseConnector,
                CommandsProject.INSERT, CommandsProject.SELECT,
                CommandsProject.SELECT_ALL, CommandsProject.DELETE,
                CommandsProject.UPDATE);

        commandsCompanies = new CommandsCompanies(databaseConnector,
                CommandsCompanies.INSERT, CommandsCompanies.SELECT,
                CommandsCompanies.SELECT_ALL, CommandsCompanies.DELETE,
                CommandsCompanies.UPDATE);

        commandsCustomers = new CommandsCustomers(databaseConnector,
                CommandsCustomers.INSERT, CommandsCustomers.SELECT,
                CommandsCustomers.SELECT_ALL, CommandsCustomers.DELETE,
                CommandsCustomers.UPDATE);

        commandsCompanies = new CommandsCompanies(databaseConnector,
                CommandsCompanies.INSERT, CommandsCompanies.SELECT,
                CommandsCompanies.SELECT_ALL, CommandsCompanies.DELETE,
                CommandsCompanies.UPDATE);

        commandsProjectDeveloper = new CommandsProjectDeveloper(databaseConnector,
                CommandsProjectDeveloper.INSERT, CommandsProjectDeveloper.SELECT,
                CommandsProjectDeveloper.SELECT_ALL, CommandsProjectDeveloper.DELETE,
                CommandsProjectDeveloper.UPDATE);

        commandsCompanyProject = new CommandsCompanyProject(databaseConnector,
                CommandsCompanyProject.INSERT, CommandsCompanyProject.SELECT,
                CommandsCompanyProject.SELECT_ALL, CommandsCompanyProject.DELETE,
                CommandsCompanyProject.UPDATE);

        commandsCustomerProject = new CommandsCustomerProject(databaseConnector,
                CommandsCustomerProject.INSERT, CommandsCustomerProject.SELECT,
                CommandsCustomerProject.SELECT_ALL, CommandsCustomerProject.DELETE,
                CommandsCustomerProject.UPDATE);

        operationOnDB = new OperationOnDB(databaseConnector);

    }
}