package com.sam.firebaseauthentication.di

import com.sam.firebaseauthentication.authentication.AuthRepository
import com.sam.firebaseauthentication.authentication.AuthRepositoryImpl
import com.sam.firebaseauthentication.datastore.DatastoreRepository
import com.sam.firebaseauthentication.datastore.DatastoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDatastoreRepository(impl: DatastoreRepositoryImpl): DatastoreRepository
}