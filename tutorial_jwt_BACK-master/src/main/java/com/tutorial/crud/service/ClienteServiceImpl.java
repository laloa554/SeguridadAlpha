/*Y la clase ClienteServiceImpl.java, será implementada por la interfaz anterior. 
 * Le añadiremos la anotación @Service, para indicar que es un servicio y 
 * también de @Autowired para inyectar nuestro DAO y hacer uso de él:
 *	@autor: Daniel García Velasco 
 * 			Abimael Rueda Galindo
 *	@version: 1
 *12/07/2021
*/
package com.tutorial.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.crud.aopDao.ClienteDAO;
import com.tutorial.crud.entity.Cliente;
@Service //marca la clase java que realiza algún servicio 
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired  //Inyecta a nuestro DAO y lo utiliza. 
	private ClienteDAO clienteDAO;
	//Método en el cual manda a llamar categoriaDAO y le asigna lo que tenga a la lista. 
	@Override 
	public List<Cliente> findAll() {
		List<Cliente> listCliente= clienteDAO.findAll();
        return listCliente;
	}
	//Método en el que se inserta el ID del DAO
	@Override
	public Cliente findById(int clave) {
		Cliente cliente = clienteDAO.findById(clave);
        return cliente;
	}
	//Guarda todo lo de la lista al DAO 
	@Override
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);		
	}

}
