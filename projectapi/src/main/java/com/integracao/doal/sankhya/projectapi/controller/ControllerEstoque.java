package com.integracao.doal.sankhya.projectapi.controller;

import com.integracao.doal.sankhya.projectapi.model.Estoque;
import com.integracao.doal.sankhya.projectapi.service.EstoqueService;
import com.integracao.doal.sankhya.projectapi.utilidades.Util;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estoques")
@Tag(name = "Estoque", description = "API para gerenciamento de Estoques")
public class ControllerEstoque {

    @Autowired
    private EstoqueService service;

    @Operation(summary = "Cadastrar um estoque")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque cadastrado com sucesso",
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
                    "codProduto": 101,
                    "qtdProduto": 10
                  }
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Pendência: quantidade é obrigatória",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Pendência",
                                    value = """
                {
                  "Status": 2,
                  "Mensagem": "Quantidade é obrigatória",
                  "Dados": []
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @PostMapping("/cadastrar")
    public Map<String, Object> criar(@RequestBody Estoque estoque) {
        try {
            Estoque salvo = service.salvar(estoque);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", salvo);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Cadastrar múltiplos estoques em lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoques cadastrados com sucesso",
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
                      "id": 4,
                      "codProduto": 102,
                      "qtdProduto": 5.0
                    },
                    {
                      "id": 5,
                      "codProduto": 103,
                      "qtdProduto": 8.75
                    }
                  ]
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Pendência: código do produto é obrigatório",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Pendência",
                                    value = """
                {
                  "Status": 2,
                  "Mensagem": "Código do Produto é obrigatório",
                  "Dados": []
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @PostMapping("/cadastrar/lote")
    public Map<String, Object> criarLote(@RequestBody List<Estoque> estoques) {
        try {
            List<Estoque> salvos = service.salvarTodos(estoques);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", salvos);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Listar todos os estoques")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estoques retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                {
                  "Status": 1,
                  "Mensagem": "Requisição realizada com sucesso!",
                  "Dados": [
                    {
                      "id": 1,
                      "codProduto": 101,
                      "qtdProduto": 10.00
                    },
                    {
                      "id": 2,
                      "codProduto": 102,
                      "qtdProduto": 5.00
                    }
                  ]
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Lista de estoques vazia",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Lista vazia",
                                    value = """
                {
                  "Status": 2,
                  "Mensagem": "Lista de Estoques Vazia!",
                  "Dados": []
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @GetMapping("/buscar")
    public Map<String, Object> listarTodos(@RequestParam(value = "codprod", required = false) Integer codProduto) {
        try {
            List<Estoque> lista;

            if (codProduto != null) {
                lista = service.buscarPorCodProduto(codProduto);
            } else {
                lista = service.listarTodos();
            }

            if (lista.isEmpty()) {
                return Util.estruturaAPI(2, "Lista de Estoques Vazia!", null);
            } else {
                return Util.estruturaAPI(1, "Requisição realizada com sucesso!", lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }


    @Operation(summary = "Buscar estoque por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                {
                  "Status": 1,
                  "Mensagem": "Requisição realizada com sucesso!",
                  "Dados": {
                    "id": 1,
                    "codProduto": 101,
                    "qtdProduto": 10.00
                  }
                }
                """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Não encontrado",
                                    value = """
                {
                  "Status": 2,
                  "Mensagem": "Estoque não encontrado",
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
            Optional<Estoque> estoque = service.buscarPorId(id);
            if (estoque.isPresent()) {
                return Util.estruturaAPI(1, "Requisição realizada com sucesso!", estoque.get());
            } else {
                return Util.estruturaAPI(2, "Estoque não encontrado", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Atualiza a quantidade do produto no estoque",
            description = "Atualiza a quantidade do produto identificado pelo código (codProduto) no estoque. " +
                    "O valor da nova quantidade deve ser passado como parâmetro na URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantidade atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
            {
              "Status": 1,
              "Mensagem": "Quantidade atualizada com sucesso!",
              "Dados": {
                "id": 1,
                "codProduto": 101,
                "qtdProduto": 50
              }
            }
            """),
                            schema = @Schema(implementation = Object.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado no estoque",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
            {
              "Status": 2,
              "Mensagem": "Produto não encontrado no estoque",
              "Dados": []
            }
            """),
                            schema = @Schema(implementation = Object.class)
                    )
            )
    })
    @PutMapping("/atualizar/{codProduto}")
    public Map<String, Object> atualizarQuantidade(@PathVariable Integer codProduto, @RequestParam BigDecimal novaQuantidade) {
        try {
            Estoque atualizado = service.atualizarQuantidade(codProduto, novaQuantidade);
            return Util.estruturaAPI(1, "Quantidade atualizada com sucesso!", atualizado);
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
            return Util.estruturaAPI(1, "Estoque excluído com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }
}
