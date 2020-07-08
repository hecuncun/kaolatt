package com.jxbn.kaolatt.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heCunCun on 2020/7/8
 */
public class ExpressBean implements Parcelable {

    /**
     * code : OK
     * no : SF1191344514992:2862
     * type : SF
     * list : [{"content":"廊坊市","time":"2020-06-14 14:56:16"},{"content":"[廊坊市]您的快件已签收，如有疑问请电联小哥【于福波，电话：13552739211】。疫情期间顺丰每日对网点消毒、小哥每日测温、配戴口罩，感谢您使用顺丰，期待再次为您服务。（主单总件数：1件）","time":"2020-06-14 14:56:13"}]
     */

    private String code;
    private String no;
    private String type;
    private List<ListBean> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * content : 廊坊市
         * time : 2020-06-14 14:56:16
         */

        private String content;
        private String time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
            dest.writeString(this.time);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.content = in.readString();
            this.time = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.no);
        dest.writeString(this.type);
        dest.writeList(this.list);
    }

    public ExpressBean() {
    }

    protected ExpressBean(Parcel in) {
        this.code = in.readString();
        this.no = in.readString();
        this.type = in.readString();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ExpressBean> CREATOR = new Parcelable.Creator<ExpressBean>() {
        @Override
        public ExpressBean createFromParcel(Parcel source) {
            return new ExpressBean(source);
        }

        @Override
        public ExpressBean[] newArray(int size) {
            return new ExpressBean[size];
        }
    };
}
