package info.marcusvinicius.contacts.infrastructure

import org.jetbrains.exposed.sql.Database

object DbSettings {
    val db by lazy {
        Database.connect(
            "jdbc:postgresql://db:5432/contacts", driver = "org.postgresql.Driver",
            user = "postgres", password = "#@StrongP455w0rd#"
        )
    }
}