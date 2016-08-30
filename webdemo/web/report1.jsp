<%@ page contentType="text/html;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%
    //报表编译之后生成的.jasper文件的存放位置
//    File reportFile =new File(this.getServletContext().getRealPath()+"/views/report/sample.jasper");
//    String url="jdbc:mysql://localhost:3306/db";
//    Class.forName("com.mysql.jdbc.Driver");
//    Map parameters =new HashMap();
//    //"SQLSTR"是报表中定义的参数名称,其类型为String型
//    //设置SQLSTR参数的内容,根据需要赋值sql语句
//    parameters.put("SQLSTR","select * from employee");
//    Connection conn =DriverManager.getConnection(url,"username","password");
//    JasperRunManager.runReportToHtmlFile(reportFile.getPath(),parameters,conn);
//    response.sendRedirect("/views/report/sample.html");
%>