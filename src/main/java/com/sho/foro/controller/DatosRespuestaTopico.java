package com.sho.foro.controller;

public record DatosRespuestaTopico(Long idUsuario,
                                   Long idTopico,
                                   String tituloTopico,
                                   String contenidoTopico,
                                   String curso) {
}
