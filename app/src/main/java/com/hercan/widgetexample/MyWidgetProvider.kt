package com.hercan.widgetexample

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?
    ) {
        context?.let {
            appWidgetIds?.forEach { appWidgetId ->
                appWidgetManager?.let {
                    updateWidget(context, it, appWidgetId)
                }
            }
        }
    }

    private fun updateWidget(
        context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_layout)

        val intent = Intent(context, WidgetUpdateReceiver::class.java)
        intent.action = "com.hercan.widgetexample.UPDATE_WIDGET"
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.btn_next, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}