package com.mouse.numberfact.domain.interaction

import android.util.Log
import com.mouse.numberfact.domain.State
import com.mouse.numberfact.domain.Void
import com.mouse.numberfact.domain.validate.EmptyValidation
import com.mouse.numberfact.domain.validate.Validation
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.reflect.KProperty

interface Interaction<in Params, out Result : Any> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Flow<State<Result>>
    operator fun invoke(params: Params)

    abstract class Base<in Params, out Result : Any>(
        private val scope: CoroutineScope,
        private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
        private val validation: Validation<Params> = EmptyValidation(),
    ) : Interaction<Params, Result> {

        private val mutableState: MutableSharedFlow<State<Result>> =
            MutableSharedFlow(replay = 1, extraBufferCapacity = 10)
        private var job: Job? = null

        override fun getValue(thisRef: Any?, property: KProperty<*>): Flow<State<Result>> {
            return mutableState
        }

        override fun invoke(params: Params) {
            job?.cancel()
            job = scope.launch(coroutineDispatcher) {
                val validationResult = validation.validate(params)
                if (validationResult.success) {
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
                } else mutableState.emit(State.Error(validationResult.errors.joinToString("\n")))
                mutableState.emit(State.Idle)
                job = null
            }
        }

        abstract suspend fun process(params: Params): Result
    }
}

operator fun Interaction<Void, *>.invoke(): Unit = invoke(Void)