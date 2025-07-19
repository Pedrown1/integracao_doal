package com.integracao.doal.sankhya.projectapi.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "E_PARCEIROV2")
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Informacao informacao;

    @Embedded
    private Endereco endereco;

    @Embedded
    private Credito credito;

    @Embedded
    private Fiscal fiscal;

    private String dtCadastra;
    private String dtAlter;
    private String observacao;
    private Integer idExterno;

    @Embeddable
    @Data
    public static class Informacao {
        private String ativo;
        private String nome;
        private String tipoPessoa;
        private String cnpjCpf;
    }

    @Embeddable
    @Data
    public static class Endereco {
        private Integer codCid;
    }

    @Embeddable
    @Data
    public static class Credito {
        private String situacao;
        private BigDecimal limCred;
        private String bloquearPrazo;
    }

    @Embeddable
    @Data
    public static class Fiscal {
        private String classifIcms;
        private String temIPI;
    }
}

