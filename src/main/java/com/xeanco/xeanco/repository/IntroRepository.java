package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.Intro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroRepository extends JpaRepository<Intro, Long> {
        Intro findById(long id);
}
