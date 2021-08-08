package com.power.springbootapi.mapper;

import com.power.springbootapi.dao.PostcodeInfo;
import com.power.springbootapi.dto.PostcodeInfoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostcodeInfoMapper {
    public List<PostcodeInfo> toPostcodes(List<PostcodeInfoDTO> dtos) {
        return dtos.stream().map(postcodeInfoDTO ->
                PostcodeInfo.builder()
                        .postcode(postcodeInfoDTO.getPostcode())
                        .name(postcodeInfoDTO.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public PostcodeInfoDTO toPostcodeDTO(PostcodeInfo postcodeInfo) {
        return PostcodeInfoDTO.builder()
                .postcode(postcodeInfo.getPostcode())
                .name(postcodeInfo.getName())
                .build();
    }
}
