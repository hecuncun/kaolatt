package com.jxbn.kaolatt.bean;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/17
 */
public class MsgListBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"currentPageSize":10,"page":1,"result":"","message":"","total":1,"records":2,"rows":[{"mid":"123","isdelete":0,"title":"双十二优惠","photo":"","content":"<p><span style=\"color: rgb(194, 79, 74);\">一天帮24个人跑镖<\/span>，一个人12块，美滋滋<\/p><img src=\"https://imgsa.baidu.com/forum/w%3D580/sign=dd6fa05cbcde9c82a665f9875c8080d2/c4e8fbf2b2119313ff49bbd56a380cd790238dfc.jpg\" size=\"138640\" changedsize=\"true\" width=\"560\" height=\"315\">","remark4":"","createid":"1","createtime":"2019-12-08 20:11:15","updateid":"1","updatetime":"2019-12-08 20:11:18","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","status":"12312321332","myId":"123"},{"mid":"123","isdelete":0,"title":"双十二优惠","photo":null,"content":"<p><span style=\"color: rgb(194, 79, 74);\">一天帮24个人跑镖<\/span>，一个人12块，美滋滋<\/p><img src=\"https://imgsa.baidu.com/forum/w%3D580/sign=dd6fa05cbcde9c82a665f9875c8080d2/c4e8fbf2b2119313ff49bbd56a380cd790238dfc.jpg\" size=\"138640\" changedsize=\"true\" width=\"560\" height=\"315\">","remark4":null,"createid":"1","createtime":"2019-12-08 20:11:15","updateid":"1","updatetime":"2019-12-08 20:11:18","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"status":"fdsfasdfsadf","myId":"123"}]}
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
         * rows : [{"mid":"123","isdelete":0,"title":"双十二优惠","photo":"","content":"<p><span style=\"color: rgb(194, 79, 74);\">一天帮24个人跑镖<\/span>，一个人12块，美滋滋<\/p><img src=\"https://imgsa.baidu.com/forum/w%3D580/sign=dd6fa05cbcde9c82a665f9875c8080d2/c4e8fbf2b2119313ff49bbd56a380cd790238dfc.jpg\" size=\"138640\" changedsize=\"true\" width=\"560\" height=\"315\">","remark4":"","createid":"1","createtime":"2019-12-08 20:11:15","updateid":"1","updatetime":"2019-12-08 20:11:18","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","status":"12312321332","myId":"123"},{"mid":"123","isdelete":0,"title":"双十二优惠","photo":null,"content":"<p><span style=\"color: rgb(194, 79, 74);\">一天帮24个人跑镖<\/span>，一个人12块，美滋滋<\/p><img src=\"https://imgsa.baidu.com/forum/w%3D580/sign=dd6fa05cbcde9c82a665f9875c8080d2/c4e8fbf2b2119313ff49bbd56a380cd790238dfc.jpg\" size=\"138640\" changedsize=\"true\" width=\"560\" height=\"315\">","remark4":null,"createid":"1","createtime":"2019-12-08 20:11:15","updateid":"1","updatetime":"2019-12-08 20:11:18","startIndex":0,"pageSize":0,"orderBy":null,"fieldName":null,"startDate":null,"endDate":null,"status":"fdsfasdfsadf","myId":"123"}]
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
             * mid : 123
             * isdelete : 0
             * title : 双十二优惠
             * photo :
             * content : <p><span style="color: rgb(194, 79, 74);">一天帮24个人跑镖</span>，一个人12块，美滋滋</p><img src="https://imgsa.baidu.com/forum/w%3D580/sign=dd6fa05cbcde9c82a665f9875c8080d2/c4e8fbf2b2119313ff49bbd56a380cd790238dfc.jpg" size="138640" changedsize="true" width="560" height="315">
             * remark4 :
             * createid : 1
             * createtime : 2019-12-08 20:11:15
             * updateid : 1
             * updatetime : 2019-12-08 20:11:18
             * startIndex : 0
             * pageSize : 0
             * orderBy :
             * fieldName :
             * startDate :
             * endDate :
             * status : 12312321332
             * myId : 123
             */

            private String mid;
            private int isdelete;
            private String title;
            private String photo;
            private String content;
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
            private String status;
            private String myId;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public int getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(int isdelete) {
                this.isdelete = isdelete;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
