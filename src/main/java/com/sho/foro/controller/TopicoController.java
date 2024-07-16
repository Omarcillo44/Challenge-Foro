package com.sho.foro.controller;

import com.sho.foro.domain.topico.DatosRegistroTopico;
import com.sho.foro.domain.topico.Topico;
import com.sho.foro.domain.topico.TopicoRepository;
import com.sho.foro.domain.usuarios.Usuario;
import com.sho.foro.domain.usuarios.UsuarioRepository;
import com.sho.foro.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {


    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Validated DatosRegistroTopico datosRegistroTopico,
                                          @RequestHeader("Authorization") String authorization,
                                          UriComponentsBuilder uriComponentsBuilder) {
        // Valida el token JWT y obtiene el usuario autenticado
        var token = authorization.replace("Bearer ", "");;
       var usuarioAutenticado = tokenService.getSubject(token);

       Usuario usuario = (Usuario) usuarioRepository.findByCorreoUsuario(usuarioAutenticado);
        System.out.println(usuarioAutenticado);
       //Usuario usuario = (Usuario) usuarioRepository.findByCorreoUsuario("sho@algo.com");

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getIdUsuario(),
                topico.getIdTopico(), topico.getTituloTopico(), topico.getContenidoTopico(), topico.getCurso());

        // Crea la URI para el nuevo tópico
        URI location = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topico.getIdTopico()).toUri();

        return ResponseEntity.created(location).body(datosRespuestaTopico);
    }

    // DELETE real
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminaContenidoTopio();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getIdUsuario(),
                topico.getIdTopico(), topico.getTituloTopico(), topico.getContenidoTopico(), topico.getCurso());
        return ResponseEntity.ok(datosRespuestaTopico);
    }


    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.idTopico());
        topico.actualizarDatos(datosActualizarTopico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getIdUsuario(),
                topico.getIdTopico(), topico.getTituloTopico(), topico.getContenidoTopico(), topico.getCurso());
        return ResponseEntity.ok(datosRespuestaTopico);
    }

}
