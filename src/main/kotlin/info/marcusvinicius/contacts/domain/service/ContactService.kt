package info.marcusvinicius.contacts.domain.service

import info.marcusvinicius.contacts.domain.Contact
import info.marcusvinicius.contacts.domain.repository.ContactRepository

class ContactService(
    private var contactRepository: ContactRepository
) {
    fun findAll(): List<Contact> {
        return contactRepository.findAll()
    }
}
