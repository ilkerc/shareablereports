import io.rakam.shareablereports.ShareableReportApp
import io.rakam.shareablereports.dto.*
import io.rakam.shareablereports.entity.Report
import io.rakam.shareablereports.service.ReportService
import io.rakam.shareablereports.service.ReportServiceImpl
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ReportServiceTest {

    companion object {

        var reportService: ReportService? = null

        @BeforeAll
        @JvmStatic
        fun beforeAllTestCases() {
            var jdbi : Jdbi = ShareableReportApp.getJdbi()
            reportService = ReportServiceImpl(jdbi)
        }
    }

    @Test
    fun when_correct_edit_parameters_are_posted_then_200_with_updated_report() {

        var reportRequestDTO = ReportRequestDTO("Title", "Description","Author")
        var addReportResponse : SuccessResponseDTO<Report> = reportService!!.add(reportRequestDTO) as SuccessResponseDTO<Report>

        assertEquals(201, addReportResponse.getStatus())
        assertEquals("Author", addReportResponse.getResponse().getReportAuthor())
        assertEquals("Title",addReportResponse.getResponse().getReportTitle())
        assertEquals("Description",addReportResponse.getResponse().getReportDescription())

        var shareReportRequestDTO = ShareReportRequestDTO(addReportResponse.getResponse().id!!, hashSetOf("reportTitle","reportDescription"))
        var shareReportResponse : SuccessResponseDTO<ShareReportResponseDTO> = reportService!!.shareReport(shareReportRequestDTO) as SuccessResponseDTO<ShareReportResponseDTO>

        assertEquals(201 , shareReportResponse.getStatus())
        assertEquals("/rest/v1/share/report?reportId=${addReportResponse.getResponse().id!!}", shareReportResponse.getResponse().getResourceURL())

        var readSharedReportResponse : SuccessResponseDTO<Report> = reportService!!.readSharedReport(addReportResponse.getResponse().id!!, "Bearer ${shareReportResponse.getResponse().getAccessToken()!!}") as SuccessResponseDTO<Report>

        assertEquals(200, readSharedReportResponse.getStatus())
        assertEquals("Author", readSharedReportResponse.getResponse().getReportAuthor())
        assertEquals("Title",readSharedReportResponse.getResponse().getReportTitle())
        assertEquals("Description",readSharedReportResponse.getResponse().getReportDescription())


        val editSharedReport : SuccessResponseDTO<Report> = reportService!!.editSharedReport(addReportResponse.getResponse().id!!, "Bearer ${shareReportResponse.getResponse().getAccessToken()!!}", hashMapOf("reportTitle" to "A", "reportDescription" to "B")) as SuccessResponseDTO<Report>
        assertEquals(200, editSharedReport.getStatus())
        assertEquals("Author", editSharedReport.getResponse().getReportAuthor())
        assertEquals("A",editSharedReport.getResponse().getReportTitle())
        assertEquals("B",editSharedReport.getResponse().getReportDescription())

    }

    @Test
    fun when_invalid_token_then_403_with_invalid_token_error_message(){
        var reportRequestDTO = ReportRequestDTO("Title", "Description","Author")
        var addReportResponse : SuccessResponseDTO<Report> = reportService!!.add(reportRequestDTO) as SuccessResponseDTO<Report>

        assertEquals(201, addReportResponse.getStatus())
        assertEquals("Author", addReportResponse.getResponse().getReportAuthor())
        assertEquals("Title",addReportResponse.getResponse().getReportTitle())
        assertEquals("Description",addReportResponse.getResponse().getReportDescription())

        var shareReportRequestDTO = ShareReportRequestDTO(addReportResponse.getResponse().id!!, hashSetOf("reportTitle","reportDescription"))
        var shareReportResponse : SuccessResponseDTO<ShareReportResponseDTO> = reportService!!.shareReport(shareReportRequestDTO) as SuccessResponseDTO<ShareReportResponseDTO>

        assertEquals(201 , shareReportResponse.getStatus())
        assertEquals("/rest/v1/share/report?reportId=${addReportResponse.getResponse().id!!}", shareReportResponse.getResponse().getResourceURL())

        val editSharedReport : FailureResponseDTO = reportService!!.editSharedReport(addReportResponse.getResponse().id!!, "Bearer ${shareReportResponse.getResponse().getAccessToken()!!}+1", hashMapOf("reportTitle" to "A", "reportDescription" to "B")) as FailureResponseDTO
        assertEquals(403, editSharedReport.getStatus())
        assertEquals("The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256", editSharedReport.getMessage())
    }

    @Test
    fun when_unauthorized_attribute_send_then_400() {
        var reportRequestDTO = ReportRequestDTO("Title", "Description","Author")
        var addReportResponse : SuccessResponseDTO<Report> = reportService!!.add(reportRequestDTO) as SuccessResponseDTO<Report>

        assertEquals(201, addReportResponse.getStatus())
        assertEquals("Author", addReportResponse.getResponse().getReportAuthor())
        assertEquals("Title",addReportResponse.getResponse().getReportTitle())
        assertEquals("Description",addReportResponse.getResponse().getReportDescription())

        var shareReportRequestDTO = ShareReportRequestDTO(addReportResponse.getResponse().id!!, hashSetOf("reportTitle"))
        var shareReportResponse : SuccessResponseDTO<ShareReportResponseDTO> = reportService!!.shareReport(shareReportRequestDTO) as SuccessResponseDTO<ShareReportResponseDTO>

        assertEquals(201 , shareReportResponse.getStatus())
        assertEquals("/rest/v1/share/report?reportId=${addReportResponse.getResponse().id!!}", shareReportResponse.getResponse().getResourceURL())

        val editSharedReport : FailureResponseDTO = reportService!!.editSharedReport(addReportResponse.getResponse().id!!, "Bearer ${shareReportResponse.getResponse().getAccessToken()!!}", hashMapOf("reportTitle" to "A", "reportDescription" to "B")) as FailureResponseDTO
        assertEquals(400, editSharedReport.getStatus())
        assertEquals("Available fields: [reportTitle]", editSharedReport.getMessage())
    }

    @Test
    fun when_token_expired_then_403_with_token_expired_message(){
        var reportRequestDTO = ReportRequestDTO("Title", "Description","Author")
        var addReportResponse : SuccessResponseDTO<Report> = reportService!!.add(reportRequestDTO) as SuccessResponseDTO<Report>

        assertEquals(201, addReportResponse.getStatus())
        assertEquals("Author", addReportResponse.getResponse().getReportAuthor())
        assertEquals("Title",addReportResponse.getResponse().getReportTitle())
        assertEquals("Description",addReportResponse.getResponse().getReportDescription())

        var shareReportRequestDTO = ShareReportRequestDTO(addReportResponse.getResponse().id!!, hashSetOf("reportTitle","reportDescription"))
        var shareReportResponse : SuccessResponseDTO<ShareReportResponseDTO> = reportService!!.shareReport(shareReportRequestDTO) as SuccessResponseDTO<ShareReportResponseDTO>

        assertEquals(201 , shareReportResponse.getStatus())
        assertEquals("/rest/v1/share/report?reportId=${addReportResponse.getResponse().id!!}", shareReportResponse.getResponse().getResourceURL())

        var readSharedReportResponse : SuccessResponseDTO<Report> = reportService!!.readSharedReport(addReportResponse.getResponse().id!!, "Bearer ${shareReportResponse.getResponse().getAccessToken()!!}") as SuccessResponseDTO<Report>

        assertEquals(200, readSharedReportResponse.getStatus())
        assertEquals("Author", readSharedReportResponse.getResponse().getReportAuthor())
        assertEquals("Title",readSharedReportResponse.getResponse().getReportTitle())
        assertEquals("Description",readSharedReportResponse.getResponse().getReportDescription())

        Thread.sleep(1000 * 31)

        val editSharedReport : FailureResponseDTO = reportService!!.editSharedReport(addReportResponse.getResponse().id!!, "Bearer ${shareReportResponse.getResponse().getAccessToken()!!}", hashMapOf("reportTitle" to "A", "reportDescription" to "B")) as FailureResponseDTO
        assertEquals(403, editSharedReport.getStatus())
        assert(editSharedReport.getMessage().startsWith("The Token has expired "))
    }
}
