package io.rakam.shareablereports.io.rakam.shareablereports.model;

public class Report {

    private final String reportName;
    private final String reportTitle;
    private final String reportDescription;
    private final String reportAuthor;

    public Report(String reportName, String reportTitle, String reportDescription, String reportAuthor) {
        this.reportName = reportName;
        this.reportTitle = reportTitle;
        this.reportDescription = reportDescription;
        this.reportAuthor = reportAuthor;
    }

    public String getReportName() {
        return reportName;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public String getReportAuthor() {
        return reportAuthor;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Report{");
        sb.append("reportName='").append(reportName).append('\'');
        sb.append(", reportTitle='").append(reportTitle).append('\'');
        sb.append(", reportDescription='").append(reportDescription).append('\'');
        sb.append(", reportAuthor='").append(reportAuthor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
