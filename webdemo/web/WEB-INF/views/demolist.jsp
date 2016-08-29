<%--
  Created by IntelliJ IDEA.
  User: smq
  Date: 2016/8/23
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/favicon.ico">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
    <title>Title</title>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="../conf/media/css/jquery.dataTables.css">
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="../conf/media/js/jquery-1.12.3.min.js"></script>
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="../conf/media/js/jquery.dataTables.js"></script>
</head>
<body>

<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display">
    <thead>
    <tr>
        <th>Column 1</th>
        <th>Column 2</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Row 1 Data 1</td>
        <td>Row 1 Data 2</td>
    </tr>
    <tr>
        <td>Row 2 Data 1</td>
        <td>Row 2 Data 2</td>
    </tr>
    </tbody>
</table>

</body>
<script type="text/javascript" language="javascript" class="init">
    $(document).ready(function() {
        $('#table_id_example').DataTable();
    } );
</script>
</html>
