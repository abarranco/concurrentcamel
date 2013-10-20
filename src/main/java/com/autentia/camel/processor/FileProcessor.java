package com.autentia.camel.processor;

import java.io.File;
import java.io.FileInputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileProcessor implements Processor {
	
	private final static Logger log = LoggerFactory.getLogger(FileProcessor.class); 

	@SuppressWarnings("rawtypes")
	public void process(Exchange exchange) throws Exception {
		
		
		final File file = (File) ((GenericFile) exchange.getIn().getBody()).getFile();
		log.info("***** START processing file : " + file.getName());
		final FileInputStream fileInputStream = new FileInputStream(file);;
		try {
			
			byte[] charsReaded = new byte[2048];
			while (fileInputStream.read(charsReaded) != -1) {
				System.out.println("-- Leída línea del fichero " + file.getName());
			}
		} finally {
			fileInputStream.close();
		}
		log.info("***** END processing file : " + file.getName());
	}
}