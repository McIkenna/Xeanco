package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {

    Clients findById(long id);

    @Override
    List<Clients> findAll();

}
