package mx.uv.anteiku;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.productos.RegistrarProductoRequest;
import https.t4is_uv_mx.productos.RegistrarProductoResponse;
import https.t4is_uv_mx.productos.BorrarProductoRequest;
import https.t4is_uv_mx.productos.BorrarProductoResponse;






@Endpoint
public class EndPoint {
   
    @Autowired
    private IProducto iProducto;

    @PayloadRoot(localPart = "RegistrarProductoRequest", namespace = "https://t4is.uv.mx/productos")
    @ResponsePayload
    public RegistrarProductoResponse Registrar(@RequestPayload RegistrarProductoRequest peticion){
        RegistrarProductoResponse respuesta = new RegistrarProductoResponse();
        respuesta.setRespuesta("El Producto " +peticion.getNombre()+" ha sido agregado a la base");
        Producto producto = new Producto();
        producto.setNombre(peticion.getNombre());
        producto.setMarca(peticion.getMarca);
        producto.setPrecio(peticion.getPrecio());
        producto.setCantidad(peticion.getCantidad);


        iProducto.save(producto);
        return respuesta;

    
    }

    @PayloadRoot(localPart = "BuscarProductoRequest", namespace = "https://t4is.uv.mx/productos")
    @ResponsePayload
    public BuscarProductoResponse Buscar(@RequestPayload BuscarProductoRequest peticion){
        BuscarProductoResponse respuesta = new BuscarProductoResponse();
        Optional<Vendedor> x = iProducto.findById(peticion.getId());
        Producto y = x.get();
        respuesta.setRespuesta("Se ha encontrado el Producto: "+y.getNombre());
        return respuesta;
    }

}