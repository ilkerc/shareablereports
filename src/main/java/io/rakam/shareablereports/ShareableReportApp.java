package io.rakam.shareablereports;

import io.rakam.shareablereports.io.rakam.shareablereports.model.Report;
import org.rakam.server.http.HttpServer;
import org.rakam.server.http.HttpServerBuilder;
import org.rakam.server.http.HttpService;
import org.rakam.server.http.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.HashSet;

public class ShareableReportApp {
    public static void main(String[] args) throws Exception {
        HttpServer build = new HttpServerBuilder()
                .setHttpServices(new HashSet<>(Arrays.asList(new CustomHttpServer())))
                .setUseEpollIfPossible(false)
                .build();

        build.bindAwait("127.0.0.1", 8080);
    }

    @Path("/rest/v1")
    public static class CustomHttpServer extends HttpService {

        @JsonRequest
        @Path("/report")
        @ApiOperation(value = "Create report")
        public Report createReport(@BodyParam Report report){
            return report;
        }

        @GET
        @JsonRequest
        @Path("/report")
        @ApiOperation(value = "Read report")
        public Report readReport(@QueryParam(value = "reportId", required = true) Long reportId){
            return new Report("reportName","reportTitle","reportDescription","reportAuthor");
        }

        @PUT
        @JsonRequest
        @Path("/report")
        @ApiOperation(value = "Edit report")
        public Report editReport(@QueryParam(value = "reportId", required = true) Long reportId, @BodyParam Report report){
            return report;
        }
    }
}
