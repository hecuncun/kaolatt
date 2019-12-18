package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/18
 */
public class ScoreListBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"currentPageSize":10,"page":1,"result":"","message":"","total":1,"records":5,"rows":[{"magorid":"6a345e7f3c9542518d32e14daddbd1d1","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"-255","remark4":"55a5585bcccf40bf93ac8916edd3c133","isdelete":0,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"6a345e7f3c9542518d32e14daddbd1d1"},{"magorid":"9187bf736281482b88cc1f40e855161f","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"-255","remark4":"ea31a42c6bfd4d2db2dc738377106fa2","isdelete":0,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"9187bf736281482b88cc1f40e855161f"},{"magorid":"dsafadf","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"50","remark4":"1","isdelete":0,"createid":"1","createtime":"2019-12-11 16:37:07","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsafadf"},{"magorid":"sdsfsafa","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"2","integral":"-30","remark4":"1","isdelete":0,"createid":"1","createtime":"2019-12-11 16:36:46","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"sdsfsafa"},{"magorid":"dsfsadfsa","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"50","remark4":"1","isdelete":0,"createid":"1","createtime":"2019-12-11 16:36:35","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsfsadfsa"}]}
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
         * records : 5
         * rows : [{"magorid":"6a345e7f3c9542518d32e14daddbd1d1","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"-255","remark4":"55a5585bcccf40bf93ac8916edd3c133","isdelete":0,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:52:41","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"6a345e7f3c9542518d32e14daddbd1d1"},{"magorid":"9187bf736281482b88cc1f40e855161f","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"-255","remark4":"ea31a42c6bfd4d2db2dc738377106fa2","isdelete":0,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:43:37","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"9187bf736281482b88cc1f40e855161f"},{"magorid":"dsafadf","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"50","remark4":"1","isdelete":0,"createid":"1","createtime":"2019-12-11 16:37:07","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsafadf"},{"magorid":"sdsfsafa","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"2","integral":"-30","remark4":"1","isdelete":0,"createid":"1","createtime":"2019-12-11 16:36:46","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"sdsfsafa"},{"magorid":"dsfsadfsa","uid":"fb22796b5c1a48c38c42d0a2034ba27e","type":"1","integral":"50","remark4":"1","isdelete":0,"createid":"1","createtime":"2019-12-11 16:36:35","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"myId":"dsfsadfsa"}]
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
             * magorid : 6a345e7f3c9542518d32e14daddbd1d1
             * uid : fb22796b5c1a48c38c42d0a2034ba27e
             * type : 1
             * integral : -255
             * remark4 : 55a5585bcccf40bf93ac8916edd3c133
             * isdelete : 0
             * createid : fb22796b5c1a48c38c42d0a2034ba27e
             * createtime : 2019-12-13 20:52:41
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * myId : 6a345e7f3c9542518d32e14daddbd1d1
             */

            private String magorid;
            private String uid;
            private String type;
            private String integral;
            private String remark4;
            private int isdelete;
            private String createid;
            private String createtime;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
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
