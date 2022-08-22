package com.mouse.numberfact.domain

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KProperty

interface Interaction<in Params, out Result : Any> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Flow<State<Result>>
    operator fun invoke(params: Params)

    abstract class Base<in Params, out Result : Any>(
        private val scope: CoroutineScope,
        private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) : Interaction<Params, Result> {

        private val mutableState: MutableStateFlow<State<Result>> = MutableStateFlow(State.Idle)
        private var job: Job? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): Flow<State<Result>> {
            return mutableState
        }

        override fun invoke(params: Params) {
            job?.cancel()
            job = scope.launch(coroutineDispatcher) {
                try {
                    mutableState.emit(State.Loading)
                    val result = process(params)
                    mutableState.emit(State.Success(result))
                } catch (error: CancellationException) {
                    Log.i("Network", "Coroutine cancelled: ${this@Base}")
                } catch (error: Exception) {
                    val message = error.localizedMessage.ifEmpty { "Something went wrong!" }
                    mutableState.emit(State.Error(message))
                }
                mutableState.emit(State.Idle)
                job = null
            }
        }

        abstract suspend fun process(params: Params): Result
    }
}