package com.integracao.doal.sankhya.projectapi.repository;

import com.integracao.doal.sankhya.projectapi.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepositoryEstoque extends JpaRepository<Estoque, Integer> {
    List<Estoque> findByCodProduto(Integer codProduto);
}
