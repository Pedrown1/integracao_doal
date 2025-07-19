package com.integracao.doal.sankhya.projectapi.service;

import com.integracao.doal.sankhya.projectapi.model.Estoque;
import com.integracao.doal.sankhya.projectapi.repository.RepositoryEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private RepositoryEstoque repository;

    public Estoque salvar(Estoque estoque) {
        validarEstoque(estoque);
        return repository.save(estoque);
    }

    public List<Estoque> salvarTodos(List<Estoque> estoques) {
        for (Estoque e : estoques) {
            validarEstoque(e);
        }
        return repository.saveAll(estoques);
    }

    public List<Estoque> listarTodos() {
        return repository.findAll();
    }

    public Optional<Estoque> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    public List<Estoque> buscarPorCodProduto(Integer codProduto) {
        return repository.findByCodProduto(codProduto);
    }

    public Estoque atualizarQuantidade(Integer codProduto, BigDecimal novaQuantidade) {
        Estoque estoque = repository.findAll().stream()
                .filter(e -> e.getCodProduto().equals(codProduto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no estoque"));

        estoque.setQtdProduto(novaQuantidade);
        return repository.save(estoque);
    }

    private void validarEstoque(Estoque estoque) {
        if (estoque.getCodProduto() == null) {
            throw new IllegalArgumentException("Código do Produto é obrigatório");
        }
        if (estoque.getQtdProduto() == null) {
            throw new IllegalArgumentException("Quantidade é obrigatória");
        }
    }
}
