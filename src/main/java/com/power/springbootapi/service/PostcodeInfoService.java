package com.power.springbootapi.service;

import com.power.springbootapi.common.NamesComparator;
import com.power.springbootapi.dao.PostcodeInfo;
import com.power.springbootapi.dao.PostcodeInfoRepository;
import com.power.springbootapi.dto.PostcodeInfoDTO;
import com.power.springbootapi.mapper.PostcodeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostcodeInfoService {
    @Autowired
    private PostcodeInfoMapper mapper;
    @Autowired
    private PostcodeInfoRepository repository;

    public List<PostcodeInfoDTO> savePostcodes(List<PostcodeInfoDTO> postcodeInfoDTOList) {
        List<PostcodeInfo> postcodeInfoList = mapper.toPostcodes(postcodeInfoDTOList);
        return repository.saveAll(postcodeInfoList)
                .stream()
                .map(postcodeInfo -> mapper.toPostcodeDTO(postcodeInfo))
                .collect(Collectors.toList());
    }

    public List<String> findRangeOfSortedNames(String start, String end) {
        List<PostcodeInfo> postcodeInfoList = repository.findRangeOfPostcodes(start, end);
        return postcodeInfoList.stream()
                .map(PostcodeInfo::getName)
                .sorted(new NamesComparator())
                .collect(Collectors.toList());

    }
}
