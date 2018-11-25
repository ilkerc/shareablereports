package io.rakam.shareablereports.repository

import io.rakam.shareablereports.entity.Report
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate


@RegisterBeanMapper(Report::class)
interface ReportRepository {

    @SqlQuery("SELECT * FROM report WHERE id = :id")
    fun get(@Bind("id") id: Long): Report

    @SqlUpdate("INSERT INTO report (id, report_title, report_description, report_author) " +
            "values (:id, :reportTitle, :reportDescription, :reportAuthor)")
    fun add(@BindBean report: Report): Boolean

    @SqlUpdate("UPDATE report SET report_title = :reportTitle, report_description = :reportDescription, report_author = :reportAuthor " +
            "WHERE id = :id")
    fun update(@BindBean report: Report) : Boolean

    @SqlUpdate("DELETE FROM report WHERE id = :id")
    fun delete(@Bind("id") id: Long?): Boolean

}