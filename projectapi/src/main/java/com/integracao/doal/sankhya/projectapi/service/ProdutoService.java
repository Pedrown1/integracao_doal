package com.integracao.doal.sankhya.projectapi.service;

import com.integracao.doal.sankhya.projectapi.model.Produto;
import com.integracao.doal.sankhya.projectapi.repository.RepositoryProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private RepositoryProduto repository;

    public Produto salvar(Produto produto) {
        validarProduto(produto);
        return repository.save(produto);
    }

    public List<Produto> salvarTodos(List<Produto> produtos) {
        for (Produto p : produtos) {
            validarProduto(p);
        }
        return repository.saveAll(produtos);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private void validarProduto(Produto produto) {
        if (produto.getAtivo() == null || produto.getAtivo().trim().isEmpty()) {
            throw new IllegalArgumentException("Ativo é obrigatório");
        }
        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
        if (produto.getUnidade() == null || produto.getUnidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Unidade é obrigatória");
        }
        if (produto.getIdExterno() == null) {
            throw new IllegalArgumentException("Id Externo é obrigatório");
        }
    }
}

