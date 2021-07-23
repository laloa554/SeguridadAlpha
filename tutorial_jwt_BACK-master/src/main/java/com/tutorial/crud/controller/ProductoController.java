package com.tutorial.crud.controller;

import com.tutorial.crud.aopDao.endpoints;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.configuracion;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.security.service.UsuarioService;
import com.tutorial.crud.service.*;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductoController {
	
	@Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    RolService rolService;
	
	endpoints e = new endpoints();
    
    @Autowired
    configuracionService configuracionService;
    
    @GetMapping("/getConsultaMiembroID")
    public void getConsultaMiembroID(@Valid @RequestBody String id ) { 
    	configuracion o = configuracionService.findByServiceName("getConsultaMiembroID").get();
    	e.conectaApiClubGET(o.getEndpointAlpha() + id);
    }
    
    @PostMapping("/getRecibosCliente")
    public void getRecibosCliente() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":2227,\n"
    			+ "\"Fecha\":\"12/27/2019\",\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getRecibosCliente").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    @PostMapping("/getPedido")
    public void getPedido() { 	
    	String body2 = "{\n"
    			+ "\"IdCliente\":60887,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getPedido").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
   @PostMapping("/getClientesById")
    public void getClientesById() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":60887,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getClientesById").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    @PostMapping("/RegistraOV")
    public void RegistraOV() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":60388,\n"
    			+ "\"IDClub\":4,\n"
    			+ "\"Cantidad\":1,\n"
    			+ "\"IDProductoServicio\":1558,\n"
    			+ "\"Observaciones\":\"\",\n"
    			+ "\"DescuentoPorciento\":0,\n"
    			+ "\"FechaInicio\":\"2020-07-01 00:00:00\",\n"
    			+ "\"FechaFin\":\"2021-06-30 00:00:00\",\n"
    			+ "\"CobroProporcional\":0,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("RegistraOV").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    @PostMapping("/getLigaPago")
    public void getLigaPago() {
    	String body2 = "{\n"
    			+ "\"IdCliente\":60887,\n"
    			+ "\"Token\":\"10A4DF47-EAC7-45BD-8E2E-684989B4F0B0\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getLigaPago").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    
    @PostMapping("/getPedidoById")
    public void getPedidoById() {
    	String body2 = "{\n"
    			+ "\"NoPedido\":3,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getPedidoById").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    @PostMapping("/AsignaLigaPago")
    public void AsignaLigaPago() {
    	String body2 = "{\n"
    			+ "\"NoPedido\":108,\n"
    			+ "\"Url\":\"https://wppsandbox.mit.com.mx/i/3EWWGWUM\",\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("AsignaLigaPago").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    @PostMapping("/RegistraPago")
    public void RegistraPago() {
    	String body2 = "{\n"
    			+ "\"NoPedido\":13798,\n"
    			+ "\"Monto\":1914.00,\n"
    			+ "\"Notarjeta\":\"1111\",\n"
    			+ "\"FolioInterbancario\":\"123456789\",\n"
    			+ "\"NoAutorizacion\":\"123456789\",\n"
    			+ "\"FechaPago\":\"18/04/2021\",\n"
    			+ "\"HoraPago\":\"14:14:22.667\",\n"
    			+ "\"TitularCuenta\":\"Pascual\",\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("RegistraPago").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
    @PostMapping("/ValidaAccesoClub")
    public void ValidaAccesoClub() {
    	String body2 = "{\n"
    			+ "\"Membresia\":\"1066250200\",\n"
    			+ "\"Club\":\"Club Alpha 2\",\n"
    			+ "\"Token\":\"D42CD61C-9BD3-4359-8401-A223A35BDFA5\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("ValidaAccesoClub").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    
   @PostMapping("/getConfig")
    public void getConfig() {
    	String body2 = "{\n"
    			+ "\"Equipo\":\"AzureServer\",\n"
    			+ "\"Token\":\"10A4DF47-EAC7-45BD-8E2E-684989B4F0B0\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getConfig").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
   
   	@PostMapping("/ValidaEstatus")
    public void ValidaEstatus() {
    	String body2 = "{\n"
    			+ "\"Membresia\":\"1066250200\",\n"
    			+ "\"Token\":\"8CA09B3D-9AAB-4C88-8BED-A429EC5FB842\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("ValidaEstatus").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
   @PostMapping("/getMiembroTG")
    public void getMiembroTG() {
    	String body2 = "{\n"
    			+ "\"Id\":\"27610\",\n"
    			+ "\"Token\":\"10A4DF47-EAC7-45BD-8E2E-684989B4F0B0\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getMiembroTG").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
   
    @PostMapping("/getPasesById")
    public void getPasesById() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":9416,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	configuracion o = configuracionService.findByServiceName("getPasesById").get();
    	e.conectaApiClubPOST(body2,o.getEndpointAlpha());
    }
    

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUsers")
    public ResponseEntity<?> addUsers(@RequestBody String id){
    	if(!id.isEmpty()) {
    		JSONObject json = new JSONObject(id);
    		id = json.getString("id");
    		configuracion o = configuracionService.findByServiceName("getConsultaMiembroID").get();
        	String j = e.conectaApiClubGET(o.getEndpointAlpha() + id);
        	json = new JSONObject(j);
        	String idCliente, membresia,email, fechadenacimiento,x[];
        	idCliente = String.valueOf(json.get("IdCliente"));
        	membresia = String.valueOf(json.get("NoMembresia"));
        	email = String.valueOf(json.get("EMail"));
        	x = String.valueOf(json.get("FechaNacimiento")).split("T");
        	fechadenacimiento = x[0];
        	if(usuarioService.existsByNombreUsuario(idCliente) || usuarioService.existsByEmail(email)) {
                return new ResponseEntity(new Mensaje("Id y/o Email ya existente"), HttpStatus.BAD_REQUEST);
            }else {
            	Usuario usuario = new Usuario(idCliente, membresia, email,passwordEncoder.encode(idCliente + fechadenacimiento));
            	Set<Rol> roles = new HashSet<>();
            	roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            	usuario.setRoles(roles);
            	usuarioService.save(usuario);
            	return new ResponseEntity(new Mensaje("usuario agregado"), HttpStatus.OK);
            }
        }   
        return new ResponseEntity(new Mensaje("error, no hay id"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}