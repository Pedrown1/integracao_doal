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

import java.util.*;

import static com.integracao.doal.sankhya.projectapi.utilidades.Util.montarRespostaParceiro;

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
                                                    "informacao": {
                                                        "ativo": "S",
                                                        "tipoPessoa": "Fisica",
                                                        "nome": "Carlos Eduardo Martins",
                                                        "cnpjCpf": "174.712.130-52"
                                                    },
                                                    "observacao": "Parceiro Integrado do E-commerce",
                                                    "endereco": {
                                                        "codCid": 2345
                                                    },
                                                    "dtCadastra": "19/07/2025",
                                                    "fiscal": {
                                                        "temIPI": "Sim",
                                                        "classifIcms": "Revendedor"
                                                    },
                                                    "idExterno": 101,
                                                    "credito": {
                                                        "situacao": "Excelente",
                                                        "LimCred": 750.45,
                                                        "bloquearPrazo": "Nao"
                                                    },
                                                    "id": 1,
                                                    "dtAlter": "19/07/2025"
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
                                                "Mensagem": "TipoPessoa é obrigatório",
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
            Map<String, Object> parceiroMap = Util.montarRespostaParceiro(salvo);
            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", parceiroMap);
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
                                                         "informacao": {
                                                             "ativo": "S",
                                                             "tipoPessoa": "Fisica",
                                                             "nome": "Carlos Eduardo Martins",
                                                             "cnpjCpf": "174.712.130-52"
                                                         },
                                                         "observacao": "Parceiro Integrado do E-commerce",
                                                         "endereco": {
                                                             "codCid": 2345
                                                         },
                                                         "dtCadastra": "19/07/2025",
                                                         "fiscal": {
                                                             "temIPI": "Sim",
                                                             "classifIcms": "Revendedor"
                                                         },
                                                         "idExterno": 101,
                                                         "credito": {
                                                             "situacao": "Excelente",
                                                             "LimCred": 750.45,
                                                             "bloquearPrazo": "Nao"
                                                         },
                                                         "id": 1,
                                                         "dtAlter": "19/07/2025"
                                                     },
                                                     {
                                                         "informacao": {
                                                             "ativo": "S",
                                                             "tipoPessoa": "Fisica",
                                                             "nome": "Ana Beatriz Souza",
                                                             "cnpjCpf": "468.295.380-00"
                                                         },
                                                         "observacao": "Parceiro Integrado do E-commerce",
                                                         "endereco": {
                                                             "codCid": 1020
                                                         },
                                                         "dtCadastra": "19/07/2025",
                                                         "fiscal": {
                                                             "temIPI": "Nao",
                                                             "classifIcms": "Isento de ICMS"
                                                         },
                                                         "idExterno": 102,
                                                         "credito": {
                                                             "situacao": "Bom",
                                                             "LimCred": 523.00,
                                                             "bloquearPrazo": "Sim"
                                                         },
                                                         "id": 2,
                                                         "dtAlter": "19/07/2025"
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
        List<Object> listaFormatada = new ArrayList<>();

        try {
            List<Parceiro> salvos = service.salvarTodos(parceiros);

            for (Parceiro p : salvos) {
                Object objFormatado = montarRespostaParceiro(p);
                listaFormatada.add(objFormatado);
            }

            return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", listaFormatada);
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
                                                         "informacao": {
                                                             "ativo": "S",
                                                             "tipoPessoa": "Fisica",
                                                             "nome": "Carlos Eduardo Martins",
                                                             "cnpjCpf": "174.712.130-52"
                                                         },
                                                         "observacao": "Parceiro Integrado do E-commerce",
                                                         "endereco": {
                                                             "codCid": 2345
                                                         },
                                                         "dtCadastra": "19/07/2025",
                                                         "fiscal": {
                                                             "temIPI": "Sim",
                                                             "classifIcms": "Revendedor"
                                                         },
                                                         "idExterno": 101,
                                                         "credito": {
                                                             "situacao": "Excelente",
                                                             "LimCred": 750.45,
                                                             "bloquearPrazo": "Nao"
                                                         },
                                                         "id": 1,
                                                         "dtAlter": "19/07/2025"
                                                     },
                                                     {
                                                         "informacao": {
                                                             "ativo": "S",
                                                             "tipoPessoa": "Fisica",
                                                             "nome": "Ana Beatriz Souza",
                                                             "cnpjCpf": "468.295.380-00"
                                                         },
                                                         "observacao": "Parceiro Integrado do E-commerce",
                                                         "endereco": {
                                                             "codCid": 1020
                                                         },
                                                         "dtCadastra": "19/07/2025",
                                                         "fiscal": {
                                                             "temIPI": "Nao",
                                                             "classifIcms": "Isento de ICMS"
                                                         },
                                                         "idExterno": 102,
                                                         "credito": {
                                                             "situacao": "Bom",
                                                             "LimCred": 523.00,
                                                             "bloquearPrazo": "Sim"
                                                         },
                                                         "id": 2,
                                                         "dtAlter": "19/07/2025"
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
        List<Object> listaFormatada = new ArrayList<>();
        try {
            List<Parceiro> lista = service.listarTodos();

            if (lista.isEmpty()) {
                return Util.estruturaAPI(2, "Lista de Parceiros Vazia!", null);
            } else {
                for(Parceiro parceiro : lista){
                    Object objFormatado = montarRespostaParceiro(parceiro);
                    listaFormatada.add(objFormatado);
                }
                return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", listaFormatada);
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
                                                     "informacao": {
                                                         "ativo": "S",
                                                         "tipoPessoa": "Fisica",
                                                         "nome": "Carlos Eduardo Martins",
                                                         "cnpjCpf": "174.712.130-52"
                                                     },
                                                     "observacao": "Parceiro Integrado do E-commerce",
                                                     "endereco": {
                                                         "codCid": 2345
                                                     },
                                                     "dtCadastra": "19/07/2025",
                                                     "fiscal": {
                                                         "temIPI": "Sim",
                                                         "classifIcms": "Revendedor"
                                                     },
                                                     "idExterno": 101,
                                                     "credito": {
                                                         "situacao": "Excelente",
                                                         "LimCred": 750.45,
                                                         "bloquearPrazo": "Nao"
                                                     },
                                                     "id": 1,
                                                     "dtAlter": "19/07/2025"
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
                Map<String, Object> parceiroMap = montarRespostaParceiro(parceiro.get());
                return Util.estruturaAPI(1, "Requisição realizada com Sucesso!", parceiroMap);
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

