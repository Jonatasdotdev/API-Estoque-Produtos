package com.example.estoque.repository;

import com.example.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContaining(String nome);

    @Query("SELECT p FROM Produto p WHERE p.preco BETWEEN :minPreco AND :maxPreco")
    List<Produto> findByPrecoBetween(@Param("minPreco") Double minPreco, @Param("maxPreco") Double maxPreco);

    List<Produto> findByQuantidadeEstoqueGreaterThan(Integer quantidadeEstoque);
}