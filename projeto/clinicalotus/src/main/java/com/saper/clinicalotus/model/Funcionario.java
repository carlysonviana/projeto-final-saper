package com.saper.clinicalotus.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import com.saper.clinicalotus.dto.FuncionarioRequestDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;

@Entity
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long Id;

    @Column(
            nullable = false,
            unique = true
    )

    private String cpf;

    @Column(nullable = false)
    private String nome;
    private String email;

    @Column(
            nullable = false,
            unique = true
    )
    private String login;

    @Column(nullable = false)
    private String senha;
    private LocalDate dataNascimento;
    private String telefone;
    private String celular;
    private LocalDate dataAdmissao;

    @ManyToOne(optional = true)
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "categoriaFuncionario_id")
    private CategoriaFuncionario categoriaFuncionario;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Medico medico;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "funcionario_role",
        joinColumns = @JoinColumn(name = "funcionario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles;

    public Funcionario(){

    }

    public Funcionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        this.nome = funcionarioRequestDTO.nome;
        this.cpf = funcionarioRequestDTO.cpf;
        this.email = funcionarioRequestDTO.email;
        this.login = funcionarioRequestDTO.login;
        this.senha = new BCryptPasswordEncoder().encode(funcionarioRequestDTO.senha);
        this.dataNascimento = funcionarioRequestDTO.dataNascimento;
        this.telefone = funcionarioRequestDTO.telefone;
        this.celular = funcionarioRequestDTO.celular;
        this.dataAdmissao = funcionarioRequestDTO.dataAdmissao;
        this.endereco = funcionarioRequestDTO.endereco;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public CategoriaFuncionario getCategoriaFuncionario() {
        return categoriaFuncionario;
    }

    public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
        this.categoriaFuncionario = categoriaFuncionario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setRoles(Set<Role>roles) { this.roles = roles; }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (dataAdmissao == null) {
            if (other.dataAdmissao != null)
                return false;
        } else if (!dataAdmissao.equals(other.dataAdmissao))
            return false;
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
