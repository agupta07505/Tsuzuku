package com.agupta07505.tsuzuku.focus

import com.agupta07505.tsuzuku.data.FocusSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class PhonePosition { FACE_DOWN, PICKED_UP, UNKNOWN }

data class FocusRuntimeState(
    val active: Boolean = false,
    val sessionName: String = "",
    val plannedDurationMinutes: Int = 0,
    val allowedMistakes: Int = 0,
    val mistakesUsed: Int = 0,
    val remainingMillis: Long = 0,
    val phonePosition: PhonePosition = PhonePosition.UNKNOWN,
    val warningSeconds: Int? = null,
    val appReturnSeconds: Int? = null,
    val lastResult: FocusSession? = null
)

object FocusSessionManager {
    const val UNLIMITED_MISTAKES = Int.MAX_VALUE

    private val mutableState = MutableStateFlow(FocusRuntimeState())
    val state = mutableState.asStateFlow()

    fun update(transform: (FocusRuntimeState) -> FocusRuntimeState) {
        mutableState.value = transform(mutableState.value)
    }

    fun clearResult() {
        mutableState.value = FocusRuntimeState()
    }
}
