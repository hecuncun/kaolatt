package com.jxbn.kaolatt.bean;

/**
 * Created by heCunCun on 2019/12/23
 */
public class AddOrderBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"oid":"2aaeb3e645a8419aaf6fc252aea2c5a4","uid":"dc53f0e607a84cc6ada18e9b04c305cf","orderNo":"20191223163805541","num":1,"priceTotalGood":993,"priceTotalOrder":993,"priceInteger":0,"priceCard":0,"cardId":"","status":1,"isdelete":"","remark1":"d132f72aaa97499282f928a703f82b02","remark2":"","remark3":"","remark4":"","createtime":"2019-12-23 16:38:05","updateid":"dc53f0e607a84cc6ada18e9b04c305cf","updatetime":"2019-12-23 16:38:05","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"2aaeb3e645a8419aaf6fc252aea2c5a4"}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * oid : 2aaeb3e645a8419aaf6fc252aea2c5a4
         * uid : dc53f0e607a84cc6ada18e9b04c305cf
         * orderNo : 20191223163805541
         * num : 1
         * priceTotalGood : 993
         * priceTotalOrder : 993
         * priceInteger : 0
         * priceCard : 0
         * cardId :
         * status : 1
         * isdelete :
         * remark1 : d132f72aaa97499282f928a703f82b02
         * remark2 :
         * remark3 :
         * remark4 :
         * createtime : 2019-12-23 16:38:05
         * updateid : dc53f0e607a84cc6ada18e9b04c305cf
         * updatetime : 2019-12-23 16:38:05
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * myId : 2aaeb3e645a8419aaf6fc252aea2c5a4
         */

        private String oid;
        private String uid;
        private String orderNo;
        private int num;
        private int priceTotalGood;
        private double priceTotalOrder;
        private int priceInteger;
        private int priceCard;
        private String cardId;
        private int status;
        private String isdelete;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
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

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getPriceTotalGood() {
            return priceTotalGood;
        }

        public void setPriceTotalGood(int priceTotalGood) {
            this.priceTotalGood = priceTotalGood;
        }

        public double getPriceTotalOrder() {
            return priceTotalOrder;
        }

        public void setPriceTotalOrder(double priceTotalOrder) {
            this.priceTotalOrder = priceTotalOrder;
        }

        public int getPriceInteger() {
            return priceInteger;
        }

        public void setPriceInteger(int priceInteger) {
            this.priceInteger = priceInteger;
        }

        public int getPriceCard() {
            return priceCard;
        }

        public void setPriceCard(int priceCard) {
            this.priceCard = priceCard;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(String isdelete) {
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
