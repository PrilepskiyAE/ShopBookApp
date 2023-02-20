package com.prilepskiy.shopbookapp.core

sealed class ActionResult<out S> {
    data class Success<S>(val data: S) : ActionResult<S>()
    data class Error(val errors: Throwable) : ActionResult<Nothing>()
}

val ActionResult<*>.succeeded
    get() = this is ActionResult.Success && data != null

val <T> ActionResult<T>.data : T?
    get() = (this as? ActionResult.Success)?.data