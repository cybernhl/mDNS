package com.deepseek.chat.developer.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cybernhl.utils.getInetAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener
import javax.jmdns.ServiceTypeListener

class MainViewModel : ViewModel() {
    fun discoverServices(serviceType: String = "_nsdchat._tcp.local.") {
        viewModelScope.launch(Dispatchers.IO) {
            // Create a JmDNS instance
            val jmdns = JmDNS.create(getInetAddress()).let { it ->
                it.addServiceListener(serviceType, object : ServiceListener {
                    override fun serviceAdded(event: ServiceEvent) {
                        println("Service added: " + event.info)
                        it.requestServiceInfo(event.type, event.name, true)
                    }

                    override fun serviceResolved(event: ServiceEvent) {
                        println("Found Service")
                        println("Service resolved: " + event.info)
                        val serviceType = event.type
                        val serviceName = event.name
                        val addresses = event.info.hostAddresses
                        println("Name: $serviceName")
                        println("Type: $serviceType")
                        println("Address: ${addresses.joinToString()}")
                        println("Port: ${event.info.port}")
                    }

                    override fun serviceRemoved(event: ServiceEvent) {
                        println("Service removed: " + event.info)
                    }
                })
                it.addServiceTypeListener(object : ServiceTypeListener {
                    override fun serviceTypeAdded(event: ServiceEvent?) = Unit
                    override fun subTypeForServiceTypeAdded(event: ServiceEvent?) = Unit
                })
            }
            Thread.sleep(3000)
        }
    }
}