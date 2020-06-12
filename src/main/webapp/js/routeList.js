$(function () {
    var search =location.search;
    // alert(search)//?cid=5
    var cid=  search.split("=")[1];
    //拿到了cid的值
    //获取该cid下的路线总数和总页数
    var pageInfo;
    $.get("route/findByCid",{cid:cid,pageNum:1,pageSize:8},function (data) {
        pageInfo=data;
        $("#pages").html(data.pages);
        $("#total").html(data.total);
    });

    setTimeout(function () {
        console.log(pageInfo);
        var lis='<li><a href="route_list.html?cid="'+cid+'>首页</a></li>';
        //判断是否是第一页，是第一页则上一页按钮不可用
        if (pageInfo.hasPreviousPage){
            lis+=' <li class="threeword"><a href="route_list.html?cid='+cid+'&pageNum='+pageInfo.prePage+'">上一页</a></li>'
        }else {
            lis+=' <li class="threeword"><a href="#" disabled="disabled">上一页</a></li>'
        }

        //拼接页码
        for (var i = 1; i <= pageInfo.pages; i++) {

            lis+='<li><a href="route_list.html?cid='+cid+'&pageNum='+i+'">'+i+'</a></li>';
            console.log(i);
        }

        //判断是否是最后一页，是则下一页按钮不可用
        if (pageInfo.hasNextPage){
            lis+='<li class="threeword"><a href="route_list.html?cid='+cid+'&pageNum='+pageInfo.nextPage+'">下一页</a></li>';

        }else {
            lis+='<li class="threeword"><a href="javascript:return false;" disabled="disabled">下一页</a></li>';
        }

        lis+='<li class="threeword"><a href="route_list.html?cid='+cid+'&pageNum='+pageInfo.pages+'">末页</a></li>';

        $("#yeMa").html(lis);

    },100);



});