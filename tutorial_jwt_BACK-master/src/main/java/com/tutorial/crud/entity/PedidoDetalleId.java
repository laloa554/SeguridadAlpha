/*En este paquete tenemos nuestro clase PedidoDetalle.java y utilizaremos las @anotaciones 
 * JPA para relacionarla con nuestra tabla PedidoDetalle, quedaría de la siguiente forma:
 *	@autor: Daniel García Velasco 
 * 			Abimael Rueda Galindo
 *	@version: 1
 *12/07/2021
*/

package com.tutorial.crud.entity;

import java.io.Serializable;

public class PedidoDetalleId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int NoPedido;
	private int IdOrdenVentaDetalle;
	public PedidoDetalleId() {
		
	}
	public PedidoDetalleId(int noPedido, int idOrdenVentaDetalle) {
		NoPedido = noPedido;
		IdOrdenVentaDetalle = idOrdenVentaDetalle;
	}
	public int getNoPedido() {
		return NoPedido;
	}
	public void setNoPedido(int noPedido) {
		NoPedido = noPedido;
	}
	public int getIdOrdenVentaDetalle() {
		return IdOrdenVentaDetalle;
	}
	public void setIdOrdenVentaDetalle(int idOrdenVentaDetalle) {
		IdOrdenVentaDetalle = idOrdenVentaDetalle;
	}
	@Override
	public String toString() {
		return "PedidoDetalleId [NoPedido=" + NoPedido + ", IdOrdenVentaDetalle=" + IdOrdenVentaDetalle + "]";
	}
	
	
	
	
	
}
