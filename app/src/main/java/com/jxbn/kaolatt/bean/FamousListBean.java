package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/16
 */
public class FamousListBean {

    /**
     * code : 10001
     * message : 成功
     * data : [{"bcid":"321123","name":"后","picture":"/uploadtemp/image/20180320/4.jpg","isdelete":0,"remark1":"","remark2":"","remark3":"","remark4":"","createid":"1","createtime":"2019-12-09 18:02:45","updateid":"1","updatetime":"2019-12-09 18:02:47","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"321123"},{"bcid":"123123","name":"雅诗兰黛","picture":"/uploadtemp/image/20180318/20180318231253_183.png","isdelete":0,"remark1":null,"remark2":null,"remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-09 18:02:08","updateid":"1","updatetime":"2019-12-09 18:02:10","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"123123"}]
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
         * bcid : 321123
         * name : 后
         * picture : /uploadtemp/image/20180320/4.jpg
         * isdelete : 0
         * remark1 :
         * remark2 :
         * remark3 :
         * remark4 :
         * createid : 1
         * createtime : 2019-12-09 18:02:45
         * updateid : 1
         * updatetime : 2019-12-09 18:02:47
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * myId : 321123
         */

        private String bcid;
        private String name;
        private String picture;
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

        public String getBcid() {
            return bcid;
        }

        public void setBcid(String bcid) {
            this.bcid = bcid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
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
