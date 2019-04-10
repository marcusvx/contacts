package info.marcusvinicius.contacts.domain.repository

import info.marcusvinicius.contacts.domain.Contact

interface ContactRepository {
    fun findAll(): List<Contact>
}
