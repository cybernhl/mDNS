package com.cto51.blog.mob649e81673fa5.mdns.discovery

import com.github.cybernhl.getProtocolOverTansportLayerLocal
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