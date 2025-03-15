package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
                    return produtoRepository.save(produto);
                })
                .orElseGet(() -> {
                    produtoAtualizado.setId(id);
                    return produtoRepository.save(produtoAtualizado);
                });
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public List<Produto> buscarPorFaixaDePreco(Double minPreco, Double maxPreco) {
        return produtoRepository.findByPrecoBetween(minPreco, maxPreco);
    }

    public List<Produto> buscarPorQuantidadeEstoque(Integer quantidade) {
        return produtoRepository.findByQuantidadeEstoqueGreaterThan(quantidade);
    }
}