package com.ecommerce.lojadegames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.lojadegames.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
	
	public Optional<Categoria> findById(Long idCategoria);
	public List<Categoria> findByDescricaoContaining(String descricao);

}
