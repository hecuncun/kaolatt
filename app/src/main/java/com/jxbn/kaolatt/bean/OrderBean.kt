package com.jxbn.kaolatt.bean

/**
 * Created by hecuncun on 2019/12/4
 */
data class OrderBean(
        val type: Int,
        val orderNum: String,
        val imgUrl: String,
        val goodsName: String,
        val goodsNum: Int,
        val mark: String,
        val money: Double)