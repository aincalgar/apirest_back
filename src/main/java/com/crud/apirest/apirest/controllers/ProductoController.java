package com.crud.apirest.apirest.controllers;

import com.crud.apirest.apirest.models.Producto;
import com.crud.apirest.apirest.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping
    public ArrayList<Producto> obtenerTodos() {
        return productoService.obtenerTodosProductos();
    }
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id){
        return productoService.obtenerProductoPorId(id);
    }
    @PostMapping
    public Producto newProducto(@RequestBody Producto p){
        return productoService.crearProducto(p);
    }
}
