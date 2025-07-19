package com.integracao.doal.sankhya.projectapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String ativo;

    String descricao;

    String unidade;

    String dtAlter;

    Integer codGrupoProd;

    Integer cstIpiEnt;

    Integer cstIpiSai;

    String marca;

    String refFornecedor;

    Integer idExterno;
}
