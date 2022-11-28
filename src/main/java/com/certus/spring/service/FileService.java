package com.certus.spring.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.config.MvcConfig;
import com.certus.spring.helper.responseFileGeneric;
import com.certus.spring.models.ResponseFile;
import com.certus.spring.service.inteface.IFileGeneric;
import com.certus.spring.service.inteface.IHelper;

@Component
public class FileService implements IFileGeneric {
	
	
	@Autowired
	MvcConfig config;
	
	@Autowired
	IHelper helperFile;

	@Override
	public ResponseFile crearFile(MultipartFile fileGeneric) {		
		ResponseFile respuesta =  new ResponseFile();
		//responseFileGeneric rfg = new FileService();
		
		String NewExtention = StringUtils.getFilenameExtension(fileGeneric.getOriginalFilename());
		String newName = UUID.randomUUID().toString() + "." + NewExtention;
		
				
		try {
			byte[] bytesFile = fileGeneric.getBytes();//arreglo de bytes
			Path enlaceAGuardar = Paths.get(config.pathImage()+"//"+newName);
			Files.write(enlaceAGuardar, bytesFile);
			
			respuesta.setEstado(true);
			respuesta.setNombreFile(newName);
			
		} catch (IOException e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileGeneric.getOriginalFilename());
			respuesta.setMensajeError(e.getStackTrace().toString());
		}						
		
		return respuesta;
	}
	
	@Override
	public ResponseFile crearFileAPI(String fileBase64) {		
		
		ResponseFile respuesta =  new ResponseFile();
		
		responseFileGeneric rfg = new responseFileGeneric();
		
		String NewExtention = StringUtils.getFilenameExtension(fileGeneric.getOriginalFilename());
		
		String newName = UUID.randomUUID().toString() + "." + NewExtention;
		
				
		try {
			//byte[] bytesFile = helperFile.procesarFile(fileBase64);
			byte[] bytesFile = null;
			rfg = helperFile.procesarFile(fileBase64);
			
			if (rfg.isEstado()) {
				bytesFile = rfg.getFileBytes();
			}
			
			Path enlaceAGuardar = Paths.get(config.pathImage()+"//"+newName);
			Files.write(enlaceAGuardar, bytesFile);
			
			respuesta.setEstado(true);
			respuesta.setNombreFile(newName);
			
		} catch (IOException e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileGeneric.getOriginalFilename());
			respuesta.setMensajeError(e.getStackTrace().toString());
		}						
		
		return respuesta;
	}


	@Override
	public ResponseFile eliminarFile(String fileName) {
		
		ResponseFile respuesta =  new ResponseFile();
		
		Path enlaceGuardado = Paths.get(config.pathImage()+"\\"+fileName);
		
		try {
			File fileEliminar = enlaceGuardado.toFile();
			if (fileEliminar.exists()) {
				fileEliminar.delete();
				
				respuesta.setEstado(true);
				respuesta.setNombreFile(fileName);
				
			}else {
				respuesta.setEstado(false);
				respuesta.setNombreFile(fileName);
				respuesta.setMensajeError("No se encontró el archivo");
			}
			
		} catch (Exception e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileName);
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
		return respuesta;
	}

}
