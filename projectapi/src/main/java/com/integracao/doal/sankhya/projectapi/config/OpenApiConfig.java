package com.integracao.doal.sankhya.projectapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Integração Doal",
                version = "v1.0",
                description = "Esta API foi desenvolvida para centralizar e facilitar processos de integração com sistemas externos. Seu principal objetivo é oferecer endpoints organizados e padronizados para operações comuns, como cadastro e consulta de entidades (ex: parceiros, produtos, estoques), servindo como ponto de entrada confiável para fluxos de integração entre aplicações corporativas.\n" +
                        "\n" +
                        "Ideal para times que atuam com integração de sistemas (como ERPs), automações de processos e consumo de serviços REST, esta API também adota boas práticas de documentação via OpenAPI/Swagger, facilitando testes, validação e entendimento das rotas disponíveis.\n\npedro.carvalho@doalti.com.br"
        )
)
public class OpenApiConfig {}
