package com.jxbn.kaolatt.pay;

/**
 * Created by hecuncun on 2019/12/24
 */
public class WxPaySignBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"timeStamp":"1577117175","paySign":"F002A67C2F82948EB93017B856E62F92","signType":"MD5","prepayId":"wx240006154789537020e7b2831107043800","nonceStr":"1577117175"}
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
         * timeStamp : 1577117175
         * paySign : F002A67C2F82948EB93017B856E62F92
         * signType : MD5
         * prepayId : wx240006154789537020e7b2831107043800
         * nonceStr : 1577117175
         */

        private String timeStamp;
        private String paySign;
        private String signType;
        private String prepayId;
        private String nonceStr;

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }
    }
}
