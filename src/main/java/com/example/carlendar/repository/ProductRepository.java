package com.example.carlendar.repository;

import com.example.carlendar.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

//    DELETE FROM `product` WHERE id="1"

//    @Query("DELETE FROM `product` WHERE id = :id")
//    void deleteUsersByFirstName(long id);

//    long deleteByPD(long typeid);

//    @Query(value = "DELETE FROM `product` WHERE id =%:keyword%", nativeQuery = true)
//    ProductEntity DeletePD(@Param("keyword") long keyword);

    @Query(value = "select * from product p where p.name like %:keyword%", nativeQuery = true)
    List<ProductEntity> findByKeyword(@Param("keyword") String keyword);

}
