package com.jxbn.kaolatt.bean;

/**
 * Created by hecuncun on 2019/12/22
 */
public class OrderGoodsBean {

    /**
     * remark3 : 商品id
     * numTotal : 商品数量
     * specs : 商品规格信息，例如：规格名称-属性,规格名称-属性
     */

    private String remark3;
    private String numTotal;
    private String specs;

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getNumTotal() {
        return numTotal;
    }

    public void setNumTotal(String numTotal) {
        this.numTotal = numTotal;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
