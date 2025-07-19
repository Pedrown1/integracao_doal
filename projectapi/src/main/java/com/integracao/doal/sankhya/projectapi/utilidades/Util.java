package com.integracao.doal.sankhya.projectapi.utilidades;

import com.integracao.doal.sankhya.projectapi.model.Parceiro;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Util {

    public static Map<String, Object> estruturaAPI(int status, String message, Object dados){

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("Status", status);
        response.put("Mensagem", message);
        response.put("Dados", dados != null ? dados : Collections.emptyList());

        return response;
    }

    // Estrutura Parceiros
    public static Map<String, Object> montarRespostaParceiro(Parceiro p) {
        Map<String, Object> info = new HashMap<>();
        if (p.getInformacao() != null) {
            info.put("ativo", p.getInformacao().getAtivo());
            info.put("nome", p.getInformacao().getNome());
            info.put("tipoPessoa", p.getInformacao().getTipoPessoa());
            info.put("cnpjCpf", p.getInformacao().getCnpjCpf());
        }

        Map<String, Object> endereco = new HashMap<>();
        if (p.getEndereco() != null) {
            endereco.put("codCid", p.getEndereco().getCodCid());
        }

        Map<String, Object> credito = new HashMap<>();
        if (p.getCredito() != null) {
            credito.put("situacao", p.getCredito().getSituacao());
            credito.put("LimCred", p.getCredito().getLimCred());
            credito.put("bloquearPrazo", p.getCredito().getBloquearPrazo());
        }

        Map<String, Object> fiscal = new HashMap<>();
        if (p.getFiscal() != null) {
            fiscal.put("classifIcms", p.getFiscal().getClassifIcms());
            fiscal.put("temIPI", p.getFiscal().getTemIPI());
        }

        Map<String, Object> parceiroMap = new HashMap<>();
        parceiroMap.put("id", p.getId());
        parceiroMap.put("informacao", info);
        parceiroMap.put("endereco", endereco);
        parceiroMap.put("credito", credito);
        parceiroMap.put("fiscal", fiscal);
        parceiroMap.put("dtCadastra", p.getDtCadastra());
        parceiroMap.put("dtAlter", p.getDtAlter());
        parceiroMap.put("observacao", p.getObservacao() != null ? p.getObservacao() : "Parceiro Integrado do E-commerce");
        parceiroMap.put("idExterno", p.getIdExterno());

        return parceiroMap;
    }
}
