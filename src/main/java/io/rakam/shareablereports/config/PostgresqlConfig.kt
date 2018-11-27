package io.rakam.shareablereports.config

import org.jdbi.v3.core.Jdbi

class PostgreSqlConfig(
        private val connectionUrl: String,
        private val userName: String,
        private val password: String) {

    fun connection(): Jdbi {
        return Jdbi.create(connectionUrl, userName, password)
    }
}