package cn.fy.utils;

public class PageUtils {
    //当前页码
    private int pageIndex;
    //页码大小，每页显示的条数
    private int pageSize;

    public PageUtils() {
    }


    public PageUtils(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
