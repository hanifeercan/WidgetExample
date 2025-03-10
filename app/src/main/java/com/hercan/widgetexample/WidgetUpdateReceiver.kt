package com.hercan.widgetexample

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.hercan.widgetexample.core.common.ResponseState
import com.hercan.widgetexample.core.domain.GetJokeUseCase
import com.hercan.widgetexample.core.model.Joke
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class WidgetUpdateReceiver : BroadcastReceiver() {

    @Inject
    lateinit var getJokeUseCase: GetJokeUseCase

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            updateWidgetWithNewQuote(context)
        } else {
            Log.d("WidgetAPI", "Context veya Intent null")
        }

    }

    private fun updateWidgetWithNewQuote(context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val componentName = ComponentName(context, MyWidgetProvider::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

        if (appWidgetIds.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val newQuote = fetchRandomWord()
                withContext(Dispatchers.Main) {
                    updateWidgetUI(context, appWidgetManager, appWidgetIds, newQuote)
                }
            }
        }
    }

    private fun updateWidgetUI(
        context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray, quote: Joke
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_layout)
        views.setTextViewText(R.id.tv_setup, quote.setup)
        views.setTextViewText(R.id.tv_delivery, quote.delivery)

        for (appWidgetId in appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private suspend fun fetchRandomWord(): Joke {
        var result = Joke("", "Loading...")
        try {
            getJokeUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {}
                    is ResponseState.Loading -> {}
                    is ResponseState.Success -> {
                        result = responseState.data
                    }
                }
            }
        } catch (e: Exception) {
            result = Joke("", "Connection Error")
        }
        return result
    }
}