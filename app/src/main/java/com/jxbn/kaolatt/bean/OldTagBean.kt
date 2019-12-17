package com.jxbn.kaolatt.bean

import org.litepal.crud.LitePalSupport

/**
 * Created by heCunCun on 2019/12/17
 */
data class OldTagBean(val tag:String) :LitePalSupport(){
    val id:Long=1//唯一
}