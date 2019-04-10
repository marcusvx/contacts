package info.marcusvinicius.contacts.domain.service

import info.marcusvinicius.contacts.domain.Contact
import info.marcusvinicius.contacts.domain.repository.ContactRepository

class ContactService(
    private var contactRepository: ContactRepository
) {
    fun findAll(limit: Int = 20, offset: Int = 0): List<Contact> {
        return contactRepository.findAll(limit, offset)
    }

    fun create(createContact: Contact) {

    }
}
