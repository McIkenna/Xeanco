package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
    About findById(long id);
    About save(About about);

    @Override
    List<About> findAll();

}
