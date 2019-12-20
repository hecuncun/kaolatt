package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/20
 */
public class EvaluateListBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"currentPageSize":10,"page":1,"result":"","message":"","total":1,"records":2,"rows":[{"eid":"dsafa","gid":"123","uid":"fb22796b5c1a48c38c42d0a2034ba27e","content":"很好哦","isdelete":0,"remark1":"1","remark2":"","remark3":"","remark4":"","createtime":"2019-12-10 19:50:10","updateid":"1","updatetime":"2019-12-10 19:50:12","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","nickname":"我是测试","path":"/uploadtemp/image/2019/12/f5c596efe2404dae9987e3341b229161.bmp","myId":"dsafa"},{"eid":"adsfsafd","gid":"123","uid":"fb22796b5c1a48c38c42d0a2034ba27e","content":"that' so good!","isdelete":0,"remark1":"2","remark2":null,"remark3":null,"remark4":null,"createtime":"2019-12-10 19:50:10","updateid":"1","updatetime":"2019-12-10 19:50:12","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"nickname":"我是测试","path":"/uploadtemp/image/2019/12/f5c596efe2404dae9987e3341b229161.bmp","myId":"adsfsafd"}]}
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
         * rows : [{"eid":"dsafa","gid":"123","uid":"fb22796b5c1a48c38c42d0a2034ba27e","content":"很好哦","isdelete":0,"remark1":"1","remark2":"","remark3":"","remark4":"","createtime":"2019-12-10 19:50:10","updateid":"1","updatetime":"2019-12-10 19:50:12","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","nickname":"我是测试","path":"/uploadtemp/image/2019/12/f5c596efe2404dae9987e3341b229161.bmp","myId":"dsafa"},{"eid":"adsfsafd","gid":"123","uid":"fb22796b5c1a48c38c42d0a2034ba27e","content":"that' so good!","isdelete":0,"remark1":"2","remark2":null,"remark3":null,"remark4":null,"createtime":"2019-12-10 19:50:10","updateid":"1","updatetime":"2019-12-10 19:50:12","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"nickname":"我是测试","path":"/uploadtemp/image/2019/12/f5c596efe2404dae9987e3341b229161.bmp","myId":"adsfsafd"}]
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
             * eid : dsafa
             * gid : 123
             * uid : fb22796b5c1a48c38c42d0a2034ba27e
             * content : 很好哦
             * isdelete : 0
             * remark1 : 1
             * remark2 :
             * remark3 :
             * remark4 :
             * createtime : 2019-12-10 19:50:10
             * updateid : 1
             * updatetime : 2019-12-10 19:50:12
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * nickname : 我是测试
             * path : /uploadtemp/image/2019/12/f5c596efe2404dae9987e3341b229161.bmp
             * myId : dsafa
             */

            private String eid;
            private String gid;
            private String uid;
            private String content;
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
            private String nickname;
            private String path;
            private String myId;

            public String getEid() {
                return eid;
            }

            public void setEid(String eid) {
                this.eid = eid;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
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
