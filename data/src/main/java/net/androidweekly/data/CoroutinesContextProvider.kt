package net.androidweekly.data

import kotlin.coroutines.CoroutineContext

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
interface CoroutinesContextProvider {

    val main: CoroutineContext

    val io: CoroutineContext
}
