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
    protocol: String = PROTOCOL_TYPE_WEBSOCKET,
    transport_layer: String = TRANSPORT_LAYER_TYPE_TCP
): String {
    return protocol.plus(".").plus(transport_layer).plus(".")
}

fun getProtocolOverTansportLayerLocal( //ex : _http._tcp.local.
    protocol: String = PROTOCOL_TYPE_WEBSOCKET,
    transport_layer: String = TRANSPORT_LAYER_TYPE_TCP
): String {
    return protocol.plus(".").plus(transport_layer).plus(".").plus(LAN_TYPE).plus(".")
}

fun mapToString(map: Map<String, Any>): String {
    return map.entries.joinToString(", ") { "${it.key}=${it.value}" }
}

fun stringToMap(input: String): Map<String, Any> {
    val regex = """(\w+)[:=]([^,]+)""".toRegex()
    val matches = regex.findAll(input)
//    if (matches.sumOf { it.groups.size - 1 } * 2 != input.split(',').size * 2) {
//        return emptyMap()
//    }
    if (matches.none()) {
        return emptyMap()
    }

    return matches.associate { matchResult ->
        val (key, value) = matchResult.destructured
        val parsedValue: Any = when {
            value.toIntOrNull() != null -> value.toInt()
            value.toBooleanStrictOrNull() != null -> value.toBooleanStrict()
            else -> value
        }
        key to parsedValue
    }
}