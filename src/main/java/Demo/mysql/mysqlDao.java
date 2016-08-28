package Demo.mysql;

import Demo.util.common;
import com.google.gson.JsonArray;
//import com.sun.org.apache.xpath.internal.operations.String;
//import com.sun.org.apache.xpath.internal.operations.String;

import  java.lang.*;
import java.beans.Statement;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smq on 16/7/23.
 */




public class mysqlDao {
    public  Connection conn = null;
    PreparedStatement statement = null;

    //    private void mysqlDao() throws IOException {
//        connSQL();
//    }
    // connect to MySQL
    public void connSQL() throws IOException {

        String server = common.GetConfigValue("mysql.server");
        String port = common.GetConfigValue("mysql.port");
        String database = common.GetConfigValue("mysql.database");//"jdbc:mysql://localhost:3306/demo?characterEncoding=UTF-8";
        String username = common.GetConfigValue("mysql.user");//"root";
        String password = common.GetConfigValue("mysql.password");//""; // 加载驱动程序以连接数据库
//?useUnicode=true&characterEncoding=utf8
        String url = "jdbc:mysql://"+server+":"+port+"/"+database+"?useUnicode=true&characterEncoding=utf8";


        try {
            Class.forName("com.mysql.jdbc.Driver" );
            conn = DriverManager.getConnection( url,username, password );
        }
        //捕获加载驱动程序异常
        catch ( ClassNotFoundException cnfex ) {
            System.err.println(
                    "装载 JDBC/ODBC 驱动程序失败。" );
            cnfex.printStackTrace();
        }
        //捕获连接数据库异常
        catch ( SQLException sqlex ) {
            System.err.println( "无法连接数据库" );
            sqlex.printStackTrace();
        }
    }

    // disconnect to MySQL
    void deconnSQL() {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("关闭数据库问题 ：");
            e.printStackTrace();
        }
    }

    // execute selection language
    public ResultSet selectSQL(String sql) {
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //执行SQL命令
    public boolean ExecuteSql(String sqlCmd){
        try {
            statement = conn.prepareStatement(sqlCmd);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Sql-op数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("sql-op 插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    /***
     * 返回列表并转为json串
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String selectnamelist001()throws SQLException, IOException {
        PreparedStatement psql;
        psql= conn.prepareStatement("select code,first_name  ,second_name ,'original' as userdb_status from namelist order by code");
        ResultSet res =psql.executeQuery();
        java.lang.String strjson = common.ResultSetToJsonStr(res);
//        java.lang.String strjson = common.ResultSetToJsonString(res);
        System.out.println(strjson);
        return strjson;
    }

    public java.lang.String selectnamelist103()throws SQLException, IOException {
        PreparedStatement psql;
        psql= conn.prepareStatement("select code,first_name  ,second_name ,'original' as userdb_status from namelist order by code");
        ResultSet res =psql.executeQuery();
//        java.lang.String strjson = common.ResultSetToJsonStr(res);
//        java.lang.String strjson = common.ResultSetToJsonString(res);
        java.lang.String jaStr = common.ResultSetToJsonString(res);
//        System.out.println(strjson);
        return jaStr;
    }
    public void utfTran() {
        String testStr = "中国人民123";
        try {
            // 得到指定编码的字节数组 字符串--->字节数组
            byte[] t_iso = testStr.getBytes("ISO8859-1");
            byte[] t_gbk = testStr.getBytes("GBK");
            byte[] t_utf8 = testStr.getBytes("UTF-8");
            System.out.println("使用ISO解码..." + t_iso.length);
            System.out.println("使用GBK解码..." + t_gbk.length);
            System.out.println("使用UTF8解码..." + t_utf8.length);
            // 解码后在组装
            String ut_iso = new String(t_iso, "ISO8859-1");
            String ut_gbk = new String(t_gbk, "GBK");
            String ut_utf8 = new String(t_utf8, "UTF-8");
            System.out.println("使用ISO解码后再用ISO组装..." + ut_iso);
            System.out.println("使用GBK解码后再用GBK组装..." + ut_gbk);
            System.out.println("使用UTF8解码后再用UTF8组装..." + ut_utf8);
            // 有时候要求必须是iso字符编码类型
            // 可以先用GBK/UTF8编码后，用ISO8859-1组装成字符串，解码时逆向即可获得正确中文字符
            String t_utf8Toiso = new String(t_utf8, "ISO8859-1");
            // 将iso编码的字符串进行还原
            String ut_utf8Toiso = new String(t_utf8Toiso.getBytes("ISO8859-1"),"UTF-8");
            System.out.println("使用ISO组装utf8编码字符..." + t_utf8Toiso);
            System.out.println("使用ISO解码utf8编码字符..." + ut_utf8Toiso);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void selectnamelist() throws SQLException, IOException {
//        Demo.mysql.mysqlDao db = new Demo.mysql.mysqlDao();
//        db.connSQL();

//        db.InsertNameList(first_name,second_name);
//        out.println("namelist:select * from namelist<br>");
//        out.println("<hr>");
        PreparedStatement psql;
        String code,first_name,second_name;
//        ResultSet res;
/*
        //预处理添加数据，其中有两个参数--“？”
//        psql = conn.prepareStatement(insert);
        System.out.printf("Delete 内容:%s %n", common.GetCurrentDateTime());
        psql=conn.prepareStatement("delete from namelist");
        System.out.printf("已经delete start:%s %n",common.GetCurrentDateTime());
        psql.executeUpdate();

        System.out.printf("准备插入数据:%s %n",common.GetCurrentDateTime());

        for (int i1 = 1;i1<100;i1++){
            first_name=String.format("name_%s",i1);
            second_name = String.format("second_name%s",i1);
            InsertNameList(first_name,second_name);
        }
*/
        first_name="日本人";
//        String  str_name = new String(first_name.getBytes("iso-8859-1"),"UTF-8");

        String updateSql="update namelist set first_name=? ";
//        String strSql=new String(updateSql.getBytes("iso-8859-1"),"UTF-8");

        psql=conn.prepareStatement(updateSql);

        psql.setString(1,first_name);

        System.out.println("已经更新start:"+updateSql);
        psql.executeUpdate();
        System.out.println("已经更新OK:"+updateSql);

        psql= conn.prepareStatement("select * from namelist order by code");
//            res = psql.executeQuery();          //执行预处理sql语句
        ResultSet res =psql.executeQuery();



        System.out.println("code------first_name-------second_name");

        while(true){
            if (!(res.next())) break;
            code = res.getString("code");

//            first_name = res.getString("first_name");
            String utf8Str= res.getString("first_name");
            first_name = new String(utf8Str.getBytes("UTF-8"),"ISO-8859-1");
            ;//new String( first_name.getBytes(Charset.forName("UTF-8")));
//            first_name = new String( first_name.getBytes(Charset.forName("UTF-8")));

            second_name = res.getString("second_name");
//            second_name = new String(second_name.getBytes("latin1"),"utf-8");

            //ju_userName = new String(ju_userName.getBytes("ISO-8859-1"),"gb2312");
//            System.out.println(first_name + "\t" + second_name);
            System.out.println(""+code+"\t\t"+utf8Str+"\t\t"+first_name+"\t\t"+second_name+"");
        }
    }
    //插入人员列表
    public void InsertNameList(String first_name,String second_name) throws SQLException, IOException {
        String insert = "insert into namelist(first_name,second_name) values(?,?)";
        PreparedStatement psql;
//        connSQL();
        first_name = common.GetUtfString(first_name);
        second_name = common.GetUtfString(second_name);
//        first_name=new String(first_name.getBytes("iso-8859-1"),"UTF-8");
//        second_name=new String(second_name.getBytes("iso-8859-1"),"UTF-8");
        psql = conn.prepareStatement(insert);
        psql.setString(1, first_name);
        psql.setString(2,second_name);

        psql.executeUpdate();

    }
    /***
     * 返回列表并转为值列表
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public List<ArrayList<String>> selectnamelisttovaluelist()throws SQLException, IOException {
        PreparedStatement psql;
        psql= conn.prepareStatement("select code,first_name  ,second_name ,'original' as userdb_status from namelist order by code");
        ResultSet res =psql.executeQuery();
//        java.lang.String strjson = common.ResultSetToJsonStr(res);
        List<ArrayList<String>> strList = common.ResultSetToStrListOnlyValue(res);
        return strList;
    }
    /***
     * 返回列表并转为json串
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String selectnamelist002()throws SQLException, IOException {
        PreparedStatement psql;
        psql= conn.prepareStatement("select first_name,second_name from namelist where code<20 order by code");
        ResultSet res =psql.executeQuery();
        java.lang.String strjson = common.ResultSetToJsonStr(res);
        return strjson;
    }
    //测试用例,动态传入参数
    public void runSql() throws SQLException {
        String insert = "insert into ju_users(ju_userID,TaobaoID,ju_userName,ju_userPWD) values(?,?,?,?)";
        String update = "update ju_users set ju_userPWD =123 where ju_userName= 'mm'";
        String delete = "delete from ju_users where ju_userName= 'mm'";

        PreparedStatement psql;
        ResultSet res;

        //预处理添加数据，其中有两个参数--“？”
        psql = conn.prepareStatement(insert);

        psql.setString(1, "888");              //设置参数1  ju_userID
        psql.setInt(2, 9999);      //设置参数2，  TaobaoID
        psql.setString(3, "TestName");      //设置参数3 ju_userName
        psql.setString(4, "123456");      //设置参数4  ju_userPWD

        psql.executeUpdate();           //执行Insert

        //查询修改数据后student表中的数据
        psql = conn.prepareStatement("select * from ju_users");
        res = psql.executeQuery();          //执行预处理sql语句
        System.out.println("执行增加、修改、删除后的数据");
        String ju_userID=null,ju_userName=null,ju_userPWD=null;
        while(res.next()){
            ju_userID = res.getString("ju_userID");
            ju_userName = res.getString("ju_userName");
            //ju_userName = new String(ju_userName.getBytes("ISO-8859-1"),"gb2312");
            System.out.println(ju_userID + "\t" + ju_userName);
        }

        //预处理更新（修改）数据
        psql = conn.prepareStatement("update ju_users set ju_userPWD =? where ju_userName= ?");
        psql.setString(1,"23456");       //设置参数1，ju_userPWD
        psql.setString(2,"TestName");    //设置参数2，ju_userName
        psql.executeUpdate();

        //预处理删除数据
        psql = conn.prepareStatement("delete from ju_users where ju_userName= ?");
        psql.setString(1, "TestName");
        psql.executeUpdate();

        //查询修改数据后student表中的数据
        psql = conn.prepareStatement("select * from ju_users");
        res = psql.executeQuery();          //执行预处理sql语句
        System.out.println("执行增加、修改、删除后的数据");

//        String ju_userID=null,ju_userName=null,ju_userPWD=null;
        while(res.next()){
            ju_userID = res.getString("ju_userID");
            ju_userName = res.getString("ju_userName");
            //ju_userName = new String(ju_userName.getBytes("ISO-8859-1"),"gb2312");
            System.out.println(ju_userID + "\t" + ju_userName);
        }
        res.close();
        psql.close();

    }
    // execute insertion language
    boolean insertSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    //execute delete language
    boolean deleteSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    //execute update language
    boolean updateSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }
    // show data in ju_users
    void layoutStyle2(ResultSet rs) {
        System.out.println("-----------------");
        System.out.println("执行结果如下所示:");
        System.out.println("-----------------");
        System.out.println(" 用户ID" + "/t/t" + "淘宝ID" + "/t/t" + "用户名"+ "/t/t" + "密码");
        System.out.println("-----------------");
        try {
            while (rs.next()) {
                System.out.println(rs.getInt("ju_userID") + "/t/t"
                        + rs.getString("taobaoID") + "/t/t"
                        + rs.getString("ju_userName")
                        + "/t/t"+ rs.getString("ju_userPWD"));
            }
        } catch (SQLException e) {
            System.out.println("显示时数据库出错。");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("显示出错。");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        mysqlDao h = new mysqlDao();
        try{
            h.connSQL();
        }
        catch (Exception er){
            er.printStackTrace();
        }
        String s = "select * from ju_users";

        String insert = "insert into ju_users(ju_userID,TaobaoID,ju_userName,ju_userPWD) values("+8329+","+34243+",'mm','789')";
        String update = "update ju_users set ju_userPWD =123 where ju_userName= 'mm'";
        String delete = "delete from ju_users where ju_userName= 'mm'";

        if (h.insertSQL(insert) == true) {
            System.out.println("insert successfully");
            ResultSet resultSet = h.selectSQL(s);
            h.layoutStyle2(resultSet);
        }
        if (h.updateSQL(update) == true) {
            System.out.println("update successfully");
            ResultSet resultSet = h.selectSQL(s);
            h.layoutStyle2(resultSet);
        }
        if (h.insertSQL(delete) == true) {
            System.out.println("delete successfully");
            ResultSet resultSet = h.selectSQL(s);
            h.layoutStyle2(resultSet);
        }

        h.deconnSQL();
    }
}