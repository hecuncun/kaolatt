package com.jxbn.kaolatt.ui.activity

import android.Manifest
import android.support.design.bottomnavigation.LabelVisibilityMode
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.ui.fragment.HomeFragment
import com.jxbn.kaolatt.ui.fragment.MineFragment
import com.jxbn.kaolatt.ui.fragment.ShoppingCartFragment
import com.jxbn.kaolatt.ui.fragment.SortFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : BaseActivity() {
    private val FRAGMENT_HOME = 0x01
    private val FRAGMENT_SORT = 0X02
    private val FRAGMENT_SHOPPING_CART = 0X03
    private val FRAGMENT_MINE = 0X04

    private var mIndex = FRAGMENT_HOME

    private var mHomeFragment: HomeFragment? = null
    private var mSortFragment: SortFragment? = null
    private var mShoppingCartFragment: ShoppingCartFragment? = null
    private var mMineFragment: MineFragment? = null
    private val PERMISS_REQUEST_CODE = 0x100

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initData() {
        if (checkPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))) {

        } else {
            requestPermission(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), PERMISS_REQUEST_CODE)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (PERMISS_REQUEST_CODE == requestCode) {
            //先初始化一个用户对象
          //  val user = PersonalInfoBean("野顽玩家", "60")
           // user.save()
        }
    }


    override fun initView() {
        toolbar_title.text = "首页"

        bottom_navigation.run {
            // 以前使用 BottomNavigationViewHelper.disableShiftMode(this) 方法来设置底部图标和字体都显示并去掉点击动画
            // 升级到 28.0.0 之后，官方重构了 BottomNavigationView ，目前可以使用 labelVisibilityMode = 1 来替代
            // BottomNavigationViewHelper.disableShiftMode(this)
            /**
             * auto   当item小于等于3是，显示文字，item大于3个默认不显示，选中显示文字
            labeled  始终显示文字
            selected  选中时显示文字
            unlabeled 不显示文字
             */
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        }

        showFragment(mIndex)
    }

    /**
     * 显示Fragment
     */
    private fun showFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragment(transaction)
        mIndex = index
        toolbar_right_img.visibility = View.GONE
        toolbar_right_tv.visibility = View.GONE
        iv_back.visibility = View.GONE

        when (index) {
            FRAGMENT_HOME -> {
                toolbar_title.text ="首页"
//                toolbar_right_img.visibility = View.VISIBLE
//                toolbar_right_img.setImageResource(R.drawable.icon_ble_close)
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.container, mHomeFragment!!, "home")
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }

            FRAGMENT_SORT -> {
              //  toolbar_title.text = getString(R.string.main_sport_record)
                if (mSortFragment == null) {
                    mSortFragment = SortFragment.getInstance()
                    transaction.add(R.id.container, mSortFragment!!, "sports")
                } else {
                    transaction.show(mSortFragment!!)
                }
            }

            FRAGMENT_SHOPPING_CART -> {
              //  toolbar_title.text = getString(R.string.main_setting)
              //  toolbar_right_tv.visibility = View.VISIBLE
                //toolbar_right_tv.text = "保存"

                if (mShoppingCartFragment == null) {
                    mShoppingCartFragment = ShoppingCartFragment.getInstance()
                    transaction.add(R.id.container, mShoppingCartFragment!!, "setting")
                } else {
                    transaction.show(mShoppingCartFragment!!)
                }
            }

            FRAGMENT_MINE -> {
               // toolbar_title.text = getString(R.string.main_connect)
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.getInstance()
                    transaction.add(R.id.container, mMineFragment!!, "connect")
                } else {
                    transaction.show(mMineFragment!!)
                }
            }
        }
        transaction.commit()
    }

    /**
     * 隐藏所有fragment
     */
    private fun hideFragment(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mSortFragment?.let { transaction.hide(it) }
        mShoppingCartFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }

    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener when (item.itemId) {
            R.id.action_home -> {
                showFragment(FRAGMENT_HOME)
                true
            }

            R.id.action_sort -> {
                showFragment(FRAGMENT_SORT)
                true
            }

            R.id.action_shopping_cart -> {
                showFragment(FRAGMENT_SHOPPING_CART)
                true
            }

            R.id.action_mine -> {
                showFragment(FRAGMENT_MINE)
                true
            }
            else -> {
                false
            }

        }
    }

    override fun initListener() {
    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
