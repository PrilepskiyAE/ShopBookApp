package com.prilepskiy.shopbookapp.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListDataBaseUseCase
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListNetworkUseCase
import com.prilepskiy.shopbookapp.domain.interactors.GetListBannerUseCase
import com.prilepskiy.shopbookapp.domain.model.BookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(private val getBookListNetworkUseCase: GetBookListNetworkUseCase,
                                       private val getBookListDataBaseUseCase: GetBookListDataBaseUseCase,
                                       private val getListBannerUseCase: GetListBannerUseCase):ViewModel() {
    data class ListsViewState(
        val bookList:Flow<PagingData<BookModel>>?=null,
        val bannerList: List<Int>?=null
    )

    private val _state = MutableStateFlow(ListsViewState())
    val state: StateFlow<ListsViewState> = _state.asStateFlow()

//    fun getBooks(){
//
//        viewModelScope.launch {
//            when(val result=getBookListNetworkUseCase()){
//                is ActionResult.Success ->{
//                    _state.value=_state.value.copy(bookList = result.data)
//                    //_bookList.emit(result.data)
//               }
//                is ActionResult.Error ->{
//                    val cashResult=getBookListDataBaseUseCase()
//                    cashResult.collectLatest {
//                        _state.value= _state.value.copy(bookList = it)
//                    }
//                }
//           }
//            val bannerResult=getListBannerUseCase()
//            _state.value= _state.value.copy(bannerList = bannerResult)
//        }
//    }


//    private val _bookList: MutableStateFlow<List<BookModel>?> by lazy {
//        MutableStateFlow(
//            null
//        )
//    }
//    val bookList =_bookList.asStateFlow()
//    fun getBooks(){
//        viewModelScope.launch {
//            when(val result=getBookListNetworkUseCase()){
//                is ActionResult.Success ->{
//                    _bookList.emit(result.data)
//                }
//                is ActionResult.Error ->{
//                    val cashResult=getBookListDataBaseUseCase()
//                    cashResult.collectLatest {
//                        _bookList.value=it
//                    }
//                }
//            }
//        }
//    }


     fun getBook(): Flow<PagingData<BookModel>> =  getBookListNetworkUseCase()

    fun getBooks(){
        viewModelScope.launch {
            _state.value=_state.value.copy(bookList = getBook())
            _state.value=_state.value.copy(bannerList =getListBannerUseCase() )

        }
    }


}