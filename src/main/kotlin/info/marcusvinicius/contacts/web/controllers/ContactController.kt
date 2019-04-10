package info.marcusvinicius.contacts.web.controllers

import info.marcusvinicius.contacts.domain.service.ContactService
import io.javalin.Context

class ContactController(private var contactService: ContactService) {
    // GET
    fun findAll(ctx: Context) {
        ctx.json(contactService.findAll())
    }

    // POST
    fun create(ctx: Context) {

    }
}