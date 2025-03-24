package com.cto51.blog.mob649e81673fa5.mdns.server


import com.github.cybernhl.getProtocolOverTansportLayer
import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

//Ref : https://github.com/jmdns/jmdns/tree/main
@Throws(InterruptedException::class)
fun main() {
    try {
        ServiceInfo.create(getProtocolOverTansportLayer(), "My_JVM", 13595, "path=index.html").let { it ->
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