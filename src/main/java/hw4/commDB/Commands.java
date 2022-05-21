package hw4.CRUD;

import java.util.List;

public interface Commands {
    public void insertData(Object object);
    public Object selectData(int id);
    public List<Object> selectAllData(int id);
    public boolean delete(int id);
    public boolean update(Object object);

}
