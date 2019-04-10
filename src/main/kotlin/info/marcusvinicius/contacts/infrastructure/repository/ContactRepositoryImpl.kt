package info.marcusvinicius.contacts.infrastructure.repository

import info.marcusvinicius.contacts.domain.Contact
import info.marcusvinicius.contacts.domain.Phone
import info.marcusvinicius.contacts.domain.repository.ContactRepository
import info.marcusvinicius.contacts.infrastructure.DbSettings
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

private object ContactTable : LongIdTable() {
    val firstName: Column<String> = varchar("first_name", 150)
    val middleName: Column<String?> = varchar("middle_name", 150).nullable()
    val lastName: Column<String?> = varchar("last_name", 150).nullable()
    val email: Column<String?> = varchar("email", 150).nullable()
    val website: Column<String?> = varchar("website", 150).nullable()
    val birthday: Column<DateTime?> = date("birthday").nullable()
    val jobTitle: Column<String?> = varchar("job_title", 150).nullable()
    val company: Column<String?> = varchar("company", 150).nullable()
    val title: Column<String?> = varchar("title", 150).nullable()
}

private object PhoneTable : Table() {
    val countryCode: Column<String> = varchar("country_code", 5)
    val number: Column<String> = varchar("number", 15)
    val contactId = reference("contact_id", ContactTable.id).index("contact_index")
}

fun ResultRow.toContact(phones: List<Phone>): Contact {
    return Contact(
        id = this[ContactTable.id].value,
        firstName = this[ContactTable.firstName],
        lastName = this[ContactTable.lastName],
        middleName = this[ContactTable.middleName],
        email = this[ContactTable.email],
        website = this[ContactTable.website],
        birthday = this[ContactTable.birthday]?.toDate(),
        company = this[ContactTable.company],
        jobTitle = this[ContactTable.jobTitle],
        title = this[ContactTable.title],
        phones = phones
    )
}

fun List<ResultRow>.toPhoneList(): List<Phone> {
    val phoneNumber: String? = this.first().tryGet(PhoneTable.number)
    if (phoneNumber != null) {
        return this.map {
            Phone(
                number = it[PhoneTable.number],
                regionCode = it[PhoneTable.countryCode]
            )
        }

    }
    return listOf()
}

class ContactRepositoryImpl : ContactRepository {
    private var dataSource: Database = DbSettings.db

    init {
        transaction(dataSource) {
            SchemaUtils.create(ContactTable)
            SchemaUtils.create(PhoneTable)
        }
    }

    override fun findAll(): List<Contact> {
        return transaction(dataSource) {
            ContactTable.leftJoin(PhoneTable, { ContactTable.id }, { PhoneTable.contactId })
                .slice(PhoneTable.columns + ContactTable.columns)
                .selectAll()
                .groupBy { it[ContactTable.id] }
                .map { it.value.first().toContact(it.value.toPhoneList()) }
        }.toList()
    }
}