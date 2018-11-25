package io.rakam.shareablereports.config

import org.jdbi.v3.core.ConnectionFactory
import org.jdbi.v3.core.Jdbi

class PostgreSqlConfig {

    private val connectionUrl: String
    private val userName: String
    private val password: String

    constructor(connectionUrl: String, userName: String, password: String) {
        this.connectionUrl = connectionUrl;
        this.userName = userName;
        this.password = password
    }

    fun connection(): Jdbi {
        return Jdbi.create(connectionUrl, userName, password)
    }
}