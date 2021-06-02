package com.ecommerce.lojadegames.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.lojadegames.model.Categoria;
import com.ecommerce.lojadegames.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	//verifica se o id da categoria existe
	public Optional<Categoria> findById(Long id){
		Optional<Categoria> opt = repository.findById(id);
		if(opt.isEmpty()) {
			return null;
		} else {
			return opt;
		}
	}
	

}
