package hw4.commandsDB.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Skills {
    private int idDev;
    private boolean hasJava;
    private boolean hasCPlusPlus;
    private boolean hasCSharp;
    private boolean hasJS;
    private String levelOfPosition;

    public final static Skills INCORRECT_QUERY = new Skills(
            0,false,false,false,false,"error");

}
