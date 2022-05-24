package com.biramon.smartparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biramon.smartparking.model.Estacionamento;
import com.biramon.smartparking.repository.EstacionamentoRepository;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	@GetMapping
	public List<Estacionamento> listarEstacionamentos () {
		return estacionamentoRep.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estacionamento adicionar (@RequestBody Estacionamento e) {
		return estacionamentoRep.save(e);
	}
}
