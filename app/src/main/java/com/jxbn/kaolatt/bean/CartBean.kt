package com.jxbn.kaolatt.bean

/**
 * Created by hecuncun on 2019/12/10
 */
data class CartBean(val goodsId:Int,val imgUrl:String,val goodsName:String,val mask:String,val price:String,var num:Int,var isCheck:Boolean) {
}