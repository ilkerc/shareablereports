package io.rakam.shareablereports.converter

import io.rakam.shareablereports.dto.ReportRequestDTO
import io.rakam.shareablereports.entity.Report

class ReportRequestDtoToRequest: Converter<Report, ReportRequestDTO> {

    override fun convert(item: ReportRequestDTO): Report {
        return Report(System.currentTimeMillis(), item.getReportTitle(), item.getReportDescription(), item.getReportAuthor())
    }

}