package com.integracao.doal.sankhya.projectapi.service;

import com.integracao.doal.sankhya.projectapi.model.Parceiro;
import com.integracao.doal.sankhya.projectapi.repository.RepositoryParceiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParceiroService {

    @Autowired
    private RepositoryParceiro repository;

    public Parceiro salvar(Parceiro parceiro) {
        validarParceiro(parceiro);
        return repository.save(parceiro);
    }

    public List<Parceiro> salvarTodos(List<Parceiro> parceiros) {
        for (Parceiro p : parceiros) {
            validarParceiro(p);
        }
        return repository.saveAll(parceiros);
    }

    public List<Parceiro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Parceiro> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private void validarParceiro(Parceiro parceiro) {
        if (parceiro.getNome() == null || parceiro.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (parceiro.getAtivo() == null || parceiro.getAtivo().trim().isEmpty()) {
            throw new IllegalArgumentException("Ativo é obrigatório");
        }
        if (parceiro.getTipo() == null || parceiro.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo é obrigatório");
        }
        if (parceiro.getTipoPessoa() == null || parceiro.getTipoPessoa().trim().isEmpty()) {
            throw new IllegalArgumentException("TipoPessoa é obrigatório");
        }
        if (parceiro.getCnpjCpf() == null || parceiro.getCnpjCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ/CPF é obrigatório");
        }
        if (parceiro.getIdExterno() == null) {
            throw new IllegalArgumentException("Id Externo é obrigatório");
        }
    }
}
