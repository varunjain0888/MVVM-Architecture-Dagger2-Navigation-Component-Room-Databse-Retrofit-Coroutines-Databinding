package com.skills.newsapp

import android.app.Application
import androidx.multidex.MultiDex
import com.skills.newsapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
/**
 *  Application class registered in AndroidManifest.xml
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class NewsApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this) //multidex used because of JOCOCO method limit exeeds by 65833 > 65536
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }
}