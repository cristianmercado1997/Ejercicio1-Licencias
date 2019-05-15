package clases.licencias;

import clases.Licencia;
import java.time.LocalDate;

public class LicenciaTemporal extends Licencia {
    private LocalDate fechaCaducidad;
    private boolean caducada;
    public LicenciaTemporal(String email, String servicio, long meses) {
        super(email, servicio);
        this.fechaCaducidad = this.fechaCreacion;
        this.fechaCaducidad = fechaCaducidad.plusMonths(meses);
    }
    
    public LicenciaTemporal(String email, String servicio) {
        this(email, servicio, 3);
    }

    public boolean isCaducada() {
        LocalDate fechaActual = LocalDate.now();
        return (fechaActual.isEqual(fechaCaducidad) || fechaActual.isAfter(fechaCaducidad));
    }
    
    public void extenderFechaCaducidad(long meses) { 
        this.fechaCaducidad = fechaCaducidad.plusMonths(meses); 
    }

    @Override
    public boolean licenciaAplicable(Licencia licencia) {
        return !isCaducada();
    }  

    @Override
    public String toString() {
        return super.toString() + "LicenciaTemporal{" + "fechaCaducidad=" + fechaCaducidad + ", caducada=" + caducada + '}';
    }

    @Override
    public LicenciaTemporal clone() {
        LicenciaTemporal lt = (LicenciaTemporal) super.clone();
        return lt;
    }
}