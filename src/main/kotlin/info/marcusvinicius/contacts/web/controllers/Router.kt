package info.marcusvinicius.contacts.web.controllers

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

class Router(private val contactController: ContactController) {
    fun register(app: Javalin) {
        app.routes {
            path("api") {
                path("contacts") {
                    get(contactController::findAll)
                    post(contactController::create)
                }
            }
        }
    }
}