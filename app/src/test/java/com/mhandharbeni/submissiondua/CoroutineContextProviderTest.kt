package com.mhandharbeni.submissiondua

import com.mhandharbeni.submissiondua.tools.CoroutineContextProvider
import kotlinx.coroutines.Unconfined
import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderTest : CoroutineContextProvider(){
    override val main: CoroutineContext = Unconfined
}