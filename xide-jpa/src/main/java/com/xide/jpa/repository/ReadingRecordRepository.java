package com.xide.jpa.repository;

import com.xide.jpa.entity.ReadingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRecordRepository extends JpaRepository<ReadingRecord, String> {
}