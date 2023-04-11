package com.hybrid

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.hybrid.utils.DataHelper
import com.hybrid.utils.FullSuite

class FullSuiteModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "FullSuiteModule"

    @ReactMethod
    fun run(dataSet: String, numberOfRuns: Int, apiURL: String) {
        FullSuite().run(DataHelper().inputToDataSet(dataSet), numberOfRuns, apiURL)
    }
}