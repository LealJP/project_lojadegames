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

import com.ecommerce.lojadegames.model.Produto;
import com.ecommerce.lojadegames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Produto>> findAllProdutos(){
		List<Produto> listaDeProdutos = repository.findAll();
		if(listaDeProdutos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(listaDeProdutos);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable long id){
		return repository.findById(id)
				.map(produtoLocalizado -> ResponseEntity.ok(produtoLocalizado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> findByDescricao(@PathVariable String descricao){
		List<Produto> listaProduto = repository.findByDescricaoContaining(descricao);
		
		if(listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(listaProduto);
		}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		return ResponseEntity.status(201).body(repository.save(produto));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		return ResponseEntity.status(201).body(repository.save(produto));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deleteProduto(@PathVariable long id) {
		repository.deleteById(id);
	}
}
