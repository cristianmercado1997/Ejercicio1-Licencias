package main;

import clases.*;
import clases.licencias.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programa {

    public static void main(String[] args) {

        LicenciaTemporal lct = new LicenciaTemporal("juan@um.es", "http://api.um.es/disco/v1/", 1);
        LicenciaTransaccionesLimitadas lcl = new LicenciaTransaccionesLimitadas("pepe@um.es", "http://api.um.es/disco/v1/", 3);
        LicenciaLimiteDiario lmd = new LicenciaLimiteDiario("paco@um.es", "http://api.um.es/disco/v1/", 4, 1);


        List<Licencia> lista = new ArrayList<>();
        lista.add(lct);
        lista.add(lcl);
        lista.add(lmd);

        for (Licencia l : lista) {
            System.out.println(l.toString());
        }
        
        
        for(Licencia l : lista) {
            if(l instanceof LicenciaLimiteDiario) {
                System.out.println("Transacciones Restantes Hoy: " + ((LicenciaLimiteDiario) l).getTransaccionesPorDia(LocalDate.now()));
            } else if(l instanceof LicenciaTemporal) {
                l.revocar();
            }
        }

        
        for (Licencia l : lista) {
            for (int i = 0; i < 4; i++) {
                Transaccion t = l.getAutorizacionServicio(l);
                if (t != null) {
                    System.out.println(t.toString());
                } else {
                    System.out.println("NO AUTORIZADA");
                }
            }
            System.out.println("\n------------------\n");
        }

        
        for (Licencia l : lista) {
            System.out.println(l.toString());
        }

        
        List<Licencia> copias = new ArrayList<>();
        System.out.println(".:COPIAS:.");
        for (Licencia l : lista) {
            copias.add(l.clone());
        }

        for (Licencia l : copias) {
            System.out.println(l.toString());
        }
    }
}
