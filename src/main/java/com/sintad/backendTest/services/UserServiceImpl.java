package com.sintad.backendTest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sintad.backendTest.models.Usuario;
import com.sintad.backendTest.repositories.IUsuarioRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	IUsuarioRepository usuarioRepository;

	@Transactional
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email no encontrado: " + email));

		return UserService.build(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username no encontrado: " + username));

		return UserService.build(usuario);
	}
}
