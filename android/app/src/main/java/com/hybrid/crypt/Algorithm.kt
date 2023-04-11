package com.hybrid.crypt

import com.hybrid.utils.RunResult

interface Algorithm {
    val name: String

    fun performRun(data: Array<String>, numberOfRuns: Int): RunResult {
        return RunResult(1)
    }
}