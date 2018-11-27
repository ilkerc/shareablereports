package io.rakam.shareablereports.dto

class ShareReportRequestDTO (
        private var reportId: Long,
        private var editableFields: HashSet<String>) {

    fun getReportId(): Long {
        return this.reportId
    }

    fun setReportTitle(reportId: Long) {
        this.reportId = reportId
    }

    fun getEditableFields():  HashSet<String>?{
        return this.editableFields
    }

    fun setEditableFields(editableFields: HashSet<String>){
        this.editableFields = editableFields
    }

    override fun toString(): String {
        return "ShareReportRequestDTO(reportId=$reportId, editableFields=$editableFields)"
    }
}