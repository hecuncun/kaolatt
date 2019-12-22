package com.jxbn.kaolatt.bean

import org.litepal.crud.LitePalSupport

/**
 * Created by hecuncun on 2019/12/10
 */
data class CartBean(val goodsId:String,val imgUrl:String,val goodsName:String,val mask:String,val price:String,var num:Int,var isCheck:Boolean):LitePalSupport() {
    val id:Long=1
}