package com.jxbn.kaolatt.pay;

/**
 * Created by heCunCun on 2019/12/24
 */
public class AliPaySignBean {

    /**
     * code : 10001
     * message : 成功
     * data : {"sign":"alipay_sdk=alipay-sdk-java-4.8.73.ALL&app_id=2021000199685376&biz_content=%7B%22body%22%3A%22%E8%80%83%E6%8B%89%E6%B7%98%E6%B7%98%22%2C%22out_trade_no%22%3A%224d3e4898bbd54430aedc457ff05222bb%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%80%83%E6%8B%89%E6%B7%98%E6%B7%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F59.110.230.192%2Fapppay%2FaliPaySucc.ph&sign=R8hXtn8qZUE%2BcNKw6SQfVDBcGlditVICR38zWttOOdVjdI3mwMsGDnm%2BfQADShaECxCkqqoib%2BLTM%2BT4k%2B%2BA44yK7mC57lmnAJUKucntxyBGmMxZTaj0mwx1YouKHV6rkoxqazThLG5sR1kJ1UKAyK470z9QHdmdM0bgooZSZGHqaFfMiuLhT8y5n2pZgJsfT%2FSas8LQ0LnmkOZMq%2FVd59FJfy4lZqQM%2FLCeYB7h7%2Biy2yhhVGS%2Fdvzhtste4hJuujZoGgM8wX32Mi6E2UsxuaiqfmDsDFbb41%2FQRUBUjF2zJkW8qIAW3hxT1QAM9ey3wUQ1nJoH8et5aWFKAAm35w%3D%3D&sign_type=RSA2&timestamp=2019-12-24+09%3A30%3A38&version=1.0"}
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
         * sign : alipay_sdk=alipay-sdk-java-4.8.73.ALL&app_id=2021000199685376&biz_content=%7B%22body%22%3A%22%E8%80%83%E6%8B%89%E6%B7%98%E6%B7%98%22%2C%22out_trade_no%22%3A%224d3e4898bbd54430aedc457ff05222bb%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%80%83%E6%8B%89%E6%B7%98%E6%B7%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F59.110.230.192%2Fapppay%2FaliPaySucc.ph&sign=R8hXtn8qZUE%2BcNKw6SQfVDBcGlditVICR38zWttOOdVjdI3mwMsGDnm%2BfQADShaECxCkqqoib%2BLTM%2BT4k%2B%2BA44yK7mC57lmnAJUKucntxyBGmMxZTaj0mwx1YouKHV6rkoxqazThLG5sR1kJ1UKAyK470z9QHdmdM0bgooZSZGHqaFfMiuLhT8y5n2pZgJsfT%2FSas8LQ0LnmkOZMq%2FVd59FJfy4lZqQM%2FLCeYB7h7%2Biy2yhhVGS%2Fdvzhtste4hJuujZoGgM8wX32Mi6E2UsxuaiqfmDsDFbb41%2FQRUBUjF2zJkW8qIAW3hxT1QAM9ey3wUQ1nJoH8et5aWFKAAm35w%3D%3D&sign_type=RSA2&timestamp=2019-12-24+09%3A30%3A38&version=1.0
         */

        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
