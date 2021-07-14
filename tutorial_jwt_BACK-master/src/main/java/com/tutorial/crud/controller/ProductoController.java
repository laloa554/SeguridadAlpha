package com.tutorial.crud.controller;

import com.tutorial.crud.aopDao.endpoints;
import com.tutorial.crud.service.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductoController {
	
	endpoints e = new endpoints();

    @Autowired
    ProductoService productoService;
    @Autowired
    configuracionService configuracionService;
    
    @GetMapping("/getConsultaMiembroID")
    public void getConsultaMiembroID(@Valid @RequestBody String id ) { 
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Miembro/" + id;
    	//String url = configuracionService.findByServiceName("getConsultaMiembroID").toString();
    	//System.out.println(url);
    	e.conectaApiClubGET(url);
    }
    
    @PostMapping("/getRecibosCliente")
    public void getRecibosCliente() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":2227,\n"
    			+ "\"Fecha\":\"12/27/2019\",\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Pagos/GetRecibosByClienteFecha";
    	e.conectaApiClubPOST(body2,url);
    }
    
    @PostMapping("/getPedido")
    public void getPedido() { 	
    	String body2 = "{\n"
    			+ "\"IdCliente\":60887,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Pagos/GetPedidoByCliente";
    	e.conectaApiClubPOST(body2,url);
    }
    
   @PostMapping("/getClientesById")
    public void getClientesById() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":60887,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Clientes/GetClientesbyId";
    	e.conectaApiClubPOST(body2,url);
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
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/OrdenDeVenta/Registra";
    	e.conectaApiClubPOST(body2,url);
    }
    
    @PostMapping("/getLigaPago")
    public void getLigaPago() {
    	String body2 = "{\n"
    			+ "\"IdCliente\":60887,\n"
    			+ "\"Token\":\"10A4DF47-EAC7-45BD-8E2E-684989B4F0B0\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/LigaPago/ObtenLigaPago";
    	e.conectaApiClubPOST(body2,url);
    }
    
    
    @PostMapping("/getPedidoById")
    public void getPedidoById() {
    	String body2 = "{\n"
    			+ "\"NoPedido\":3,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Pagos/GetPedidoById";
    	e.conectaApiClubPOST(body2,url);
    }
    
    @PostMapping("/AsignaLigaPago")
    public void AsignaLigaPago() {
    	String body2 = "{\n"
    			+ "\"NoPedido\":108,\n"
    			+ "\"Url\":\"https://wppsandbox.mit.com.mx/i/3EWWGWUM\",\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Pagos/AsignaLigaPago";
    	e.conectaApiClubPOST(body2,url);
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
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Pagos/RegistraPago";
    	e.conectaApiClubPOST(body2,url);
    }
    
    @PostMapping("/ValidaAccesoClub")
    public void ValidaAccesoClub() {
    	String body2 = "{\n"
    			+ "\"Membresia\":\"1066250200\",\n"
    			+ "\"Club\":\"Club Alpha 2\",\n"
    			+ "\"Token\":\"D42CD61C-9BD3-4359-8401-A223A35BDFA5\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/ControlAcceso/ValidaAccesoClub";
    	e.conectaApiClubPOST(body2,url);
    }
    
   @PostMapping("/getConfig")
    public void getConfig() {
    	String body2 = "{\n"
    			+ "\"Equipo\":\"AzureServer\",\n"
    			+ "\"Token\":\"10A4DF47-EAC7-45BD-8E2E-684989B4F0B0\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/TerminalConf/GetConfiguracion";
    	e.conectaApiClubPOST(body2,url);
    }
   
   	@PostMapping("/ValidaEstatus")
    public void ValidaEstatus() {
    	String body2 = "{\n"
    			+ "\"Membresia\":\"1066250200\",\n"
    			+ "\"Token\":\"8CA09B3D-9AAB-4C88-8BED-A429EC5FB842\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Miembro/ValidaStatus/";
    	e.conectaApiClubPOST(body2,url);
    }
   @PostMapping("/getMiembroTG")
    public void getMiembroTG() {
    	String body2 = "{\n"
    			+ "\"Id\":\"27610\",\n"
    			+ "\"Token\":\"10A4DF47-EAC7-45BD-8E2E-684989B4F0B0\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/AlphaTraining/GetMiembro";
    	e.conectaApiClubPOST(body2,url);
    }
   
    @PostMapping("/getPasesById")
    public void getPasesById() {
    	String body2 = "{\n"
    			+ "\"IDCliente\":9416,\n"
    			+ "\"Token\":\"77D5BDD4-1FEE-4A47-86A0-1E7D19EE1C74\"\n"
    			+ "}";
    	String url = "http://192.168.20.26/ServiciosClubAlpha/api/Pases/GetPasesbyId";
    	e.conectaApiClubPOST(body2,url);
    }
    

   /* @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }*/
}
