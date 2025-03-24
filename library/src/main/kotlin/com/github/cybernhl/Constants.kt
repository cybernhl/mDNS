package com.github.cybernhl


const val LAN_TYPE = "local"
const val TRANSPORT_LAYER_TYPE_TCP = "_tcp"
const val PROTOCOL_TYPE_UDP = "_udp"
const val SERVICE_TYPE_ALL = "_services._dns-sd"
const val PROTOCOL_TYPE_HTTP = "_http"
const val PROTOCOL_TYPE_WEBSOCKET = "_ws"
const val PROTOCOL_TYPE_WEBSOCKET_TLS = "_wss"
const val PROTOCOL_TYPE_SSH = "_ssh"
const val PROTOCOL_TYPE_SELF_DEFINE = "_myservice"

const val SERVICE_TYPE_USER_SELF_DEFINE_UDP_LOCAL = "_myservice._udp local."

fun getProtocolOverTansportLayer(// ex : _http._tcp.
    service: String = PROTOCOL_TYPE_WEBSOCKET,
    protocol: String = TRANSPORT_LAYER_TYPE_TCP
): String {
    return service.plus(".").plus(protocol).plus(".")
}

fun getProtocolOverTansportLayerLocal( //ex : _http._tcp.local.
    service: String = PROTOCOL_TYPE_WEBSOCKET,
    protocol: String = TRANSPORT_LAYER_TYPE_TCP
): String {
    return service.plus(".").plus(protocol).plus(".").plus(LAN_TYPE).plus(".")
}