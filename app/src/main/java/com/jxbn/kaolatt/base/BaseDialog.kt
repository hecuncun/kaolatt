package com.jxbn.kaolatt.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.jxbn.kaolatt.R


/**
 * BaseDialog class
 *
 * @author hesanwei created on 2020/1/10
 *
 */
abstract class BaseDialog : DialogFragment() {

    private val TAG = "base_dialog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_TITLE,
            if (Gravity.BOTTOM == getGravity()) R.style.BottomDialog else R.style.NormalDialog
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
//        params.dimAmount = getDimAmount()
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = getGravity()

        window?.attributes = params
    }

    open fun getGravity(): Int? {
        return Gravity.BOTTOM
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

}