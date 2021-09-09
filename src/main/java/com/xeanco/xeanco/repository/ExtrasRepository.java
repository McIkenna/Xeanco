package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtrasRepository extends JpaRepository<Extra, Long> {

    Extra findById(long id);

    List<Extra> findAll();
}
