package com.github.cybernhl.utils

import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface

fun getInetAddress(): InetAddress {
    return  NetworkInterface.getNetworkInterfaces()
            .asSequence()
            .flatMap { it.inetAddresses.asSequence() }
            .filter { inetAddress ->
                inetAddress.isSiteLocalAddress && !inetAddress.hostAddress.contains(":") &&
                        inetAddress.hostAddress != "127.0.0.1"
            }
            .filterIsInstance<Inet4Address>().first()
}