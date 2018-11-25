package io.rakam.shareablereports.entity

class Report {
    private var id: Long? = null
    private var reportTitle: String? = null
    private var reportDescription: String? = null
    private var reportAuthor: String? = null

    constructor()

    constructor(id: Long?, reportTitle: String?, reportDescription: String?, reportAuthor: String?) {
        this.id = id
        this.reportTitle = reportTitle
        this.reportDescription = reportDescription
        this.reportAuthor = reportAuthor
    }

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getReportTitle(): String? {
        return reportTitle
    }

    fun setReportTitle(reportTitle: String) {
        this.reportTitle = reportTitle
    }

    fun getReportDescription(): String? {
        return reportDescription
    }

    fun setReportDescription(reportDescription: String?) {
        this.reportDescription = reportDescription
    }

    fun getReportAuthor(): String? {
        return reportAuthor
    }

    fun setReportAuthor(reportAuthor: String?) {
        this.reportAuthor = reportAuthor
    }

    override fun toString(): String {
        return "Report(id=$id, reportTitle=$reportTitle, reportDescription=$reportDescription, reportAuthor=$reportAuthor)"
    }
}