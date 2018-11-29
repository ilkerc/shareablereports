package io.rakam.shareablereports.service

import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import io.rakam.shareablereports.converter.ReportRequestDtoToRequest
import io.rakam.shareablereports.dto.*
import io.rakam.shareablereports.entity.Report
import io.rakam.shareablereports.repository.ReportRepository
import io.rakam.shareablereports.util.TokenUtil
import org.jdbi.v3.core.Jdbi
import java.util.*
import java.util.function.Predicate

class ReportServiceImpl(private val dbi: Jdbi) : ReportService {

    private val editableFields =  hashSetOf("reportTitle", "reportDescription", "reportAuthor")

    private val dao = dbi.onDemand(ReportRepository::class.java)

    override fun add(reportRequestDTO: ReportRequestDTO): ResponseDTO {

        val report = ReportRequestDtoToRequest().convert(reportRequestDTO)

        return when (dao.add(report)) {
            true  -> SuccessResponseDTO<Report>(201, report);
            false -> FailureResponseDTO(500, "Cannot create report")
        }
    }

    override fun get(id: Long): ResponseDTO {
        val report = dao.get(id)

        return when (report != null) {
            true  -> SuccessResponseDTO<Report>(200, report);
            false -> FailureResponseDTO(404, "Report not found")
        }
    }

    override fun update(reportId: Long, reportRequestDTO: ReportRequestDTO): ResponseDTO {

        val report : Report = ReportRequestDtoToRequest().convert(reportRequestDTO)
        report.id = reportId

        var isUpdated = dao.update(report)

        return when (isUpdated) {
            true  -> SuccessResponseDTO<Report>(200, report);
            false -> FailureResponseDTO(404, "Report not found")
        }
    }

    override fun delete(id: Long): ResponseDTO {

        var isDeleted = dao.delete(id)

        return when (isDeleted) {
            true  -> ResponseDTO(204)
            false -> FailureResponseDTO(404, "Report not found")
        }
    }

    override fun shareReport(shareReportRequestDTO: ShareReportRequestDTO): ResponseDTO {

        if(shareReportRequestDTO.getReportId().compareTo(0) == 0){
            return FailureResponseDTO(403, "reportId field is required and it should be different than 0.")
        }

        if(shareReportRequestDTO.getEditableFields() == null){
            return FailureResponseDTO(403, "At least one editableField required. editableFields: [reportTitle, reportDescription, reportAuthor]")
        }

        if( !editableFields.containsAll(shareReportRequestDTO.getEditableFields()!!)) {
            return FailureResponseDTO(403, "Only supported editableField attributes: [reportTitle, reportDescription, reportAuthor]")
        }

        var report = dao.get(shareReportRequestDTO.getReportId())

        if(report == null){
           return FailureResponseDTO(404, "Report not found")
        }

        val editableOptionsArray = arrayOfNulls<String>(shareReportRequestDTO.getEditableFields()!!.size)

        try{
            val token = TokenUtil.createToken(1000 * 30 , shareReportRequestDTO.getReportId(), shareReportRequestDTO.getEditableFields()!!.toArray(editableOptionsArray))
            return SuccessResponseDTO(201, ShareReportResponseDTO("/rest/v1/share/report?reportId=${shareReportRequestDTO.getReportId()}",token))
        }catch (exception: JWTCreationException){
            return FailureResponseDTO(500, "There was a problem in token creation.")
        }
    }

    override fun readSharedReport(id: Long, authorization: String): ResponseDTO {

        val prefix : String = "Bearer "
        if(!authorization.startsWith(prefix)){
            return FailureResponseDTO(403, "Authorization header must start with Bearer")
        }

        try{
            var token = authorization.removePrefix(prefix)
            var tokenInfo = TokenUtil.decodeToken(token)

            return when(tokenInfo.reportId.compareTo(id) == 0){
                true  -> get(id)
                false -> FailureResponseDTO(400, "Cannot access reportId:$id with this token")
            }

        }catch (ex: JWTVerificationException){
            return FailureResponseDTO(403, ex.message!!)
        }
    }

    override fun editSharedReport(id: Long, authorization: String, attributes: HashMap<String,String>): ResponseDTO {

        val prefix : String = "Bearer "
        if(!authorization.startsWith(prefix)){
            return FailureResponseDTO(403, "Authorization header must start with Bearer")
        }

        try{
            var token = authorization.removePrefix(prefix)
            var tokenInfo = TokenUtil.decodeToken(token)

            if(!attributes.keys.stream().allMatch(Predicate { p -> tokenInfo.editableOptions.contains(p)})){
                return FailureResponseDTO(400, "Available fields: ${Arrays.toString(tokenInfo.editableOptions)}")
            }

            return when(tokenInfo.reportId.compareTo(id) == 0 ){
                true  -> checkReport(id, attributes)
                false -> FailureResponseDTO(400, "Cannot access reportId:$id with this token")
            }
        }catch (ex: JWTVerificationException){
            return FailureResponseDTO(403, ex.message!!)
        }
    }

    private fun checkReport(id: Long, attributes: HashMap<String,String>) : ResponseDTO {
        var report = dao.get(id)
        return when (report != null) {
            true  -> updateSharedReportAttributes(report, attributes)
            false -> FailureResponseDTO(404, "Report not found")
        }
    }

    private fun updateSharedReportAttributes(report: Report, attributes: HashMap<String,String>): ResponseDTO{
        attributes.keys.forEach { p ->
            when(p) {
                "reportTitle" -> report.setReportTitle(attributes.get(p)!!)
                "reportDescription" -> report.setReportDescription(attributes.get(p)!!)
                "reportAuthor" -> report.setReportAuthor(attributes.get(p)!!)
            }
        }

        var isUpdated = dao.update(report)
        return when (isUpdated) {
            true  -> SuccessResponseDTO(200, report);
            false -> FailureResponseDTO(404, "Report not found")
        }
    }
}