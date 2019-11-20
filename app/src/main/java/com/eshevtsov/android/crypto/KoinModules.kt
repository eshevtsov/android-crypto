package com.eshevtsov.android.crypto

import androidx.fragment.app.FragmentFactory
import androidx.room.Room
import com.eshevtsov.android.crypto.core.feature.MainScheduler
import com.eshevtsov.android.crypto.core.feature.Scheduler
import com.eshevtsov.android.crypto.data.ICoinDetailsDataSource
import com.eshevtsov.android.crypto.data.ICoinIdsDataSource
import com.eshevtsov.android.crypto.data.ICoinListDataSource
import com.eshevtsov.android.crypto.data.ICryptoToolsDataSource
import com.eshevtsov.android.crypto.data.database.CryptoRoomDatabase
import com.eshevtsov.android.crypto.data.network.BooleanAdapter
import com.eshevtsov.android.crypto.data.network.*
import com.eshevtsov.android.crypto.data.repository.CoinDetailsRepository
import com.eshevtsov.android.crypto.data.repository.CoinIdsRepository
import com.eshevtsov.android.crypto.data.repository.CoinListRepository
import com.eshevtsov.android.crypto.data.repository.CryptoToolsRepository
import com.eshevtsov.android.crypto.feature.currency.detail.domain.GetCoinDetailModelUseCase
import com.eshevtsov.android.crypto.feature.currency.detail.ui.CurrencyDetailViewModel
import com.eshevtsov.android.crypto.feature.currency.filter.domain.GetCoinIdsUseCase
import com.eshevtsov.android.crypto.feature.currency.filter.domain.SaveFilterUseCase
import com.eshevtsov.android.crypto.feature.currency.filter.ui.CurrencyFilterNavigation
import com.eshevtsov.android.crypto.feature.currency.filter.ui.CurrencyFilterViewModel
import com.eshevtsov.android.crypto.feature.currency.list.domain.GetCoinModelsUseCase
import com.eshevtsov.android.crypto.feature.currency.list.domain.MapCoinDtoUseCase
import com.eshevtsov.android.crypto.feature.currency.list.ui.CurrencyListNavigation
import com.eshevtsov.android.crypto.feature.currency.list.ui.CurrencyListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule by lazy {
    module {
        single { AppFragmentFactory(getKoin()) as FragmentFactory }
        single { AppNavigator() }
        single { MainScheduler() as Scheduler }
    }
}

val featureCurrencyDetailModule by lazy {
    module {
        single { GetCoinDetailModelUseCase(get()) }
        viewModel { CurrencyDetailViewModel(get(), get()) }
    }
}

val featureCurrencyListModule by lazy {
    module {
        single { GetCoinModelsUseCase(get(), get()) }
        single { MapCoinDtoUseCase() }
        viewModel { CurrencyListViewModel(get(), get()) }
        single { get<AppNavigator>() as CurrencyListNavigation }
    }
}

val featureCurrencyFilterModule by lazy {
    module {
        single { GetCoinIdsUseCase(get()) }
        single { SaveFilterUseCase(get()) }
        viewModel { CurrencyFilterViewModel(get(), get(), get()) }
        single { get<AppNavigator>() as CurrencyFilterNavigation }
    }
}

val dataModule by lazy {
    module {
        single {
            Moshi.Builder()
                .add(BooleanAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()
        }
        single {
            MoshiConverterFactory.create(get<Moshi>())
        }
        single {
            val headerName = androidContext().getString(R.string.api_auth_header_name)
            val headerKey = androidContext().getString(R.string.api_auth_header_key)
            AddHeadersInterceptor(mapOf(headerName to headerKey))
        }
        single {
            OkHttpClient.Builder()
                .addInterceptor(get<AddHeadersInterceptor>())
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()
        }
        single {
            val baseUrl = androidContext().getString(R.string.api_base_url)
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(get<MoshiConverterFactory>())
                .client(get<OkHttpClient>())
                .build()
        }
        single {
            get<Retrofit>().create(CoinDetailsService::class.java)
        }
        single {
            get<Retrofit>().create(CryptoToolsService::class.java)
        }
        single {
            get<Retrofit>().create(CoinListService::class.java)
        }
        single {
            val databaseName = androidContext().getString(R.string.room_database_name)
            Room.databaseBuilder(
                androidContext(), CryptoRoomDatabase::class.java, databaseName
            ).build()
        }
        single {
            CoinDetailsRepository(get(), get()) as ICoinDetailsDataSource
        }
        single {
            CoinListRepository(get(), get()) as ICoinListDataSource
        }
        single {
            CoinIdsRepository(get(), get()) as ICoinIdsDataSource
        }
        single {
            CryptoToolsRepository(get()) as ICryptoToolsDataSource
        }
    }
}