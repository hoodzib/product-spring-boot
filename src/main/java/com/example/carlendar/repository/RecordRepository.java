package com.example.carlendar.repository;

import com.example.carlendar.entity.ProductEntity;
import com.example.carlendar.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity,Integer> {


    @Query(value = "select * from record r where r.name like %:keyword%", nativeQuery = true)
    List<RecordEntity> findByKeyword(@Param("keyword") String keyword);

    @Query(value="SELECT SUM(price) FROM record WHERE date = :date", nativeQuery = true)
    public Integer Totaltoday(@Param("date") LocalDate date);

    @Query(value="SELECT SUM(price) FROM record WHERE month(date) = :date", nativeQuery = true)
    public Integer Totalmonth(@Param("date") int date);

    @Query(value="SELECT SUM(price) FROM record WHERE year(date) = :date", nativeQuery = true)
    public Integer Totalyear(@Param("date") int date);
}
