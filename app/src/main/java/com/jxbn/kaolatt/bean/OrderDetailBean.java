package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/22
 */
public class OrderDetailBean {


    /**
     * code : 10001
     * message : 成功
     * data : {"oid":"c39dd442369d496b80954769e5f60c50","uid":"dc53f0e607a84cc6ada18e9b04c305cf","orderNo":"20191229160911089","num":1,"priceTotalGood":0.01,"priceTotalOrder":0.01,"priceInteger":0,"priceCard":0,"cardId":"","status":6,"isdelete":0,"remark1":"d132f72aaa97499282f928a703f82b02","remark2":"","remark3":"0","remark4":"","createtime":"2019-12-29 16:09:10","updateid":"dc53f0e607a84cc6ada18e9b04c305cf","updatetime":"2019-12-31 09:36:51","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","orderGoodslList":[{"ogid":"576b0d231a0149f0879887f400a9a2da","picture":"/uploadtemp/image/2019/12/4cea32f1e4ac4809a4c1cdd20826dae8.jpg,/uploadtemp/image/2019/12/5b2703c8e19f40e4abeb2cce28c59110.jpg","name":"跑步机","priceReal":0.01,"numTotal":1,"isdelete":0,"specs":"颜色-红色","remark2":"c39dd442369d496b80954769e5f60c50","remark3":"123123","remark4":null,"createid":"dc53f0e607a84cc6ada18e9b04c305cf","createtime":"2019-12-29 16:09:10","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"576b0d231a0149f0879887f400a9a2da"}],"userAddress":{"magorid":"d132f72aaa97499282f928a703f82b02","name":"亚索","phone":"13569826875","card":"23558556666","area":"北京市-北京市-昌平区","areaDetail":"社区","remark4":"1","isdelete":0,"createid":"dc53f0e607a84cc6ada18e9b04c305cf","createtime":"2019-12-23 14:49:39","updateid":"dc53f0e607a84cc6ada18e9b04c305cf","updatetime":"2019-12-26 15:04:44","cardPhotoZ":"","cardPhotoF":"","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"d132f72aaa97499282f928a703f82b02"},"orderReturn":{"rid":"1b631440fcae478b9e1cb2fc65ebdd5a","oid":"c39dd442369d496b80954769e5f60c50","uid":"dc53f0e607a84cc6ada18e9b04c305cf","type":1,"reason":"商品破损（个人原因除外）","picture":"/uploadtemp/image/2019/12/d5124e68f6d24fbd8f56a72640dd1866.JPEG","returnMoney":"","remark":"","isdelete":0,"createtime":"2019-12-31 09:36:51","updateid":"dc53f0e607a84cc6ada18e9b04c305cf","updatetime":"2019-12-31 09:36:51","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"1b631440fcae478b9e1cb2fc65ebdd5a"},"userPhone":"13753638431","nickname":"辣么帅","myId":"c39dd442369d496b80954769e5f60c50"}
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
         * oid : c39dd442369d496b80954769e5f60c50
         * uid : dc53f0e607a84cc6ada18e9b04c305cf
         * orderNo : 20191229160911089
         * num : 1
         * priceTotalGood : 0.01
         * priceTotalOrder : 0.01
         * priceInteger : 0
         * priceCard : 0
         * cardId :
         * status : 6
         * isdelete : 0
         * remark1 : d132f72aaa97499282f928a703f82b02
         * remark2 :
         * remark3 : 0
         * remark4 :
         * createtime : 2019-12-29 16:09:10
         * updateid : dc53f0e607a84cc6ada18e9b04c305cf
         * updatetime : 2019-12-31 09:36:51
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * orderGoodslList : [{"ogid":"576b0d231a0149f0879887f400a9a2da","picture":"/uploadtemp/image/2019/12/4cea32f1e4ac4809a4c1cdd20826dae8.jpg,/uploadtemp/image/2019/12/5b2703c8e19f40e4abeb2cce28c59110.jpg","name":"跑步机","priceReal":0.01,"numTotal":1,"isdelete":0,"specs":"颜色-红色","remark2":"c39dd442369d496b80954769e5f60c50","remark3":"123123","remark4":null,"createid":"dc53f0e607a84cc6ada18e9b04c305cf","createtime":"2019-12-29 16:09:10","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"576b0d231a0149f0879887f400a9a2da"}]
         * userAddress : {"magorid":"d132f72aaa97499282f928a703f82b02","name":"亚索","phone":"13569826875","card":"23558556666","area":"北京市-北京市-昌平区","areaDetail":"社区","remark4":"1","isdelete":0,"createid":"dc53f0e607a84cc6ada18e9b04c305cf","createtime":"2019-12-23 14:49:39","updateid":"dc53f0e607a84cc6ada18e9b04c305cf","updatetime":"2019-12-26 15:04:44","cardPhotoZ":"","cardPhotoF":"","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"d132f72aaa97499282f928a703f82b02"}
         * orderReturn : {"rid":"1b631440fcae478b9e1cb2fc65ebdd5a","oid":"c39dd442369d496b80954769e5f60c50","uid":"dc53f0e607a84cc6ada18e9b04c305cf","type":1,"reason":"商品破损（个人原因除外）","picture":"/uploadtemp/image/2019/12/d5124e68f6d24fbd8f56a72640dd1866.JPEG","returnMoney":"","remark":"","isdelete":0,"createtime":"2019-12-31 09:36:51","updateid":"dc53f0e607a84cc6ada18e9b04c305cf","updatetime":"2019-12-31 09:36:51","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"1b631440fcae478b9e1cb2fc65ebdd5a"}
         * userPhone : 13753638431
         * nickname : 辣么帅
         * myId : c39dd442369d496b80954769e5f60c50
         */

        private String oid;
        private String uid;
        private String orderNo;
        private int num;
        private double priceTotalGood;
        private double priceTotalOrder;
        private int priceInteger;
        private int priceCard;
        private String cardId;
        private int status;
        private int isdelete;
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
        private UserAddressBean userAddress;
        private OrderReturnBean orderReturn;
        private String userPhone;
        private String nickname;
        private String myId;
        private List<OrderGoodslListBean> orderGoodslList;

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

        public double getPriceTotalGood() {
            return priceTotalGood;
        }

        public void setPriceTotalGood(double priceTotalGood) {
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

        public UserAddressBean getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(UserAddressBean userAddress) {
            this.userAddress = userAddress;
        }

        public OrderReturnBean getOrderReturn() {
            return orderReturn;
        }

        public void setOrderReturn(OrderReturnBean orderReturn) {
            this.orderReturn = orderReturn;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMyId() {
            return myId;
        }

        public void setMyId(String myId) {
            this.myId = myId;
        }

        public List<OrderGoodslListBean> getOrderGoodslList() {
            return orderGoodslList;
        }

        public void setOrderGoodslList(List<OrderGoodslListBean> orderGoodslList) {
            this.orderGoodslList = orderGoodslList;
        }

        public static class UserAddressBean {
            /**
             * magorid : d132f72aaa97499282f928a703f82b02
             * name : 亚索
             * phone : 13569826875
             * card : 23558556666
             * area : 北京市-北京市-昌平区
             * areaDetail : 社区
             * remark4 : 1
             * isdelete : 0
             * createid : dc53f0e607a84cc6ada18e9b04c305cf
             * createtime : 2019-12-23 14:49:39
             * updateid : dc53f0e607a84cc6ada18e9b04c305cf
             * updatetime : 2019-12-26 15:04:44
             * cardPhotoZ :
             * cardPhotoF :
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * myId : d132f72aaa97499282f928a703f82b02
             */

            private String magorid;
            private String name;
            private String phone;
            private String card;
            private String area;
            private String areaDetail;
            private String remark4;
            private int isdelete;
            private String createid;
            private String createtime;
            private String updateid;
            private String updatetime;
            private String cardPhotoZ;
            private String cardPhotoF;
            private int startIndex;
            private int pageSize;
            private String orderBy;
            private String fieldName;
            private String startDate;
            private String endDate;
            private String myId;

            public String getMagorid() {
                return magorid;
            }

            public void setMagorid(String magorid) {
                this.magorid = magorid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAreaDetail() {
                return areaDetail;
            }

            public void setAreaDetail(String areaDetail) {
                this.areaDetail = areaDetail;
            }

            public String getRemark4() {
                return remark4;
            }

            public void setRemark4(String remark4) {
                this.remark4 = remark4;
            }

            public int getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(int isdelete) {
                this.isdelete = isdelete;
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

            public String getCardPhotoZ() {
                return cardPhotoZ;
            }

            public void setCardPhotoZ(String cardPhotoZ) {
                this.cardPhotoZ = cardPhotoZ;
            }

            public String getCardPhotoF() {
                return cardPhotoF;
            }

            public void setCardPhotoF(String cardPhotoF) {
                this.cardPhotoF = cardPhotoF;
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

        public static class OrderReturnBean {
            /**
             * rid : 1b631440fcae478b9e1cb2fc65ebdd5a
             * oid : c39dd442369d496b80954769e5f60c50
             * uid : dc53f0e607a84cc6ada18e9b04c305cf
             * type : 1
             * reason : 商品破损（个人原因除外）
             * picture : /uploadtemp/image/2019/12/d5124e68f6d24fbd8f56a72640dd1866.JPEG
             * returnMoney :
             * remark :
             * isdelete : 0
             * createtime : 2019-12-31 09:36:51
             * updateid : dc53f0e607a84cc6ada18e9b04c305cf
             * updatetime : 2019-12-31 09:36:51
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * myId : 1b631440fcae478b9e1cb2fc65ebdd5a
             */

            private String rid;
            private String oid;
            private String uid;
            private int type;
            private String reason;
            private String picture;
            private String returnMoney;
            private String remark;
            private int isdelete;
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

            public String getRid() {
                return rid;
            }

            public void setRid(String rid) {
                this.rid = rid;
            }

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getReturnMoney() {
                return returnMoney;
            }

            public void setReturnMoney(String returnMoney) {
                this.returnMoney = returnMoney;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(int isdelete) {
                this.isdelete = isdelete;
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

        public static class OrderGoodslListBean {
            /**
             * ogid : 576b0d231a0149f0879887f400a9a2da
             * picture : /uploadtemp/image/2019/12/4cea32f1e4ac4809a4c1cdd20826dae8.jpg,/uploadtemp/image/2019/12/5b2703c8e19f40e4abeb2cce28c59110.jpg
             * name : 跑步机
             * priceReal : 0.01
             * numTotal : 1
             * isdelete : 0
             * specs : 颜色-红色
             * remark2 : c39dd442369d496b80954769e5f60c50
             * remark3 : 123123
             * remark4 : null
             * createid : dc53f0e607a84cc6ada18e9b04c305cf
             * createtime : 2019-12-29 16:09:10
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * myId : 576b0d231a0149f0879887f400a9a2da
             */

            private String ogid;
            private String picture;
            private String name;
            private double priceReal;
            private int numTotal;
            private int isdelete;
            private String specs;
            private String remark2;
            private String remark3;
            private Object remark4;
            private String createid;
            private String createtime;
            private int startIndex;
            private int pageSize;
            private String orderBy;
            private String fieldName;
            private String startDate;
            private String endDate;
            private String myId;

            public String getOgid() {
                return ogid;
            }

            public void setOgid(String ogid) {
                this.ogid = ogid;
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

            public double getPriceReal() {
                return priceReal;
            }

            public void setPriceReal(double priceReal) {
                this.priceReal = priceReal;
            }

            public int getNumTotal() {
                return numTotal;
            }

            public void setNumTotal(int numTotal) {
                this.numTotal = numTotal;
            }

            public int getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(int isdelete) {
                this.isdelete = isdelete;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
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

            public Object getRemark4() {
                return remark4;
            }

            public void setRemark4(Object remark4) {
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
}
