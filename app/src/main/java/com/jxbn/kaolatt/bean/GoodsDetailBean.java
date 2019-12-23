package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/19
 */
public class GoodsDetailBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"gid":"123","cid":"1-2","picture":"/uploadtemp/image/20180318/20180318225610_961.jpg,/uploadtemp/image/20180318/20180318225713_835.jpg","name":"物品1","priceReal":88,"priceSecond":128,"salesVolume":"0","content":"这个物品有点好","numTotal":0,"numRemainder":255,"exquisite":1,"isdelete":0,"remark1":"","remark2":"0","remark3":"","remark4":"","createid":"1","max":"","min":"","createtime":"2019-12-09 17:36:20","updateid":"1","updatetime":"2019-12-09 17:36:23","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","collectId":"aae347b61d564880a7a976ede22de04c","goodsSpecslList":[{"sid":"asdfsaf","gid":"123","isdelete":0,"name":"颜色","info":"白色,黑色,红色","remark3":"","remark4":"","createid":"1","createtime":"2019-12-10 17:58:26","updateid":"1","updatetime":"2019-12-10 17:58:29","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":"","startDate":"","endDate":"","myId":"asdfsaf"},{"sid":"dsafaf","gid":"13","isdelete":0,"name":"外观","info":"炫彩,清颜,蓝秀","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 17:59:23","updateid":"1","updatetime":"2019-12-10 17:59:26","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsafaf"}],"goodsParamList":[{"gpid":"fdasfd","gid":"123","isdelete":0,"name":"产品品牌","info":"lancome/兰蔻","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:00:29","updateid":"1","updatetime":"2019-12-10 18:00:31","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"fdasfd"},{"gpid":"adsfsaf","gid":"123","isdelete":0,"name":"兰蔻单品","info":"新精华肌底液","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:01:20","updateid":"1","updatetime":"2019-12-10 18:01:22","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"adsfsaf"},{"gpid":"dsafasfd","gid":"123","isdelete":0,"name":"产品产地","info":"法国","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:02:09","updateid":"1","updatetime":"2019-12-10 18:02:12","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsafasfd"},{"gpid":"adafsad","gid":"123","isdelete":0,"name":"产品功效","info":"补水 保湿 滋润","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:09:12","updateid":"1","updatetime":"2019-12-10 18:09:16","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"adafsad"},{"gpid":"asdfasfsadf","gid":"123","isdelete":0,"name":"规格类型","info":"正常规格","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:09:14","updateid":"1","updatetime":"2019-12-10 18:09:19","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"asdfasfsadf"}],"myId":"123"}
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
         * collectId : aae347b61d564880a7a976ede22de04c
         * goodsSpecslList : [{"sid":"asdfsaf","gid":"123","isdelete":0,"name":"颜色","info":"白色,黑色,红色","remark3":"","remark4":"","createid":"1","createtime":"2019-12-10 17:58:26","updateid":"1","updatetime":"2019-12-10 17:58:29","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":"","startDate":"","endDate":"","myId":"asdfsaf"},{"sid":"dsafaf","gid":"13","isdelete":0,"name":"外观","info":"炫彩,清颜,蓝秀","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 17:59:23","updateid":"1","updatetime":"2019-12-10 17:59:26","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsafaf"}]
         * goodsParamList : [{"gpid":"fdasfd","gid":"123","isdelete":0,"name":"产品品牌","info":"lancome/兰蔻","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:00:29","updateid":"1","updatetime":"2019-12-10 18:00:31","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"fdasfd"},{"gpid":"adsfsaf","gid":"123","isdelete":0,"name":"兰蔻单品","info":"新精华肌底液","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:01:20","updateid":"1","updatetime":"2019-12-10 18:01:22","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"adsfsaf"},{"gpid":"dsafasfd","gid":"123","isdelete":0,"name":"产品产地","info":"法国","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:02:09","updateid":"1","updatetime":"2019-12-10 18:02:12","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsafasfd"},{"gpid":"adafsad","gid":"123","isdelete":0,"name":"产品功效","info":"补水 保湿 滋润","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:09:12","updateid":"1","updatetime":"2019-12-10 18:09:16","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"adafsad"},{"gpid":"asdfasfsadf","gid":"123","isdelete":0,"name":"规格类型","info":"正常规格","remark3":null,"remark4":null,"createid":"1","createtime":"2019-12-10 18:09:14","updateid":"1","updatetime":"2019-12-10 18:09:19","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"asdfasfsadf"}]
         * myId : 123
         */

        private String gid;
        private String cid;
        private String picture;
        private String name;
        private double priceReal;
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
        private String collectId;
        private String myId;
        private List<GoodsSpecslListBean> goodsSpecslList;
        private List<GoodsParamListBean> goodsParamList;

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

        public double getPriceReal() {
            return priceReal;
        }

        public void setPriceReal(double priceReal) {
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

        public String getCollectId() {
            return collectId;
        }

        public void setCollectId(String collectId) {
            this.collectId = collectId;
        }

        public String getMyId() {
            return myId;
        }

        public void setMyId(String myId) {
            this.myId = myId;
        }

        public List<GoodsSpecslListBean> getGoodsSpecslList() {
            return goodsSpecslList;
        }

        public void setGoodsSpecslList(List<GoodsSpecslListBean> goodsSpecslList) {
            this.goodsSpecslList = goodsSpecslList;
        }

        public List<GoodsParamListBean> getGoodsParamList() {
            return goodsParamList;
        }

        public void setGoodsParamList(List<GoodsParamListBean> goodsParamList) {
            this.goodsParamList = goodsParamList;
        }

        public static class GoodsSpecslListBean {
            /**
             * sid : asdfsaf
             * gid : 123
             * isdelete : 0
             * name : 颜色
             * info : 白色,黑色,红色
             * remark3 :
             * remark4 :
             * createid : 1
             * createtime : 2019-12-10 17:58:26
             * updateid : 1
             * updatetime : 2019-12-10 17:58:29
             * startIndex : 0
             * pageSize : 0
             * orderBy : null
             * fieldName :
             * startDate :
             * endDate :
             * myId : asdfsaf
             */

            private String sid;
            private String gid;
            private int isdelete;
            private String name;
            private String info;
            private String remark3;
            private String remark4;
            private String createid;
            private String createtime;
            private String updateid;
            private String updatetime;
            private int startIndex;
            private int pageSize;
            private Object orderBy;
            private String fieldName;
            private String startDate;
            private String endDate;
            private String myId;

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public int getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(int isdelete) {
                this.isdelete = isdelete;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
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

            public Object getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(Object orderBy) {
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

        public static class GoodsParamListBean {
            /**
             * gpid : fdasfd
             * gid : 123
             * isdelete : 0
             * name : 产品品牌
             * info : lancome/兰蔻
             * remark3 : null
             * remark4 : null
             * createid : 1
             * createtime : 2019-12-10 18:00:29
             * updateid : 1
             * updatetime : 2019-12-10 18:00:31
             * startIndex : 0
             * pageSize : 0
             * orderBy : null
             * fieldName : null
             * startDate : null
             * endDate : null
             * myId : fdasfd
             */

            private String gpid;
            private String gid;
            private int isdelete;
            private String name;
            private String info;
            private Object remark3;
            private Object remark4;
            private String createid;
            private String createtime;
            private String updateid;
            private String updatetime;
            private int startIndex;
            private int pageSize;
            private Object orderBy;
            private Object fieldName;
            private Object startDate;
            private Object endDate;
            private String myId;

            public String getGpid() {
                return gpid;
            }

            public void setGpid(String gpid) {
                this.gpid = gpid;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public int getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(int isdelete) {
                this.isdelete = isdelete;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public Object getRemark3() {
                return remark3;
            }

            public void setRemark3(Object remark3) {
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
