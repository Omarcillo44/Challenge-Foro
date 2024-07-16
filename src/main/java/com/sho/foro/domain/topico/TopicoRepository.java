package com.sho.foro.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    //Page<Topico> findByActivoTrue(Pageable paginacion);

    @Query(value = "update topicos set titulo_topico = '', contenido_topico = ''" +
            " where id_topico =:id", nativeQuery = true)
    void borraTopico(@Param("id") String id);

}
