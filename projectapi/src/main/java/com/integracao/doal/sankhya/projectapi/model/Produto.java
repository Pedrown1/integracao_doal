package com.integracao.doal.sankhya.projectapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "E_PRODUTOS")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Informacao informacao;

    @Embedded
    private Impostos impostos;

    @Embedded
    private Grupo grupo;

    private String dtAlter;
    private Integer idExterno;

    @Embeddable
    @Data
    public static class Informacao {
        private String ativo;
        private String descricao;
        private String unidade;
        private String refFornecedor;
        private String marca;
    }

    @Embeddable
    @Data
    public static class Impostos {
        private String calcIcms;
        private String tipSubst;
        private String cstIpiEnt;
        private String cstIpiSai;
    }

    @Embeddable
    @Data
    public static class Grupo {
        private String grupoProd;
    }
}
