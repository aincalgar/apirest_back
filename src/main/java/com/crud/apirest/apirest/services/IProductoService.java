package com.crud.apirest.apirest.services;

import com.crud.apirest.apirest.models.Producto;

import java.util.ArrayList;

public interface IProductoService {
    ArrayList<Producto> obtenerTodosProductos();
    Producto obtenerProductoPorId(Long id);
    Producto crearProducto(Producto p);
}
