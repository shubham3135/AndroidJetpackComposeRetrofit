package com.shubhamkumarwinner.composeretrofit.work

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shubhamkumarwinner.composeretrofit.MainActivity
import com.shubhamkumarwinner.composeretrofit.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

const val CHANNEL_ID = "ChannelId1"
@HiltWorker
class LoadDataWorker @AssistedInject constructor(@Assisted appContext: Context, @Assisted params: WorkerParameters) : Worker(appContext, params){

    companion object {
        const val WORK_NAME = "com.shubhamkumarwinner.composeretrofit.work.LoadDataWorker"
    }

    override fun doWork(): Result {
        try {
            createNotification(applicationContext)
        }catch (e: HttpException){
            return Result.retry()
        }
        return Result.success()
    }

    private fun createNotification(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("WorkManager")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Work is running")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, notification)
    }
}