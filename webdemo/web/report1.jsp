<%@ page contentType="text/html;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%
    //�������֮�����ɵ�.jasper�ļ��Ĵ��λ��
//    File reportFile =new File(this.getServletContext().getRealPath()+"/views/report/sample.jasper");
//    String url="jdbc:mysql://localhost:3306/db";
//    Class.forName("com.mysql.jdbc.Driver");
//    Map parameters =new HashMap();
//    //"SQLSTR"�Ǳ����ж���Ĳ�������,������ΪString��
//    //����SQLSTR����������,������Ҫ��ֵsql���
//    parameters.put("SQLSTR","select * from employee");
//    Connection conn =DriverManager.getConnection(url,"username","password");
//    JasperRunManager.runReportToHtmlFile(reportFile.getPath(),parameters,conn);
//    response.sendRedirect("/views/report/sample.html");
%>