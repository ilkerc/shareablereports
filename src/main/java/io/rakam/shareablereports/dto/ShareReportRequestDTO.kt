package io.rakam.shareablereports.dto

class ShareReportRequestDTO {

    private var reportId: Long = 0
    private var editableFields:  HashSet<String>? = null

    constructor()

    constructor(reportId: Long, editableFields: HashSet<String>) {
        this.reportId = reportId
        this.editableFields = editableFields
    }

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