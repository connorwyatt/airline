package com.github.connorwyatt.airline.aircraft.data

import com.github.connorwyatt.airline.aircraft.data.configuration.DataConfiguration
import com.github.connorwyatt.airline.aircraft.data.configuration.RepositoryImplementation
import com.github.connorwyatt.airline.aircraft.data.inmemory.InMemoryDuplicateAircraftRepository
import com.github.connorwyatt.airline.aircraft.data.mongodb.MongoDBDuplicateAircraftRepository
import com.github.connorwyatt.airline.aircraft.data.mongodb.models.DuplicateAircraftDocument
import com.github.connorwyatt.common.eventstore.mongodbmodels.CursorDocument
import com.github.connorwyatt.common.mongodb.configuration.MongoDBConfiguration
import com.github.connorwyatt.common.mongodb.kodein.bindMongoDBCollection
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

fun aircraftDataDependenciesModule(
    dataConfiguration: DataConfiguration,
    mongoDBConfiguration: MongoDBConfiguration,
): DI.Module =
    DI.Module(name = ::aircraftDataDependenciesModule.name) {
        bindMongoDBCollection<CursorDocument>()
        bindMongoDBCollection<DuplicateAircraftDocument>()

        when (dataConfiguration.repositoryImplementation) {
            RepositoryImplementation.MongoDB -> {
                bindProvider<DuplicateAircraftRepository> {
                    MongoDBDuplicateAircraftRepository(
                        instance(),
                        mongoDBConfiguration.databaseName
                    )
                }
            }
            RepositoryImplementation.InMemory -> {
                bindSingleton<DuplicateAircraftRepository> { InMemoryDuplicateAircraftRepository() }
            }
        }
    }
