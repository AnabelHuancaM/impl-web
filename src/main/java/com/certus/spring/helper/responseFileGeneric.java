package com.certus.spring.helper;

public class responseFileGeneric {

	private boolean estado;
	private String mensaje;
	private Byte[] fileBytes;
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileContent) {
		this.fileBytes = fileContent;
	}

	
}
