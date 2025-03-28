package com.deepseek.chat.developer.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cybernhl.getProtocolOverTansportLayer
import com.github.cybernhl.stringToMap
import com.github.cybernhl.utils.createmDNSServiceInfo
import com.github.cybernhl.utils.getInetAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

class MainViewModel : ViewModel() {
    fun registerService(
        serviceName: String = "NsdChat",
        serviceType: String = "_nsdchat._tcp.",
        port: Int = 8888
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getInetAddress().let { inetAddress ->
                val service_describing = "path=index.html"
                val service_properties = mutableMapOf<String, Any>(
                    "demo_key1" to "home.html",
                    "demo_key2" to 2,
                    "demo_key3" to true
                )
                // Create a JmDNS instance
                JmDNS.create(inetAddress, inetAddress.hostName).apply {
                    createmDNSServiceInfo(
                        serviceType,
                        serviceName,
                        port,
                        service_describing, service_properties
                    ).let {
                        registerService(it)
                        Thread.sleep(20000)
                        // Unregister all services
                        unregisterService(it)
                    }
                }
            }
        }
    }
}