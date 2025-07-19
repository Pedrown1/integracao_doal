package com.integracao.doal.sankhya.projectapi.controller;

import com.integracao.doal.sankhya.projectapi.model.Produto;
import com.integracao.doal.sankhya.projectapi.service.ProdutoService;
import com.integracao.doal.sankhya.projectapi.utilidades.Util;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produtos")
@Tag(name = "Produto", description = "API para gerenciamento de produtos")
public class ControllerProduto {

    @Autowired
    private ProdutoService service;

    @Operation(summary = "Cadastrar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                                                                        {
                                                                            "Status": 1,
                                                                            "Mensagem": "Requisição realizada com Sucesso!",
                                                                            "Dados": {
                                                                                "id": 1,
                                                                                "informacao": {
                                                                                    "ativo": "Sim",
                                                                                    "descricao": "Mouse Logitech MX Master 3",
                                                                                    "unidade": "UNIDADE",
                                                                                    "refFornecedor": "REF1001",
                                                                                    "marca": "LOGITECH"
                                                                                },
                                                                                "impostos": {
                                                                                    "calcIcms": "Sim",
                                                                                    "tipSubst": "Subst. na compra e na venda",
                                                                                    "cstIpiEnt": "49-Outras Entradas",
                                                                                    "cstIpiSai": "99-Outras Saídas"
                                                                                },
                                                                                "grupo": {
                                                                                    "grupoProd": "DEPÓSITO CENTRAL"
                                                                                },
                                                                                "dtAlter": "19/07/2025",
                                                                                "idExterno": null
                                                                            }
                                                                        }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro de validação no produto",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Erro de validação",
                                    value = """
                                            {
                                              "Status": 2,
                                              "Mensagem": "Origem do Produto é obrigatória",
                                              "Dados": []
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @PostMapping("/cadastrar")
    public Map<String, Object> criar(@RequestBody Produto produto) {
        try {
            Produto salvo = service.salvar(produto);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", salvo);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Cadastrar múltiplos produtos em lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos cadastrados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso em lote",
                                    value = """
                                                                        {
                                                                            "Status": 1,
                                                                            "Mensagem": "Requisição realizada com Sucesso!",
                                                                            "Dados": [
                                                                                {
                                                                                    "id": 1,
                                                                                    "informacao": {
                                                                                        "ativo": "Sim",
                                                                                        "descricao": "Mouse Logitech MX Master 3",
                                                                                        "unidade": "UNIDADE",
                                                                                        "refFornecedor": "REF1001",
                                                                                        "marca": "LOGITECH"
                                                                                    },
                                                                                    "impostos": {
                                                                                        "calcIcms": "Sim",
                                                                                        "tipSubst": "Subst. na compra e na venda",
                                                                                        "cstIpiEnt": "49-Outras Entradas",
                                                                                        "cstIpiSai": "99-Outras Saídas"
                                                                                    },
                                                                                    "grupo": {
                                                                                        "grupoProd": "DEPÓSITO CENTRAL"
                                                                                    },
                                                                                    "dtAlter": "19/07/2025",
                                                                                    "idExterno": null
                                                                                },
                                                                                {
                                                                                    "id": 2,
                                                                                    "informacao": {
                                                                                        "ativo": "Nao",
                                                                                        "descricao": "Caderno Tilibra Universitário 200 folhas",
                                                                                        "unidade": "CAIXA",
                                                                                        "refFornecedor": "REF1002",
                                                                                        "marca": "TILIBRA"
                                                                                    },
                                                                                    "impostos": {
                                                                                        "calcIcms": "Nao",
                                                                                        "tipSubst": "Revenda com subst. tributária (cálculo de Subst. na compra)",
                                                                                        "cstIpiEnt": "00-Entrada c/Recuperação de Crédito",
                                                                                        "cstIpiSai": "50-Saída Tributada"
                                                                                    },
                                                                                    "grupo": {
                                                                                        "grupoProd": "DEPÓSITO CENTRAL"
                                                                                    },
                                                                                    "dtAlter": "19/07/2025",
                                                                                    "idExterno": null
                                                                                }
                                                                            ]
                                                                        }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro de validação em algum produto",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Erro em lote",
                                    value = """
                                            {
                                              "Status": 2,
                                              "Mensagem": "Descrição é obrigatória",
                                              "Dados": []
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @PostMapping("/cadastrar/lote")
    public Map<String, Object> criarLote(@RequestBody List<Produto> produtos) {
        try {
            List<Produto> salvos = service.salvarTodos(produtos);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", salvos);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Listar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                                                                        {
                                                                            "Status": 1,
                                                                            "Mensagem": "Requisição realizada com Sucesso!",
                                                                            "Dados": [
                                                                                {
                                                                                    "id": 1,
                                                                                    "informacao": {
                                                                                        "ativo": "Sim",
                                                                                        "descricao": "Mouse Logitech MX Master 3",
                                                                                        "unidade": "UNIDADE",
                                                                                        "refFornecedor": "REF1001",
                                                                                        "marca": "LOGITECH"
                                                                                    },
                                                                                    "impostos": {
                                                                                        "calcIcms": "Sim",
                                                                                        "tipSubst": "Subst. na compra e na venda",
                                                                                        "cstIpiEnt": "49-Outras Entradas",
                                                                                        "cstIpiSai": "99-Outras Saídas"
                                                                                    },
                                                                                    "grupo": {
                                                                                        "grupoProd": "DEPÓSITO CENTRAL"
                                                                                    },
                                                                                    "dtAlter": "19/07/2025",
                                                                                    "idExterno": null
                                                                                },
                                                                                {
                                                                                    "id": 2,
                                                                                    "informacao": {
                                                                                        "ativo": "Nao",
                                                                                        "descricao": "Caderno Tilibra Universitário 200 folhas",
                                                                                        "unidade": "CAIXA",
                                                                                        "refFornecedor": "REF1002",
                                                                                        "marca": "TILIBRA"
                                                                                    },
                                                                                    "impostos": {
                                                                                        "calcIcms": "Nao",
                                                                                        "tipSubst": "Revenda com subst. tributária (cálculo de Subst. na compra)",
                                                                                        "cstIpiEnt": "00-Entrada c/Recuperação de Crédito",
                                                                                        "cstIpiSai": "50-Saída Tributada"
                                                                                    },
                                                                                    "grupo": {
                                                                                        "grupoProd": "DEPÓSITO CENTRAL"
                                                                                    },
                                                                                    "dtAlter": "19/07/2025",
                                                                                    "idExterno": null
                                                                                }
                                                                            ]
                                                                        }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Lista de produtos vazia",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sem resultados",
                                    value = """
                                            {
                                              "Status": 2,
                                              "Mensagem": "Lista de Produtos Vazia!",
                                              "Dados": []
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @GetMapping("/buscar")
    public Map<String, Object> listarTodos() {
        try {
            List<Produto> lista = service.listarTodos();
            if (lista.isEmpty()) {
                return Util.estruturaAPI(2, "Lista de Produtos Vazia!", null);
            } else {
                return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Buscar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                                                                        {
                                                                            "Status": 1,
                                                                            "Mensagem": "Requisição realizada com Sucesso!",
                                                                            "Dados": {
                                                                                "id": 1,
                                                                                "informacao": {
                                                                                    "ativo": "Sim",
                                                                                    "descricao": "Mouse Logitech MX Master 3",
                                                                                    "unidade": "UNIDADE",
                                                                                    "refFornecedor": "REF1001",
                                                                                    "marca": "LOGITECH"
                                                                                },
                                                                                "impostos": {
                                                                                    "calcIcms": "Sim",
                                                                                    "tipSubst": "Subst. na compra e na venda",
                                                                                    "cstIpiEnt": "49-Outras Entradas",
                                                                                    "cstIpiSai": "99-Outras Saídas"
                                                                                },
                                                                                "grupo": {
                                                                                    "grupoProd": "DEPÓSITO CENTRAL"
                                                                                },
                                                                                "dtAlter": "19/07/2025",
                                                                                "idExterno": null
                                                                            }
                                                                        }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Não encontrado",
                                    value = """
                                            {
                                              "Status": 2,
                                              "Mensagem": "Produto não encontrado",
                                              "Dados": []
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @GetMapping("/buscar/{id}")
    public Map<String, Object> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<Produto> produto = service.buscarPorId(id);
            if (produto.isPresent()) {
                return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", produto.get());
            } else {
                return Util.estruturaAPI(2, "Produto não encontrado", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Hidden
    @DeleteMapping("/{id}")
    public Map<String, Object> excluir(@PathVariable Integer id) {
        try {
            service.excluir(id);
            return Util.estruturaAPI(1, "Produto excluído com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }
}
