package com.power.springbootapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostcodeInfoRepository extends JpaRepository<PostcodeInfo, Long> {

    @Query("SELECT p FROM PostcodeInfo p WHERE p.postcode >= ?1 AND p.postcode <= ?2")
    List<PostcodeInfo> findRangeOfPostcodes(String start, String end);
}
