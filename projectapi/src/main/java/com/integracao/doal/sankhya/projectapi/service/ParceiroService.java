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

        if (parceiro.getInformacao() == null) {
            throw new IllegalArgumentException("Informação do parceiro é obrigatória");
        }
        if (isNullOrEmpty(parceiro.getInformacao().getNome())) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (isNullOrEmpty(parceiro.getInformacao().getAtivo())) {
            throw new IllegalArgumentException("Campo 'ativo' é obrigatório");
        }
        if (isNullOrEmpty(parceiro.getInformacao().getTipoPessoa())) {
            throw new IllegalArgumentException("TipoPessoa é obrigatório");
        }
        if (isNullOrEmpty(parceiro.getInformacao().getCnpjCpf())) {
            throw new IllegalArgumentException("CNPJ/CPF é obrigatório");
        }


        if (parceiro.getEndereco() == null || parceiro.getEndereco().getCodCid() == null) {
            throw new IllegalArgumentException("Código da cidade é obrigatório");
        }

        if (parceiro.getCredito() == null) {
            throw new IllegalArgumentException("Informações de crédito são obrigatórias");
        }
        if (isNullOrEmpty(parceiro.getCredito().getSituacao())) {
            throw new IllegalArgumentException("Situação do crédito é obrigatória");
        }
        if (parceiro.getCredito().getLimCred() == null) {
            throw new IllegalArgumentException("Limite de crédito é obrigatório");
        }
        if (isNullOrEmpty(parceiro.getCredito().getBloquearPrazo())) {
            throw new IllegalArgumentException("Informar se bloqueia prazo é obrigatório");
        }

        if (parceiro.getFiscal() == null) {
            throw new IllegalArgumentException("Informações fiscais são obrigatórias");
        }
        if (isNullOrEmpty(parceiro.getFiscal().getClassifIcms())) {
            throw new IllegalArgumentException("Classificação ICMS é obrigatória");
        }
        if (isNullOrEmpty(parceiro.getFiscal().getTemIPI())) {
            throw new IllegalArgumentException("Informar se tem IPI é obrigatório");
        }

        // Datas
        if (isNullOrEmpty(parceiro.getDtCadastra())) {
            throw new IllegalArgumentException("Data de cadastro é obrigatória");
        }
        if (isNullOrEmpty(parceiro.getDtAlter())) {
            throw new IllegalArgumentException("Data de alteração é obrigatória");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
