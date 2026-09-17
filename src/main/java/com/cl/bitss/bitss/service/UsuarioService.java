package com.cl.bitss.bitss.service;

import com.cl.bitss.bitss.model.Usuario;
import com.cl.bitss.bitss.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // GET
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // GET ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // GET CORREO
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // POST
    public Usuario registrarUsuario(Usuario usuario) {
        //VALIDACION
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        // GUARDAR
        return usuarioRepository.save(usuario);
    }

    // SET
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setApellido(usuarioActualizado.getApellido());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        return usuarioRepository.save(usuario);
    }

    // DELETE
    public void eliminarUsuario(Long id) {
        //VALIDACION
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}