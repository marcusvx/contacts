package info.marcusvinicius.contacts.config

import info.marcusvinicius.contacts.domain.repository.ContactRepository
import info.marcusvinicius.contacts.domain.service.ContactService
import info.marcusvinicius.contacts.infrastructure.repository.ContactRepositoryImpl
import info.marcusvinicius.contacts.web.controllers.ContactController
import info.marcusvinicius.contacts.web.controllers.Router
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.experimental.builder.single

object ModulesConfig {
    private val contactModule = module {
        single { ContactController(get()) }
        single { ContactService(get()) }
        factory { ContactRepositoryImpl() } bind ContactRepository::class
    }

    private val configModule = module {
        single { AppConfig() }
        single<Router>()
    }
    internal val allModules = listOf(ModulesConfig.configModule, ModulesConfig.contactModule)
}