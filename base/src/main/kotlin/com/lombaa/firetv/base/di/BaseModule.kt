package com.lombaa.firetv.base.di

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.lombaa.firetv.base.dialog.DialogViewModelDelegate
import com.lombaa.firetv.base.dialog.DialogViewModelDelegateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

  @Singleton
  @Provides
  fun provideObjectMapper(): ObjectMapper {
    val mapper = ObjectMapper()
    mapper.registerModule(KotlinModule())
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    // Important to support empty object for ArrayList, as Jackson silenced the exception and hard to trace
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
    return mapper
  }
}

@Module
@InstallIn(ViewModelComponent::class)
object BaseViewModelModule {

  @ViewModelScoped
  @Provides
  fun provideDialogViewModelDelegate(): DialogViewModelDelegate =
    DialogViewModelDelegateImpl()
}