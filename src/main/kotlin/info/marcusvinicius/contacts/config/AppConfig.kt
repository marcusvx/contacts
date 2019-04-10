package info.marcusvinicius.contacts.config

import info.marcusvinicius.contacts.web.controllers.Router
import io.javalin.Javalin
import io.javalin.JavalinEvent
import org.koin.core.KoinComponent
import org.koin.core.context.stopKoin
import org.koin.core.inject

class AppConfig : KoinComponent {
    private val router: Router by inject()
    fun setup(): Javalin {
        return Javalin.create()
            .also { app: Javalin ->
                app.enableCorsForAllOrigins()
                    .event(JavalinEvent.SERVER_STOPPING) {
                        stopKoin()
                    }
                router.register(app)
                app.port(7000)
            }
    }
}