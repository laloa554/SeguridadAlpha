package com.tutorial.crud.security.controller;

import com.tutorial.crud.correo.Correo;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.security.dto.JwtDto;
import com.tutorial.crud.security.dto.LoginUsuario;
import com.tutorial.crud.security.dto.NuevoUsuario;
import com.tutorial.crud.security.encryption.DesCipherUtil;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.jwt.JwtProvider;
import com.tutorial.crud.security.repository.UsuarioRepository;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.security.service.UsuarioService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController{
	@Value("${my.property.s}")
	private String ss;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UsuarioRepository u;

	/*@PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
       if(bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }else if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()) || usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Id y/o Email ya existente"), HttpStatus.BAD_REQUEST);
        }else {
        	 Usuario usuario =
                     new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                             passwordEncoder.encode(nuevoUsuario.getPassword()),nuevoUsuario.getEstatus(),nuevoUsuario.getCliente(),nuevoUsuario.getClienteTipo(),nuevoUsuario.getEstatusCobranza(),nuevoUsuario.getNivel1());
             Set<Rol> roles = new HashSet<>();
             roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
             if(nuevoUsuario.getRoles().contains("admin")) {
                 roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
             }
             usuario.setRoles(roles);
             usuarioService.save(usuario);
        }
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }*/
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
    	
    	String pass = DesCipherUtil.decrypt(loginUsuario.getPassword(),ss);
    	if(bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), pass));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<?>  forgotPassword(@RequestBody String datos) {
    	JwtProvider jwt=new JwtProvider();
    	if(!datos.isEmpty()) {
    		JSONObject json = new JSONObject(datos);
    		Optional<Usuario> x = u.findByNombreUsuario(json.getString("nombreUsuario"));
    		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$");
    		Matcher mEmail = pattern.matcher(x.get().getEmail().toLowerCase());
    		if(mEmail.matches()) {
    			Correo c = new Correo("alx591535@gmail.com","prueba542318",x.get().getEmail());
    			c.enviar_correo("lalo",jwt.generateTokenReset(x.get()));
    			return new ResponseEntity(new Mensaje("Se ha enviado un correo con el token"), HttpStatus.OK);
    		}else {
    			return new ResponseEntity(new Mensaje("No tiene asignado un correo o no es valido"), HttpStatus.OK);
    		}
    	}
    	return new ResponseEntity(new Mensaje("no ha mandado nada"), HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/new-password")
    public ResponseEntity<?> newPassword(@RequestBody String datos) {
    	if(!datos.isEmpty()) {
    		JSONObject json = new JSONObject(datos);
    		String token = json.getString("token");
    		JwtProvider jwt=new JwtProvider();
    		if(jwt.validateTokenReset(token)) {
    			String user = jwt.getNombreUsuarioFromTokenReset(token);
    			if(u.existsByNombreUsuario(user) && json.getString("password").equals(json.getString("confirmaPass"))) {
    				Optional<Usuario> x = u.findByNombreUsuario(user);
    				x.get().setPassword(passwordEncoder.encode(json.getString("password")));
    				u.save(x.get());
    				return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
    			}else {
    				return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
    			}
    		}else {
    			return new ResponseEntity(new Mensaje("El token no es valido"), HttpStatus.BAD_REQUEST);
    		}
    	}else {
    		return new ResponseEntity(new Mensaje("no ha mandado nada"), HttpStatus.BAD_REQUEST);
    	}
    	
    }
}
