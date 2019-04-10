package info.marcusvinicius.contacts.domain.service

import info.marcusvinicius.contacts.domain.Contact
import info.marcusvinicius.contacts.domain.Phone
import info.marcusvinicius.contacts.domain.repository.ContactRepository
import org.amshove.kluent.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class ContactServiceTest {
    private lateinit var contactService: ContactService

    @Mock
    private lateinit var contactRepository: ContactRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        this.contactService = ContactService(contactRepository)
    }

    @Test
    fun `get all contacts`() {
        // Arrange
        val expected = listOf(
            createContact(
                firstName = "John", lastName = "Doe", email = "john.doe@test.com",
                phones = listOf(Phone(regionCode = "+55", number = "11 555 555 555"))
            ),
            createContact(
                firstName = "Jane", lastName = "Doe", email = "jane.doe@test.com",
                phones = listOf(Phone(regionCode = "+55", number = "11 444 444 444"))
            )
        )

        given(contactRepository.findAll(20, 0)).willReturn(expected)

        // Act
        val allContacts = contactService.findAll(20, 0)

        // Assert
        allContacts.size `should be` expected.size
        expected.forEach {
            allContacts `should contain` it
        }
    }

    @Test
    fun `insert a contact`() {
        // Arrange

        // Act
        contactService.create(createContact(firstName = "Jane"))
    }

    private fun createContact(
        id: Long = 1,
        firstName: String = "John",
        middleName: String? = null,
        lastName: String? = null,
        email: String? = null,
        website: String? = null,
        birthday: Date? = null,
        company: String? = null,
        jobTitle: String? = null,
        title: String? = null,
        phones: List<Phone> = listOf()
    ): Contact {
        return Contact(
            id, firstName, middleName, lastName, email, website, birthday, company, jobTitle, title, phones
        )
    }
}