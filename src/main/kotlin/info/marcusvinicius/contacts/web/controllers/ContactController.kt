package info.marcusvinicius.contacts.web.controllers

import info.marcusvinicius.contacts.domain.service.ContactService
import io.javalin.Context

class ContactController(private var contactService: ContactService) {
    // GET
    fun findAll(ctx: Context) {
        val limit: Int = ctx.queryParam("limit")?.toIntOrNull() ?: 20
        val offset: Int = ctx.queryParam("offset")?.toIntOrNull() ?: 0

        ctx.json(contactService.findAll(limit, offset))
    }

    // POST
    fun create(ctx: Context) {

    }
}