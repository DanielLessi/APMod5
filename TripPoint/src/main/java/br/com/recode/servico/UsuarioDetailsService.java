package br.com.recode.servico;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.recode.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService{
	
	private final UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return Optional.ofNullable(usuarioRepositorio.findByUsername(username))
				.orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
	}
}
