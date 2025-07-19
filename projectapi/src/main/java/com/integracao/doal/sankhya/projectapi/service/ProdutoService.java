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

        if (produto.getInformacao() == null) {
            throw new IllegalArgumentException("Informação é obrigatória");
        }
        if (produto.getGrupo() == null || produto.getGrupo().getGrupoProd() == null) {
            throw new IllegalArgumentException("Código do grupo do produto é obrigatório");
        }
        if (produto.getImpostos() == null) {
            throw new IllegalArgumentException("Informações de impostos são obrigatórias");
        }

        validarCampo(produto.getInformacao().getAtivo(), "Ativo é obrigatório");
        validarCampo(produto.getInformacao().getDescricao(), "Descrição é obrigatória");
        validarCampo(produto.getInformacao().getUnidade(), "Unidade é obrigatória");
        validarCampo(produto.getDtAlter(), "Data de alteração é obrigatória");
        validarCampo(produto.getImpostos().getCstIpiEnt(), "CST IPI Entrada é obrigatório");
        validarCampo(produto.getImpostos().getCstIpiSai(), "CST IPI Saída é obrigatório");
        validarCampo(produto.getInformacao().getMarca(), "Marca é obrigatória");
        validarCampo(produto.getInformacao().getRefFornecedor(), "Referência do fornecedor é obrigatória");
    }

    private void validarCampo(Object valor, String mensagemErro) {
        if (valor == null) {
            throw new IllegalArgumentException(mensagemErro);
        }
        if (valor instanceof String && ((String) valor).trim().isEmpty()) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
}

