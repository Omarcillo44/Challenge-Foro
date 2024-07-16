package com.sho.foro.domain.topico;

import com.sho.foro.controller.DatosActualizarTopico;
import com.sho.foro.domain.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "topicos", schema = "foro", catalog = "")
public class Topico {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_topico")
    private long idTopico;
    @Basic
    @Column(name = "id_usuario")
    private long idUsuario;
    @Basic
    @Column(name = "contenido_topico")
    private String contenidoTopico;
    @Basic
    @Column(name = "curso")
    private String curso;
    @Basic
    @Column(name = "titulo_topico")
    private String tituloTopico;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario usuario) {

        this.idUsuario = usuario.getIdUsuario();
        this.tituloTopico = datosRegistroTopico.titulo();
        this.contenidoTopico = datosRegistroTopico.contenidoTopico();
        this.curso = datosRegistroTopico.curso();
    }

    public Topico() {

    }

    public long getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(long idTopico) {
        this.idTopico = idTopico;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContenidoTopico() {
        return contenidoTopico;
    }

    public void setContenidoTopico(String contenidoTopico) {
        this.contenidoTopico = contenidoTopico;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTituloTopico() {
        return tituloTopico;
    }

    public void setTituloTopico(String tituloTopico) {
        this.tituloTopico = tituloTopico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topico that = (Topico) o;

        if (idTopico != that.idTopico) return false;
        if (idUsuario != that.idUsuario) return false;
        if (contenidoTopico != null ? !contenidoTopico.equals(that.contenidoTopico) : that.contenidoTopico != null)
            return false;
        if (curso != null ? !curso.equals(that.curso) : that.curso != null) return false;
        if (tituloTopico != null ? !tituloTopico.equals(that.tituloTopico) : that.tituloTopico != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idTopico ^ (idTopico >>> 32));
        result = 31 * result + (int) (idUsuario ^ (idUsuario >>> 32));
        result = 31 * result + (contenidoTopico != null ? contenidoTopico.hashCode() : 0);
        result = 31 * result + (curso != null ? curso.hashCode() : 0);
        result = 31 * result + (tituloTopico != null ? tituloTopico.hashCode() : 0);
        return result;
    }


    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.tituloTopico() != null) {
            this.tituloTopico = datosActualizarTopico.tituloTopico();
        }
        if (datosActualizarTopico.contenidoTopico() != null) {
            this.contenidoTopico = datosActualizarTopico.contenidoTopico();
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = datosActualizarTopico.curso();
        }
    }
        public void eliminaContenidoTopio() {
            this.tituloTopico = " ";
            this.contenidoTopico = " ";
            this.curso = " ";
        }

}
