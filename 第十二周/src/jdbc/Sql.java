package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sql {
    //"jdbc:mysql://localhost:3306/访问的数据库名称?characterEncoding=utf-8"数据库的访问地址
    static final  String URL = "jdbc:mysql://localhost:3306/shopdb?characterEncoding=utf-8";
    static final String USERNAME = "root";
    static final String PASSWORD = "5201314";
    static final String DRIVER = "com.mysql.jdbc.Driver"; //数据库驱动名称

    public static void main(String[] args) throws Exception {
        //使用反射技术加载数据库连接驱动
        Class.forName(DRIVER);
        //建立数据库连接
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        //创建能够传入sql语句的对象
        Statement statement = connection.createStatement();
        //传入需要执行的sql ResultSet对象是用来存放查询结果的对象
        ResultSet resultSet = statement.executeQuery("select * from category");
        //得到sql语句的处理结果
        //解析处理结果的数据
        System.out.println("编号\t\t\t名称");
        while (resultSet.next()){
            System.out.println(resultSet.getInt("cid")+"\t\t\t"+resultSet.getString("cname"));
        }
        //关闭数据库连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
