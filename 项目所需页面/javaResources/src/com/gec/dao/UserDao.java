package com.gec.dao;

import com.gec.entity.User;
import com.gec.test.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    //QueryRunner类是Dbutils的核心类之一，它显著的简化了SQL的查询 并与ResultSetHandel协同工作将使代码量大为减少
    /**
     * QueryRunner包含下面几个方法
     * 1.query(Connection conn, String sql, Object[] params, ResultSetHandler rsh)：执行选择查询，在查询中，对象阵列的值被用来作为查询的置换参数。
     * 2.query(String sql, Object[] params, ResultSetHandler rsh)：方法本身不提供数据库连接，执行选择查询，在查询中，对象阵列的值被用来作为查询的置换参数。
     * 3.query(Connection conn, String sql, ResultSetHandler rsh)：执行无需参数的选择查询。
     * 4.update(Connection conn, String sql, Object[] params)：被用来执行插入、更新或删除（DML）操作
     *
     * 其中，ResultSetHandler接口执行处理一个结果集对象，将数据转变并处理为任何一种形式，供其他应用使用
     * 1.ArrayHandler：把结果集中的第一行数据转成对象数组。
     * 2.ArrayListHandler：把结果集中的每一行数据都转成一个对象数组，再存放到List中。
     * 3.BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
     * 4.BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
     * 5.MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值
     * 6.MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
     * 7.ColumnListHandler：将结果集中某一列的数据存放到List中
     * 8.KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里(List<Map>)，再把这些map再存到一个map里，其key为指定的列。
     * 9.ScalarHandler:将结果集第一行的某一列放到某个对象中
     */

    /**
     * 通过用户名和密码查询用户
     *
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public User findUserByUsernameAndPassword(String username, String password) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //创建一个字符串数组用来存放待会执行query用来替换的参数
        String[] args = {username, password};  //用来替换的参数为用户名和密码
        //执行sql语句,并将返回的结果封装在user对象中
        User user = queryRunner.query("select * from user where username=? and password=?", args, new BeanHandler<>(User.class));
        return user;  //将user返回
    }

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT * FROM USER WHERE username = ?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 传入一个user对象添加该对象
     *
     * @param user
     * @return
     */
    public int addUser(User user) {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //创建一个字符串用来存放sql语句 对user表执行插入操作
        String sql = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        //创建一个字符串数组用来存放待会调用update方法需要替换的参数
        String [] arr = {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail()
                ,user.getTelephone(),user.getBirthday(),user.getSex(),String.valueOf(user.getState()),user.getCode()
                ,user.getAddress()};
        int row = 0;
        try {
            //调用update方法执行sql语句 将返回的结果存放在row中
            row = queryRunner.update(sql, arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
