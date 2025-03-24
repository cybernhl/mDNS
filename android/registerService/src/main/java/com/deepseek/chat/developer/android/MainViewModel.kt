package com.deepseek.chat.developer.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cybernhl.utils.getInetAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

class MainViewModel : ViewModel() {
    fun registerService(
        serviceName: String = "NsdChat",
        serviceType: String = "_nsdchat._tcp.",
        port: Int = 8888
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getInetAddress()?.let { inetAddress ->
                // Create a JmDNS instance
                JmDNS.create(inetAddress, inetAddress.hostName).apply {
                    ServiceInfo.create(serviceType, serviceName, port, "path=index.html").let {
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