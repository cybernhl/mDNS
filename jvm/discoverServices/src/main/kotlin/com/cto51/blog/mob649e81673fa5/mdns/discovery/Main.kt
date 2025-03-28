package com.cto51.blog.mob649e81673fa5.mdns.discovery

import com.github.cybernhl.getProtocolOverTansportLayerLocal
import com.github.cybernhl.stringToMap
import java.io.IOException
import java.net.InetAddress
import java.net.UnknownHostException

import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener
import javax.jmdns.ServiceTypeListener

//Ref : https://github.com/jmdns/jmdns/tree/main
@Throws(InterruptedException::class)
fun main() {
    try {
        // Create a JmDNS instance
        val jmdns = JmDNS.create(InetAddress.getLocalHost()).let { it ->
            it.addServiceListener(getProtocolOverTansportLayerLocal(), object : ServiceListener {
                override fun serviceAdded(event: ServiceEvent) {
                    println("Service added: " + event.info)
                    it.requestServiceInfo(event.type, event.name, true)
                }

                override fun serviceRemoved(event: ServiceEvent) {
                    println("Service removed: " + event.info)
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
                    println(sb.toString())
                }
            })
            it.addServiceTypeListener(object : ServiceTypeListener {
                override fun serviceTypeAdded(event: ServiceEvent) {
                    println("Service serviceTypeAdded : " + event.info)
                }

                override fun subTypeForServiceTypeAdded(event: ServiceEvent) {
                    println("Service subTypeForServiceTypeAdded : " + event.info)
                }
            })
        }
        Thread.sleep(30000)
    } catch (e: UnknownHostException) {
        System.out.println(e.message)
    } catch (e: IOException) {
        println(e.message)
    }
}