/**
 * Created by smq on 2016/8/27.
 */
// $.extend( $.fn.dataTable.defaults, {
//     "searching": false,
//     "ordering": false,
//     "oLanguage": { //国际化配置
//         "sProcessing" : "正在获取数据，请稍后...",
//         "sLengthMenu" : "显示 _MENU_ 条",
//         "sZeroRecords" : "没有您要搜索的内容",
//         "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
//         "sInfoEmpty" : "记录数为0",
//         "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
//         "sInfoPostFix" : "",
//         "sSearch" : "搜索",
//         "sUrl" : "",
//         "oPaginate": {
//             "sFirst" : "第一页",
//             "sPrevious" : "上一页",
//             "sNext" : "下一页",
//             "sLast" : "最后一页"
//         }
//     }
// } );
//传入一个回调，根据回调函数条件过滤数组
Array.prototype.filter = function(callback) {
    var rs = [];
    for ( var i = 0, length = this.length; i < length; i++ ) {
        if (callback(this[i], i)) {
            rs.push(this[i]);
        }
    }
    return rs;
};