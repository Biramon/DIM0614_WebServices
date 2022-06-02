package com.biramon.smartparking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biramon.smartparking.model.Estacionamento;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
	
	@Query("SELECT e.nVagasTotais FROM Estacionamento e WHERE e.id = :id")
	public int findnVagasTotaisById (@Param("id") long id);
	
	@Query("SELECT e.nVagasOcupadas FROM Estacionamento e WHERE e.id = :id")
	public int findnVagasOcupadasById (@Param("id") long id);
	
	@Query("SELECT e.nome FROM Estacionamento e WHERE e.id = :id")
	public String findNomeById (@Param("id") long id);
	
	@Query("SELECT e.id FROM Estacionamento e WHERE e.id = :id")
	public int findId ();
	
	@Query("SELECT e.id FROM Estacionamento e WHERE e.nome LIKE %:nome%")
	public int findIdByNome (@Param("nome") String nome);
	
}
