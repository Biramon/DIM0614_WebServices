package com.biramon.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biramon.smartparking.model.Estacionamento;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

}
