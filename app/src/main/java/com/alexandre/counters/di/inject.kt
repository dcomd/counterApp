package com.alexandre.counters.di

import android.app.Application
import androidx.room.Room
import com.alexandre.counters.data.dbnetwork.CounterDao
import com.alexandre.counters.data.dbnetwork.CounterDatabse
import com.alexandre.counters.data.repository.CounterRepository
import com.alexandre.counters.domain.useCase.CreateCounter
import com.alexandre.counters.domain.useCase.DeleteCounters
import com.alexandre.counters.domain.useCase.GetCounters
import com.alexandre.counters.domain.useCase.Update
import com.alexandre.counters.presentation.viewModel.CounterViewModel
import com.alexandre.counters.presentation.viewModel.CounterViewModelImp
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CounterRepository(get()) }
    factory { CreateCounter(get()) }
    factory { Update(get()) }
    factory { DeleteCounters(get()) }
    factory { GetCounters(get()) }
    viewModel<CounterViewModel> { CounterViewModelImp(get(),get(),get(),get()) }

}

val databaseModule = module {

    fun provideDatabase(application: Application): CounterDatabse {
        return Room.databaseBuilder(application, CounterDatabse::class.java, "counters")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CounterDatabse): CounterDao {
        return  database.counterDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}