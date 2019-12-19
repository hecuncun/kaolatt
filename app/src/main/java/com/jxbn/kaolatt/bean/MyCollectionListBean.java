package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/19
 */
public class MyCollectionListBean {

    /**
     * code : 10001
     * message : 成功
     * data : [{"gid":"123","cid":"1-2","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"priceSecond":128,"salesVolume":"0","content":"这个物品有点好","numTotal":0,"numRemainder":255,"exquisite":1,"isdelete":0,"remark1":"","remark2":"0","remark3":"","remark4":"","createid":"1","max":"","min":"","createtime":"2019-12-09 17:36:20","updateid":"1","updatetime":"2019-12-09 17:36:23","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","collectId":"dsafaasdf","goodsSpecslList":"","goodsParamList":"","myId":"123"}]
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
        private boolean isChecked;//是否选中
        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        /**
         * gid : 123
         * cid : 1-2
         * picture : /uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg
         * name : 物品1
         * priceReal : 88
         * priceSecond : 128
         * salesVolume : 0
         * content : 这个物品有点好
         * numTotal : 0
         * numRemainder : 255
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
         * updatetime : 2019-12-09 17:36:23
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * collectId : dsafaasdf
         * goodsSpecslList :
         * goodsParamList :
         * myId : 123
         */

        private String gid;
        private String cid;
        private String picture;
        private String name;
        private int priceReal;
        private int priceSecond;
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
        private String collectId;
        private String goodsSpecslList;
        private String goodsParamList;
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

        public int getPriceReal() {
            return priceReal;
        }

        public void setPriceReal(int priceReal) {
            this.priceReal = priceReal;
        }

        public int getPriceSecond() {
            return priceSecond;
        }

        public void setPriceSecond(int priceSecond) {
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

        public String getCollectId() {
            return collectId;
        }

        public void setCollectId(String collectId) {
            this.collectId = collectId;
        }

        public String getGoodsSpecslList() {
            return goodsSpecslList;
        }

        public void setGoodsSpecslList(String goodsSpecslList) {
            this.goodsSpecslList = goodsSpecslList;
        }

        public String getGoodsParamList() {
            return goodsParamList;
        }

        public void setGoodsParamList(String goodsParamList) {
            this.goodsParamList = goodsParamList;
        }

        public String getMyId() {
            return myId;
        }

        public void setMyId(String myId) {
            this.myId = myId;
        }
    }
}
