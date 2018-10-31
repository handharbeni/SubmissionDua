package com.mhandharbeni.submissiondua

import com.mhandharbeni.submissiondua.tools.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderTest : CoroutineContextProvider(){
    override val main: CoroutineContext = Unconfined
}