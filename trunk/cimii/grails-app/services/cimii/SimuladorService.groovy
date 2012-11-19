package cimii

/**
 * SimuladorService
 * A service class encapsulates the core business logic of a Grails application
 */
class SimuladorService {

    static transactional = true
	static int contadorSimulacion = 0

    def serviceMethod() {

    }
	
	public String simular(Linea linea){
		
		if((contadorSimulacion % 20)==0){
			linea.frecuencia.recalcularFrecuencia(linea.sistemaDeMolinetes.obtenerFlujo())
		}
		contadorSimulacion = contadorSimulacion + 1
			
		linea.trenes.each{tren ->
			tren.vivir();
	
		}
		return ""
		
	}
	
}
