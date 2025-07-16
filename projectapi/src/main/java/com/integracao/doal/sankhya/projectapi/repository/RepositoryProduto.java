package com.integracao.doal.sankhya.projectapi.repository;

import com.integracao.doal.sankhya.projectapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProduto extends JpaRepository<Produto, Integer> {}
