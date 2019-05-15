package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Licencia implements Cloneable {
    protected final String email;
    protected final LocalDate fechaCreacion;
    protected final String codigo;
    protected List<Transaccion> transacciones;
    protected boolean revocada = false;
    protected final String servicio;
    protected Licencia(String email, String servicio) {
        this.email = email;
        this.servicio = servicio;
        this.fechaCreacion = LocalDate.now();
        this.codigo = UUID.randomUUID().toString();
        this.transacciones = new ArrayList<>();
        this.revocada = false;
    }
    
   
    public String getEmail() { return email; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public String getCodigo() { return codigo; }
    public List<Transaccion> getTransacciones() { return transacciones; }
    public String getServicio() { return servicio; }
    public boolean isRevocada() { return revocada; }
    public int getNumeroTransacciones() { return this.transacciones.size(); }
   
    
    
    public void revocar() { this.revocada = true; }
    public abstract boolean licenciaAplicable(Licencia licencia);
    public Transaccion getAutorizacionServicio(Licencia licencia) {
        if(!revocada && licenciaAplicable(licencia)) {
            Transaccion tr = new Transaccion(licencia);
            this.transacciones.add(tr);
            return tr;
        }
        
        return null;
    }

    @Override
    public Licencia clone()  {
        try {
            Licencia lc = (Licencia) super.clone();
            lc.transacciones = new ArrayList<>(lc.transacciones);
            return lc;
        } catch(CloneNotSupportedException e) {
            System.err.println("ERROR EN LA CLONACION: " + e.getMessage());
        }
        
        return null;
    }
}