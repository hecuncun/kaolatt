package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/21
 */
public class OrderListBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"currentPageSize":10,"page":1,"result":"","message":"","total":1,"records":2,"rows":[{"oid":"55a5585bcccf40bf93ac8916edd3c133","uid":"fb22796b5c1a48c38c42d0a2034ba27e","orderNo":"20191213205241403","num":12,"priceTotalGood":1056,"priceTotalOrder":1053.45,"priceInteger":2.55,"priceCard":0,"cardId":"","status":1,"isdelete":0,"remark1":"53e73677761d41f99522fa58ccd97f19","remark2":"","remark3":"255","remark4":"","createtime":"2019-12-13 20:52:41","updateid":"fb22796b5c1a48c38c42d0a2034ba27e","updatetime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","orderGoodslList":[{"ogid":"8e45b44093e0404a9843a6318d4d6aa6","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":10,"isdelete":0,"specs":"颜色-白色,外观-清颜","remark2":"55a5585bcccf40bf93ac8916edd3c133","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"8e45b44093e0404a9843a6318d4d6aa6"},{"ogid":"f62cad46aad74912962b2fca74b4a3d0","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":2,"isdelete":0,"specs":"颜色-白色,外观-蓝秀","remark2":"55a5585bcccf40bf93ac8916edd3c133","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"f62cad46aad74912962b2fca74b4a3d0"}],"userAddress":null,"myId":"55a5585bcccf40bf93ac8916edd3c133"},{"oid":"ea31a42c6bfd4d2db2dc738377106fa2","uid":"fb22796b5c1a48c38c42d0a2034ba27e","orderNo":"20191213204611686","num":11,"priceTotalGood":968,"priceTotalOrder":935.45,"priceInteger":2.55,"priceCard":30,"cardId":"dfafda","status":1,"isdelete":0,"remark1":"53e73677761d41f99522fa58ccd97f19","remark2":null,"remark3":"255","remark4":null,"createtime":"2019-12-13 20:43:37","updateid":"fb22796b5c1a48c38c42d0a2034ba27e","updatetime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"orderGoodslList":[{"ogid":"998fcdc990834535917cdad90af8d5b8","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":1,"isdelete":0,"specs":"颜色-白色,外观-蓝秀","remark2":"ea31a42c6bfd4d2db2dc738377106fa2","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"998fcdc990834535917cdad90af8d5b8"},{"ogid":"cd9abe955cd04760a897664f6c0d26b6","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":10,"isdelete":0,"specs":"颜色-白色,外观-清颜","remark2":"ea31a42c6bfd4d2db2dc738377106fa2","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"cd9abe955cd04760a897664f6c0d26b6"}],"userAddress":null,"myId":"ea31a42c6bfd4d2db2dc738377106fa2"}]}
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
         * currentPageSize : 10
         * page : 1
         * result :
         * message :
         * total : 1
         * records : 2
         * rows : [{"oid":"55a5585bcccf40bf93ac8916edd3c133","uid":"fb22796b5c1a48c38c42d0a2034ba27e","orderNo":"20191213205241403","num":12,"priceTotalGood":1056,"priceTotalOrder":1053.45,"priceInteger":2.55,"priceCard":0,"cardId":"","status":1,"isdelete":0,"remark1":"53e73677761d41f99522fa58ccd97f19","remark2":"","remark3":"255","remark4":"","createtime":"2019-12-13 20:52:41","updateid":"fb22796b5c1a48c38c42d0a2034ba27e","updatetime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","orderGoodslList":[{"ogid":"8e45b44093e0404a9843a6318d4d6aa6","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":10,"isdelete":0,"specs":"颜色-白色,外观-清颜","remark2":"55a5585bcccf40bf93ac8916edd3c133","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"8e45b44093e0404a9843a6318d4d6aa6"},{"ogid":"f62cad46aad74912962b2fca74b4a3d0","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":2,"isdelete":0,"specs":"颜色-白色,外观-蓝秀","remark2":"55a5585bcccf40bf93ac8916edd3c133","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"f62cad46aad74912962b2fca74b4a3d0"}],"userAddress":null,"myId":"55a5585bcccf40bf93ac8916edd3c133"},{"oid":"ea31a42c6bfd4d2db2dc738377106fa2","uid":"fb22796b5c1a48c38c42d0a2034ba27e","orderNo":"20191213204611686","num":11,"priceTotalGood":968,"priceTotalOrder":935.45,"priceInteger":2.55,"priceCard":30,"cardId":"dfafda","status":1,"isdelete":0,"remark1":"53e73677761d41f99522fa58ccd97f19","remark2":null,"remark3":"255","remark4":null,"createtime":"2019-12-13 20:43:37","updateid":"fb22796b5c1a48c38c42d0a2034ba27e","updatetime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"orderGoodslList":[{"ogid":"998fcdc990834535917cdad90af8d5b8","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":1,"isdelete":0,"specs":"颜色-白色,外观-蓝秀","remark2":"ea31a42c6bfd4d2db2dc738377106fa2","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"998fcdc990834535917cdad90af8d5b8"},{"ogid":"cd9abe955cd04760a897664f6c0d26b6","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":10,"isdelete":0,"specs":"颜色-白色,外观-清颜","remark2":"ea31a42c6bfd4d2db2dc738377106fa2","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"cd9abe955cd04760a897664f6c0d26b6"}],"userAddress":null,"myId":"ea31a42c6bfd4d2db2dc738377106fa2"}]
         */

        private int currentPageSize;
        private int page;
        private String result;
        private String message;
        private int total;
        private int records;
        private List<RowsBean> rows;

        public int getCurrentPageSize() {
            return currentPageSize;
        }

        public void setCurrentPageSize(int currentPageSize) {
            this.currentPageSize = currentPageSize;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * oid : 55a5585bcccf40bf93ac8916edd3c133
             * uid : fb22796b5c1a48c38c42d0a2034ba27e
             * orderNo : 20191213205241403
             * num : 12
             * priceTotalGood : 1056
             * priceTotalOrder : 1053.45
             * priceInteger : 2.55
             * priceCard : 0
             * cardId :
             * status : 1
             * isdelete : 0
             * remark1 : 53e73677761d41f99522fa58ccd97f19
             * remark2 :
             * remark3 : 255
             * remark4 :
             * createtime : 2019-12-13 20:52:41
             * updateid : fb22796b5c1a48c38c42d0a2034ba27e
             * updatetime : 2019-12-13 20:52:41
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * orderGoodslList : [{"ogid":"8e45b44093e0404a9843a6318d4d6aa6","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":10,"isdelete":0,"specs":"颜色-白色,外观-清颜","remark2":"55a5585bcccf40bf93ac8916edd3c133","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"8e45b44093e0404a9843a6318d4d6aa6"},{"ogid":"f62cad46aad74912962b2fca74b4a3d0","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"numTotal":2,"isdelete":0,"specs":"颜色-白色,外观-蓝秀","remark2":"55a5585bcccf40bf93ac8916edd3c133","remark3":"123","remark4":null,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"f62cad46aad74912962b2fca74b4a3d0"}]
             * userAddress : null
             * myId : 55a5585bcccf40bf93ac8916edd3c133
             */

            private String oid;
            private String uid;
            private String orderNo;
            private int num;
            private double priceTotalGood;
            private double priceTotalOrder;
            private double priceInteger;
            private double priceCard;
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
            private Object userAddress;
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

            public double getPriceInteger() {
                return priceInteger;
            }

            public void setPriceInteger(double priceInteger) {
                this.priceInteger = priceInteger;
            }

            public double getPriceCard() {
                return priceCard;
            }

            public void setPriceCard(double priceCard) {
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

            public Object getUserAddress() {
                return userAddress;
            }

            public void setUserAddress(Object userAddress) {
                this.userAddress = userAddress;
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

            public static class OrderGoodslListBean {
                /**
                 * ogid : 8e45b44093e0404a9843a6318d4d6aa6
                 * picture : /uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg
                 * name : 物品1
                 * priceReal : 88
                 * numTotal : 10
                 * isdelete : 0
                 * specs : 颜色-白色,外观-清颜
                 * remark2 : 55a5585bcccf40bf93ac8916edd3c133
                 * remark3 : 123
                 * remark4 : null
                 * createid : fb22796b5c1a48c38c42d0a2034ba27e
                 * createtime : 2019-12-13 20:52:41
                 * startIndex : 0
                 * pageSize : 0
                 * orderBy : null
                 * fieldName : null
                 * startDate : null
                 * endDate : null
                 * myId : 8e45b44093e0404a9843a6318d4d6aa6
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
                private Object orderBy;
                private Object fieldName;
                private Object startDate;
                private Object endDate;
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

                public Object getOrderBy() {
                    return orderBy;
                }

                public void setOrderBy(Object orderBy) {
                    this.orderBy = orderBy;
                }

                public Object getFieldName() {
                    return fieldName;
                }

                public void setFieldName(Object fieldName) {
                    this.fieldName = fieldName;
                }

                public Object getStartDate() {
                    return startDate;
                }

                public void setStartDate(Object startDate) {
                    this.startDate = startDate;
                }

                public Object getEndDate() {
                    return endDate;
                }

                public void setEndDate(Object endDate) {
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
}
