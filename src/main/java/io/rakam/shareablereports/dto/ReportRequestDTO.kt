package io.rakam.shareablereports.dto

class ReportRequestDTO(
        private var reportTitle: String,
        private var reportDescription: String,
        private var reportAuthor: String) {

    fun getReportTitle(): String {
        return reportTitle
    }

    fun setReportTitle(reportTitle: String) {
        this.reportTitle = reportTitle
    }

    fun getReportDescription(): String {
        return reportDescription
    }

    fun setReportDescription(reportDescription: String) {
        this.reportDescription = reportDescription
    }

    fun getReportAuthor(): String {
        return reportAuthor
    }

    fun setReportAuthor(reportAuthor: String) {
        this.reportAuthor = reportAuthor
    }

    override fun toString(): String {
        return "ReportRequestDTO(reportTitle=$reportTitle, reportDescription=$reportDescription, reportAuthor=$reportAuthor)"
    }

}