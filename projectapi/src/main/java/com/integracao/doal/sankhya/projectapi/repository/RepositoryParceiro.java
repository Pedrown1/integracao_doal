package com.integracao.doal.sankhya.projectapi.repository;

import com.integracao.doal.sankhya.projectapi.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryParceiro extends JpaRepository<Parceiro, Integer> {}
