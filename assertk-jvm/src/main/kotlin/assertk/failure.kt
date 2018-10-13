@file:JvmName("FailureJVMKt")

package assertk

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun failWithNotInStacktrace(error: AssertionError): Nothing {
    val filtered = error.stackTrace
        .dropWhile { it.className.startsWith("assertk") }
        .toTypedArray()
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UnsafeCast")
    (error as java.lang.Throwable).stackTrace = filtered
    throw error
}

@Suppress("NOTHING_TO_INLINE")
internal actual inline fun throwIfFatal(e: Throwable) {
    if (e is OutOfMemoryError) {
        throw e
    }
}