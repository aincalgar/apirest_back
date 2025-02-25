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

    @Override
    public Producto obtenerProductoPorId(Long id) {
        ArrayList<Producto> productos = this.obtenerTodosProductos();
        for (Producto p:productos) { //por cada producto de productos
            if (p.getId() == id){
                return p;
            }
        }
        return new Producto();
    }

    @Override
    public Producto crearProducto(Producto p) {
        ArrayList<Producto> productos = this.obtenerTodosProductos();
        //Crear el ID
        Long id = 1L;
        if (!productos.isEmpty()){ //si el array de productos no está vacío
            id =productos.get(productos.size() - 1).getId() + 1; //hacemos que el id sea autoincremental
        }
        p.setId(id);
        //Array de productos (añadir el producto)
        productos.add(p);
        //Guardar el JSON (mapear el arrayList en un JSON)
        try {
            File archivo = new File(getClass().getClassLoader().getResource(FILE_PATH).toURI());
            objectMapper.writeValue(archivo,productos);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public Producto actualizarProducto(Long id, Producto p) {
        //Producto productoEncontrado = obtenerProductoPorId(id);
        ArrayList<Producto> productos = obtenerTodosProductos();
        Producto productoEncontrado = null;
        for (Producto prod: productos){
            if (prod.getId() == id){
                // Actualizamos el array con el producto modificado
                prod.setNombre(p.getNombre());
                prod.setPrecio(p.getPrecio());
                productoEncontrado = prod;
                break;
            }
        }
        if (productoEncontrado != null && productoEncontrado.getId() == id){
            // Guardamos en el JSON
            try {
                File archivo = new File(getClass().getClassLoader().getResource(FILE_PATH).toURI());
                objectMapper.writeValue(archivo,productos);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return productoEncontrado;
    }

    @Override
    public Producto borrarProducto(Long id) {
        ArrayList<Producto> productos = obtenerTodosProductos(); // 1º - volcamos JSON en un array
        Producto productoBorrado = null;
        for (Producto prod: productos){ //2º - encontrar ID en Array
            if (prod.getId() == id){
                //3º - borrar item del Array
                productos.remove(prod);
                productoBorrado = prod;
                break;
            }
        }
        if (productoBorrado != null && productoBorrado.getId() == id){
            //4º - Guardamos en el JSON
            try {
                File archivo = new File(getClass().getClassLoader().getResource(FILE_PATH).toURI());
                objectMapper.writeValue(archivo,productos);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return productoBorrado; //5º - return item borrado
    }
}
