package com.power.springbootapi.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
class PostcodeInfoRepositoryTest {
    @Autowired
    private PostcodeInfoRepository repository;

    @Test
    public void testSave() {
        PostcodeInfo info = PostcodeInfo.builder()
                .postcode("1234")
                .name("xyz")
                .build();
        PostcodeInfo savedInfo = repository.save(info);
        Assert.isTrue(repository.findById(savedInfo.getId())
                .isPresent(), "PostcodeInfo save successfully.");
    }

    @Test
    public void testUniqueConstraint() {
        PostcodeInfo info = PostcodeInfo.builder()
                .postcode("1234")
                .name("xyz")
                .build();
        PostcodeInfo savedInfo = repository.save(info);
        Assert.isTrue(repository.findById(savedInfo.getId())
                .isPresent(), "PostcodeInfo save successfully.");
    }

    @Test
    public void testSaveAll() {
        PostcodeInfo info1 = PostcodeInfo.builder()
                .postcode("1234")
                .name("xyz")
                .build();
        PostcodeInfo info2 = PostcodeInfo.builder()
                .postcode("2345")
                .name("abc")
                .build();
        List<PostcodeInfo> list = Arrays.asList(info1, info2);
        List<PostcodeInfo> savedList = repository.saveAll(list);
        Assert.isTrue(!savedList.isEmpty(), "PostcodeInfo save successfully.");
    }

    @Test
    public void testFindRangeOfPostcodes() {
        PostcodeInfo info1 = PostcodeInfo.builder()
                .postcode("1234")
                .name("name one")
                .build();
        PostcodeInfo info2 = PostcodeInfo.builder()
                .postcode("2345")
                .name("name two")
                .build();
        PostcodeInfo info3 = PostcodeInfo.builder()
                .postcode("2346")
                .name("not in the range")
                .build();
        List<PostcodeInfo> list = Arrays.asList(info1, info2, info3);
        repository.saveAll(list);
        List<PostcodeInfo> postcodeInfoList = repository.findRangeOfPostcodes("1234", "2345");
        Assert.isTrue(postcodeInfoList.size() == 2, "Not right set of range values.");
        Assert.isTrue(postcodeInfoList.get(0).getPostcode().equals("1234"), "Postcode 1234 expected.");
        Assert.isTrue(postcodeInfoList.get(1).getPostcode().equals("2345"), "Postcode 2345 expected.");
    }
}