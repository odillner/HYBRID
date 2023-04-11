package com.hybrid.utils

import com.hybrid.crypt.AESGCM
import com.hybrid.crypt.BFOFB
import com.hybrid.crypt.ECIESSECP256K1
import com.hybrid.crypt.RSAOAEP

class FullSuite {
    val algorithms = arrayOf(RSAOAEP(), AESGCM(), BFOFB(), ECIESSECP256K1())
    val helper = DataHelper()

    fun run(dataSet: Array<String>, numberOfRuns: Int, apiURL: String) {
        for (algorithm in algorithms) {
            println("Running ${algorithm.name}...")

            val res = algorithm.performRun(dataSet, numberOfRuns)

            if (res.data contentEquals dataSet) {
                println("Encrypted and decrypted data matches original dataset")
            } else {
                println("Encrypted and decrypted data does not match")
                continue
            }

            println("${algorithm.name} done, uploading data...")

            helper.uploadResult(res, algorithm.name, dataSet.size, apiURL)
        }

        println("All tests done!")
    }
}