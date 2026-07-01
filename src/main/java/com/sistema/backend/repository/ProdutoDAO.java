package com.sistema.backend.repository;

import com.sistema.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {

    /**
     * Busca produtos cujo nome, plataforma ou gênero contenham o termo informado
     * (ignorando maiúsculas/minúsculas), ou cujo id corresponda ao termo (busca por código).
     */
    @Query("SELECT p FROM Produto p WHERE " +
           "LOWER(p.nome) LIKE LOWER(CONCAT('%', :busca, '%')) OR " +
           "LOWER(p.plataforma) LIKE LOWER(CONCAT('%', :busca, '%')) OR " +
           "LOWER(p.genero) LIKE LOWER(CONCAT('%', :busca, '%')) OR " +
           "CAST(p.id AS string) LIKE CONCAT('%', :busca, '%')")
    List<Produto> buscar(@Param("busca") String busca);
}
