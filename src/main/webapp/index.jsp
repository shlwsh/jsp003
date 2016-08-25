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
                    url: '/hello/namelist',
                    type: 'Get',
                    dataType: 'json',
//            timeout: 1000,
//            cache: false,
//            beforeSend: LoadFunction, //加载执行方法
//            error: erryFunction,  //错误执行方法
                    success:  function (data) {
                        //成功执行方法
                        alert(data);
                    }}
        )
    };
    function succFunction() {
        alert("aaaaaa");
    };
    //            $('#table_id_example').DataTable( {
    //            ajax: '/hello/namelist'
    //        } );

</script>
<form name="fm001" action="/hello/Web003" method="post">
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
<%--<h3>当前时间:--%>
<%--<%out.println(Demo.util.common.GetCurrentDateTime());%></h3>--%>

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

<!--第三步：初始化Datatables-->
<script language="javascript">
    var data = [
        [
            "Tiger Nixon",
            "System Architect"
        ],
        [
            "Garrett Winters",
            "Director"
        ],
        ["name001","name002"],["asass","asasas"],["123","23443"],["123212","234431212"],
        ["123212ww","234431212ww"],["dddddd","esddsds"],["ewwe","dssddsds"],["ddsds","ddsds"],
        ["dsasa","saas"],["fdfdfd","fdfdfd"],["ddsds","dssdsd"],["ddsds","dssds"],["ddsds","dssds"],
        ["ddsds","dssds"],["asa","sasds"],["dewew","dsdsds"],
        ["aaaaaaaaaaaaaaaaaaa","dsdsdsaaaaaaaaaaaaaa"],
        ["vvv","vvvvvvvvvvvvvv"],["vccvvc","vcvcvcvc"],
        ["mmmmmmmmmm","mmmmmmmm"],["eew","ew"],["ddsds","dsdsds"],
        ["fdfdfd","fdfdfd"],["rere","rrere"],["rere","rrere"],["fdfdfd","fddffd"],["ewwefdfd","23232"],["ewwefdfd","23232"],
        ["eeeff","fffff"],["eeeff","fffff"],["wqwqwq","dddd"],["asasa","sasasasa"]
    ];
    $(document).ready($('#table_id_example').DataTable( {
        data: data
//        ajax: '/hello/namelist'
    } ));

</script>


</body>
</html>