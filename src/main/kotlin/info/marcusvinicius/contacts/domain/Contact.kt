package info.marcusvinicius.contacts.domain

import java.util.*

data class Contact(
    val id: Long,
    val firstName: String,
    val middleName: String?,
    val lastName: String?,
    val email: String?,
    val website: String?,
    val birthday: Date?,
    val company: String?,
    val jobTitle: String?,
    val title: String?,
    val phones: List<Phone>
)
