package com.prilepskiy.shopbookapp.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListDataBaseUseCase
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListNetworkUseCase
import com.prilepskiy.shopbookapp.domain.model.BookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(private val getBookListNetworkUseCase: GetBookListNetworkUseCase,
                                       private val getBookListDataBaseUseCase: GetBookListDataBaseUseCase):ViewModel() {


    private val _bookList: MutableStateFlow<List<BookModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val bookList =_bookList.asStateFlow()
    fun getBooks(){
        viewModelScope.launch {
            when(val result=getBookListNetworkUseCase()){
                is ActionResult.Success ->{
                    _bookList.emit(result.data)
                }
                is ActionResult.Error ->{
                    val cashResult=getBookListDataBaseUseCase()
                    cashResult.collectLatest {
                        _bookList.value=it
                    }
                }
            }
        }
    }
}