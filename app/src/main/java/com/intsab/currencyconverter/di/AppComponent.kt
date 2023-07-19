package com.intsab.currencyconverter.di

import android.app.Application
import com.intsab.currencyconverter.CurrencyApp
import com.intsab.currencyconverter.MainActivity
import dagger.*
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by intsabhaider
 * on 29,April,2023
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivitiesModuleBind::class, NetworkModule::class])
interface AppComponent : AndroidInjector<CurrencyApp> {
    fun inject(activity: MainActivity)
    fun inject(app: AppComponent)
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: CurrencyApp): AppComponent
    }
}

@Module(includes = [AppModuleBinds::class])
class AppModule {
    @Provides
    fun provideContext(app: CurrencyApp) = app.applicationContext
}

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideApplication(bind: CurrencyApp): Application

}

// Register Activities
@Module(
    subcomponents = [MainActivitySubComponent::class]
)
abstract class ActivitiesModuleBind {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindMainActivity(factory: MainActivitySubComponent.Factory): AndroidInjector.Factory<*>
}

@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}