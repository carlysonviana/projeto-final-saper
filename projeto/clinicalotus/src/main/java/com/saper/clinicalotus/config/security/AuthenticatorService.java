package com.saper.clinicalotus.config.security;

import com.saper.clinicalotus.model.Funcionario;
import com.saper.clinicalotus.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticatorService implements UserDetailsService {

    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByLogin(username);

        if(funcionarioOptional.isEmpty()){
            throw new UsernameNotFoundException("Funcionário não encontrado!");
        }
        else{
            return funcionarioOptional.get();
        }
    }
}
