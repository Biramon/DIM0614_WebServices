package com.biramon.smartparking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.biramon.smartparking.model.Estacionamento;
import com.biramon.smartparking.repository.EstacionamentoRepository;

@RestController
@RequestMapping("/api")
public class EstacionamentoController {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRep;
	
	/**
	 * Esse método todos os estacionamentos cadastrados
	 * @return Lista de estacionamentos
	 */
	@GetMapping("/estacionamentos")
	public List<Estacionamento> listarEstacionamentos () {
		return estacionamentoRep.findAll();
	}
	
	/**
	 * Esse método realiza o cadastro dos estacionamentos
	 * @param e Dados do estacionamento, que são recebidos de um JSON via método POST
	 * @return O Estacionamento criado
	 */
	@PostMapping("/estacionamentos")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estacionamento adicionar (@RequestBody Estacionamento e) {
		return estacionamentoRep.save(e);
	}
	
	/**
	 * Este método realiza a busca por um estacionamento em específico, de acordo com o seu ID
	 * @param id Identificador único do Estacionamento
	 * @return Se existir um estacionamento cadastrado, retornará seus dados
	 */
	@GetMapping("/estacionamento/{id}")
	public Optional<Estacionamento> listarEstacionamento (@PathVariable(value = "id") long id) {
		return estacionamentoRep.findById(id);
	}
	
	/**
	 * Esse método exibe o total de vagas em cada estacionamento e o somatório de vagas entre eles
	 * @return Mensagem de texto com o total de vagas em cada estacionamento e o total geral
	 */
	@GetMapping("/vagas")
	public String totalVagas () {
		long nEst = estacionamentoRep.count();
		String vagas = "";
		int totalVagas = 0;
		
		for (int i = 1; i <= nEst; i++) {
			vagas = vagas + "Total de vagas no estacionamento " + estacionamentoRep.findById((long) i).get().getNome() + " (ID #" 
					+ estacionamentoRep.findById((long) i).get().getId() + "): "
					+ estacionamentoRep.findnVagasTotaisById(i) + "\n";
			totalVagas += estacionamentoRep.findnVagasTotaisById(i);
		}
		
		return vagas + "Total de vagas em todos os Estacionamentos: " + totalVagas + "\n";
	}
	
	/**
	 * Esse método realiza a consulta das vagas disponíveis em cada um dos estacionamentos
	 * cadastrados, listando-os separadamente
	 * @return
	 */
	@GetMapping("/vagas/disponiveis")
	public String vagasDisponiveis () {
		long nEst = estacionamentoRep.count();
		String vagas = "";
		
		for (int i = 1; i <= nEst; i++) {
			vagas = vagas + "Vagas disponíveis no estacionamento " + estacionamentoRep.findById((long) i).get().getNome() + " (ID #" 
					+ estacionamentoRep.findById((long) i).get().getId() + "): "
					+ (estacionamentoRep.findnVagasTotaisById(i) - estacionamentoRep.findnVagasOcupadasById(i)) + "\n";
		}
		return vagas;
	}
	
	
	
	/**
	 * TO_DO Método que simule o estacionamento de um veículo, ou seja: ao ser invocado, o método altera 
	 * a quantidade de vagas disponíveis
	 * @param id
	 * @return
	 
	@PatchMapping("/estacionar/{id}")
	public Estacionamento estacionar (@PathVariable("id") long id) {
		
	}
	*/
	
}
