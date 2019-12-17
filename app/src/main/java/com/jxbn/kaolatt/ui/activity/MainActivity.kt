package com.jxbn.kaolatt.ui.activity

import android.Manifest
import android.content.Intent
import android.support.design.bottomnavigation.LabelVisibilityMode
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.ext.startActivityCheckLogin
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
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

        //初始化用户未读消息
        val unReadMsgCall = SLMRetrofit.getInstance().api.unReadMsgCall(uid)
        unReadMsgCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
            override fun onSucceed(t: BaseNoDataBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                   val unreadNum = t.data.toString().toDouble().toInt()
                    if (unreadNum == 0){
                        toolbar_msg_num.visibility=View.GONE
                    }else{
                        toolbar_msg_num.visibility=View.VISIBLE
                        toolbar_msg_num.text =unreadNum.toString()
                    }
                }
            }

            override fun onFailed() {
            }
        })

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
        toolbar_title.visibility=View.GONE
        toolbar_right_img.visibility = View.GONE
        toolbar_left_img.visibility=View.GONE
        toolbar_right_tv.visibility = View.GONE
        tool_bar_search.visibility=View.GONE
        toolbar_msg_num.visibility=View.GONE
        toolbar.visibility=View.GONE
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        when (index) {
            FRAGMENT_HOME -> {
                toolbar.visibility=View.VISIBLE
                tool_bar_search.visibility=View.VISIBLE
                toolbar_left_img.visibility=View.VISIBLE
                toolbar_right_img.visibility=View.VISIBLE
                toolbar_left_img.setImageResource(R.mipmap.icon_logo)
                toolbar_right_img.setImageResource(R.mipmap.icon_message)
                tool_bar_search.setOnClickListener { jumpToSearchActivity() }
                toolbar_fl_msg.setOnClickListener { jumpToMsgActivity() }
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.container, mHomeFragment!!, "home")
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }

            FRAGMENT_SORT -> {
                toolbar.visibility=View.GONE
              //  toolbar_title.text = getString(R.string.main_sport_record)
                if (mSortFragment == null) {
                    //mSortFragment =MySortFragment()
                    mSortFragment=SortFragment.getInstance()
                    transaction.add(R.id.container, mSortFragment!!, "sort")
                } else {
                    transaction.show(mSortFragment!!)
                }
            }

            FRAGMENT_SHOPPING_CART -> {
              //  toolbar_title.text = getString(R.string.main_setting)
              //  toolbar_right_tv.visibility = View.VISIBLE
              //  toolbar_right_tv.text = "保存"
                toolbar.visibility=View.GONE
                if (mShoppingCartFragment == null) {
                    mShoppingCartFragment = ShoppingCartFragment.getInstance()
                    transaction.add(R.id.container, mShoppingCartFragment!!, "shoppingCart")
                } else {
                    transaction.show(mShoppingCartFragment!!)
                }
            }

            FRAGMENT_MINE -> {
                toolbar.visibility=View.VISIBLE
                toolbar_title.visibility = View.GONE
                toolbar_left_img.visibility = View.VISIBLE
                toolbar_right_img.visibility = View.VISIBLE
                toolbar_left_img.setImageDrawable(resources.getDrawable(R.mipmap.icon_ziliao))
                toolbar_right_img.setImageDrawable(resources.getDrawable(R.mipmap.icon_message))
                toolbar_left_img.setOnClickListener {
                    jumpToPersonalInfoActivity()
                }
                toolbar_right_img.setOnClickListener {
                    jumpToMsgActivity()
                }
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.getInstance()
                    transaction.add(R.id.container, mMineFragment!!, "mine")
                } else {
                    transaction.show(mMineFragment!!)
                }
            }
        }
        transaction.commit()
    }

    private fun jumpToMsgActivity() {

      startActivityCheckLogin(MsgActivity::class.java)

    }

    private fun jumpToPersonalInfoActivity() {
        val intent = Intent(this@MainActivity,PersonalInfoActivity::class.java)
        startActivity(intent)
    }

    private fun jumpToSearchActivity() {
        val intent = Intent(this@MainActivity,SearchActivity::class.java)
        startActivity(intent)
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
