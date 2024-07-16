package com.muxingzhe.check.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kampf
 * @date 2019/7/22 15:54
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -6179152122137980411L;

    /**首页值*/
    private int first;

    /**尾页值*/
    private int last;

    /**是否为首页*/
    private boolean isFirst;

    /**是否为尾页*/
    private boolean isLast;

    /**下一页值*/
    private int next;

    /**上一页值*/
    private int prev;

    /**当前页*/
    private int pageNo;

    /**分页数据*/
    private List<T> list;

    private String funcParam;

    private String pageInfo;

    private int centerNum;

    /**每页显示数据条目*/
    @Value("${page.pageSize}")
    private int pageSize;

    /**前后展示页数*/
    private int bothNum;

    /**总页数*/
    private long count;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public int getNext() {
        return this.isLast ? this.pageNo : this.pageNo + 1;
    }

    public int getPrev() {
        return this.isFirst ? this.pageNo : this.pageNo - 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getFuncParam() {
        return funcParam;
    }

    public void setFuncParam(String funcParam) {
        this.funcParam = funcParam;
    }

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getCenterNum() {
        return centerNum;
    }

    public void setCenterNum(int centerNum) {
        this.centerNum = centerNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        if((this.count = count) != -1L && (long)this.pageSize >= count){
            this.pageNo = 1;
        }

        this.initialize();
    }

    public void setBothNum(int bothNum) {
        this.bothNum = bothNum;
    }

    /**
     * 基础构造函数
     */
    public Page(){
        this.pageNo = 1;
        this.bothNum = 1;
        this.centerNum = 5;
        this.list = new ArrayList<>();
    }

    /**
     * 进阶构造函数
     * @param pageNo
     * @param pageSize
     * @param value
     * @param list
     */
    public Page(int pageNo, int pageSize, long value, List<T> list){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.bothNum = 1;
        this.centerNum = 5;
        this.list = new ArrayList<>();
        this.count = value;
        if(list != null){
            this.list = list;
        }
    }

    /**
     * 进阶构造函数调用方法
     * @param pageNo
     * @param pageSize
     * @param count
     */
    public Page(int pageNo, int pageSize, long count) {
        this(pageNo, pageSize, count, (List)null);
    }

    /**
     * 进阶构造函数调用方法封装
     * @param pageNo
     * @param pageSize
     */
    public Page(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0L);
    }

    /**
     * 进阶构造函数
     * @param httpServletRequest
     * @param httpServletResponse
     * @param defaultPageSize
     */
    //TODO 这个是根据cookie判断页面分页，后根据实际情况修改
//    public Page(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int defaultPageSize){
//        this.pageNo = 1;
//        this.bothNum = 1;
//        this.centerNum = 5;
//        this.list = new ArrayList<>();
//        HttpServletRequest request;
//        String
//    }

    /**
     * 判断每页size是否正常
     * @return
     */
    @JsonIgnore
    public boolean isNoPaging(){
        return this.pageSize == -1;
    }

    /**
     * 判断总页数是否正常
     * @return
     */
    @JsonIgnore
    public boolean isNotCount(){
        return this.count == -1L || this.isNoPaging();
    }

    /**
     * 判断是否不足一页
     * @return
     */
    @JsonIgnore
    public boolean isOnlyCount(){
        return this.count == -2L;
    }

    public void initialize(){
        if(!this.isNoPaging() && !this.isNotCount() && !this.isOnlyCount()){
            this.first = 1;
            this.last = (int)(this.count / (long)(this.pageSize < 1 ? 10 : this.pageSize) + (long)this.first - 1L);
            if(this.count % (long)this.pageSize != 0L || this.last == 0){
                ++this.last;
            }

            if(this.last < this.first){
                this.last = this.first;
            }

            Page page;
            if(this.pageNo <= 1){
                page = this;
                this.pageNo = this.first;
                this.isFirst = true;
            } else {
                page = this;
                this.isFirst = false;
            }

            if(page.pageNo >= this.last){
                page = this;
                this.pageNo = page.last;
                this.isLast = true;
            }else {
                page = this;
                this.isFirst = false;
            }

            if (page.pageNo < this.last - 1) {
                page = this;
                this.next = this.pageNo + 1;
            } else {
                page = this;
                this.next = this.last;
            }

            if (page.pageNo > 1) {
                page = this;
                this.prev = this.pageNo - 1;
            } else {
                page = this;
                this.prev = this.first;
            }

            if (page.pageNo < this.first) {
                this.pageNo = this.first;
            }

            if (this.pageNo > this.last) {
                this.pageNo = this.last;
            }
        }
    }
}
