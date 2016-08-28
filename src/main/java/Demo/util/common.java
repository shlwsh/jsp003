package Demo.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by smq on 16/6/30.
 */
public class common {

    /***
     * 根据数据集返回值列表
     * @param rs
     * @return
     */
    public static final List<ArrayList<String>> ResultSetToStrListOnlyValue(ResultSet rs) {
        ResultSetMetaData rsmd = null;
        List<ArrayList<String>> strList = new ArrayList<ArrayList<String>>();

        String columnName, columnValue = null;

        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                ArrayList<String> rowList = new ArrayList<String>();

                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    rowList.add("\""+columnValue+"\"");

                }
                strList.add(rowList);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strList;
    }
    /***
     * 将数据集合转换成ＪＯＳＮ值串
     * @param rs
     * @return
     */
    public static final String ResultSetToJsonStr(ResultSet rs) {
        ResultSetMetaData rsmd = null;
        String columnName, columnValue,strJson = null;
        strJson="[";
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                strJson+="{";
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    strJson+="\""+columnName+"\":"+"";
                    strJson+="\""+columnValue+"\""+",";
                }
                strJson=strJson.substring(0,strJson.length()-1);
                strJson+="},";
//                ja.add(element);
            }
            strJson=strJson.substring(0,strJson.length()-1);
            strJson+="]";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strJson;
    }
    public static final JsonArray ResultSetToJsonArray(ResultSet rs) {
        JsonObject element = null;
        JsonArray ja = new JsonArray();
        ResultSetMetaData rsmd = null;
        String columnName, columnValue = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                element = new JsonObject();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    element.addProperty(columnName, columnValue);
                }
                ja.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ja;
    }

    public static final JsonObject ResultSetToJsonObject(ResultSet rs) {
        JsonObject element = null;
        JsonArray ja = new JsonArray();
        JsonObject jo = new JsonObject();
        ResultSetMetaData rsmd = null;
        String columnName, columnValue = null;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                element = new JsonObject();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i + 1);
                    columnValue = rs.getString(columnName);
                    element.addProperty(columnName, columnValue);
                }
                ja.add(element);
            }
            jo.add("result", ja);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jo;
    }

    /*****
     * 将resultset转换为json串
     * @param rs
     * @return
     */
    public static final String ResultSetToJsonString(ResultSet rs) {
        return ResultSetToJsonArray(rs).toString();
    }
    //返回默认属性文件的属性值
    public static String GetConfigValue(String propName) throws IOException {
        String  propValue="";
        InputStream path1 =Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource1-context.properties");
        Properties pro = new Properties();//属性集合对象
        pro.load(path1);
        propValue = pro.getProperty(propName);
        return  propValue;
    }
    public static String GetCurrentDateTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

        String str = dateFormat.format( now );
        return str;
    }
    public static void main(String[] args){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );
        System.out.println(hehe);

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改




        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);
    }
    public  static String GetUtfString(String strTran) throws UnsupportedEncodingException {
//        StringBuilder utfStr = new StringBuilder();
//        utfStr.append(strTran);

        if(strTran.equals(new String(strTran.getBytes("iso8859-1"), "iso8859-1")))
        {
            strTran=new String(strTran.getBytes("iso8859-1"),"utf-8");
        }
        return strTran;
    }
}
