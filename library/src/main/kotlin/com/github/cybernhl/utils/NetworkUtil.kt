package com.github.cybernhl.utils

import com.github.cybernhl.PROTOCOL_TYPE_WEBSOCKET
import com.github.cybernhl.TRANSPORT_LAYER_TYPE_TCP
import com.github.cybernhl.getProtocolOverTansportLayer
import com.github.cybernhl.mapToString
import com.github.cybernhl.stringToMap
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import javax.jmdns.ServiceInfo

fun getInetAddress(): InetAddress {
    return NetworkInterface.getNetworkInterfaces()
        .asSequence()
        .flatMap { it.inetAddresses.asSequence() }
        .filter { inetAddress ->
            inetAddress.isSiteLocalAddress && !inetAddress.hostAddress.contains(":") &&
                    inetAddress.hostAddress != "127.0.0.1"
        }
        .filterIsInstance<Inet4Address>().first()
}

fun createmDNSServiceInfo(
    protocol_with_transport_layer: String = getProtocolOverTansportLayer(),
    service_name: String ,
    port: Int ,
    service_describing: String? = null,
    service_properties: Map<String, Any>? = null
): ServiceInfo {
    var props: Map<String, Any> = mutableMapOf<String, Any>()
    service_describing?.let {
        props = (props + stringToMap(service_describing)).toMutableMap()
    }
    service_properties?.let {
        props = (props + it).toMutableMap()
    }
    return if (props.isNotEmpty()){
        ServiceInfo.create(protocol_with_transport_layer, service_name, port, mapToString(props))
    }else{
        ServiceInfo.create(protocol_with_transport_layer, service_name, port,"")
    }
}