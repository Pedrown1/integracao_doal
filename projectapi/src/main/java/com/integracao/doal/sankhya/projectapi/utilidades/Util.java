package com.integracao.doal.sankhya.projectapi.utilidades;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
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
}
