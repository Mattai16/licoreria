package mx.uv.empleado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.empleados.RegistrarEmpleadoRequest;
import https.t4is_uv_mx.empleados.RegistrarEmpleadoResponse;
import https.t4is_uv_mx.empleados.BuscarEmpleadoResponse;
import https.t4is_uv_mx.empleados.BuscarEmpleadoRequest;
import https.t4is_uv_mx.empleados.BorrarEmpleadoRequest;
import https.t4is_uv_mx.empleados.BorrarEmpleadoResponse;

@Endpoint
public class EndPoint {

    private List<Empleado> listaEmpleados = new ArrayList<>();

    @PayloadRoot(localPart = "RegistrarEmpleadoRequest", namespace = "https://t4is.uv.mx/empleados")
    @ResponsePayload
    public RegistrarEmpleadoResponse Registrar(@RequestPayload RegistrarEmpleadoRequest peticion) {
        Empleado empleado = new Empleado();
        empleado.setNumeroTrabajador(peticion.getNumeroTrabajador());
        empleado.setNombre(peticion.getNombre());
        empleado.setApellidoP(peticion.getApellidoP());
        empleado.setApellidoM(peticion.getApellidoM());
        empleado.setTelefono(peticion.getTelefono());

        listaEmpleados.add(empleado);

        RegistrarEmpleadoResponse respuesta = new RegistrarEmpleadoResponse();
        respuesta.setResultado("El empleado " + empleado.getNombre() + " " + empleado.getApellidoP()
                + " ha sido registrado exitosamente");
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarEmpleadoRequest", namespace = "https://t4is.uv.mx/empleados")
    @ResponsePayload
    public BuscarEmpleadoResponse Buscar(@RequestPayload BuscarEmpleadoRequest peticion) {
        BuscarEmpleadoResponse respuesta = new BuscarEmpleadoResponse();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getNumeroTrabajador().equals(peticion.getNumeroTrabajador())) {
                respuesta.setNombre(empleado.getNombre());
                respuesta.setApellidoP(empleado.getApellidoP());
                respuesta.setApellidoM(empleado.getApellidoM());
                respuesta.setTelefono(empleado.getTelefono());
                break;
            }else{
                respuesta.setNombre("No econtrado");
            }
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarEmpleadoRequest", namespace = "https://t4is.uv.mx/empleados")
    @ResponsePayload
    public BorrarEmpleadoResponse BorrarEmpleado(@RequestPayload BorrarEmpleadoRequest peticion) {
        BorrarEmpleadoResponse respuesta = new BorrarEmpleadoResponse();

        String nombreEmpleado = peticion.getNombre();

        // Borrar el empleado de la lista
        boolean encontrado = listaEmpleados.removeIf(empleado -> empleado.getNombre().equals(nombreEmpleado));

        if (encontrado) {
            respuesta.setResultado("El empleado " + nombreEmpleado + " ha sido eliminado exitosamente");
        } else {
            respuesta.setResultado("El empleado " + nombreEmpleado + " no ha sido encontrado");
        }

        return respuesta;
    }

}
