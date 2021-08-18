package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.Intro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntroRepository extends JpaRepository<Intro, Long> {
        Intro findById(long id);

        @Override
        List<Intro> findAll();
}
