package info.marcusvinicius.contacts.domain.repository

import info.marcusvinicius.contacts.domain.Contact

interface ContactRepository {
    fun findAll(limit: Int, offset: Int = 0): List<Contact>
}
