package com.jxbn.kaolatt.db

import android.content.ContentValues
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport
import org.litepal.extension.find
import org.litepal.extension.findAll

/**
 *
@author：created by xtqb
@description:
@date : 2019/11/15 16:53
 *
 */
object CommOperation {

    /**
     * 单个插入
     */

    @Synchronized
    fun <T : LitePalSupport> insert(t: T) {
        t.save()
    }

    /**
     * 批量插入
     */
    @Synchronized
    fun <T : LitePalSupport> insert(list: MutableList<T>) {
        LitePal.saveAll(list)
    }

    /**
     * 条件删除
     */
    @Synchronized
    inline fun <reified T : LitePalSupport> delete(key: String, value: String): Int {
        return LitePal.deleteAll(T::class.java, "$key = ?", value)
    }

    /**
     * 多条件删除
     */
    @Synchronized
    inline fun <reified T : LitePalSupport> delete(map: MutableMap<String, String>): Int {
        if (map == null || map.size == 0) return 0
        var sql = ""
        var conditions = arrayOfNulls<String>(map.size)
        var counter = 0
        map.forEach { key, value ->
            conditions[counter] = value
            counter++;
            if (counter == map.size) {
                sql += "$key = ? and "
            } else {
                sql += "$key = ?"
            }
        }
        return LitePal.deleteAll(T::class.java, sql, *conditions)
    }

    /**
     * 单条件更新
     */
    @Synchronized
    inline fun <reified T : LitePalSupport> update(values: ContentValues, id: Long): Int {
        return LitePal.update(T::class.java, values, id)
    }

    /**
     * 多条件更新
     */
    @Synchronized
    inline fun <reified T : LitePalSupport> update(map: MutableMap<String, String>, values: ContentValues): Int {
        if (map == null || map.size == 0) return 0
        var conditions = arrayOfNulls<String>(map.size)
        var sql = ""
        var counter = 0
        map.forEach { key, value ->
            conditions[counter] = value
            counter++;
            if (counter == map.size) {
                sql += "$key = ? and "
            } else {
                sql += "$key = ?"
            }
        }
        return LitePal.updateAll(T::class.java, values, sql, *conditions)
    }

    /**
     * 查询全部
     */
    inline fun <reified T : LitePalSupport> query(): List<T> {
        return LitePal.findAll()
    }

    /**
     * 单条件查询查询
     */
    inline fun <reified T : LitePalSupport> query(key: String, value: String): List<T> {
        return LitePal.where("$key = ?", value).find()
    }

    /**
     * 多条件查询
     */
    inline fun <reified T : LitePalSupport> query(map: MutableMap<String, String>): List<T>? {
        if (map == null || map.isEmpty()) return null
        var conditions = arrayOfNulls<String>(map.size)
        var sql = ""
        var counter = 0
        map.forEach { key, value ->
            conditions[counter] = value
            counter++;
            if (counter == map.size) {
                sql += "$key = ? and "
            } else {
                sql += "$key = ?"
            }
        }
        return LitePal.where(sql, *conditions).find()
    }
}