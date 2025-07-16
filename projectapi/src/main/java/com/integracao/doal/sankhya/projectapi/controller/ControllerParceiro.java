package com.integracao.doal.sankhya.projectapi.controller;

import com.integracao.doal.sankhya.projectapi.model.Parceiro;
import com.integracao.doal.sankhya.projectapi.service.ParceiroService;
import com.integracao.doal.sankhya.projectapi.utilidades.Util;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v1/parceiros")
@Tag(name = "Parceiro", description = "API para gerenciamento de parceiros")
public class ControllerParceiro {

    @Autowired
    private ParceiroService service;

    @Operation(summary = "Cadastrar parceiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parceiro cadastrado com sucesso",
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
                                                "nome": "TechNova Ltda",
                                                "ativo": "S",
                                                "tipo": "Cliente",
                                                "tipoPessoa": "J",
                                                "cnpjCpf": "11.111.111/0001-01",
                                                "idExterno": 101
                                              }
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Erro",
                                    value = """
                                            {
                                              "Status": 2,
                                              "Mensagem": "Id Externo é obrigatório",
                                              "Dados": []
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    ))
    })
    @PostMapping("/cadastrar")
    public Map<String, Object> criar(@RequestBody Parceiro parceiro) {
        try {
            Parceiro salvo = service.salvar(parceiro);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", salvo);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Cadastrar vários parceiros em lote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parceiros cadastrados com sucesso",
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
                                                  "id": 2,
                                                  "nome": "TechNova Ltda",
                                                  "ativo": "S",
                                                  "tipo": "Cliente",
                                                  "tipoPessoa": "J",
                                                  "cnpjCpf": "11.111.111/0001-01",
                                                  "idExterno": 101
                                                },
                                                {
                                                  "id": 3,
                                                  "nome": "Lucas Almeida",
                                                  "ativo": "S",
                                                  "tipo": "Cliente",
                                                  "tipoPessoa": "F",
                                                  "cnpjCpf": "123.456.789-01",
                                                  "idExterno": 201
                                                }
                                              ]
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Erro de validação",
                                    value = """
                                            {
                                              "Status": 2,
                                              "Mensagem": "CNPJ/CPF é obrigatório",
                                              "Dados": []
                                            }
                                            """
                            ),
                            schema = @Schema(implementation = Object.class)
                    ))
    })
    @PostMapping("/cadastrar/lote")
    public Map<String, Object> criarLote(@RequestBody List<Parceiro> parceiros) {
        try {
            List<Parceiro> salvos = service.salvarTodos(parceiros);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", salvos);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Listar todos os parceiros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de parceiros retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                                            {
                                                "Status": 1,
                                                "Mensagem": "Requisição realizada com Sucesso!",
                                                "Dados": [
                                                    {
                                                        "id": 1,
                                                        "nome": "TechNova Ltda",
                                                        "ativo": "S",
                                                        "tipo": "Cliente",
                                                        "tipoPessoa": "J",
                                                        "cnpjCpf": "11.111.111/0001-01",
                                                        "idExterno": 101.00
                                                    },
                                                    {
                                                        "id": 2,
                                                        "nome": "Lucas Almeida",
                                                        "ativo": "S",
                                                        "tipo": "Cliente",
                                                        "tipoPessoa": "F",
                                                        "cnpjCpf": "123.456.789-01",
                                                        "idExterno": 201.00
                                                    }
                                                ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Lista de parceiros vazia",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Lista Vazia",
                                    value = """
                                            {
                                                "Status": 2,
                                                "Mensagem": "Lista de Parceiros Vazia!",
                                                "Dados": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/buscar")
    public Map<String, Object> listarTodos() {
        try {
            List<Parceiro> lista = service.listarTodos();

            if (lista.isEmpty()) {
                return Util.estruturaAPI(2, "Lista de Parceiros Vazia!", null);
            } else {
                return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }

    @Operation(summary = "Buscar parceiro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parceiro encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sucesso",
                                    value = """
                                            {
                                                "Status": 1,
                                                "Mensagem": "Requisição realizada com Sucesso!",
                                                "Dados": {
                                                    "id": 1,
                                                    "nome": "TechNova Ltda",
                                                    "ativo": "S",
                                                    "tipo": "Cliente",
                                                    "tipoPessoa": "J",
                                                    "cnpjCpf": "11.111.111/0001-01",
                                                    "idExterno": 101.00
                                                }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Parceiro não encontrado",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Não encontrado",
                                    value = """
                                            {
                                                "Status": 2,
                                                "Mensagem": "Parceiro não encontrado",
                                                "Dados": []
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/buscar/{id}")
    public Map<String, Object> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<Parceiro> parceiro = service.buscarPorId(id);
            if (parceiro.isPresent()) {
                return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", parceiro.get());
            } else {
                return Util.estruturaAPI(2, "Parceiro não encontrado", null);
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
            return Util.estruturaAPI(1, "Parceiro excluído com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.estruturaAPI(2, e.getMessage(), null);
        }
    }


}

