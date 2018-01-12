package com.feluma.faleconosco.util.jsf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    
	private static final String DESTINATION_DIR_PATH = "C:"+File.separator+"wildfly"+File.separator+"welcome-content"+File.separator+"arquivos"+File.separator+"concurso"+File.separator+"imagem"+File.separator;
	
	private File destinationDir;  
       
	public void init(ServletConfig config) throws ServletException {  
        super.init(config);  
        destinationDir = new File(DESTINATION_DIR_PATH);  
        if (!destinationDir.isDirectory()) {  
            throw new ServletException(DESTINATION_DIR_PATH + " is not a directory");  
        }  
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String nome_arquivo = request.getParameter("nome_arquivo");
	  final  String caminho = DESTINATION_DIR_PATH+nome_arquivo;
	 
	  try {
	      File file = new File(caminho);
	     
	      if(!file.exists()){           
	        	System.err.println(" Não foi possivel encontrar o arquivo na url informada.");
	      }
	   
	         FileInputStream fis = new FileInputStream(file);
	         @SuppressWarnings("resource")
			 BufferedInputStream bis = new BufferedInputStream(fis);
	         byte[] bytes = new byte[bis.available()];
	         response.setContentType("image/jpg");
	         ServletOutputStream os = response.getOutputStream();
	         bis.read(bytes);
	         os.write(bytes);      
	      }
	  
      catch(IOException e){
          System.err.println(e.getMessage());
      }
      catch(Exception e){
    	  System.err.println(e.getMessage());
      }
	}

}
