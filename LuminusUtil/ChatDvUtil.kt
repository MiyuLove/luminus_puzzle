package com.exercise.luminus_project.LuminusUtil

import android.app.Activity
import android.graphics.Point
import android.widget.Toast

var projectUtil = ProjectUtil()

class ProjectUtil{
    fun logChat(getLog : String){
        println("dd123 : " + getLog + '\n')
    }

    fun deviceDisplay(activity: Activity) : Pair<Int,Int>{
        val display = activity!!.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val rtn = Pair(size.x,size.y)
        return rtn
    }

    private var toast : Toast? = null
    fun makeToast(activity: Activity, message : String,){
        toast?.cancel()
        toast = Toast.makeText(activity.applicationContext, message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}