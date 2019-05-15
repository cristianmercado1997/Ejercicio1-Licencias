package clases.licencias;

import clases.Licencia;

public class LicenciaTransaccionesLimitadas extends Licencia {
    protected final int numTransacciones;
    public LicenciaTransaccionesLimitadas(String email, String servicio, int numTransacciones) {
        super(email, servicio);
        this.numTransacciones = numTransacciones;
    }
    
  
    public int getTransaccionesRestantes() { 
        return (this.numTransacciones - super.getNumeroTransacciones()); 
    }

    @Override
    public boolean licenciaAplicable(Licencia licencia) {
        return (this.getTransaccionesRestantes() > 0);
    }

    @Override
    public String toString() {
        return super.toString() + "LicenciaTransaccionesLimitadas{" + "numTransacciones=" + numTransacciones + '}';
    }

    @Override
    public Licencia clone() {
        LicenciaTransaccionesLimitadas ltl = (LicenciaTransaccionesLimitadas) super.clone();
        return ltl;
    }
}
