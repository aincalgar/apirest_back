package com.crud.apirest.apirest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")//Aplica a todas las rutas que tengamos en el backend
                .allowedOrigins("http://localhost:5500","http://127.0.0.1:5500") //Origenes permitidos
                .allowedMethods("GET","POST","PUT","PATCH","DELET","OPTIONS")
                .allowedHeaders("*") //Permite cualquier encabezado
                .allowCredentials(true) //Enviar COOKIES
                .maxAge(3600);//Duración máxima de la caché de CORS
    }

}