import info.marcusvinicius.contacts.config.AppConfig
import info.marcusvinicius.contacts.config.ModulesConfig
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(ModulesConfig.allModules)
    }
    AppConfig().setup().start()
}