package ru.geekbrains.androiddevelopment.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isOnline(connectivityManager: ConnectivityManager): Boolean {
    val netInfo: NetworkInfo?
    netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}