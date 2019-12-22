package com.jxbn.kaolatt.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by hecuncun on 2019/12/22
 */
public class AddressListBean {

    /**
     * code : 10001
     * message : 成功
     * data : [{"magorid":"53e73677761d41f99522fa58ccd97f19","name":"张三","phone":"15210010568","card":"220221199300000000","area":"北京-北京市-昌平区","areaDetail":"回龙观东大街","remark4":"1","isdelete":0,"createid":"fb22796b5c1a48c38c42d0a2034ba27e","createtime":"2019-12-13 20:27:32","updateid":"fb22796b5c1a48c38c42d0a2034ba27e","updatetime":"2019-12-13 20:27:32","startIndex":0,"pageSize":0,"orderBy":"","fieldName":"","startDate":"","endDate":"","myId":"53e73677761d41f99522fa58ccd97f19"}]
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

    public static class DataBean implements Parcelable {
        /**
         * magorid : 53e73677761d41f99522fa58ccd97f19
         * name : 张三
         * phone : 15210010568
         * card : 220221199300000000
         * area : 北京-北京市-昌平区
         * areaDetail : 回龙观东大街
         * remark4 : 1
         * isdelete : 0
         * createid : fb22796b5c1a48c38c42d0a2034ba27e
         * createtime : 2019-12-13 20:27:32
         * updateid : fb22796b5c1a48c38c42d0a2034ba27e
         * updatetime : 2019-12-13 20:27:32
         * startIndex : 0
         * pageSize : 0
         * orderBy :
         * fieldName :
         * startDate :
         * endDate :
         * myId : 53e73677761d41f99522fa58ccd97f19
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.magorid);
            dest.writeString(this.name);
            dest.writeString(this.phone);
            dest.writeString(this.card);
            dest.writeString(this.area);
            dest.writeString(this.areaDetail);
            dest.writeString(this.remark4);
            dest.writeInt(this.isdelete);
            dest.writeString(this.createid);
            dest.writeString(this.createtime);
            dest.writeString(this.updateid);
            dest.writeString(this.updatetime);
            dest.writeInt(this.startIndex);
            dest.writeInt(this.pageSize);
            dest.writeString(this.orderBy);
            dest.writeString(this.fieldName);
            dest.writeString(this.startDate);
            dest.writeString(this.endDate);
            dest.writeString(this.myId);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.magorid = in.readString();
            this.name = in.readString();
            this.phone = in.readString();
            this.card = in.readString();
            this.area = in.readString();
            this.areaDetail = in.readString();
            this.remark4 = in.readString();
            this.isdelete = in.readInt();
            this.createid = in.readString();
            this.createtime = in.readString();
            this.updateid = in.readString();
            this.updatetime = in.readString();
            this.startIndex = in.readInt();
            this.pageSize = in.readInt();
            this.orderBy = in.readString();
            this.fieldName = in.readString();
            this.startDate = in.readString();
            this.endDate = in.readString();
            this.myId = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
