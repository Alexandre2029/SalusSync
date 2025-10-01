package com.tcc.SalusSync.controller;

import com.tcc.SalusSync.dto.AuthenticationDTO;
import com.tcc.SalusSync.dto.LoginResponseDTO;
import com.tcc.SalusSync.dto.RegisterDTO;
import com.tcc.SalusSync.dto.RegisterMedicoDTO;
import com.tcc.SalusSync.model.Medico;
import com.tcc.SalusSync.model.Usuario;
import com.tcc.SalusSync.repository.MedicoRepository;
import com.tcc.SalusSync.repository.UsuarioRepository;
import com.tcc.SalusSync.security.TokenService;
import com.tcc.SalusSync.service.MedicoService;
import com.tcc.SalusSync.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    MedicoService medicoService;

    @Autowired
    MedicoRepository medicoRepository;



    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data){
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var auth = authenticationManager.authenticate(usernamePassword);
//        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var user = (UserDetails) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register/medico")
    public ResponseEntity<String> registerMedico(@RequestBody RegisterMedicoDTO data){
        if(medicoRepository.findByLogin(data.login()).isPresent()){
            return ResponseEntity.badRequest().body("Login já existe");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        Medico newMedico = new Medico(
                data.nome(),
                data.login(),
                encryptedPassword,
                data.role(),
                data.cpf(),
                data.crm(),
                data.especializacao()

        );

      return   medicoService.castrarMedico(newMedico);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO data){
        if(repository.findByLogin(data.login()).isPresent()){
            return ResponseEntity.badRequest().body("Login já existe");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        Usuario newUser = new Usuario(
                data.login(),
                encryptedPassword,
                data.role(),
                data.cpf(),
                data.contato(),
                data.nome(),
                data.altura(),
                data.peso()
        );

        return   usuarioService.cadastrar(newUser);
    }



}
