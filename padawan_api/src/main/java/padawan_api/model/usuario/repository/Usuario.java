package padawan_api.model.usuario.repository;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import padawan_api.model.usuario.dto.AlterarRegistroDeUsuariosDTO;
import padawan_api.model.usuario.dto.AlterarSenhaUsuarioLogadoDTO;
import padawan_api.model.usuario.dto.EfetuarLoginDTO;
import padawan_api.model.usuario.dto.RegistrarUsuarioDTO;
import padawan_api.services.email.dto.RecupararSenhaPorEmailDTO;



import org.mindrot.jbcrypt.BCrypt;

//import java.util.Collection;
//import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")

public class Usuario {

//public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome_completo")
    private String nomeCompleto;

   
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nome_login")
    private String nomeLogin;

    @Column(name = "senha")
    @Size(max = 100)
    private String senha;
    
    @NotNull
    @Column(name = "situacao")
    private Boolean ativo;

    @Column(name = "hash")
    private String hash;



    public Usuario(RegistrarUsuarioDTO dados) {
        this.nomeCompleto = dados.nomeCompleto();
        this.email = dados.email();
        this.nomeLogin = dados.nomeLogin();
        this.senha = BCrypt.hashpw(dados.senha(), BCrypt.gensalt());
        this.hash = null;
        this.ativo = true;
    }

    public void atualizarUsuarioClassUsuarioJPA(AlterarRegistroDeUsuariosDTO dados) throws Exception {
        
        if(this.ativo == true){

            if(dados != null){
                 this.nomeLogin = dados.nomeLogin();
                 this.nomeCompleto = dados.nomeCompleto();
                 this.email = dados.email();
                 this.ativo = dados.ativo();
            }
            else {
                throw new Exception("Campo 'novo login' não deve ser nulo");
            }

        }

        else if(this.ativo == false && dados.ativo() == true){

           if(dados != null){
            
            this.nomeLogin = dados.nomeLogin();
            this.nomeCompleto = dados.nomeCompleto();
            this.email = dados.email();
            this.ativo = dados.ativo();

           }
           else{

            throw new Exception("Campo 'novo login' não deve ser nulo");

           }
        
        }
        else{

            throw new Exception("Usuário inativo");
        }
        
    
    }


    public void alterarSenhaClassUsuarioJPA(AlterarSenhaUsuarioLogadoDTO dados) throws Exception {
        

            if (dados.senhaAtual() != null && BCrypt.checkpw(dados.senhaAtual(), this.senha)) {
                
                if(dados.novaSenha() != null && dados.confirmarSenha() != null && dados.novaSenha().equals(dados.confirmarSenha())){

                     this.senha = BCrypt.hashpw(dados.novaSenha(), BCrypt.gensalt());

                }else{

                    throw new Exception("Divergências na nova senha e confirmação de senha");
                }
            }

            else{

                throw new Exception("Senha atual incorreta");
            }

        
         
        
    }

    public void efetuarLoginClassUsuarioJPA(EfetuarLoginDTO dados) throws Exception {
        
        if (dados.login() != null && dados.login().equals(this.nomeLogin)) {
            if (dados.senha() != null && BCrypt.checkpw(dados.senha(), this.senha)) {

        
                System.out.println("Login correto");
            }

            else{

                throw new Exception("Senha incorreta");
            }

        }
         
    }

    public void atualizarSenhaViaEmailClassJPA(RecupararSenhaPorEmailDTO dados) throws Exception{

        if(dados.novaSenha() != null && dados.confirmarSenha() != null && dados.novaSenha().equals(dados.confirmarSenha())){

              this.senha = BCrypt.hashpw(dados.novaSenha(), BCrypt.gensalt());
              this.hash = null;
         }
         else{

                throw new Exception("Divergências na nova senha e confirmação de senha");
            }
        

    }


    public void inativar(){
        this.ativo = false;
    }


    public void ativar(){
        this.ativo = true;
    }

    public boolean isSituacao(){
        return this.ativo;
    }



    /*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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

     */

}


