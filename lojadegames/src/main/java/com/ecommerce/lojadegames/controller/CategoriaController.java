package com.ecommerce.lojadegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.lojadegames.model.Categoria;
import com.ecommerce.lojadegames.repository.CategoriaRepository;
import com.ecommerce.lojadegames.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
//@CrossOrgin ? para o que serve?
public class CategoriaController {
	
	
	@Autowired
	private CategoriaRepository repository;
	
	//@Autowired
	//private CategoriaService services;
	
	@GetMapping("/todas") //feito pelo controller e repository
	public ResponseEntity<List<Categoria>> findAllCategorias() {
		List<Categoria> listaDeCategorias = repository.findAll();
	
		if(!listaDeCategorias.isEmpty()) {
			return ResponseEntity.status(201).body(listaDeCategorias);
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/id/{id}") //feito pela controller e repository
	public ResponseEntity<Categoria> getById(@PathVariable long id){
		return repository.findById(id)
				.map(categoriaLocalizada -> ResponseEntity.ok(categoriaLocalizada))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getByDescricao(@PathVariable String descricao){
		List<Categoria> listaCategoria = repository.findByDescricaoContaining(descricao);
		
		if(listaCategoria.isEmpty()) {
			return ResponseEntity.status(204).build(); //Status 204 = No Content
		} else {
			return ResponseEntity.status(201).body(listaCategoria);
		}
	}
	
	@PostMapping("/cadastrar") //implementar na service uma validação de categoriaexistente
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(201).body(repository.save(categoria));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(201).body(repository.save(categoria));
	}
	
	@DeleteMapping("/deletar/{idCategoria}")
	public void deleteCategoria(@PathVariable long idCategoria){
		repository.deleteById(idCategoria); 
	}

}
