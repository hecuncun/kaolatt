package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/21
 */
public class SortListBean {

    /**
     * code : 10001
     * message : 成功
     * data : [{"cid":"09b1df5dbeaf4660a2aaa29497245d34","name":"儿童","pid":"d47e45ff26d74454a29e056811624d63","isdelete":0,"remark1":"/uploadtemp/image/2019/12/7c12c90d14a44ce8869e009da2d0625f.jpeg","remark2":"","remark3":"","remark4":"","createid":"","createtime":"2019-12-19 20:28:33","updateid":"","updatetime":"2019-12-19 20:30:30","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"09b1df5dbeaf4660a2aaa29497245d34"},{"cid":"1","name":"护肤彩妆","pid":"0","isdelete":0,"remark1":null,"remark2":null,"remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-09 20:52:15","updateid":"1","updatetime":"2019-12-09 20:52:19","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"1"},{"cid":"265bad0b8d9741b098be50d59f8fcece","name":"个人清洁","pid":"0","isdelete":0,"remark1":null,"remark2":null,"remark3":null,"remark4":null,"createid":null,"createtime":null,"updateid":null,"updatetime":"2019-12-19 18:02:55","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"265bad0b8d9741b098be50d59f8fcece"},{"cid":"63deeeb9a5be4df9af4c314702cc68ac","name":"服饰","pid":"0","isdelete":0,"remark1":null,"remark2":null,"remark3":null,"remark4":null,"createid":null,"createtime":"2019-12-19 18:03:17","updateid":null,"updatetime":"2019-12-19 18:04:14","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"63deeeb9a5be4df9af4c314702cc68ac"},{"cid":"8bb0189ee85c406a966429c63e0f7d83","name":"哲学","pid":"d47e45ff26d74454a29e056811624d63","isdelete":0,"remark1":"/uploadtemp/image/2019/12/38b1d38620e4410aa0176f11ea4ad61e.bmp","remark2":null,"remark3":null,"remark4":null,"createid":null,"createtime":"2019-12-19 20:30:21","updateid":null,"updatetime":"2019-12-19 20:30:21","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"8bb0189ee85c406a966429c63e0f7d83"},{"cid":"d47e45ff26d74454a29e056811624d63","name":"书籍","pid":"0","isdelete":0,"remark1":null,"remark2":null,"remark3":null,"remark4":null,"createid":null,"createtime":null,"updateid":null,"updatetime":"2019-12-19 18:04:17","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"d47e45ff26d74454a29e056811624d63"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 09b1df5dbeaf4660a2aaa29497245d34
         * name : 儿童
         * pid : d47e45ff26d74454a29e056811624d63
         * isdelete : 0
         * remark1 : /uploadtemp/image/2019/12/7c12c90d14a44ce8869e009da2d0625f.jpeg
         * remark2 :
         * remark3 :
         * remark4 :
         * createid :
         * createtime : 2019-12-19 20:28:33
         * updateid :
         * updatetime : 2019-12-19 20:30:30
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * myId : 09b1df5dbeaf4660a2aaa29497245d34
         */
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String cid;
        private String name;
        private String pid;
        private int isdelete;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String createid;
        private String createtime;
        private String updateid;
        private String updatetime;
        private int startIndex;
        private int pageSize;
        private String orderBy;
        private String fieldName;
        private String startDate;
        private String endDate;
        private String myId;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public String getRemark1() {
            return remark1;
        }

        public void setRemark1(String remark1) {
            this.remark1 = remark1;
        }

        public String getRemark2() {
            return remark2;
        }

        public void setRemark2(String remark2) {
            this.remark2 = remark2;
        }

        public String getRemark3() {
            return remark3;
        }

        public void setRemark3(String remark3) {
            this.remark3 = remark3;
        }

        public String getRemark4() {
            return remark4;
        }

        public void setRemark4(String remark4) {
            this.remark4 = remark4;
        }

        public String getCreateid() {
            return createid;
        }

        public void setCreateid(String createid) {
            this.createid = createid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdateid() {
            return updateid;
        }

        public void setUpdateid(String updateid) {
            this.updateid = updateid;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getMyId() {
            return myId;
        }

        public void setMyId(String myId) {
            this.myId = myId;
        }
    }
}
