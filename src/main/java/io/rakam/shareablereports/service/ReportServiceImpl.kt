package io.rakam.shareablereports.service

import io.rakam.shareablereports.converter.ReportRequestDtoToRequest
import io.rakam.shareablereports.dto.FailureResponseDTO
import io.rakam.shareablereports.dto.ReportRequestDTO
import io.rakam.shareablereports.dto.ResponseDTO
import io.rakam.shareablereports.dto.SuccessResponseDTO
import io.rakam.shareablereports.entity.Report
import io.rakam.shareablereports.repository.ReportRepository
import org.jdbi.v3.core.Jdbi

class ReportServiceImpl(private val dbi: Jdbi) : ReportService {

    private val dao = dbi.onDemand(ReportRepository::class.java)

    override fun add(reportRequestDTO: ReportRequestDTO): ResponseDTO {

        val report = ReportRequestDtoToRequest().convert(reportRequestDTO)

        when (dao.add(report)) {
            true -> return SuccessResponseDTO<Report>(201, report);
            false -> return FailureResponseDTO(500, "Cannot create report")
        }
    }

    override fun get(id: Long): ResponseDTO {
        val report = dao.get(id)

        when (report != null) {
            true -> return SuccessResponseDTO<Report>(200, report);
            false -> return FailureResponseDTO(404, "Report not found")
        }
    }

    override fun update(reportId: Long, reportRequestDTO: ReportRequestDTO): ResponseDTO {

        val report : Report = ReportRequestDtoToRequest().convert(reportRequestDTO)
        report.id = reportId

        var isUpdated = dao.update(report)

        when (isUpdated) {
            true -> return SuccessResponseDTO<Report>(200, report);
            false -> return FailureResponseDTO(404, "Report not found")
        }
    }

    override fun delete(id: Long): ResponseDTO {

        var isDeleted = dao.delete(id)

        when (isDeleted) {
            true -> return ResponseDTO(204)
            false -> return FailureResponseDTO(404, "Report not found")
        }
    }
}