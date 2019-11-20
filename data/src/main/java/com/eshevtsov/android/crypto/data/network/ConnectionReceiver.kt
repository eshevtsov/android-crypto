package com.eshevtsov.android.crypto.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest

class ConnectionReceiver(context: Context) {

    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    fun existInternetConnection(): Boolean =
        connectivityManager.activeNetworkInfo?.isConnected
            ?: false

    fun registerNetworkCallback(onLost: () -> Unit, onConnect: () -> Unit) {
        unregisterNetworkCallback()
        networkCallback = object : ConnectivityManager.NetworkCallback(){
            override fun onLost(network: Network?) = onLost()
            override fun onUnavailable() = onLost()
            override fun onLosing(network: Network?, maxMsToLive: Int) = onLost()
            override fun onAvailable(network: Network?) = onConnect()
        }
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)

        if (existInternetConnection()) {
            onConnect()
        } else {
            onLost()
        }
    }

    fun unregisterNetworkCallback() {
        networkCallback?.let {
            connectivityManager.unregisterNetworkCallback(it)
        }
        networkCallback = null
    }
}