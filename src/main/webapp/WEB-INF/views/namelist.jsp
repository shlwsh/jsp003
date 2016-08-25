
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%--
  Created by IntelliJ IDEA.
  User: smq
  Date: 16/8/12
  Time: 下午6:19
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>namelist</title>
    <%String basePath = request.getContextPath();%>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/conf/media/css/jquery.dataTables.css">
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/jquery.js"></script>
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/jquery.dataTables.js"></script>
</head>
<body>
<%
    Demo.mysql.mysqlDao db = new Demo.mysql.mysqlDao();
    db.connSQL();
//        db.InsertNameList(first_name,second_name);
    out.println("编码 namelist:select * from namelist<br>");
    out.println("<hr>");
    ResultSet res = db.selectSQL("select * from namelist order by code");
    String second_name,first_name;
%>
<table id="table_id_example" class="display">
    <thead>
    <tr>
        <th width="20%">编码</th>
        <th width="40%">名称001</th>
        <th width="40%">名称002</th>
    </tr>
    </thead>
    <tbody>
    <%
        while(res.next()){
    %>
    <tr>
        <td><%out.println(res.getString("code"));%></td>
        <td><%out.println(res.getString("first_name")+"--");
            first_name=res.getString("first_name");
            out.println(new String(first_name.getBytes("utf-8"),"utf-8"));
        %></td>
        <td><%
            out.println(res.getString("second_name")+"--");
            second_name=res.getString("second_name");
            out.println(new String(second_name.getBytes("utf-8"),"utf-8"));
        %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<script type="text/javascript" language="javascript" class="init">
    $(document).ready(function() {
        $('#table_id_example').DataTable();
    } );
</script>
</body>
</html>
