package com.jxbn.kaolatt.ext

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


/**
 * 扩展函数
 */

fun Fragment.showToast(content: String) {
    Toast.makeText(this.activity?.applicationContext,content,Toast.LENGTH_SHORT).show()
}

fun Context.showToast(content: String) {
    Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
}

/**
 * 格式化当前日期
 */
fun formatCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
}

/**
 * String 转 Calendar
 */
fun String.stringToCalendar(): Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = sdf.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar
}