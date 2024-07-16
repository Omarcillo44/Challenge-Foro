package com.sho.foro.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios", schema = "foro", catalog = "")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario")
    private long idUsuario;
    @Basic
    @Column(name = "correo_usuario")
    private String correoUsuario;
    @Basic
    @Column(name = "pass_usuario")
    private String passUsuario;



    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario that = (Usuario) o;

        if (idUsuario != that.idUsuario) return false;
        if (correoUsuario != null ? !correoUsuario.equals(that.correoUsuario) : that.correoUsuario != null)
            return false;
        if (passUsuario != null ? !passUsuario.equals(that.passUsuario) : that.passUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUsuario ^ (idUsuario >>> 32));
        result = 31 * result + (correoUsuario != null ? correoUsuario.hashCode() : 0);
        result = 31 * result + (passUsuario != null ? passUsuario.hashCode() : 0);
        return result;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return passUsuario;
    }

    @Override
    public String getUsername() {
        return correoUsuario;
    }
}
