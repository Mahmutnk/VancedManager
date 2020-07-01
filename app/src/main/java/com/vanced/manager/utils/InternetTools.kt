package com.vanced.manager.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.dezlum.codelabs.getjson.GetJson
import com.vanced.manager.BuildConfig
import com.vanced.manager.R
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.RuntimeException
import java.util.concurrent.ExecutionException

object InternetTools {

    fun openUrl(Url: String, color: Int, context: Context) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(context, color))
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(Url))
    }

    fun getFileNameFromUrl(url: String) = url.substring(url.lastIndexOf('/')+1, url.length)

    fun displayJsonString(json: String, obj: String, context: Context): String {
        val installUrl = PreferenceManager.getDefaultSharedPreferences(context).getString("install_url", baseUrl)
        return try {
            GetJson().AsJSONObject("$installUrl/$json").get(obj).asString
        } catch (e: ExecutionException) {
            context.getString(R.string.unavailable)
        } catch (e: InterruptedException) {
            context.getString(R.string.unavailable)
        } catch (e: IllegalStateException) {
            context.getString(R.string.unavailable)
        } catch (e: RuntimeException) {
            context.getString(R.string.unavailable)
        }
    }

    fun displayJsonInt(json: String, obj: String, context: Context): Int {
        val installUrl = PreferenceManager.getDefaultSharedPreferences(context).getString("install_url", baseUrl)
        return try {
            GetJson().AsJSONObject("$installUrl/$json").get(obj).asInt
        } catch (e: ExecutionException) {
            0
        } catch (e: InterruptedException) {
            0
        } catch (e: IllegalStateException) {
            0
        } catch (e: RuntimeException) {
            0
        }

    }

    fun isUpdateAvailable(): Boolean {
        val checkUrl = GetJson().AsJSONObject("https://x1nto.github.io/VancedFiles/manager.json")
        val remoteVersion = checkUrl.get("versionCode").asInt

        return remoteVersion > BuildConfig.VERSION_CODE
    }

    const val baseUrl = "https://vanced.app/api/v1"

}