$(function () {

/*    var search =location.search;
    // alert(search)//?cid=5
    var cid=  search.split("=")[1];*/

    var cid = getParameter("cid");
    var rname = getParameter("rname");
    if (cid==="null"){
        cid=null;
    }
    //如果rname不为空或不为null
    if (rname){
        rname=window.decodeURIComponent(rname);
    }
    if (rname==="null"){
        rname="";
    }
    load(cid,null,rname);
    //拿到了cid的值
    //获取该cid下的路线总数和总页数

});

function load(cid, pageNum,rname) {

    $.get("route/findByCid",{cid:cid,pageNum:pageNum,pageSize:8,rname:rname},function (pageInfo) {
        //展示总页码
        $("#pages").html(pageInfo.pages);
        $("#total").html(pageInfo.total);

        //准备拼接分页工具条
        var lis = "";
        //加转义符号是为了给比如说西安加上引号“西安”
        var firstPage='<li onclick="javascipt:load('+cid+',1,\''+rname+'\')" class="threeword"><a href="javascript:void(0)">首页</a></li>';

        var beforePage='';
        //如果是第一页就禁用上一页标签
        if (pageInfo.isFirstPage){
            var beforePage = '<li   class="threeword" disabled="disabled"><a href="javascript:void(0)" disabled="disabled">上一页</a></li>';
        }else {
            var beforePage = '<li  onclick="javascipt:load('+cid+','+pageInfo.prePage+',\''+rname+'\')" class="threeword"><a href="javascript:void(0)">上一页</a></li>';
        }
        //把首页和上一页的两个li都添加好
        lis += firstPage;
        lis += beforePage;
        /*
           1.一共展示10个页码，能够达到前5后4的效果
           2.如果前边不够5个，后边补齐10个
           3.如果后边不足4个，前边补齐10个
        */

        // 定义开始位置begin,结束位置 end
        var begin; // 开始位置
        var end ; //  结束位置
        if (pageInfo.pages<10){
            //如果总页码不够10页
            begin=1;
            end = pageInfo.pages;
        }else {
            //总页码超过10页
            begin = pageInfo.pageNum - 5 ;
            end = pageInfo.pageNum + 4 ;

            //2.如果前面不够5个，后面补齐10个
            if (begin<1){
                begin=1;
                end = begin + 9;
            }
            //3.如果后边不足4个，前边补齐
            if (end > pageInfo.pages ){
                end =pageInfo.pages;
                begin =end -9;
            }
        }

        for (var i = begin; i <= end ; i++) {
            var li;
            //判断当前页码是否等于i
            if(pageInfo.pageNum === i){

                li = '<li class="curPage" onclick="javascipt:load('+cid+','+i+',\''+rname+'\')"><a href="javascript:void(0)">'+i+'</a></li>';

            }else{
                //创建页码的li
                li = '<li onclick="javascipt:load('+cid+','+i+',\''+rname+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            //拼接字符串
            lis += li;
        }

        var lastPage = '<li onclick="javascipt:load('+cid+','+pageInfo.pages+',\''+rname+'\')" class="threeword"><a href="javascript:void(0)">末页</a></li>';

        var nextPage='';
        //如果是第一页就禁用上一页标签
        if (pageInfo.isLastPage){
            var nextPage = '<li   class="threeword" disabled="disabled"><a href="javascript:void(0)" disabled="disabled">下一页</a></li>';
        }else {
            var nextPage = '<li   onclick="javascipt:load('+cid+','+pageInfo.nextPage+',\''+rname+'\')" class="threeword"><a href="javascript:void(0)">下一页</a></li>';
        }
        lis += nextPage;
        lis += lastPage;

        $("#yeMa").html(lis);

        //2.列表数据展示
        var route_lis = "";

        for (var i = 0; i < pageInfo.list.length; i++) {
            //获取{rid:1,rname:"xxx"}
            var route = pageInfo.list[i];

            var li = '<li>\n' +
                '                        <div class="img"><img src="'+route.rimage+'" style="width: 299px;"></div>\n' +
                '                        <div class="text1">\n' +
                '                            <p>'+route.rname+'</p>\n' +
                '                            <br/>\n' +
                '                            <p>'+route.routeIntroduce+'</p>\n' +
                '                        </div>\n' +
                '                        <div class="price">\n' +
                '                            <p class="price_num">\n' +
                '                                <span>&yen;</span>\n' +
                '                                <span>'+route.price+'</span>\n' +
                '                                <span>起</span>\n' +
                '                            </p>\n' +
                '                            <p><a href="route_detail.html?rid='+route.rid+'">查看详情</a></p>\n' +
                '                        </div>\n' +
                '                    </li>';
            route_lis += li;
        }
        $("#route").html(route_lis);

        //定位到页面顶部
        window.scrollTo(0,0);









    });


}