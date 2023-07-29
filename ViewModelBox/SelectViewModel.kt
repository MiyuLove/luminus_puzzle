package com.exercise.luminus_project.ViewModelBox

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.luminus_project.LuminusUtil.projectUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectViewModel(_size : Int) : ViewModel() {
    val size = _size

    private val _btnStates = arrayListOf<MutableState<Int>>()
    val btnStates = arrayListOf<State<Int>>()

    private var _columnState = MutableStateFlow(false)
    var columnState = _columnState.asStateFlow()

    var _sTState = MutableStateFlow(-1)
    var sTState = _sTState.asStateFlow()

    init{
        startSelect(size)
        //checkData()
    }

    fun updateListStates(valBool : Boolean){
        viewModelScope.launch {
            _columnState.value = valBool
            columnState = _columnState
        }
    }

    fun initiateBtnStates(){
        viewModelScope.launch {
            for (i in 0 until size) {
                _btnStates[i].value = -1
                btnStates[i] = _btnStates[i]
            }

            _sTState.value = -1
            sTState = _sTState
        }
    }

    fun updateBtnStates(num : Int, valNum : Int = -1){
        viewModelScope.launch {
            for (i in 0 until size) {
                _btnStates[i].value = -1
                btnStates[i] = _btnStates[i]
            }
            _btnStates[num].value = valNum
            btnStates[num] = _btnStates[num]

            _sTState.value = valNum
            sTState = _sTState
        }
    }

    fun startSelect(size : Int){
        for(i in 0 until size){
            _btnStates.add(mutableStateOf(-1))
            btnStates.add(_btnStates[i])
        }
        _columnState.value = false
        columnState = _columnState
    }

    fun checkData(){
        for(i in btnStates) projectUtil.logChat(i.value.toString())
    }
}