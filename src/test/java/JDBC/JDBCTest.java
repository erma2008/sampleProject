package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        //we use 3 interface to make a connection with database
        //Connection,Statement,ResultSet
        Connection connection= DriverManager.getConnection(
                "jdbc:oracle:thin:@techotrialdb.c2cuobkpe1em.us-east-2.rds.amazonaws.com:1521:xe",
                "hr","hr");
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        //ResultSet -> to get rows data from executed query
        ResultSet resultSet=statement.executeQuery("SELECT * FROM employees");
        resultSet.beforeFirst();
        resultSet.next();
        System.out.println(resultSet.getString("FIRST_NAME"));
        System.out.println(resultSet.getString("salary"));
        System.out.println(resultSet.getString("hire_date"));
        resultSet.next();
        System.out.println(resultSet.getString("first_name"));

        while(resultSet.next()==true){
            System.out.println(resultSet.getString("first_name")+" "+resultSet.getString("last_name"));
        }
        resultSet.last();
        System.out.println(resultSet.getRow());
        //++++++++++++Wroking with DataBaseMetaData to get database details( username,database name...)
        DatabaseMetaData metaData=connection.getMetaData();
        System.out.println("User: "+metaData.getUserName());
        System.out.println("Database: "+metaData.getDatabaseProductName());
        System.out.println(metaData.getCatalogs());

        //ResultSetMetaData -> to get the column numbers and column names of executed query
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        System.out.println("Column number "+resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnType(1));
        System.out.println(resultSetMetaData.getColumnTypeName(1));
        System.out.println(resultSetMetaData.getColumnClassName(1));

        resultSet.first();
      for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
          System.out.println(resultSetMetaData.getColumnName(i));
      }

        List<Map<String,Object>>data=new ArrayList<>();
      while(resultSet.next()){
          Map<String,Object>map=new HashMap<>();
          for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
              map.put(resultSetMetaData.getColumnName(i),
                      resultSet.getObject(resultSetMetaData.getColumnName(i)));
          }
          data.add(map);
      }
        System.out.println(data);

        System.out.println("++++++++++++SALARY+++++++++");

        for(int i=0;i<data.size();i++){
            System.out.println(data.get(i).get("Salary".toUpperCase()));
        }



    }
}
