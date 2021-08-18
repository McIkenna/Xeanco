package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.Extras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtrasRepository extends JpaRepository<Extras, Long> {

    Extras findById(long id);

    List<Extras> findAll();
}
