package com.power.springbootapi.dto;

import com.power.springbootapi.common.Constants;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Builder
@Validated
public class PostcodeInfoDTO {

    @Pattern(regexp = Constants.POSTCODE_PATTERN, message = Constants.POSTCODE_INVALID_MSG)
    @NotNull
    private String postcode;

    @Size(min = 2, max = 256)
    @NotNull
    private String name;
}
