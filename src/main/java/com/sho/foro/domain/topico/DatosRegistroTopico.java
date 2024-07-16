package com.sho.foro.domain.topico;

import com.sho.foro.domain.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.w3c.dom.Text;

public record DatosRegistroTopico(
        @NotNull String titulo,
        @NotBlank String contenidoTopico,
        @NotBlank String curso) {
}
