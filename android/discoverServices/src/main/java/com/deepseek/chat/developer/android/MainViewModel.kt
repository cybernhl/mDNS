package com.deepseek.chat.developer.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cybernhl.stringToMap
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
                        Log.v("mDNS", "Service added: " + event.info)
                        it.requestServiceInfo(event.type, event.name, true)
                    }

                    override fun serviceResolved(event: ServiceEvent) {
                        val serviceType = event.type
                        val serviceName = event.name
                        val addresses = event.info.hostAddresses.joinToString()
                        val sb = StringBuilder()
                        sb.appendLine(
                            """
    Resolved Found Device
    --------------------------------------------
    Service Name  : $serviceName
    Service Type  : $serviceType
    Address       : $addresses
    Port          : ${event.info.port}
    """.trimIndent()
                        )

                        if (event.info.hasData()) {
                            sb.append("Service Extra Data\n")
                            stringToMap(event.info.niceTextString).forEach { (key, value) ->
                                sb.append("$key=$value , \n ")
                            }
                            sb.setLength(sb.length - 2)
                        }
                        Log.e("mDNS", sb.toString())
                    }

                    override fun serviceRemoved(event: ServiceEvent) {
                        Log.v("mDNS", "Service removed: " + event.info)
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