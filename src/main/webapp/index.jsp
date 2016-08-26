<%@ taglib prefix="display" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>DemoTitle</title>
    <!--第一步：引入Javascript / CSS （CDN）-->
    <%String basePath = request.getContextPath();%>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/conf/media/css/jquery.dataTables.css">
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/jquery.js"></script>
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/jquery.dataTables.js"></script>

</head>

<body>
<h1> hello World!</h1>
<script language="javascript">

    function test1() {
        $.ajax({
                    url: '/hello/namelist1',
                    type: 'Get',
                    dataType: 'json',
//            timeout: 1000,
//            cache: false,
//            beforeSend: LoadFunction, //加载执行方法
//            error: erryFunction,  //错误执行方法
                    success:  function (data) {
                        //成功执行方法
                        var json = eval(data.data); //数组
                        $.each(json, function (index, item) {
                            //循环获取数据
                            var first_name = json[index].first_name;
                            var code = json[index].code;
                            var second_name = json[index].second_name;
                            $("#list").html($("#list").html() + "<br>" + code + " - " + first_name + " - " + second_name + "<br/>");
                        });
                    }}
        )
    };
    function succFunction() {
        alert("aaaaaa");
    };

</script>

<form name="fm001" action="/hello/Web003" method="post" display:display:none>
    登录编码:
    <input type="text" name="first_name">
    <br>
    登录口令:
    <input type="text" name="second_name" >
    <br>
    <input type="submit" value="提交" />
    <input type="button" name="test" value="Test" onclick="test1();">
</form>
<hr>
<ul id="list"> </ul>
<%--<h3>当前时间:--%>
<%--<%out.println(Demo.util.common.GetCurrentDateTime());%></h3>--%>

<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example" class="display">
    <thead>
    <tr>
        <th>Column 1</th>
        <th>Column 2</th>
        <th>Column 3</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Row 1 Data 1</td>
        <td>Row 1 Data 2</td>
        <td>Row 1 Data 2</td>
    </tr>
    <tr>
        <td>Row 2 Data 1</td>
        <td>Row 2 Data 2</td>
        <td>Row 1 Data 2</td>
    </tr>
    </tbody>
</table>

<!--第三步：初始化Datatables-->
<script language="javascript">

    $(document).ready($('#table_id_example').DataTable( {
//        data: data
        ajax: '/hello/namelist1',
        columns:[
            {"data":"code","title":"code"},
            {"data":"first_name","title":"first_name"},
            {"data":"second_name","title":"second_name"}
        ]


    } ));

</script>


</body>
</html>