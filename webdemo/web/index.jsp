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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/conf/media/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/conf/media/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/conf/media/css/select.bootstrap.min.css">
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/jquery.js"></script>
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/jquery.dataTables.js"></script>
    <%--<script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/json2.js"></script>--%>
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/sx_default.js"></script>
    <script type="text/javascript" charset="utf8" src="<%=basePath%>/conf/media/js/winning-table.js"></script>
</head>

<body>
<h1> hello World!</h1>
<script language="javascript">

    var testData=[
        [ "100","aa3aa","bb2bb","in3sert" ],
        [ "1001","1aa3aa","1bb2bb","1in3sert" ]
    ];
    function test11() {
        var alldata=$('#table_id_example').dataTable().fnGetData();
        $('#table_id_example').data("jsondata",alldata);

        //查找 --第一个字段默认为主键
        var sqlList=Array();

        for(var k=0;k<alldata.length;i++) {
            var opStatus="";
            var item = alldata[k];
            for(var j=0;j<testData.length;i++) {
                if(alldata[k][0] == testData[j][0]){
                    for(var i=0;i<alldata[k].length;i++){
                        if(alldata[k][i]!=testData[j][i]){
                            item.push("update");
                            opStatus = "update";
                            sqlList.push(item);
                            break;
                        }
                    }
                    if(opStatus==""){
                        opStatus="original";
                    }
                }
            }
            if(opStatus==""){
                item.push("delete");
                opStatus="delete";
                sqlList.push(item);
            }
        }
        console.log(sqlList);
        //返向查找 --第一个字段默认为主键
        for(var k=0;k<testData.length;i++) {
            var opStatus="";
            var item = testData[k];
            for(var j=0;j<alldata.length;i++) {
                var item1 = testData[j];
                if(item[0] == item1[0]){
                    opStatus = "original";
                    break;
                }
            }
            if(opStatus==""){
                item.push("insert");
                opStatus="insert";
                sqlList.push(item);
            }
        }
        console.log(sqlList);
        return;
//        json=$('#table_id_example').data("jsondata");
        $('#table_id_example').DataTable(
                {
                    data: testData,
//                                    ajax: '/hello/namelist1',
                    destroy:true,
                    columns:[
                        {title:"code"},
                        {title:"first_name"},
                        {title:"second_name"},
                        {title:"userdb_status"}
                    ]
                }
        );

        return;
    }
    function test1() {
        $.ajax({
                    url: '/hello/namelist1',
                    type: 'Get',
                    dataType: 'json',
                    success:  function (data) {



                        var dataJson = data;
//                        var json = JSON.parse(dataJson);
                        //成功执行方法
//                        var strjson = JSON.stringify(data); //数组
//                        var json = JSON.parse(strjson);

                        var dataList = new Array();
                        $.each(dataJson.data, function (index, item) {
                                    if(index>5){
                                        return;
                                    }
                                    //循环获取数据
                                    var strItem = JSON.stringify(item);

                                    var s1List = new Array();
                                    for(var s1 in item){
                                        s1List.push(""+item[s1]+"");
                                    }
                                    dataList.push(s1List);
                                }
                        );

                        $('#table_id_example').DataTable(
                                {
                                    data: dataList,
//                                    ajax: '/hello/namelist1',
                                    destroy:true,
                                    columns:[
                                        {title:"code"},
                                        {title:"first_name"},
                                        {title:"second_name"},
                                        {title:"userdb_status"}
                                    ]
                                }
                        );
//                        var alldata=$('#table_id_example').dataTable().fnGetData();
////                        $('#table_id_example').data("jsondata",alldata);
////                        json=$('#table_id_example').data("jsondata");
//
//                        $.each(alldata, function (index, item) {
//                                    //循环获取数据
//                                    var first_name = alldata[index].first_name;
//                                    var code = alldata[index].code;
//                                    var second_name = alldata[index].second_name;
//                                    $("#list").html($("#list").html() + "<br>" + code + " - " + first_name + " - " + second_name + "<br/>");
//                                }
//                        );
                    }
        }
        )
    };
    function test_tovaluelist() {
        $.ajax({
                    url: '/hello/namelist2',
                    type: 'Get',
                    dataType: 'json',
                    success:  function (data) {



                        var dataJson = eval("("+data+")");
//                        var json = JSON.parse(dataJson);
                        //成功执行方法
//                        var strjson = JSON.stringify(data); //数组
//                        var json = JSON.parse(strjson);
                        var dataList = new Array();
                        $.each(dataJson.data, function (index, item) {
                                    if(index>5){
                                        return;
                                    }
                                    //循环获取数据
                                    var strItem = JSON.stringify(item);

                                    var s1List = new Array();
                                    for(var s1 in item){
                                        s1List.push(""+item[s1]+"");
                                    }
                                    dataList.push(s1List);
                                }
                        );

                        $('#table_id_example').DataTable(
                                {
                                    data: dataJson,
//                                    ajax: '/hello/namelist1',
                                    destroy:true,
                                    columns:[
                                        {title:"code"},
                                        {title:"first_name"},
                                        {title:"second_name"},
                                        {title:"userdb_status"}
                                    ]
                                }
                        );
//                        var alldata=$('#table_id_example').dataTable().fnGetData();
////                        $('#table_id_example').data("jsondata",alldata);
////                        json=$('#table_id_example').data("jsondata");
//
//                        $.each(alldata, function (index, item) {
//                                    //循环获取数据
//                                    var first_name = alldata[index].first_name;
//                                    var code = alldata[index].code;
//                                    var second_name = alldata[index].second_name;
//                                    $("#list").html($("#list").html() + "<br>" + code + " - " + first_name + " - " + second_name + "<br/>");
//                                }
//                        );
                    }
                }
        )
    };
</script>

<form name="fm001" action="/hello/Web003" method="post" >
    登录编码:
    <input type="text" name="first_name">
    <br>
    登录口令:
    <input type="text" name="second_name" >
    <br>
    <input type="submit" value="提交" />
    <input type="button" name="test" value="Test" onclick="test11();">
    <input type="button" name="buttonAdd" id="buttonAdd" value="新增">
    <input type="button" name="buttonDel" id="buttonDel" value="删除">
    <input type="button" name="buttonSel" id="buttonSel" value="选择">
    <input type="button" name="buttonFilter" id="buttonFilter" value="过滤">
    <input type="button" name="buttonShow" id="buttonShow" value="隐藏">
    <input type="button" name="buttonEdit" id="buttonEdit" value="编辑行">
</form>
<hr>
<ul id="list"> </ul>
<%--<h3>当前时间:--%>
<%--<%out.println(Demo.util.common.GetCurrentDateTime());%></h3>--%>

<!--第二步：添加如下 HTML 代码-->
<table id="table_id_example">
    <thead>
    <tr>
        <th>Column 1</th>
        <th>Column 2</th>
        <th>Column 3</th>
        <th>Column 4</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Row 1 Data 1</td>
        <td>Row 1 Data 2</td>
        <td>Row 1 Data 2</td>
        <td>Row 1 Data 2</td>
    </tr>
    </tbody>
</table>

<!--第三步：初始化Datatables-->
<script language="javascript">

    $(document).ready(
            table_init()
    );
    function  table_init() {

        $('#table_id_example').DataTable(
                {
//                    data: testData,
                    ajax: '/hello/namelist1',
                    destroy:true,
                    columns:[
                        {"data":"code","title":"code"},
                        {"data":"first_name","title":"first_name"},
                        {"data":"second_name","title":"second_name"},
                        {"data":"userdb_status","title":"userdb_status"}
                    ],
                buttons: [
                                'excel', 'pdf', 'print'
                            ]
                }
        );
    }

    $('#table_id_example tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
//    var alldata=$('#table_id_example').dataTable().fnGetData();
//    $('#table_id_example').data("jsondata",alldata);

    //隐藏列
    $('#buttonShow').click( function () {
        var table = $('#table_id_example').DataTable();
        // Get the column API object
//        var column = table.column( $('userdb_status').attr('data-column') );
        var column = table.column(3);
        // Toggle the visibility
        column.visible( ! column.visible() );
    } );

//    //单选行删除
//    $('#buttonDel').click( function () {
//        table.row('.selected').remove().draw( false );
//    } );

    //多选行删除
        $('#buttonDel').click( function () {
            table.rows('.selected').remove().draw( false );
        } );
    //新增
    $('#buttonAdd').click( function () {
        var t = $('#table_id_example').DataTable();
//        var counter = 1;// t.rows().data().length + 1;
        var dr=
            {
            "code":"111",
            "first_name":"dddddddd",
            "second_name":"dddddddddddd",
            "userdb_status":"insert"
        };
        t.row.add(dr).draw();
    } );
    $('#buttonSel').click( function () {
        console.log( table.rows('.selected').data().length +' row(s) selected' );
        var selData =table.rows('.selected').data();
        for(var r in selData){
//            var myObject = JSON.parse(arr[r]);
            alert(selData[r].first_name);
        }
    } );

    $('#buttonFilter').click( function () {
        fnFilter();
    } );

    function fnFilter(){
        var strJason =table.rows().data();
        var arr = $.grep(strJason,function (n, i) {
            return n.code>10
        },true
        );
//        var myObject = JSON.stringify(arr);

        for(var r in arr){
//            var myObject = JSON.parse(arr[r]);
            alert(arr[r].first_name);
        }

    }
    $('#buttonEdit').click( function () {
        var t = $('#table_id_example').DataTable();
        var dr=
        {
            "code":"111",
            "first_name":"dddddddd",
            "second_name":"dddddddddddd",
            "userdb_status":"insert"
        };
        t.row(0).data(dr).draw();
        var newDr = t.row(0).data();
        console.log(newDr.second_name);
//        t.row.add(dr).draw();
    } );
</script>
<br>


</body>
</html>