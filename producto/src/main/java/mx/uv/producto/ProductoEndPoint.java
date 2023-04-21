package mx.uv.producto;


import https.uv_mx.producto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class ProductoEndPoint {

    @Autowired
    IProducto iProducto;
    @PayloadRoot(localPart = "RegistrarProductoRequest", namespace = "https://uv.mx/producto")
    @ResponsePayload
    public RegistrarProductoResponse registrarProducto(@RequestPayload RegistrarProductoRequest peticion)
    {
        RegistrarProductoResponse respuesta = new RegistrarProductoResponse();
        Producto producto = new Producto();
        iProducto.save(producto);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarProductosRequest", namespace = "https://uv.mx/producto")
    @ResponsePayload
    public BuscarProductosResponse buscarProductosResponse()
    {
        BuscarProductosResponse respuesta = new BuscarProductosResponse();
        Iterable <Producto> listaProductos = iProducto.findAll();
        for (Producto o : listaProductos)
        {
            BuscarProductosResponse.Producto e = new BuscarProductosResponse.Producto();
            e.setId(o.getId());
            respuesta.getProducto().add(e);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarProductoRequest", namespace = "https://uv.mx/producto")
    @ResponsePayload
    public ModificarProductoResponse modificarProductoResponse(@RequestPayload ModificarProductoRequest peticion)
    {
        ModificarProductoResponse respuesta = new ModificarProductoResponse();
        Producto e = new Producto();
        e.setId(peticion.getId());
        e.setNombre(peticion.getNombre());
        e.setMarca(peticion.getMarca());
        e.setPrecio(peticion.getPrecio());
        e.setCantidad(peticion.getCantidad());
        iProducto.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }
    
    @PayloadRoot(localPart = "BorrarProductoRequest", namespace = "https://uv.mx/producto")
    @ResponsePayload
    public BorrarProductoResponse borrarProducto(@RequestPayload BorrarProductoRequest peticion)
    {
        BorrarProductoResponse respuesta = new BorrarProductoResponse();
        iProducto.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }
}
