package com.crud.apirest.apirest.services;

import com.crud.apirest.apirest.models.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService  implements IProductoService{
    private static final String FILE_PATH = "productos.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Obtener todos los productos
    @Override
    public ArrayList<Producto> obtenerTodosProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            File archivo = new File(getClass().getClassLoader().getResource(FILE_PATH).toURI());
            productos = objectMapper.readValue(archivo, new
                    TypeReference<ArrayList<Producto>>(){});
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }
}
