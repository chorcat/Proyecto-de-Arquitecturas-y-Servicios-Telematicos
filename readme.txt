Instrucciones para la compilaci�n e instalaci�n del proyecto en modo no distribuido.(Todos los archivos se encuentran dentro del AST_C4.zip)

1-Copie las librer�as situadas en la carpeta lib en las siguientes rutas(Sobreescribir si es necesario):
-Ra�z_axis2_bin/lib
-Ra�z_Tomcat/webbaps/axis2/WEB-INF/lib


2-Crear en la ruta -Ra�z_Tomcat/webbaps/axis2/WEB-INF/classes dos directorios:
-gestor(En el cual introducimos la clase Libros.class)
-autentificador(En el cual introducimos la clase Usuarios.class)

3-Copiar en la ruta -Ra�z_Tomcat/webbaps/axis2/WEB-INF/classes/META-INF uddi.xml

4-Compilar los tres servicios con la herramienta ant.

5-Copiar los tres .aar generados en la ruta -Ra�z_Tomcat/webbaps/axis2/WEB-INF/services

6-Copiar en la ruta -Ra�z_Tomcat/conf/server.xml(Sobreescribir si es necesario).
-El nuevo puerto del tomcat ser� el 8181, dejando el puerto 8080 para el juddi.

7-Copiar en la ruta -Ra�z_Tomcat/webbaps/axis2/WEB-INF/conf el archivo axis2.xml.

8-Copiar en la ruta -Ra�z_Tomcat/webbaps/axis2/WEB-INF/modules 
-loggingModule.mar(M�dulo para imprimir mensajes soap)
-loginModule.mar(M�dulo del handler)

