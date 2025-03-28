package com.cto51.blog.mob649e81673fa5.mdns.server

import com.github.cybernhl.getProtocolOverTansportLayer
import com.github.cybernhl.utils.createmDNSServiceInfo
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket
import java.util.UUID
import javax.jmdns.JmDNS;

//Ref : https://github.com/jmdns/jmdns/tree/main
@Throws(InterruptedException::class)
fun main() {
    try {
        val props:Map<String, Any> = mutableMapOf<String, Any>("path" to "index.html", "demo_key1" to "home.html","demo_key2" to 3,"demo_key3" to true)
        createmDNSServiceInfo(
            getProtocolOverTansportLayer(),
            "JVM_${UUID.randomUUID()}",
             ServerSocket(0).localPort,
            "path=index.html",props
        ).let { it ->
            // Create a JmDNS instance
            JmDNS.create(InetAddress.getLocalHost()).apply {
                // Register a service
                registerService(it)
                // Wait a bit
                Thread.sleep(300000)
                // Unregister all services
                unregisterService(it)
            }
        }
    } catch (e: IOException) {
        println(e.message)
    }
}