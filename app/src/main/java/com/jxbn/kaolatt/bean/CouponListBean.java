package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/19
 */
public class CouponListBean {

    /**
     * code : 10001
     * message : 成功
     * data : [{"sid":"dfafda","valueMax":300,"valueSubtraction":30,"type":"","isdelete":0,"startTime":"2019-12-11 16:15:40","endTime":"2020-01-31 16:15:42","remark3":"","remark4":"","createid":"1","createtime":"2019-12-11 16:15:54","updateid":"1","updatetime":"2019-12-11 16:16:04","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","cuid":"444e9f7b10c34796b1942506076fe7fb","myId":"dfafda"}]
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
         * sid : dfafda
         * valueMax : 300
         * valueSubtraction : 30
         * type :
         * isdelete : 0
         * startTime : 2019-12-11 16:15:40
         * endTime : 2020-01-31 16:15:42
         * remark3 :
         * remark4 :
         * createid : 1
         * createtime : 2019-12-11 16:15:54
         * updateid : 1
         * updatetime : 2019-12-11 16:16:04
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * cuid : 444e9f7b10c34796b1942506076fe7fb
         * myId : dfafda
         */

        private String sid;
        private int valueMax;
        private int valueSubtraction;
        private String type;
        private int isdelete;
        private String startTime;
        private String endTime;
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
        private String cuid;
        private String myId;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public int getValueMax() {
            return valueMax;
        }

        public void setValueMax(int valueMax) {
            this.valueMax = valueMax;
        }

        public int getValueSubtraction() {
            return valueSubtraction;
        }

        public void setValueSubtraction(int valueSubtraction) {
            this.valueSubtraction = valueSubtraction;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
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

        public String getCuid() {
            return cuid;
        }

        public void setCuid(String cuid) {
            this.cuid = cuid;
        }

        public String getMyId() {
            return myId;
        }

        public void setMyId(String myId) {
            this.myId = myId;
        }
    }
}
