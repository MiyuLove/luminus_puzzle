package com.exercise.luminus_project.NavGame

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.exercise.luminus_project.LuminusUtil.projectUtil
import kotlin.random.Random

class GameLogic (col_row : Int, colorNum : Int, colorRange : Int){
    private val columnInfoGroup = arrayOf(2,2,3,3,4,4,5,5)
    private val rowInfoGroup = arrayOf(2,3,3,4,4,5,5,6)
    private val colorNumberGroup = arrayOf(2,3,4,5)
    private val colorRangeGroup = arrayOf(2,3,4)

    var col = columnInfoGroup[col_row]
    var row = rowInfoGroup[col_row]
    var colorNum = colorNumberGroup[colorNum]
    var colorRange = colorRangeGroup[colorRange]

    private var dat = Array(col){IntArray(row)}

    private var restoreDat = ArrayList<Array<IntArray>>()


    private val _States = arrayListOf<MutableState<Int>>()
    val States = arrayListOf<State<Int>>()

    init{
        projectUtil.logChat(col.toString() +" " + row.toString())

        initiateObject()
    }

    fun setData(x : Int, y : Int) : Boolean{
        restoreBoard()
        if(++dat[x][y] >= colorNum) dat[x][y] = 0
        updateStates((row) * x + y, dat[x][y])

        for(i in 1 until  colorRange){
            val xpi = x+i
            val xmi = x-i
            val ypi = y+i
            val ymi = y-i

            if(xpi < dat.size){
                if(++dat[xpi][y] >= colorNum) dat[xpi][y] = 0
                updateStates(row * xpi + y, dat[xpi][y])
            }

            if(xmi >= 0){
                if(++dat[xmi][y] >= colorNum) dat[xmi][y] = 0
                updateStates(row * xmi + y, dat[xmi][y])
            }

            if(ypi < dat[0].size){
                if(++dat[x][ypi] >= colorNum) dat[x][ypi] = 0
                updateStates(row * x + ypi, dat[x][ypi])
            }
            if(ymi >= 0){
                if(++dat[x][ymi] >= colorNum) dat[x][ymi] = 0
                updateStates(row * x + ymi, dat[x][ymi])
            }
        }
        return finishGame()
    }

    private fun checkData(){
        for(i in 0 until dat.size){
            print("dd123 : ")
            for(j in 0 until dat[i].size){
                print(dat[i][j].toString() + " ")
            }
            println()
        }
    }

    private fun initiateObject(){
        initiateGame()
        initiateStates()
        checkData()
    }

    private fun updateStates(num : Int,col : Int){
        _States[num].value = col
        States[num] = _States[num]
    }

    private fun initiateStates(){
        var idx = 0
        for(i in dat){
            for(j in i){
                _States.add(mutableStateOf(j))
                States.add(_States[idx])
                idx ++
            }
        }
    }

    private fun initiateGame(){
        for(i in 0 until dat.size){
            for(j in 0 until dat[i].size){
                dat[i][j] = Random.nextInt(0, colorNum)
            }
        }
        if(finishGame())initiateGame()
    }

    private fun copyDat():Array<IntArray>{
        val _dat = Array(col){IntArray(row)}

        for(i in 0 until dat.size){
            for(j in 0 until dat[i].size){
                _dat[i][j] = dat[i][j]
            }
        }
        return _dat
    }

    private fun coverColor(){
        var idx = 0
        for(i in dat){
            for(j in i){
                _States[idx].value = j
                States[idx] = _States[idx]
                idx ++
            }
        }
    }

    private fun finishGame():Boolean{
        val q = dat[0][0]
        for(i in dat){
            for(j in i){
                if(q != j) return false
            }
        }
        return true
    }

    //나중에 여기에 광고 기능
    private var resetNum = 0
    private var resetT = false

    fun resetBtn(){
        if(resetNum - 1 < 0) return
        resetT = true
        --resetNum
        dat = restoreDat[resetNum]
        coverColor()
    }

    private fun restoreBoard(){
        if(resetT) {
            restoreDat = ArrayList<Array<IntArray>>()
            resetNum = 0
        }
        restoreDat.add(copyDat())
        resetNum ++

        resetT = false
        if(resetNum > 30){
            resetNum --
            restoreDat.removeAt(0)
        }
    }
}