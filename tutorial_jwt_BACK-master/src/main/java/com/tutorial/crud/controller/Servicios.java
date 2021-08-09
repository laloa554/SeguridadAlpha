package com.tutorial.crud.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tutorial.crud.entity.*;
import com.tutorial.crud.service.*;

/**
 * 	Esta clase permite hacer uso de todos los service para crear, actualizar y obtener las entidades mapeadas
 * @author: Daniel García Velasco y Abimael Rueda Galindo
 * @version: 12/7/2021
 *
 */

@RestController
@RequestMapping("/alpha")
public class Servicios 
{
	//service para club
	@Autowired
	private ClubService clubService;
	
	//service para lunes
	@Autowired
	private LunesService lunesService;
	
	//service para martes
	@Autowired
	private MartesService martesService;
	
	//service para miercoles
	@Autowired
	private MiercolesService miercolesService;
	
	//service para jueves
	@Autowired
	private JuevesService juevesService;
	

	//service para viernes
	@Autowired
	private ViernesService viernesService;

	//service para sabado
	@Autowired
	private SabadoService sabadoService;
	

	//service para domingo
	@Autowired
	private DomingoService domingoService;
	
	//service para horario
	@Autowired
	private HorarioService horarioService;
	
	//service para horario acceso
	@Autowired
	private HorarioAccesoService horarioAccesoService;
	
	//service para horario otro club
	@Autowired
	private HorarioOtroClubService horarioOtroClubService;
	
	//service para mensajes
	@Autowired
	private MensajesService mensajesService;
	
	//service para estatus cobranza
	@Autowired
	private EstatusCobranzaService estatusCobranzaService;
	
	//service para estatus membresia
	@Autowired
	private EstatusMembresiaService estatusMembresiaService;
	
	//service para tipo cliente
	@Autowired
	private TipoClienteService tipoClienteService;
	
	//service para curso detalle 
	@Autowired
	private CursoDetalleService cursoDetalleService;
	

	//service para curso horario
	@Autowired
	private CursoHorarioService cursoHorarioService;
	
	//service para orden venta
	@Autowired
	private OrdenVentaService ordenVentaService;
	
	//service para pago
	@Autowired
	private PagoService pagoService;
	
	//service para pase
	@Autowired
	private PaseService paseService;
	
	//service para pedido
	@Autowired
	private PedidoService pedidoService;
	
	//service para pedido detalle
	@Autowired
	private PedidoDetalleService pedidoDetalleService;
	
	//service para recibo
	@Autowired
	private ReciboService reciboService;
	
	//service para venta
	@Autowired
	private VentaService ventaService;
	
	//sevice para categoria
	@Autowired
	private CategoriaService categoriaService;
	
	//service para estatus cliente
	@Autowired
	private EstatusClienteService estatusClienteService;
	
	//service para tipo membresia
	@Autowired
	private TipoMembresiaService tipoMembresiaService;
	
	//service para cliente
	@Autowired
	private ClienteService clienteService;
	
	//--------------------------SERVICIO WEB CLUB----------------------------------
	/**
	 * Metodo que muestra todos los clubs almacenados en la base de datos
	 * @return lista de clubs
	 */
	@GetMapping("/obtenerClub")
    public List<Club> findAll(){
        return clubService.findAll();
    }//fin del metodo
	
	/**
	 * Metodo que muestra solo un club
	 * @param clubId es el id del club que se quiere mostrar, en caso de no encontrarlo genera un RuntimeException
	 * @return El club con el id clubId
	 */
	@GetMapping("/obtenerClub/{clubId}")
	@ResponseBody
    public Club getClub(@PathVariable("clubId") int clubId){
        Club club = clubService.findById(clubId);

        if(club == null) {
            throw new RuntimeException("club id not found -"+clubId);
        }
        return club;
    }//fin del metodo
	
	/**
	 * Metodo que añade a la base de datos un nuevo club
	 * @param club es el objeto club que se desea añadir, en caso de contar con el mismo id, actualiza los valores solamente
	 * @return el objeto club que fue almacenado
	 */
	
	@PostMapping("/agregarClub")
    public Club addClub(@RequestBody Club club) {
		club.setFechaCreacion(new Date());
		club.setFechaModificacion(new Date());
		club.setActivo(true);
        clubService.save(club);
        return club;

    }//fin del metodo
	
	/**
	 * Metodo que modifica un club ya existente en la base de datos (el club debe existir sino sera creado uno nuevo)
	 * @param club es el objecto club que se quiere modificar
	 * @return objeto club ya modificado
	 */
	@PutMapping("/modificarClub")
    public Club updateClub(@RequestBody Club club) {
		Club clubs=clubService.findById(club.getIdClub());
		clubs.setNombre(club.getNombre());
		clubs.setActivo(club.isActivo());
		clubs.setFechaCreacion(club.getFechaCreacion());
		clubs.setFechaModificacion(club.getFechaModificacion());
        clubService.save(clubs);
        //este metodo actualizará al usuario enviado
        club.setFechaModificacion(new Date());
        return club;
    }//fin del metodo
	//--------------------------SERVICIO WEB HORARIO LUNES----------------------------------
	
	/**
	 * Muestra todos los dias lunes registrados
	 * @return regresa la lista de objetos lunes
	 */
		@GetMapping("/obtenerHorarioLunes")
	    public List<Lunes> findAllHorarioLunes(){
	        return lunesService.findAll();
	    }//fin del metodo
		
		/**
		 * Muestra solo un dia lunes 
		 * @param horarioId id del dia lunes que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
		 * @return regresa el objeto lunes con id horarioId
		 */
		@GetMapping("/obtenerHorarioLunes/{horarioId}")
		@ResponseBody
	    public Lunes getLunes(@PathVariable("horarioId") int horarioId){
	        Lunes lunes = lunesService.findById(horarioId);

	        if(lunes == null) {
	            throw new RuntimeException("lunes id not found -"+horarioId);
	        }
	        //retornará al usuario con id pasado en la url
	        return lunes;
	    }//fin del metodo
		
		/**
		 * Este metodo añade un nuevo horario para el día lunes
		 * @param lunes objeto lunes que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valroes
		 * @return objeto lunes añadido
		 */
		@PostMapping("/agregarHorarioLunes")
	    public Lunes addLunes(@RequestBody Lunes lunes) {
	        //Este metodo guardará al usuario enviado
			lunes.setFechamodificacion(new Date());
			lunes.setFechacreacion(new Date());
			lunes.setActivo(true);
	        lunesService.save(lunes);

	        return lunes;

	    }//fin del metodo
		
		/**
		 * Metodo que modifica un horario del día lunes ya registrado
		 * @param lunes objeto que se desea modificar, en caso de no existir se crea uno nuevo
		 * @return objecto lunes que fue modificado
		 */
		@PutMapping("/modificarHorarioLunes")
	    public Lunes updateLunes(@RequestBody Lunes lunes) {
			Lunes luness=lunesService.findById(lunes.getIdLunes());
			luness.setHoraentrada(lunes.getHoraentrada());
			luness.setHorasalida(lunes.getHorasalida());
			luness.setActivo(lunes.isActivo());
			luness.setFechacreacion(lunes.getFechacreacion());
			luness.setFechamodificacion(lunes.getFechamodificacion());
	        lunesService.save(luness);
	        //este metodo actualizará al usuario enviado
	        lunes.setFechamodificacion(new Date());
	        return lunes;
	    }// fin del metodo
		//--------------------------SERVICIO WEB HORARIO MARTES----------------------------------
	
		/**
		 * Muestra todos los dias martes registrados
		 * @return regresa la lista de objetos martes
		 */
		@GetMapping("/obtenerHorarioMartes")
	    public List<Martes> findAllHorarioMartes(){
	        return martesService.findAll();
	    }
		/**
		 * Muestra solo un dia martes 
		 * @param horarioId id del dia martes que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
		 * @return regresa el objeto martes con id horarioId
		 */
		@GetMapping("/obtenerHorarioMartes/{horarioId}")
		@ResponseBody
	    public Martes getMartes(@PathVariable("horarioId") int horarioId){
	        Martes martes = martesService.findById(horarioId);

	        if(martes == null) {
	            throw new RuntimeException("martes id not found -"+horarioId);
	        }
	        //retornará al usuario con id pasado en la url
	        return martes;
	    }//Fin del metodo
		
		/**
		 * Este metodo añade un nuevo horario para el día martes
		 * @param martes objeto martes que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
		 * @return objeto martes añadido
		 */
		@PostMapping("/agregarHorarioMartes")
	    public Martes addMartes(@RequestBody Martes martes) {
	        //Este metodo guardará al usuario enviado
			martes.setFechamodificacion(new Date());
			martes.setFechacreacion(new Date());
			martes.setActivo(true);
	        martesService.save(martes);

	        return martes;

	    }// FIN DEL METODO
		
		/**
		 * Metodo que modifica un horario del día martes ya registrado
		 * @param martes objeto que se desea modificar, en caso de no existir se crea uno nuevo
		 * @return objeto martes que fue modificado
		 */
		@PutMapping("/modificarHorarioMartes")
	    public Martes updateMartes(@RequestBody Martes martes) {
			Martes martess=martesService.findById(martes.getIdMartes());
			martess.setHoraentrada(martes.getHoraentrada());
			martess.setHorasalida(martes.getHorasalida());
			martess.setActivo(martes.isActivo());
			martess.setFechacreacion(martes.getFechacreacion());
			martess.setFechamodificacion(martes.getFechamodificacion());
	        martesService.save(martess);
	        //este metodo actualizará al usuario enviado
	        martes.setFechamodificacion(new Date());
	        return martes;
	    }//Fin del metodo
		//--------------------------SERVICIO WEB HORARIO MIERCOLES----------------------------------
		/**
		 * Muestra todos los dias miercoles registrados
		 * @return regresa la lista de objetos miercoles
		 */
			@GetMapping("/obtenerHorarioMiercoles")
		    public List<Miercoles> findAllHorarioMiercoles(){
		        return miercolesService.findAll();
		    }// fin del metodo
			/**
			 * Muestra solo un dia miercoles 
			 * @param horarioId id del dia miercoles que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto miercoles con id horarioId
			 */
			@GetMapping("/obtenerHorarioMiercoles/{horarioId}")
			@ResponseBody
		    public Miercoles getMiercoles(@PathVariable("horarioId") int horarioId){
		        Miercoles miercoles = miercolesService.findById(horarioId);

		        if(miercoles == null) {
		            throw new RuntimeException("miercoles id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return miercoles;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario para el día Miercoles
			 * @param miercoles objeto miercoles que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto miercoles añadido
			 */
			@PostMapping("/agregarHorarioMiercoles")
		    public Miercoles addMiercoles(@RequestBody Miercoles miercoles) {
		        //Este metodo guardará al usuario enviado
				miercoles.setFechamodificacion(new Date());
				miercoles.setFechacreacion(new Date());
				miercoles.setActivo(true);
		        miercolesService.save(miercoles);

		        return miercoles;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un horario del día miercoles ya registrado
			 * @param miercoles objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto miercoles que fue modificado
			 */
			@PutMapping("/modificarHorarioMiercoles")
		    public Miercoles updateMiercoles(@RequestBody Miercoles miercoles) {
				Miercoles miercoless=miercolesService.findById(miercoles.getIdMiercoles());
				miercoless.setHoraentrada(miercoles.getHoraentrada());
				miercoless.setHorasalida(miercoles.getHorasalida());
				miercoless.setActivo(miercoles.isActivo());
				miercoless.setFechacreacion(miercoles.getFechacreacion());
				miercoless.setFechamodificacion(miercoles.getFechamodificacion());
		        miercolesService.save(miercoless);
		        //este metodo actualizará al usuario enviado
		        miercoles.setFechamodificacion(new Date());
		        return miercoles;
		    }//Fin del metodo
			
			//--------------------------SERVICIO WEB HORARIO JUEVES----------------------------------
			/**
			 * Muestra todos los dias jueves registrados
			 * @return regresa la lista de objetos jueves
			 */
			@GetMapping("/obtenerHorarioJueves")
		    public List<Jueves> findAllHorarioJueves(){
		        return juevesService.findAll();
		    }// Fin del metodo
			/**
			 * Muestra solo un dia jueves 
			 * @param horarioId id del dia jueves que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto jueves con id horarioId
			 */
			@GetMapping("/obtenerHorarioJueves/{horarioId}")
			@ResponseBody
		    public Jueves getJueves(@PathVariable("horarioId") int horarioId){
		        Jueves jueves = juevesService.findById(horarioId);

		        if(jueves == null) {
		            throw new RuntimeException("jueves id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return jueves;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario para el día jueves
			 * @param jueves objeto jueves que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto jueves añadido
			 */
			@PostMapping("/agregarHorarioJueves")
		    public Jueves addJueves(@RequestBody Jueves jueves) {
		        //Este metodo guardará al usuario enviado
				jueves.setFechamodificacion(new Date());
				jueves.setFechacreacion(new Date());
				jueves.setActivo(true);
		        juevesService.save(jueves);

		        return jueves;

		    }//fiN DEL METODO
			
			/**
			 * Metodo que modifica un horario del día jueves ya registrado
			 * @param jueves objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto jueves que fue modificado
			 */
			@PutMapping("/modificarHorarioJueves")
		    public Jueves updateJueves(@RequestBody Jueves jueves) {
				Jueves juevess=juevesService.findById(jueves.getIdJueves());
				juevess.setHoraentrada(jueves.getHoraentrada());
				juevess.setHorasalida(jueves.getHorasalida());
				juevess.setActivo(jueves.isActivo());
				juevess.setFechacreacion(jueves.getFechacreacion());
				juevess.setFechamodificacion(jueves.getFechamodificacion());
		        juevesService.save(juevess);
		        //este metodo actualizará al usuario enviado
		        jueves.setFechamodificacion(new Date());
		        return jueves;
		    }//Fin del metodo
//--------------------------SERVICIO WEB HORARIO VIERNES----------------------------------
			/**
			 * Muestra todos los dias viernes registrados
			 * @return regresa la lista de objetos viernes
			 */
			@GetMapping("/obtenerHorarioViernes")
		    public List<Viernes> findAllHorarioViernes(){
		        return viernesService.findAll();
		    }//Fin del metodo
			/**
			 * Muestra solo un dia Viernes 
			 * @param horarioId id del dia viernes que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto viernes con id horarioId
			 */
			@GetMapping("/obtenerHorarioViernes/{horarioId}")
			@ResponseBody
		    public Viernes getViernes(@PathVariable("horarioId") int horarioId){
		        Viernes viernes = viernesService.findById(horarioId);

		        if(viernes == null) {
		            throw new RuntimeException("viernes id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return viernes;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario para el día viernes
			 * @param viernes objeto viernes que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto viernes añadido
			 */
			@PostMapping("/agregarHorarioViernes")
		    public Viernes addViernes(@RequestBody Viernes viernes) {
		        //Este metodo guardará al usuario enviado
				viernes.setFechamodificacion(new Date());
				viernes.setFechacreacion(new Date());
				viernes.setActivo(true);
		        viernesService.save(viernes);

		        return viernes;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un horario del día viernes ya registrado
			 * @param viernes objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto viernes que fue modificado
			 */
			@PutMapping("/modificarHorarioViernes")
		    public Viernes updateViernes(@RequestBody Viernes viernes) {
				Viernes vierness=viernesService.findById(viernes.getIdViernes());
				vierness.setHoraentrada(viernes.getHoraentrada());
				vierness.setHorasalida(viernes.getHorasalida());
				vierness.setActivo(viernes.isActivo());
				vierness.setFechacreacion(viernes.getFechacreacion());
				vierness.setFechamodificacion(viernes.getFechamodificacion());
		        viernesService.save(vierness);
		        //este metodo actualizará al usuario enviado
		        vierness.setFechamodificacion(new Date());
		        return vierness;
		    }//Fin del metodo
//--------------------------SERVICIO WEB HORARIO SABADO----------------------------------
			/**
			 * Muestra todos los dias sabado registrados
			 * @return regresa la lista de objetos sabado
			 */
			@GetMapping("/obtenerHorarioSabado")
		    public List<Sabado> findAllHorarioSabado(){
		        return sabadoService.findAll();
		    }// Fin del metodo
			/**
			 * Muestra solo un dia sabado 
			 * @param horarioId id del dia sabado que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto sabado con id horarioId
			 */
			@GetMapping("/obtenerHorarioSabado/{horarioId}")
			@ResponseBody
		    public Sabado getSabado(@PathVariable("horarioId") int horarioId){
		        Sabado sabado = sabadoService.findById(horarioId);

		        if(sabado == null) {
		            throw new RuntimeException("sabado id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return sabado;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario para el día Sabado
			 * @param sabado objeto sabado que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto sabado añadido
			 */
			@PostMapping("/agregarHorarioSabado")
		    public Sabado addSabado(@RequestBody Sabado sabado) {
		        //Este metodo guardará al usuario enviado
				sabado.setFechamodificacion(new Date());
				sabado.setFechacreacion(new Date());
				sabado.setActivo(true);
		        sabadoService.save(sabado);

		        return sabado;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un horario del día sabado ya registrado
			 * @param sabado objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto sabado que fue modificado
			 */
			@PutMapping("/modificarHorarioSabado")
		    public Sabado updateSabado(@RequestBody Sabado sabado) {
				Sabado sabados=sabadoService.findById(sabado.getIdSabado());
				sabados.setHoraentrada(sabado.getHoraentrada());
				sabados.setHorasalida(sabado.getHorasalida());
				sabados.setActivo(sabado.isActivo());
				sabados.setFechacreacion(sabado.getFechacreacion());
				sabados.setFechamodificacion(sabado.getFechamodificacion());
		        sabadoService.save(sabados);
		        //este metodo actualizará al usuario enviado
		        sabados.setFechamodificacion(new Date());
		        return sabados;
		    }//FIn del metodo
			
//--------------------------SERVICIO WEB HORARIO DOMINGO----------------------------------
			/**
			 * Muestra todos los dias Domingo registrados
			 * @return regresa la lista de objetos domingo
			 */
			@GetMapping("/obtenerHorarioDomingo")
		    public List<Domingo> findAllHorarioDomingo(){
		        return domingoService.findAll();
		    }//Fin del metodo
			/**
			 * Muestra solo un dia Domingo 
			 * @param horarioId id del dia domingo que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto domingo con id horarioId
			 */
			@GetMapping("/obtenerHorarioDomingo/{horarioId}")
			@ResponseBody
		    public Domingo getDomingo(@PathVariable("horarioId") int horarioId){
		        Domingo domingo = domingoService.findById(horarioId);

		        if(domingo == null) {
		            throw new RuntimeException("domingo id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return domingo;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario para el día domingo
			 * @param domingo objeto domingo que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto domingo añadido
			 */
			@PostMapping("/agregarHorarioDomingo")
		    public Domingo addDomingo(@RequestBody Domingo domingo) {
		        //Este metodo guardará al usuario enviado
				domingo.setFechamodificacion(new Date());
				domingo.setFechacreacion(new Date());
				domingo.setActivo(true);
		        domingoService.save(domingo);

		        return domingo;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un horario del día domingo ya registrado
			 * @param domingo objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto domingo que fue modificado
			 */
			@PutMapping("/modificarHorarioDomingo")
		    public Domingo updateDomingo(@RequestBody Domingo domingo) {
				Domingo domingos=domingoService.findById(domingo.getIdDomingo());
				domingos.setHoraentrada(domingo.getHoraentrada());
				domingos.setHorasalida(domingo.getHorasalida());
				domingos.setActivo(domingo.isActivo());
				domingos.setFechacreacion(domingo.getFechacreacion());
				domingos.setFechamodificacion(domingo.getFechamodificacion());
		        domingoService.save(domingos);
		        //este metodo actualizará al usuario enviado
		        domingos.setFechamodificacion(new Date());
		        return domingos;
		    }//fin del metodo
			
//--------------------------SERVICIO WEB HORARIO OTRO CLUB----------------------------------
			/**
			 * Muestra todos los Horario otro club registrados
			 * @return regresa la lista de objetos HorarioOtroClub
			 */
			@GetMapping("/obtenerHorarioOtroClub")
		    public List<HorarioOtroClub> findAllHorarioOtroClub(){
		        return horarioOtroClubService.findAll();
		    }// Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario otro club
			 * @param horarioOtroClub objeto de tipo HorarioOtroClub que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto HorarioOtroClub añadido
			 */
			@PostMapping("/agregarHorarioOtroClub")
		    public HorarioOtroClub addHorarioOtroClub(@RequestBody HorarioOtroClub horarioOtroClub) {
		        //Este metodo guardará al usuario enviado
				horarioOtroClub.setFechaCreacion(new Date());
				horarioOtroClub.setFechaModificacion(new Date());
				horarioOtroClub.setActivo(true);
				horarioOtroClubService.save(horarioOtroClub);

		        return horarioOtroClub;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un horario otro club ya registrado
			 * @param horarioOtroClub objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto horarioOtroClub que fue modificado
			 */
			@PutMapping("/modificarHorarioOtroClub")
		    public HorarioOtroClub updatehorarioOtroClub(@RequestBody HorarioOtroClub horarioOtroClub) {
				horarioOtroClubService.save(horarioOtroClub);
		        //este metodo actualizará al usuario enviado
		        horarioOtroClub.setFechaModificacion(new Date());
		        return horarioOtroClub;
		    }//fin del metodo
			
//--------------------------SERVICIO WEB HORARIO ----------------------------------
			/**
			 * Muestra todos los Horarios registrados
			 * @return regresa la lista de objetos Horario
			 */
			@GetMapping("/obtenerHorario")
		    public List<Horario> findAllHorario(){
		        return horarioService.findAll();
		    }//Fin del metodo
			/**
			 * Muestra solo un Horario 
			 * @param horarioId id del horario que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto horario con id horarioId
			 */
			@GetMapping("/obtenerHorario/{horarioId}")
			@ResponseBody
		    public Horario getHorario(@PathVariable("horarioId") int horarioId){
		        Horario horario = horarioService.findById(horarioId);
		        System.out.println(horario);
		        if(horario == null) {
		            throw new RuntimeException("horario id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return horario;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo horario
			 * @param horario objeto de tipo Horario que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Horario añadido
			 */
			@PostMapping("/agregarHorario")
		    public Horario addHorario(@RequestBody Horario horario) {
		        //Este metodo guardará al usuario enviado
				horario.setFechaCreacion(new Date());
				horario.setFechaModificacion(new Date());
				horario.setActivo(true);
				horario.getIdLunes().setFechacreacion(new Date());
				horario.getIdLunes().setFechamodificacion(new Date());
				horario.getIdLunes().setActivo(true);
				
				horario.getIdMartes().setFechacreacion(new Date());
				horario.getIdMartes().setFechamodificacion(new Date());
				horario.getIdMartes().setActivo(true);
				
				horario.getIdMiercoles().setFechacreacion(new Date());
				horario.getIdMiercoles().setFechamodificacion(new Date());
				horario.getIdMiercoles().setActivo(true);
				
				horario.getIdJueves().setFechacreacion(new Date());
				horario.getIdJueves().setFechamodificacion(new Date());
				horario.getIdJueves().setActivo(true);
				
				horario.getIdViernes().setFechacreacion(new Date());
				horario.getIdViernes().setFechamodificacion(new Date());
				horario.getIdViernes().setActivo(true);
				
				horario.getIdSabado().setFechacreacion(new Date());
				horario.getIdSabado().setFechamodificacion(new Date());
				horario.getIdSabado().setActivo(true);
				
				horario.getIdDomingo().setFechacreacion(new Date());
				horario.getIdDomingo().setFechamodificacion(new Date());
				horario.getIdDomingo().setActivo(true);
				System.out.println(horario);
		        horarioService.save(horario);

		        return horario;

		    }//Fin del metodo
			/**
			 * Metodo que modifica un horario ya registrado
			 * @param horario objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto horario que fue modificado
			 */
			@PutMapping("/modificarHorario")
		    public Horario updateHorario(@RequestBody Horario horario) {
		        horarioService.save(horario);
		        //este metodo actualizará al usuario enviado
		        horario.setFechaModificacion(new Date());
		        return horario;
		    }//fin del metodo
			
//--------------------------SERVICIO WEB HORARIO ACCESO----------------------------------
			/**
			 * Muestra todos los Horario acceso registrados
			 * @return regresa la lista de objetos HorarioAcceso
			 */
			@GetMapping("/obtenerHorarioAcceso")
		    public List<HorarioAcceso> findAllHorarioAcceso(){
		        return horarioAccesoService.findAll();
		    }//Fin del metodo
			
			/**
			 * Muestra solo un horario Acceso 
			 * @param horarioId id del horario acceso que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto HorarioAcceso con id horarioId
			 */
			@GetMapping("/obtenerHorarioAcceso/{horarioId}")
			@ResponseBody
		    public HorarioAcceso getHorarioAcceso(@PathVariable("horarioId") int horarioId){
		        HorarioAcceso horarioAcceso = horarioAccesoService.findById(horarioId);
		        if(horarioAcceso == null) {
		            throw new RuntimeException("horario Acceso id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return horarioAcceso;
		    }//FIn del metodo
			
			/**
			 * Este metodo añade un nuevo horario acceso
			 * @param horarioAcceso objeto de tipo HorarioAcceso que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto HorarioAcceso añadido
			 */
			@PostMapping("/agregarHorarioAcceso")
		    public HorarioAcceso addHorarioAcceso(@RequestBody HorarioAcceso horarioAcceso) {
		        //Este metodo guardará al usuario enviado
				horarioAcceso.setFechaCreacion(new Date());
				horarioAcceso.setFechaModificacion(new Date());
				horarioAcceso.setActivo(true);
		        horarioAccesoService.save(horarioAcceso);

		        return horarioAcceso;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un horario acceso ya registrado
			 * @param horarioAcceso objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto HorarioAcceso que fue modificado
			 */
			@PutMapping("/modificarHorarioAcceso")
		    public HorarioAcceso updateHorarioAcceso(@RequestBody HorarioAcceso horarioAcceso) {
				System.out.println(horarioAcceso);
		        horarioAccesoService.save(horarioAcceso);
		        //este metodo actualizará al usuario enviado
		        horarioAcceso.setFechaModificacion(new Date());
		        return horarioAcceso;
		    }//Fin de metodo
//--------------------------SERVICIO WEB MENSAJES----------------------------------
			/**
			 * Muestra todos los Mensajes registrados
			 * @return regresa la lista de objetos Mensajes
			 */
			@GetMapping("/obtenerMensajes")
		    public List<Mensajes> findAllMensajes(){
		        return mensajesService.findAll();
		    }//Fin del Metodo
			
			/**
			 * Muestra solo un mensaje
			 * @param horarioId id del mensaje que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Mensaje con id horarioId
			 */
			@GetMapping("/obtenerMensajes/{horarioId}")
			@ResponseBody
		    public Mensajes getMensajes(@PathVariable("horarioId") int horarioId){
		        Mensajes mensajes = mensajesService.findById(horarioId);
		        if(mensajes == null) {
		            throw new RuntimeException("mensajes id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return mensajes;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo mensaje
			 * @param mensajes objeto de tipo Mensajes que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Mensajes añadido
			 */
			@PostMapping("/agregarMensajes")
		    public Mensajes addMensajes(@RequestBody Mensajes mensajes) {
		        //Este metodo guardará al usuario enviado
				mensajes.setFechaCreacion(new Date());
				mensajes.setFechaModificacion(new Date());
				mensajes.setActivo(true);
				mensajesService.save(mensajes);

		        return mensajes;

		    }//Fin del metodo
			/**
			 * Metodo que modifica un mensajes ya registrado
			 * @param mensajes objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto mensajes que fue modificado
			 */
			@PutMapping("/modificarMensajes")
		    public Mensajes updateMensajes(@RequestBody Mensajes mensajes) {
				mensajesService.save(mensajes);
		        //este metodo actualizará al usuario enviado
		        mensajes.setFechaModificacion(new Date());
		        return mensajes;
		    }//Fin del metodo
//--------------------------SERVICIO WEB ESTATUS COBRANZA----------------------------------
			/**
			 * Muestra todos los Estatus cobranza registrados
			 * @return regresa la lista de objetos EstatusCobranza
			 */
			@GetMapping("/obtenerEstatusCobranza")
		    public List<EstatusCobranza> findAllEstatusCobranza(){
		        return estatusCobranzaService.findAll();
		    }//Fin del Metodo
			/**
			 * Muestra solo un estatus cobranza 
			 * @param horarioId id del estatus cobranza que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto EstatusCobranza con id horarioId
			 */
			@GetMapping("/obtenerEstatusCobranza/{horarioId}")
			@ResponseBody
		    public EstatusCobranza getEstatusCobranza(@PathVariable("horarioId") int horarioId){
				EstatusCobranza estatusCobranza = estatusCobranzaService.findById(horarioId);
		        if(estatusCobranza == null) {
		            throw new RuntimeException("Estatus Cobranza id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return estatusCobranza;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo estatus cobranza
			 * @param estatusCobranza objeto de tipo EstatusCobranza que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto EstatusCobranza añadido
			 */
			@PostMapping("/agregarEstatusCobranza")
		    public EstatusCobranza addEstatusCobranza(@RequestBody EstatusCobranza estatusCobranza) {
		        //Este metodo guardará al usuario enviado
				estatusCobranza.setFechaCreacion(new Date());
				estatusCobranza.setFechaModificacion(new Date());
				estatusCobranza.setActivo(true);
				estatusCobranzaService.save(estatusCobranza);

		        return estatusCobranza;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un estatus cobranza ya registrado
			 * @param estatusCobranza objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto EstatusCobranza que fue modificado
			 */
			@PutMapping("/modificarEstatusCobranza")
		    public EstatusCobranza updateEstatusCobranza(@RequestBody EstatusCobranza estatusCobranza) {
				estatusCobranzaService.save(estatusCobranza);
		        //este metodo actualizará al usuario enviado
				estatusCobranza.setFechaModificacion(new Date());
		        return estatusCobranza;
		    }//Fin del metodo
//--------------------------SERVICIO WEB ESTATUS MEMBRESIA----------------------------------
			/**
			 * Muestra todos los Estatus Membresia registrados
			 * @return regresa la lista de objetos EstatusMembresia
			 */
			@GetMapping("/obtenerEstatusMembresia")
		    public List<EstatusMembresia> findAllEstatusMembresia(){
		        return estatusMembresiaService.findAll();
		    }//Fin del Metodo
			
			/**
			 * Muestra solo un Estatus membresia
			 * @param horarioId id del estatus membresia que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto estatus membresia con id horarioId
			 */
			@GetMapping("/obtenerEstatusMembresia/{horarioId}")
			@ResponseBody
		    public EstatusMembresia getEstatusMembresia(@PathVariable("horarioId") int horarioId){
				EstatusMembresia estatusMembresia = estatusMembresiaService.findById(horarioId);
		        if(estatusMembresia == null) {
		            throw new RuntimeException("Estatus Membresia id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return estatusMembresia;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo estatus Membresia
			 * @param estatusMembresia objeto de tipo EstatusMembresia que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto EstatusMembresia añadido
			 */
			@PostMapping("/agregarEstatusMembresia")
		    public EstatusMembresia addEstatusMembresia(@RequestBody EstatusMembresia estatusMembresia) {
		        //Este metodo guardará al usuario enviado
				estatusMembresia.setFechaCreacion(new Date());
				estatusMembresia.setFechaModificacion(new Date());
				estatusMembresia.setActivo(true);
				estatusMembresiaService.save(estatusMembresia);

		        return estatusMembresia;

		    }//Fin del metodo
			
			/**
			 * Metodo que modifica un Estatus membresia ya registrado
			 * @param estatusMembresia objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto EstatusMembresia que fue modificado
			 */
			@PutMapping("/modificarEstatusMembresia")
		    public EstatusMembresia updateEstatusMembresia(@RequestBody EstatusMembresia estatusMembresia) {
				estatusMembresiaService.save(estatusMembresia);
		        //este metodo actualizará al usuario enviado
				estatusMembresia.setFechaModificacion(new Date());
		        return estatusMembresia;
		    }//FIn del metodo
//--------------------------SERVICIO WEB TIPO CLIENTE----------------------------------
			/**
			 * Muestra todos los Tipos Clientes registrados
			 * @return regresa la lista de objetos TipoCliente
			 */
			@GetMapping("/obtenerTipoCliente")
		    public List<TipoCliente> findAllTipoCliente(){
		        return tipoClienteService.findAll();
		    }//Fin del metodo
			
			/**
			 * Muestra solo un tipo cliente 
			 * @param horarioId id del tipo cliente que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto TipoCliente con id horarioId
			 */
			@GetMapping("/obtenerTipoCliente/{horarioId}")
			@ResponseBody
		    public TipoCliente getTipoCliente(@PathVariable("horarioId") int horarioId){
				TipoCliente tipoCliente = tipoClienteService.findById(horarioId);
		        if(tipoCliente == null) {
		            throw new RuntimeException("Tipo Cliente id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return tipoCliente;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo TipoCliente
			 * @param tipoCliente objeto de TipoCliente que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto TipoCliente añadido
			 */
			@PostMapping("/agregarTipoCliente")
		    public TipoCliente addTipoCliente(@RequestBody TipoCliente tipoCliente) {
		        //Este metodo guardará al usuario enviado
				tipoCliente.setFechaCreacion(new Date());
				tipoCliente.setFechaModificacion(new Date());
				tipoCliente.setActivo(true);
				tipoClienteService.save(tipoCliente);

		        return tipoCliente;

		    }//Fin del metodo
			/**
			 * Metodo que modifica un tipo cliente ya registrado
			 * @param TipoCliente objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto TipoCliente que fue modificado
			 */
			@PutMapping("/modificarTipoCliente")
		    public TipoCliente updateTipoCliente(@RequestBody TipoCliente tipoCliente) {
				tipoClienteService.save(tipoCliente);
		        //este metodo actualizará al usuario enviado
				tipoCliente.setFechaModificacion(new Date());
		        return tipoCliente;
		    }//FIn del metodo
			
			//--------------------------SERVICIO WEB CURSO DETALLE----------------------------------
			/**
			 * Muestra todos los detalles de curso registrados
			 * @return regresa la lista de objetos CursoDetalle
			 */
			@GetMapping("/obtenerCursoDetalle")
			public List<CursoDetalle> findAllCursoDetalle()
			{
				return cursoDetalleService.findAll();
			}//Fin del metodo
			/**
			 * Muestra solo un curso detalle
			 * @param cursoDetalleid id del curso detalle que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto CursoDetalle con id cursoDetalleid
			 */
			@GetMapping("/obtenerCursoDetalle/{cursoDetalleid}")
			@ResponseBody
			public CursoDetalle getCursoDetalle(@PathVariable("cursoDetalleid") int cursoDetalleid)
			{
				CursoDetalle cursoDetalle = cursoDetalleService.findById(cursoDetalleid);
				if(cursoDetalle == null)
				{
					throw new RuntimeException("cursoDetalle id not found -"+cursoDetalleid);
				}
				return cursoDetalle;
			}//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo curso detalle
			 * @param cursoDetalle objeto de CursoDetalle que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto CursoDetalle añadido
			 */
			@PostMapping("/agregarCursoDetalle")
			public CursoDetalle addCursoDetalle(@RequestBody CursoDetalle cursoDetalle)
			{
				cursoDetalleService.save(cursoDetalle);
				return cursoDetalle;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un curso detalle ya registrado
			 * @param cursoDetalle objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto CursoDetalle que fue modificado
			 */
			@PutMapping("/modificarCursoDetalle")
		    public CursoDetalle updateCursoDetalle(@RequestBody CursoDetalle cursoDetalle) 
			{
				CursoDetalle cursosDetalles = cursoDetalleService.findById(cursoDetalle.getIdCursoDetalle());
				cursosDetalles.setPeriodo(cursoDetalle.getPeriodo());
				cursosDetalles.setCursoTipo(cursoDetalle.getCursoTipo());
				cursosDetalles.setArea(cursoDetalle.getArea());
				cursosDetalles.setInstalacion(cursoDetalle.getInstalacion());
				cursosDetalles.setUsadoPor(cursoDetalle.getUsadoPor());
				cursosDetalles.setProfesor(cursoDetalle.getProfesor());
				cursosDetalles.setHorarioInicio(cursoDetalle.getHorarioInicio());
				cursosDetalles.setEdadMinima(cursoDetalle.getEdadMinima());
				cursosDetalles.setEdadMaxima(cursoDetalle.getEdadMaxima());
				cursosDetalles.setIdCursoHorario(cursoDetalle.getIdCursoHorario());
				cursosDetalles.setCursoTipo(cursoDetalle.getCursoTipo());
				cursosDetalles.setActivo(true);
				cursosDetalles.setFechaCreacion(new Date());
				cursosDetalles.setFechaModificacion(cursoDetalle.getFechaModificacion());
				cursoDetalleService.save(cursosDetalles);
				return cursoDetalle;
			}//Fin del metodo
			
			//--------------------------SERVICIO WEB CURSO HORARIO----------------------------------
			/**
			 * Muestra todos los cursos horarios registrados
			 * @return regresa la lista de objetos CursoHorario
			 */
			@GetMapping("/obtenerCursoHorario")
			public List<CursoHorario> findAllCursoHorario()
			{
				return cursoHorarioService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un curso Horario
			 * @param cursoHorarioid id del curso horario que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto CursoHorario con id cursoHorarioid
			 */
			@GetMapping("/obtenerCursoHorario/{cursoHorarioid}")
			@ResponseBody
			public CursoHorario getCursoHorario(@PathVariable("cursoHorarioid") int cursoHorarioid)
			{
				CursoHorario cursoHorario = cursoHorarioService.findById(cursoHorarioid);
				if(cursoHorario == null)
				{
					throw new RuntimeException("cursoDetalle id not found -"+cursoHorarioid);
				}
				return cursoHorario;
			}//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo curso Horario
			 * @param cursoHorario objeto de tipo CursoHorario que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto CursoHorario añadido
			 */
			@PostMapping("/agregarCursoHorario")
			public CursoHorario addCursoHorario(@RequestBody CursoHorario cursoHorario)
			{
				cursoHorarioService.save(cursoHorario);
				return cursoHorario;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Curso Horario ya registrado
			 * @param cursoHorario objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto CursoHorario que fue modificado
			 */
			@PutMapping("/modificarCursoHorario")
		    public CursoHorario updateCursoHorario(@RequestBody CursoHorario cursoHorario) 
			{
				CursoHorario cursosHorarios = cursoHorarioService.findById(cursoHorario.getIdCurso());
				cursosHorarios.setNombreCurso(cursoHorario.getNombreCurso());
				cursosHorarios.setLunesHoraEntrada(cursoHorario.getLunesHoraEntrada());		
				cursosHorarios.setLunesHoraSalida(cursoHorario.getLunesHoraSalida());
				cursosHorarios.setMartesHoraEntrada(cursoHorario.getMartesHoraEntrada());
				cursosHorarios.setMartesHoraSalida(cursoHorario.getMartesHoraSalida());
				cursosHorarios.setMiercolesHoraEntrada(cursoHorario.getMiercolesHoraEntrada());
				cursosHorarios.setMiercolesHoraSalida(cursoHorario.getMiercolesHoraSalida());
				cursosHorarios.setJuevesHoraEntrada(cursoHorario.getJuevesHoraEntrada());
				cursosHorarios.setJuevesHoraSalida(cursoHorario.getJuevesHoraSalida());
				cursosHorarios.setViernesHoraEntrada(cursoHorario.getViernesHoraEntrada());
				cursosHorarios.setViernesHoraSalida(cursoHorario.getViernesHoraSalida());
				cursosHorarios.setSabadoHoraEntrada(cursoHorario.getSabadoHoraEntrada());
				cursosHorarios.setSabadoHoraSalida(cursoHorario.getSabadoHoraSalida());
				cursosHorarios.setDomingoHoraEntrada(cursoHorario.getDomingoHoraEntrada());
				cursosHorarios.setDomingoHoraSalida(cursoHorario.getDomingoHoraSalida());
				cursosHorarios.setActivo(true);
				cursosHorarios.setFechaCreacion(new Date());
				cursosHorarios.setFechaModificacion(cursoHorario.getFechaModificacion());
				cursoHorarioService.save(cursosHorarios);
				return cursoHorario;
			}//FIn del metodo
			//--------------------------SERVICIO WEB ORDEN VENTA----------------------------------
			/**
			 * Muestra todos los orden de venta registrados
			 * @return regresa la lista de objetos OrdenVenta
			 */
			@GetMapping("/obtenerOrdenVenta")
			public List<OrdenVenta> findAllOrdenVenta()
			{
				return ordenVentaService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un orden venta
			 * @param ordenVentaid id del orden venta que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto OrdenVenta con id ordenVentaid
			 */
			@GetMapping("/obtenerOrdenVenta/{ordenVentaid}")
			@ResponseBody
			public OrdenVenta getOrdenVenta(@PathVariable("ordenVentaid") int ordenVentaid)
			{
				OrdenVenta ordenVenta = ordenVentaService.findById(ordenVentaid);
				if(ordenVenta == null)
				{
					throw new RuntimeException("ordenVenta id not found -"+ordenVentaid);
				}
				return ordenVenta;
			}//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo orden venta
			 * @param ordenVenta objeto de tipo OrdenVenta que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Ordenventa añadido
			 */
			@PostMapping("/agregarOrdenVenta")
			public OrdenVenta addOrdenVenta(@RequestBody OrdenVenta ordenVenta)
			{
				ordenVentaService.save(ordenVenta);
				return ordenVenta;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Orden venta ya registrado
			 * @param ordenVenta objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto OrdenVenta que fue modificado
			 */
			@PutMapping("/modificarOrdenVenta")
		    public OrdenVenta updateOrdenVenta(@RequestBody OrdenVenta ordenVenta) 
			{
				OrdenVenta ordenesVentas = ordenVentaService.findById(ordenVenta.getIdOV());
				ordenesVentas.setIdCliente(ordenesVentas.getIdCliente());
				ordenesVentas.setIdVenta(ordenesVentas.getIdVenta());
				ordenesVentas.setCantidad(ordenesVentas.getCantidad());
				ordenesVentas.setIdProductoServicio(ordenesVentas.getIdProductoServicio());
				ordenesVentas.setObservaciones(ordenesVentas.getObservaciones());
				ordenesVentas.setDescuentoPorCiento(ordenesVentas.getDescuentoPorCiento());
				ordenesVentas.setFechaInicio(ordenesVentas.getFechaInicio());
				ordenesVentas.setFechaFinal(ordenesVentas.getFechaFinal());
				ordenesVentas.setCobroProporcional(ordenesVentas.getCobroProporcional());
				ordenesVentas.setActivo(true);
				ordenesVentas.setFechaCreacion(new Date());
				ordenesVentas.setFechaModificacion(ordenesVentas.getFechaModificacion());
				ordenVentaService.save(ordenVenta);
				return ordenVenta;
			}//Fin del metodo

			//--------------------------SERVICIO WEB PAGO----------------------------------
			/**
			 * Muestra todos los Pagos registrados
			 * @return regresa la lista de objetos Pago
			 */
			@GetMapping("/obtenerPago")
			public List<Pago> findAllPago()
			{
				return pagoService.findAll();
			}//Fin del metodo

			/**
			 * Muestra solo un orden pago
			 * @param ordenPagoid id del orden pago que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto OrdenPago con id ordenPagoid
			 */
			@GetMapping("/obtenerPago/{ordenPagoid}")
			@ResponseBody
			public Pago getPago(@PathVariable("pagoid") int pagoid)
			{
				Pago pago = pagoService.findById(pagoid);
				if(pago == null)
				{
					throw new RuntimeException("pago id not found -"+pagoid);
				}
				return pago;
			}//Fin del metodo

			/**
			 * Este metodo añade un nuevo pago
			 * @param pago objeto de tipo Pago que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Pago añadido
			 */
			@PostMapping("/agregarPago")
			public Pago addPago(@RequestBody Pago pago)
			{
				pagoService.save(pago);
				return pago;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Pago ya registrado
			 * @param pago objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Pago que fue modificado
			 */
			@PutMapping("/modificarPago")
		    public Pago updatePago(@RequestBody Pago pago) 
			{
				Pago pagos = pagoService.findById(pago.getIdPago());
				pagos.setNoPedido(pagos.getNoPedido());
				pagos.setMonto(pagos.getMonto());
				pagos.setNoTarjeta(pagos.getNoTarjeta());
				pagos.setFolioInterbancario(pagos.getFolioInterbancario());
				pagos.setNoAutorizacion(pagos.getNoAutorizacion());
				pagos.setFechaPago(pagos.getFechaPago());
				pagos.setHoraPago(pagos.getHoraPago());
				pagos.setTitularCuenta(pagos.getTitularCuenta());
				pagos.setActivo(true);
				pagos.setFechaCreacion(new Date());
				pagos.setFechaModificacion(pagos.getFechaModificacion());
				pagoService.save(pago);
				return pago;
			}//Fin del metodo
			
			//--------------------------SERVICIO WEB PASE----------------------------------
			/**
			 * Muestra todos los pases registrados
			 * @return regresa la lista de objetos Pase
			 */
			@GetMapping("/obtenerPase")
			public List<Pase> findAllPase()
			{
				return paseService.findAll();
			}//Fin del Metodo

			/**
			 * Muestra solo un pase
			 * @param ordenPaseid id del pase que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Pase con id ordenPaseid
			 */
			@GetMapping("/obtenerPase/{ordenPaseid}")
			@ResponseBody
			public Pase getPase(@PathVariable("paseid") int paseid)
			{
				Pase pase = paseService.findById(paseid);
				if(pase == null)
				{
					throw new RuntimeException("pase id not found -"+paseid);
				}
				return pase;
			}
			
			/**
			 * Este metodo añade un nuevo pase
			 * @param pase objeto de tipo Pase que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Pase añadido
			 */
			@PostMapping("/agregarPase")
			public Pase addPase(@RequestBody Pase pase)
			{
				paseService.save(pase);
				return pase;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Pase ya registrado
			 * @param pase objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Pase que fue modificado
			 */
			@PutMapping("/modificarPase")
		    public Pase updatePase(@RequestBody Pase pase) 
			{
				Pase pases = paseService.findById(pase.getIdPase());
				pases.setIdProdServ(pases.getIdProdServ());
				pases.setFechaCaptura(pases.getFechaCaptura());
				pases.setConcepto(pases.getConcepto());
				pases.setVentaDetalle(pases.getVentaDetalle());
				pases.setActivo(true);
				pases.setFechaCreacion(new Date());
				pases.setFechaModificacion(pases.getFechaModificacion());
				paseService.save(pase);
				return pase;
			}//Fin del metodo

			//--------------------------SERVICIO WEB PEDIDO----------------------------------
			/**
			 * Muestra todos los pedidos registrados
			 * @return regresa la lista de objetos Pedido
			 */
			@GetMapping("/obtenerPedido")
			public List<Pedido> findAllPedido()
			{
				return pedidoService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un pedido
			 * @param ordenPedidoid id del pedido que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Pedido con id ordenPedidoid
			 */
			@GetMapping("/obtenerPedido/{ordenPedidoid}")
			@ResponseBody
			public Pedido getPedido(@PathVariable("pedidoid") int pedidoid)
			{
				Pedido pedido = pedidoService.findById(pedidoid);
				if(pedido == null)
				{
					throw new RuntimeException("pedido id not found -"+pedidoid);
				}
				return pedido;
			}//Fin del metodo

			/**
			 * Este metodo añade un nuevo pedido
			 * @param pedido objeto de tipo Pedido que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Pedido añadido
			 */
			@PostMapping("/agregarPedido")
			public Pedido addPedido(@RequestBody Pedido pedido)
			{
				pedido.setActivo(true);
				pedido.setFechaCreacion(new Date());
				pedido.setFechaModificacion(new Date());
				
				pedidoService.save(pedido);
				return pedido;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un pedido ya registrado
			 * @param pedido objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Pedido que fue modificado
			 */
			@PutMapping("/modificarPedido")
		    public Pedido updatePedido(@RequestBody Pedido pedido) 
			{
				Pedido pedidos = pedidoService.findById(pedido.getNoPedido());
				pedidos.setIdPago(pedidos.getIdPago());
				pedidos.setNoPedido(pedidos.getNoPedido());
				pedidos.setIdCliente(pedidos.getIdCliente());
				pedidos.setURLLiga(pedidos.getURLLiga());
				pedidos.setStatus(pedidos.getStatus());
				pedidos.setCreado(pedidos.getCreado());
				pedidos.setPagoFecha(pedidos.getPagoFecha());
				pedidos.setCanceladoFecha(pedidos.getCanceladoFecha());
				pedidos.setPagado(pedidos.isPagado());
				pedidos.setCancelado(pedidos.isCancelado());
				pedidos.setCorreoCliente(pedidos.getCorreoCliente());
				pedidos.setActivo(true);
				pedidos.setFechaCreacion(new Date());
				pedidos.setFechaModificacion(pedidos.getFechaModificacion());
				pedidoService.save(pedido);
				return pedido;
			}//fIN DEL METODO
			
			//--------------------------SERVICIO WEB PEDIDODETALLE----------------------------------
			/**
			 * Muestra todos los detalles de los pedidos registrados
			 * @return regresa la lista de objetos PedidoDetalle
			 */
			@GetMapping("/obtenerPedidoDetalle")
			public List<PedidoDetalle> findAllPedidoDetalle()
			{
				return pedidoDetalleService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un pedido Detalle
			 * @param ordenPedidoDetalleid id del pedido detalle que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto PedidoDetalle con id ordenPedidoDetalleid
			 */
			@GetMapping("/obtenerPedidoDetalle/{ordenPedidoDetalleid}")
			@ResponseBody
			public PedidoDetalle getPedidoDetalle(@PathVariable("pedidoDetalleid") int pedidoDetalleid)
			{
				PedidoDetalle pedidoDetalle = pedidoDetalleService.findById(pedidoDetalleid);
				if(pedidoDetalle == null)
				{
					throw new RuntimeException("pedidoDetalle id not found -"+pedidoDetalleid);
				}
				return pedidoDetalle;
			}
			/**
			 * Este metodo añade un nuevo Pedido detalle
			 * @param pedidoDetalle objeto de tipo PedidoDetalle que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto PedidoDetalle añadido
			 */
			@PostMapping("/agregarPedidoDetalle")
			public PedidoDetalle addPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle)
			{
				pedidoDetalleService.save(pedidoDetalle);
				return pedidoDetalle;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Pedido detalle ya registrado
			 * @param pedidoDetalle objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto PedidoDetalle que fue modificado
			 */
			@PutMapping("/modificarPedidoDetalle")
		    public PedidoDetalle updatePedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle) 
			{
				PedidoDetalle pedidosDetalles = pedidoDetalleService.findById(pedidoDetalle.getNoPedido());
				pedidosDetalles.setIdOrdenVentaDetalle(pedidosDetalles.getIdOrdenVentaDetalle());
				pedidosDetalles.setConcepto(pedidosDetalles.getConcepto());
				pedidosDetalles.setCantidad(pedidosDetalles.getCantidad());
				pedidosDetalles.setImporte(pedidosDetalles.getImporte());
				pedidosDetalles.setFechaInicio(pedidosDetalles.getFechaInicio());
				pedidosDetalles.setFechaFin(pedidosDetalles.getFechaFin());
				pedidosDetalles.setIdOrdenDeVenta(pedidosDetalles.getIdOrdenDeVenta());
				pedidosDetalles.setIdProdServ(pedidosDetalles.getIdProdServ());
				pedidosDetalles.setPrecioUnitario(pedidosDetalles.getPrecioUnitario());
				pedidosDetalles.setIdCasillero(pedidosDetalles.getIdCasillero());
				pedidosDetalles.setDescuento(pedidosDetalles.getDescuento());
				pedidosDetalles.setIVA(pedidosDetalles.getIVA());
				pedidosDetalles.setSubImporte(pedidosDetalles.getSubImporte());
				pedidosDetalles.setActivo(true);
				pedidosDetalles.setFechaCreacion(new Date());
				pedidosDetalles.setFechaModificacion(pedidosDetalles.getFechaModificacion());
				pedidoDetalleService.save(pedidoDetalle);
				return pedidoDetalle;
			}//Fin del metodo

			//--------------------------SERVICIO WEB RECIBO----------------------------------
			/**
			 * Muestra todos los recibos registrados
			 * @return regresa la lista de objetos Recibo
			 */
			@GetMapping("/obtenerRecibo")
			public List<Recibo> findAllRecibo()
			{
				return reciboService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un recibo
			 * @param ordenReciboid id del recibo que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Recibo con id ordenReciboid
			 */
			@GetMapping("/obtenerRecibo/{ordenReciboid}")
			@ResponseBody
			public Recibo getRecibo(@PathVariable("reciboid") String reciboid)
			{
				Recibo recibo = reciboService.findById(reciboid);
				if(recibo == null)
				{
					throw new RuntimeException("recibo id not found -"+reciboid);
				}
				return recibo;
			}//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo Recibo
			 * @param recibo objeto de tipo Recibo que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Recibo añadido
			 */
			@PostMapping("/agregarRecibo")
			public Recibo addRecibo(@RequestBody Recibo recibo)
			{
				reciboService.save(recibo);
				return recibo;
			}//Fin del metodo

			/**
			 * Metodo que modifica un Recibo ya registrado
			 * @param recibo objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Recibo que fue modificado
			 */
			@PutMapping("/modificarRecibo")
		    public Recibo updateRecibo(@RequestBody Recibo recibo) 
			{
				Recibo recibos = reciboService.findById(recibo.getFolio());
				recibos.setIdCliente(recibos.getIdCliente());
				recibos.setImporte(recibos.getImporte());
				recibos.setFecha(recibos.getFecha());
				recibos.setActivo(true);
				recibos.setFechaCreacion(new Date());
				recibos.setFechaModificacion(recibos.getFechaModificacion());
				reciboService.save(recibo);
				return recibo;
			}//Fin del metodo

			//--------------------------SERVICIO WEB VENTA----------------------------------
			/**
			 * Muestra todos las ventas registrados
			 * @return regresa la lista de objetos Venta
			 */
			@GetMapping("/obtenerVenta")
			public List<Venta> findAllVenta()
			{
				return ventaService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un venta
			 * @param ordenVentaid id del venta que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Venta con id ordenVentaid
			 */
			@GetMapping("/obtenerVenta/{ordenVentaid}")
			@ResponseBody
			public Venta getVenta(@PathVariable("ventaid") int ventaid)
			{
				Venta venta = ventaService.findById(ventaid);
				if(venta == null)
				{
					throw new RuntimeException("venta id not found -"+ventaid);
				}
				return venta;
			}//Fin del metodo

			/**
			 * Este metodo añade un nuevo venta
			 * @param venta objeto de tipo Venta que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Venta añadido
			 */
			@PostMapping("/agregarVenta")
			public Venta addVenta(@RequestBody Venta venta)
			{
				ventaService.save(venta);
				return venta;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Venta ya registrado
			 * @param venta objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Venta que fue modificado
			 */
			@PutMapping("/modificarVenta")
		    public Venta updatePedidoDetalle(@RequestBody Venta venta) 
			{
				Venta ventas = ventaService.findById(venta.getIdVenta());
				ventas.setIdCliente(ventas.getIdCliente());
				ventas.setConcepto(ventas.getConcepto());
				ventas.setImporteOV(ventas.getImporteOV());
				ventas.setCancelado(ventas.isCancelado());
				ventas.setIdOrdenDeVentaDetalle(ventas.getIdOrdenDeVentaDetalle());
				ventas.setFechaInicio(ventas.getFechaInicio());
				ventas.setFechaFin(ventas.getFechaFin());
				ventas.setMedioDePago(ventas.getMedioDePago());
				ventas.setImportePago(ventas.getImportePago());
				ventas.setFechaAplicacion(ventas.getFechaAplicacion());
				ventas.setFolioRecibo(ventas.getFolioRecibo());
				ventas.setEstaPagado(ventas.isEstaPagado());
				ventas.setCuotaTipo(ventas.getCuotaTipo());
				ventas.setFamilia(ventas.getFamilia());
				ventas.setGrupo(ventas.getGrupo());
				ventas.setCategoria(ventas.getCategoria());
				ventas.setSubcategoria(ventas.getSubcategoria());
				ventas.setProgramacionTipo(ventas.getProgramacionTipo());
				ventas.setClienteTipo(ventas.getClienteTipo());
				ventas.setMembresiaTipo(ventas.getMembresiaTipo());
				ventas.setActivo(true);
				ventas.setFechaCreacion(new Date());
				ventas.setFechaModificacion(ventas.getFechaModificacion());
				ventaService.save(venta);
				return venta;
			}//Fin del metodo
			
			//--------------------------SERVICIO WEB CATEGORIA----------------------------------
			/**
			 * Muestra todos las categorias registrados
			 * @return regresa la lista de objetos Categoria
			 */
			@GetMapping("/obtenerCategoria")
			public List<Categoria> findAllCategoria()
			{
				return categoriaService.findAll();
			}//Fin del metodo

			/**
			 * Muestra solo un Categoria
			 * @param ordenCategoriaid id del categoria que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Categoria con id ordenCategoriaid
			 */
			@GetMapping("/obtenerCategoria/{ordenCategoriaid}")
			@ResponseBody
			public Categoria getCategoria(@PathVariable("categoriaid") int categoriaid)
			{
				Categoria categoria = categoriaService.findById(categoriaid);
				if(categoria == null)
				{
					throw new RuntimeException("categoria id not found -"+categoriaid);
				}
				return categoria;
			}//Fin del metodo

			/**
			 * Este metodo añade un nuevo categoria
			 * @param categoria objeto de tipo Categoria que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Categoria añadido
			 */
			@PostMapping("/agregarCategoria")
			public Categoria addCategoria(@RequestBody Categoria categoria)
			{
				categoriaService.save(categoria);
				return categoria;
			}//FIn del metodo
			
			/**
			 * Metodo que modifica un Categoria ya registrado
			 * @param categoria objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Categoria que fue modificado
			 */
			@PutMapping("/modificarCategoria")
		    public Categoria updateCategoria(@RequestBody Categoria categoria) 
			{
				Categoria categorias = categoriaService.findById(categoria.getId());
				categorias.setNombre(categorias.getNombre());
				categorias.setActivo(true);
				categorias.setFechaCreacion(new Date());
				categorias.setFechaModificacion(categorias.getFechaModificacion());
				categoriaService.save(categoria);
				return categoria;
			}//FIn del metodo
			
			//--------------------------SERVICIO WEB ESTATUSCLIENTE----------------------------------
			/**
			 * Muestra todos los Estatus cliente registrados
			 * @return regresa la lista de objetos EstatusCliente
			 */
			@GetMapping("/obtenerEstatusCliente")
			public List<EstatusCliente> findAllEstatusCliente()
			{
				return estatusClienteService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un Estatus cliente
			 * @param ordenCategoriaid id del estatus cliente que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto EstatusCliente con id ordenCategoriaid
			 */
			@GetMapping("/obtenerEstatusCliente/{ordenCategoriaid}")
			@ResponseBody
			public EstatusCliente getEstatusCliente(@PathVariable("estatusClienteid") int estatusClienteid)
			{
				EstatusCliente estatusCliente = estatusClienteService.findById(estatusClienteid);
				if(estatusCliente == null)
				{
					throw new RuntimeException("estatusCliente id not found -"+estatusClienteid);
				}
				return estatusCliente;
			}//Fin del metodo

			/**
			 * Este metodo añade un nuevo estatus cliente
			 * @param estatusCliente objeto de tipo EstatusCliente que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto EstatusCliente añadido
			 */
			@PostMapping("/agregarEstatusCliente")
			public EstatusCliente addEstatusCliente(@RequestBody EstatusCliente estatusCliente)
			{
				estatusClienteService.save(estatusCliente);
				return estatusCliente;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Estatus Cliente ya registrado
			 * @param estatusCliente objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto EstatusCliente que fue modificado
			 */
			@PutMapping("/modificarEstatusCliente")
		    public EstatusCliente updateEstatusCliente(@RequestBody EstatusCliente estatusCliente) 
			{
				EstatusCliente estatusCategorias = estatusClienteService.findById(estatusCliente.getIdStatusCliente());
				estatusCategorias.setNombre(estatusCategorias.getNombre());
				estatusCategorias.setActivo(true);
				estatusCategorias.setFechaCreacion(new Date());
				estatusCategorias.setFechaModificacion(estatusCategorias.getFechaModificacion());
				estatusClienteService.save(estatusCliente);
				return estatusCliente;
			}//Fin del metodo
			
//--------------------------SERVICIO WEB TIPO MEMBRESIA----------------------------------
			/**
			 * Muestra todos los Tipos de membresia registrados
			 * @return regresa la lista de objetos TipoMembresia
			 */
			@GetMapping("/obtenerTipoMembresia")
			public List<TipoMembresia> findAllTipoMembresia()
			{
				return tipoMembresiaService.findAll();
			}//Fin del metodo
			
			/**
			 * Muestra solo un Tipo membresia
			 * @param  id del tipo membresia que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto TipoMembresia con id {id}
			 */
			@GetMapping("/obtenerTipoMembresia/{id}")
			@ResponseBody
			public TipoMembresia getTipoMembresia(@PathVariable("id") int id)
			{
				TipoMembresia tipoMembresia = tipoMembresiaService.findById(id);
				if(tipoMembresia == null)
				{
					throw new RuntimeException("TipoMembresia id not found -"+id);
				}
				return tipoMembresia;
			}//Fin del metodo

			/**
			 * Este metodo añade un nuevo Tipo membresia
			 * @param tipoMembresia objeto de tipo TipoMembresia que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto TipoMembresia añadido
			 */
			@PostMapping("/agregarTipoMembresia")
			public TipoMembresia addTipoMembresia(@RequestBody TipoMembresia tipoMembresia)
			{
				tipoMembresiaService.save(tipoMembresia);
				return tipoMembresia;
			}//Fin del metodo
			
			/**
			 * Metodo que modifica un Tipo Membresia ya registrado
			 * @param tipoMembresia objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto TipoMembresia que fue modificado
			 */
			@PutMapping("/modificarTipoMembresia")
		    public TipoMembresia updateTipoMembresia(@RequestBody TipoMembresia tipoMembresia) {
				tipoMembresiaService.save(tipoMembresia);
		        //este metodo actualizará al usuario enviado
				tipoMembresia.setFechaModificacion(new Date());
		        return tipoMembresia;
			}//FIn del metodo
			
//--------------------------SERVICIO WEB CLIENTE----------------------------------
			/**
			 * Muestra todos los Clientes registrados
			 * @return regresa la lista de objetos Cliente
			 */
			@GetMapping("/obtenerCliente")
		    public List<Cliente> findAllCliente(){
		        return clienteService.findAll();
		    }//Fin del metodo
			
			/**
			 * Muestra solo un cliente
			 * @param  horarioId del cliente que se quiere mostrar, en caso de no encontrarse se genera RuntimeException
			 * @return regresa el objeto Cliente con id horarioId
			 */
			@GetMapping("/obtenerCliente/{horarioId}")
			@ResponseBody
		    public Cliente getCliente(@PathVariable("horarioId") int horarioId){
		        Cliente cliente = clienteService.findById(horarioId);
		        if(cliente == null) {
		            throw new RuntimeException("cliente id not found -"+horarioId);
		        }
		        //retornará al usuario con id pasado en la url
		        return cliente;
		    }//Fin del metodo
			
			/**
			 * Este metodo añade un nuevo Cliente
			 * @param cliente objeto de tipo Cliente que se va a agregar a la base de datos, en caso de tener un id ya registrado solo se actualizan los valores
			 * @return objeto Cliente añadido
			 */
			@PostMapping("/agregarCliente")
		    public Cliente addCliente(@RequestBody Cliente cliente) throws ParseException {
		        //Este metodo guardará al usuario enviado
				cliente.setFechaCreacion(new Date());
				cliente.setFechaModificacion(new Date());
				cliente.setActivo(true);

				
				Club club=clubService.findById(cliente.getClub().getIdClub());
				if(club==null) {
					cliente.getClub().setFechaCreacion(new Date());
					cliente.getClub().setFechaModificacion(new Date());
					cliente.getClub().setActivo(true);
				}else {
					cliente.setClub(club);
				}
				
				TipoMembresia tipoMembresia=tipoMembresiaService.findById(cliente.getTipoMembresia().getIdTipoMembresia());
				if(tipoMembresia==null) {
					cliente.getTipoMembresia().setFechaCreacion(new Date());
					cliente.getTipoMembresia().setFechaModificacion(new Date());
					cliente.getTipoMembresia().setActivo(true);
					HorarioAcceso horarioAcceso=horarioAccesoService.findById(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
					if(horarioAcceso==null) {
						cliente.getTipoMembresia().getHorarioacceso().setFechaCreacion(new Date());
						cliente.getTipoMembresia().getHorarioacceso().setFechaModificacion(new Date());
						cliente.getTipoMembresia().getHorarioacceso().setActivo(true);
						
						Horario horario=cliente.getTipoMembresia().getHorarioacceso().getHorario();
						horario.setIdHorario(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.setFechaCreacion(new Date());
						horario.setFechaModificacion(new Date());
						horario.setActivo(true);

						horario.getIdLunes().setIdLunes(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdLunes().setActivo(true);
						horario.getIdLunes().setFechacreacion(new Date());
						horario.getIdLunes().setFechamodificacion(new Date());

						horario.getIdMartes().setIdMartes(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdMartes().setActivo(true);
						horario.getIdMartes().setFechacreacion(new Date());
						horario.getIdMartes().setFechamodificacion(new Date());


						horario.getIdMiercoles().setIdMiercoles(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdMiercoles().setActivo(true);
						horario.getIdMiercoles().setFechacreacion(new Date());
						horario.getIdMiercoles().setFechamodificacion(new Date());

						horario.getIdJueves().setIdJueves(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdJueves().setActivo(true);
						horario.getIdJueves().setFechacreacion(new Date());
						horario.getIdJueves().setFechamodificacion(new Date());

						horario.getIdViernes().setIdViernes(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdViernes().setActivo(true);
						horario.getIdViernes().setFechacreacion(new Date());
						horario.getIdViernes().setFechamodificacion(new Date());

						horario.getIdSabado().setIdSabado(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdSabado().setActivo(true);
						horario.getIdSabado().setFechacreacion(new Date());
						horario.getIdSabado().setFechamodificacion(new Date());

						horario.getIdDomingo().setIdDomingo(cliente.getTipoMembresia().getHorarioacceso().getIdHorarioAcceso());
						horario.getIdDomingo().setActivo(true);
						horario.getIdDomingo().setFechacreacion(new Date());
						horario.getIdDomingo().setFechamodificacion(new Date());
					}else {
						cliente.getTipoMembresia().setHorarioacceso(horarioAcceso);
					}
				}else {
					cliente.setTipoMembresia(tipoMembresia);
				}
				
				TipoCliente tipoCliente=tipoClienteService.findById(cliente.getTipoCliente().getIdTipoCliente());
				if(tipoCliente==null) {
					cliente.getTipoCliente().setFechaCreacion(new Date());
					cliente.getTipoCliente().setFechaModificacion(new Date());
					cliente.getTipoCliente().setActivo(true);
				}else {
					cliente.setTipoCliente(tipoCliente);
				}
				
				Categoria categoria=categoriaService.findById(cliente.getCategoria().getId());
				if(categoria==null) {
					cliente.getCategoria().setFechaCreacion(new Date());
					cliente.getCategoria().setFechaModificacion(new Date());
					cliente.getCategoria().setActivo(true);
				}else {
					cliente.setCategoria(categoria);
				}
				
				EstatusCliente estatusCliente=estatusClienteService.findById(cliente.getEstatusCliente().getIdStatusCliente());
				if(estatusCliente==null) {
					cliente.getEstatusCliente().setFechaCreacion(new Date());
					cliente.getEstatusCliente().setFechaModificacion(new Date());
					cliente.getEstatusCliente().setActivo(true);
				}else {
					cliente.setEstatusCliente(estatusCliente);
				}
				
				EstatusMembresia estatusMembresia=estatusMembresiaService.findById(cliente.getEstatusMembresia().getIdEstatusMembresia());
				if(estatusMembresia==null) {
					cliente.getEstatusMembresia().setFechaCreacion(new Date());
					cliente.getEstatusMembresia().setFechaModificacion(new Date());
					cliente.getEstatusMembresia().setActivo(true);
				}else {
					cliente.setEstatusMembresia(estatusMembresia);
				}
				
				EstatusCobranza estatusCobranza=estatusCobranzaService.findById(cliente.getEstatusCobranza().getIdEstatusCobranza());
				if(estatusCobranza==null) {
					cliente.getEstatusCobranza().setFechaCreacion(new Date());
					cliente.getEstatusCobranza().setFechaModificacion(new Date());
					cliente.getEstatusCobranza().setActivo(true);
				}else {
					cliente.setEstatusCobranza(estatusCobranza);
				}
				if(cliente.getMensajes()!=null) {
					List<Mensajes> mensajes=cliente.getMensajes();
					int longitud=mensajes.size();
					for(int i=0;i<longitud;i++) {
						Mensajes mensaje=mensajesService.findById(mensajes.get(i).getIdMensaje());
						if(mensaje==null) {
							cliente.getMensajes().get(i).setFechaCreacion(new Date());
							cliente.getMensajes().get(i).setFechaModificacion(new Date());
							cliente.getMensajes().get(i).setActivo(true);
							cliente.getMensajes().get(i).setCliente(cliente.getIdCliente());
							cliente.getMensajes().add(mensaje);

						}else {
							cliente.getMensajes().set(i, mensaje);
						}
					}
				} 
				 List<HorarioOtroClub> horarioOtroClubs=cliente.getHorarioOtroClub();
				if(cliente.getHorarioOtroClub()!=null) {
					int longitud=horarioOtroClubs.size();
					for(int i=0;i<longitud;i++) {
						HorarioOtroClub horarioOtroClub=horarioOtroClubService.findById(new HorarioOtroClubId(cliente.getHorarioOtroClub().get(i).getTerminalId(),cliente.getHorarioOtroClub().get(i).getDesde(),cliente.getHorarioOtroClub().get(i).getHasta()));
						if(horarioOtroClub==null) {
							cliente.getHorarioOtroClub().get(i).setFechaCreacion(new Date());
							cliente.getHorarioOtroClub().get(i).setFechaModificacion(new Date());
							cliente.getHorarioOtroClub().get(i).setActivo(true);
							HorarioAcceso horarioAcceso=horarioAccesoService.findById(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
							if(horarioAcceso==null) {
								cliente.getHorarioOtroClub().get(i).getHorarioacceso().setFechaCreacion(new Date());
								cliente.getHorarioOtroClub().get(i).getHorarioacceso().setFechaModificacion(new Date());
								cliente.getHorarioOtroClub().get(i).getHorarioacceso().setActivo(true);
								
								Horario horario=cliente.getHorarioOtroClub().get(i).getHorarioacceso().getHorario();

								horario.setIdHorario(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.setFechaCreacion(new Date());
								horario.setFechaModificacion(new Date());
								horario.setActivo(true);

								horario.getIdLunes().setIdLunes(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdLunes().setActivo(true);
								horario.getIdLunes().setFechacreacion(new Date());
								horario.getIdLunes().setFechamodificacion(new Date());

								horario.getIdMartes().setIdMartes(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdMartes().setActivo(true);
								horario.getIdMartes().setFechacreacion(new Date());
								horario.getIdMartes().setFechamodificacion(new Date());


								horario.getIdMiercoles().setIdMiercoles(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdMiercoles().setActivo(true);
								horario.getIdMiercoles().setFechacreacion(new Date());
								horario.getIdMiercoles().setFechamodificacion(new Date());

								horario.getIdJueves().setIdJueves(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdJueves().setActivo(true);
								horario.getIdJueves().setFechacreacion(new Date());
								horario.getIdJueves().setFechamodificacion(new Date());

								horario.getIdViernes().setIdViernes(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdViernes().setActivo(true);
								horario.getIdViernes().setFechacreacion(new Date());
								horario.getIdViernes().setFechamodificacion(new Date());

								horario.getIdSabado().setIdSabado(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdSabado().setActivo(true);
								horario.getIdSabado().setFechacreacion(new Date());
								horario.getIdSabado().setFechamodificacion(new Date());

								horario.getIdDomingo().setIdDomingo(cliente.getHorarioOtroClub().get(i).getHorarioacceso().getIdHorarioAcceso());
								horario.getIdDomingo().setActivo(true);
								horario.getIdDomingo().setFechacreacion(new Date());
								horario.getIdDomingo().setFechamodificacion(new Date());
							}else {
								cliente.getHorarioOtroClub().get(i).setHorarioacceso(horarioAcceso);
							}
						}else {
							cliente.getHorarioOtroClub().set(i, horarioOtroClub);
						}
					}
				}
				System.out.println("Cliente ("+cliente.getIdCliente()+"): "+cliente.getNombre());
				
				for(int i=0;i<cliente.getHorarioOtroClub().size();i++ ) {
					HorarioOtroClubId auxHorario1=new HorarioOtroClubId(cliente.getHorarioOtroClub().get(i).getTerminalId(),
							cliente.getHorarioOtroClub().get(i).getDesde(),cliente.getHorarioOtroClub().get(i).getHasta());
					for(int j=i+1;j<cliente.getHorarioOtroClub().size();j++) {
						HorarioOtroClubId auxHorario2=new HorarioOtroClubId(cliente.getHorarioOtroClub().get(j).getTerminalId(),
								cliente.getHorarioOtroClub().get(j).getDesde(),cliente.getHorarioOtroClub().get(j).getHasta());
						if(auxHorario1.getTerminalId()==auxHorario2.getTerminalId() && auxHorario1.getDesde().equals(auxHorario1.getDesde() )&& auxHorario1.getHasta().equals(auxHorario2.getHasta())) {
							cliente.getHorarioOtroClub().remove(j);
						}
					}
				}
				clienteService.save(cliente);
				
		        return cliente;

		    }
			/**
			 * Metodo que modifica un Cliente ya registrado
			 * @param cliente objeto que se desea modificar, en caso de no existir se crea uno nuevo
			 * @return objeto Cliente que fue modificado
			 */
			@PutMapping("/modificarCliente")
		    public Cliente updateCliente(@RequestBody Cliente cliente) {
				clienteService.save(cliente);
		        //este metodo actualizará al usuario enviado
		        cliente.setFechaModificacion(new Date());
		        return cliente;
		    }//FIn del metodo
}

