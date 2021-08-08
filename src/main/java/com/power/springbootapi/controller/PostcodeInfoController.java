package com.power.springbootapi.controller;

import com.power.springbootapi.common.Constants;
import com.power.springbootapi.dto.PostcodeInfoDTO;
import com.power.springbootapi.service.PostcodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
public class PostcodeInfoController implements ApplicationExceptionHandler {
    @Autowired
    private PostcodeInfoService postcodeInfoService;

    @GetMapping("/names/{start}/{end}")
    public List<String> names(
            @PathVariable("start")
            @Pattern(regexp = Constants.POSTCODE_PATTERN, message = Constants.START_VALUE_INVALID_MSG)
            @NotNull
                    String start,
            @PathVariable("end")
            @Pattern(regexp = Constants.POSTCODE_PATTERN, message = Constants.END_VALUE_INVALID_MSG)
            @NotNull
                    String end) {
        return postcodeInfoService.findRangeOfSortedNames(start, end);
    }

    @PostMapping("/postcodes")
    public void savePostcodeInfo(@Valid @RequestBody List<PostcodeInfoDTO> postcodeInfoDTOList) {
        postcodeInfoService.savePostcodes(postcodeInfoDTOList);
    }
}
