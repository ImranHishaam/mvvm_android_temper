package com.example.imranhishaam.temper

/**
 * Created by imranhishaam on 3/13/18.
 */

import android.app.Application
import android.content.Context

import com.example.imranhishaam.temper.data.JobFactory
import com.example.imranhishaam.temper.data.JobService

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TemperApplication : Application() {

    var jobService: JobService? = null
        get() {
            if (field == null) {
                this.jobService = JobFactory.create()
            }

            return field
        }
    private var scheduler: Scheduler? = null

    fun subscribeScheduler(): Scheduler {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler!!
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }

    companion object {

        private operator fun get(context: Context): TemperApplication {
            return context.applicationContext as TemperApplication
        }

        fun create(context: Context): TemperApplication {
            return TemperApplication[context]
        }
    }
}
