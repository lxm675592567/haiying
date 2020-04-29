package haiying.service.archives.controller;

import haiying.service.archives.domain.Record;
import haiying.service.archives.service.RecordService;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.validation.Valid;

@Validated
@Controller("/archives/record")
public class RecordController {

    @Inject
    private final RecordService recordService;

    public RecordController( RecordService recordService) {
        this.recordService = recordService;
    }

    @Get("/findOne/{guid}")
    public Record findOne( String guid) {
        return this.recordService.findOne(guid) ;
    }

    @Put("/updateRecord")
    public void updateRecord(@Body Record record) {
        recordService.updateRecord(record);
    }

    @Post("/saveRecord")
    public void saveRecord(@Body Record record) {
        recordService.saveRecord(record);
    }
}
