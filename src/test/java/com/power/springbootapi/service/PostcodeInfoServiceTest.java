package com.power.springbootapi.service;

import com.power.springbootapi.dao.PostcodeInfo;
import com.power.springbootapi.dao.PostcodeInfoRepository;
import com.power.springbootapi.dto.PostcodeInfoDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class PostcodeInfoServiceTest {
    @MockBean
    private PostcodeInfoRepository postcodeInfoRepository;
    @Autowired
    PostcodeInfoService postcodeInfoService;

    @Test
    void testSavePostcodes() {
        Mockito.when(postcodeInfoRepository.saveAll(Mockito.any(List.class)))
                .thenReturn(preparePostcodeInfoList());

        List<PostcodeInfoDTO> savedList = postcodeInfoService.savePostcodes(new ArrayList<PostcodeInfoDTO>());
        Assertions.assertThat(savedList)
                .isNotNull();
    }

    @Test
    void testFindNames() {
        Mockito.when(postcodeInfoRepository.findRangeOfPostcodes(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(preparePostcodeInfoList());
        List<String> nameList = postcodeInfoService.findRangeOfSortedNames("1234", "2345");
        Assertions.assertThat(nameList.containsAll(Arrays.asList("abcd", "xyz", "efgh")))
                .isNotNull();
    }

    private List<PostcodeInfo> preparePostcodeInfoList() {
        return Arrays.asList(PostcodeInfo.builder()
                .postcode("1234")
                .name("abcd")
                .build(), PostcodeInfo.builder()
                .postcode("2345")
                .name("xyz")
                .build(), PostcodeInfo.builder()
                .postcode("2346")
                .name("efgh")
                .build());
    }
}