package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/16
 */
public class GoodListBean {

    /**
     * code : 10001
     * message : 成功
     * data : [{"gid":"aasfdf","cid":"1-2","picture":"/uploadtemp/image/20180320/3.png,/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"精华","priceReal":994,"priceSecond":1334,"salesVolume":"0","content":"这个物品很不错好","numTotal":0,"numRemainder":111,"exquisite":1,"isdelete":0,"remark1":"","remark2":"0","remark3":"","remark4":"","createid":"1","max":"","min":"","createtime":"2019-12-09 17:36:20","updateid":"1","updatetime":"2019-12-09 20:36:10","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"aasfdf"},{"gid":"asdfasdfsa","cid":"1-2","picture":"/uploadtemp/image/20180320/3.png,/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"第六个物品","priceReal":995,"priceSecond":1335,"salesVolume":"0","content":"这个物品很不错好","numTotal":0,"numRemainder":34,"exquisite":1,"isdelete":0,"remark1":null,"remark2":"0","remark3":null,"remark4":null,"createid":"1","max":null,"min":null,"createtime":"2019-12-09 17:36:20","updateid":"1","updatetime":"2019-12-09 20:30:30","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"asdfasdfsa"},{"gid":"sdfas","cid":"1-2","picture":"/uploadtemp/image/20180320/3.png,/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品7","priceReal":993,"priceSecond":1333,"salesVolume":"0","content":"这个物品很不错好","numTotal":0,"numRemainder":134,"exquisite":1,"isdelete":0,"remark1":null,"remark2":"0","remark3":null,"remark4":null,"createid":"1","max":null,"min":null,"createtime":"2019-12-09 17:36:20","updateid":"1","updatetime":"2019-12-09 17:46:23","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"sdfas"},{"gid":"sdfas12","cid":"1-2","picture":"/uploadtemp/image/20180320/3.png,/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"第四个物品","priceReal":993,"priceSecond":1333,"salesVolume":"0","content":"这个物品很不错好","numTotal":0,"numRemainder":9999,"exquisite":1,"isdelete":0,"remark1":null,"remark2":"0","remark3":null,"remark4":null,"createid":"1","max":null,"min":null,"createtime":"2019-12-09 17:36:20","updateid":"1","updatetime":"2019-12-09 17:46:23","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"sdfas12"}]
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
         * gid : aasfdf
         * cid : 1-2
         * picture : /uploadtemp/image/20180320/3.png,/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg
         * name : 精华
         * priceReal : 994
         * priceSecond : 1334
         * salesVolume : 0
         * content : 这个物品很不错好
         * numTotal : 0
         * numRemainder : 111
         * exquisite : 1
         * isdelete : 0
         * remark1 :
         * remark2 : 0
         * remark3 :
         * remark4 :
         * createid : 1
         * max :
         * min :
         * createtime : 2019-12-09 17:36:20
         * updateid : 1
         * updatetime : 2019-12-09 20:36:10
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * myId : aasfdf
         */

        private String gid;
        private String cid;
        private String picture;
        private String name;
        private float priceReal;
        private String priceSecond;
        private String salesVolume;
        private String content;
        private int numTotal;
        private int numRemainder;
        private int exquisite;
        private int isdelete;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String createid;
        private String max;
        private String min;
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

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPriceReal() {
            return priceReal;
        }

        public void setPriceReal(float priceReal) {
            this.priceReal = priceReal;
        }

        public String getPriceSecond() {
            return priceSecond;
        }

        public void setPriceSecond(String priceSecond) {
            this.priceSecond = priceSecond;
        }

        public String getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(String salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getNumTotal() {
            return numTotal;
        }

        public void setNumTotal(int numTotal) {
            this.numTotal = numTotal;
        }

        public int getNumRemainder() {
            return numRemainder;
        }

        public void setNumRemainder(int numRemainder) {
            this.numRemainder = numRemainder;
        }

        public int getExquisite() {
            return exquisite;
        }

        public void setExquisite(int exquisite) {
            this.exquisite = exquisite;
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

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
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
