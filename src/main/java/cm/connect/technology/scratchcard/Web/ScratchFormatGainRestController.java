package cm.connect.technology.scratchcard.Web;

import cm.connect.technology.scratchcard.dto.ResponseDto;
import cm.connect.technology.scratchcard.entities.ScratchFormatGain;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/scratchFormatGain")
//@Api(value = "Endpoints to manage ScratchFormat", tags = "ScratchFormatGain RestController")
public interface ScratchFormatGainRestController {

    @GetMapping(value = "/getAll")
   // @ApiOperation(value = "Endpoint to get all ScratchFormatGain")
    ResponseDto<List<ScratchFormatGain>> getAll(
            @RequestHeader(name = "Accept-language", required = false) Locale locale
    );

    @PostMapping(value = "/create")
   // @ApiOperation(value = "Endpoint to create a new scratchFormatGain")
    ResponseDto<ScratchFormatGain> create(
            @RequestHeader(name = "Accept-language", required = false) Locale locale,
            @RequestBody @Valid ScratchFormatGain formatGain
    );

   // @ApiOperation("Endpoint to get an existing scratchFormatGain by name")
    @GetMapping("/name/{name}")
    ResponseDto<ScratchFormatGain> getByName(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale,
            @PathVariable("name") String name
    );

   // @ApiOperation("Endpoint to delete an existing scratchFormatGain by name")
    @DeleteMapping("/{name}")
    ResponseDto<ScratchFormatGain> delete(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale,
            @PathVariable("name") String name
    );

}
