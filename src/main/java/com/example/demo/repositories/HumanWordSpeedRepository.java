package com.example.demo.repositories;

import com.example.demo.dtos.Article;
import com.example.demo.dtos.HumanWordSpeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanWordSpeedRepository extends JpaRepository<HumanWordSpeed,Long> {

}
