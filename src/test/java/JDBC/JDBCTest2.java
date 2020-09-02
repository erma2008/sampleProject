package JDBC;

import Utils.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCTest2 {
    public static void main(String[] args) throws SQLException {
        DBUtils.establishConnection();
       List<Map<String,Object>> queryResult=DBUtils.runQuery("Select first_name,last_name,salary from employees");
       System.out.println(queryResult.get(0).get("last_name".toUpperCase()));
    DBUtils.closeConnection();
    DBUtils.establishConnection();
        System.out.println(DBUtils.getRowsNumber("Select first_name from employees"));
    DBUtils.closeConnection();
    }
}
