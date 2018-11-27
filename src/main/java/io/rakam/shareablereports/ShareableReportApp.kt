package io.rakam.shareablereports

import io.rakam.shareablereports.config.PostgreSqlConfig
import io.rakam.shareablereports.controller.ReportController
import io.rakam.shareablereports.service.ReportServiceImpl
import org.jdbi.v3.core.Jdbi
import org.rakam.server.http.HttpServerBuilder
import org.rakam.server.http.HttpService
import java.util.*
import org.jdbi.v3.sqlobject.SqlObjectPlugin



object ShareableReportApp {

    @JvmStatic
    fun main(args: Array<String>) {
        var reportService = ReportServiceImpl(getJdbi())
        var build = HttpServerBuilder()
                .setHttpServices(HashSet<HttpService>(Arrays.asList(ReportController(reportService))))
                .setUseEpollIfPossible(false)
                .build()
        build.bindAwait("127.0.0.1",8080)
    }

    fun getJdbi(): Jdbi{
        val connectionUrl = "jdbc:postgresql://localhost:5432/postgres"
        val userName = "postgres"
        val password = "mysecretpassword"

        var jdbi : Jdbi = PostgreSqlConfig(connectionUrl,userName,password).connection()
        jdbi.installPlugin(SqlObjectPlugin())
        return jdbi
    }
}