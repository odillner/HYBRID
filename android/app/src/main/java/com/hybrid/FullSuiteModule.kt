package com.hybrid

import android.app.AlertDialog
import android.os.StrictMode
import android.view.LayoutInflater
import android.widget.EditText
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.UiThreadUtil.runOnUiThread
import com.hybrid.utils.DataHelper
import com.hybrid.utils.FullSuite
import com.hybrid.utils.RunResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security


class FullSuiteModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "FullSuiteModule"

    private fun inputToDataSet(input: String) : Array<String> {
        val split = input.split("}}},")

        return Array(split.size){split[it] + "}}}"}
    }
    @ReactMethod
    fun run(name: String, dataSet: String, numberOfRuns: Int) {
        val data = inputToDataSet(dataSet)

        Security.removeProvider("BC")
        Security.insertProviderAt(BouncyCastleProvider(), 1)

        GlobalScope.launch {
            val res = FullSuite().run(name, data, numberOfRuns, Runtime.getRuntime().availableProcessors())

            fun prompt(encrypt: Double, decrypt: Double, keygen: Double) {
                val builder = AlertDialog.Builder(currentActivity)
                val inflater = LayoutInflater.from(currentActivity)
                builder.setTitle("Data")
                val dialogLayout = inflater.inflate(R.layout.prompt, null)

                builder.setView(dialogLayout)
                builder.setPositiveButton("OK") { _, _ ->
                    val encryptResult = RunResult(
                        encrypt,
                        dialogLayout.findViewById<EditText>(R.id.encmem).text.toString().toDouble(),
                        dialogLayout.findViewById<EditText>(R.id.enccpu).text.toString().toDouble() / 100,
                    )

                    val decryptResult = RunResult(
                        decrypt,
                        dialogLayout.findViewById<EditText>(R.id.decmem).text.toString().toDouble(),
                        dialogLayout.findViewById<EditText>(R.id.deccpu).text.toString().toDouble() / 100,
                    )

                    val keyGenResult = RunResult(
                        keygen,
                        dialogLayout.findViewById<EditText>(R.id.keymem).text.toString().toDouble(),
                        dialogLayout.findViewById<EditText>(R.id.keycpu).text.toString().toDouble() / 100,
                    )

                    println("Key Generation: $keyGenResult")
                    println("Encrypt: $encryptResult")
                    println("Decrypt: $decryptResult")

                    println("Uploading results..")

                    DataHelper().uploadResult(encryptResult, decryptResult, keyGenResult, name, data.size, numberOfRuns)
                }

                builder.show()
            }

            runOnUiThread {
                prompt(res[0], res[1], res[2])
            }
        }
    }
}