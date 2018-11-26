package io.rakam.shareablereports.service

import io.rakam.shareablereports.dto.ReportRequestDTO
import io.rakam.shareablereports.dto.ResponseDTO
import io.rakam.shareablereports.dto.ShareReportRequestDTO
import io.rakam.shareablereports.entity.Report

interface ReportService {

    fun get(id: Long) : ResponseDTO
    fun add(reportRequestDTO: ReportRequestDTO) : ResponseDTO
    fun update(reportId: Long, report: ReportRequestDTO): ResponseDTO
    fun delete(id: Long): ResponseDTO

    fun shareReport(shareReportRequestDTO: ShareReportRequestDTO): ResponseDTO
    fun readSharedReport(id: Long, authorization: String): ResponseDTO
    fun editSharedReport(id: Long, authorization: String, attributes: HashMap<String,String>): ResponseDTO
}