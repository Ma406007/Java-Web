package Test01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;

public class JDBC_Test {
    @Test
    //JDBC程序执行DML语句:int rowsAffected = statement.executeUpdate();返回的是影响的记录数
    public void testUpdate() throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "123456";

        //3.获取SQL语句执行对象
        Connection connection = DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();

        //4.执行SQL
        statement.executeUpdate("update user set password = '1234567890' where id = 1");

        //5.释放资源
        statement.close();
        connection.close();
    }

    @ParameterizedTest
    //如果在测试时需要传递一组参数,可以使用@CsvSource注解
    @CsvSource({"daqiao, 123456"})
    //JDBC程序执行DQL语句:ResultSet resultSet = statement.executeQuery();返回值是查询的结果集
    public void testSelect(String _username, String _password) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.创建预编译的PreparedStatement对象(?为占位符)
        /*
          预编译的SQL是在项目开发中推荐使用的SQL语句,优点有两个:
            1.防止SQL注入
            2.性能更高
          注意:在以后的项目开发中我们使用的基本全部都是预编译SQL语句
        */
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username = ? and password = ?");
        //设置参数
        preparedStatement.setString(1, _username);
        preparedStatement.setString(2, _password);

        //4.执行查询
        /*
          ResultSet(结果集对象):封装了DQL查询语句查询的结果
            1.next():将光标从当前位置向后移动一行,并判断当前行是否为有效行,返回值为boolean
              - 结果为true:有效行,当前行有数据
              - 结果为false:无效行,当前行没有数据
            2.getXxx(...):获取数据,可以根据列的编号获取,也可以根据列名获取(推荐)
        */
        ResultSet resultSet = preparedStatement.executeQuery();

        //5.处理结果集
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String uName = resultSet.getString("username");
            String pwd = resultSet.getString("password");
            String name =  resultSet.getString("name");
            int age = resultSet.getInt("age");

            System.out.println("ID:" + id + ", Username:" + uName + ", Password:" + pwd + ", Name:" + name + ", Age:" + age);
        }

        //6.关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    /*
      需求:基于JDBC程序执行如下update语句
      SQL:
        update user set password = '123456', age = 22 where id = 1;
    */
    @ParameterizedTest
    @CsvSource({"1,123456,22"})
    public void testUpdate1(int _id, String _password, int _age) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        //3.创建预编译的PreparedStatement对象(?为占位符)
        PreparedStatement preparedStatement = connection.prepareStatement("update user set password = ?, age = ? where id = ?");
        //设置参数
        preparedStatement.setString(1, _password);
        preparedStatement.setInt(2, _age);
        preparedStatement.setInt(3, _id);

        //4.执行语句
        int rowsUpdated = preparedStatement.executeUpdate();
        //输出结果
        System.out.println(rowsUpdated + " row(s) updated.");

        //5.关闭资源
        preparedStatement.close();
        connection.close();
    }
}
