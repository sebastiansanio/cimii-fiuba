package cimii

/**
 * Linea
 * A domain class describes the data object and it's mapping to the database
 */
class Linea {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	String nombre
	
//	static belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
	static hasOne		= [frecuencia: Frecuencia,sistemaDeMolinetes: SistemaDeMolinetes]
	static hasMany		= [trenes: Tren, estaciones: Estacion,trayectos:Trayecto]
	
    static mapping = {
    }
    
	static constraints = {
    	nombre blank:false
	}
	
	public String  toString(){
		return nombre
	}

	public String verificarCaminoDespejado(Lugar lugar){
		def estacion = lugar
				
		
		ArrayList list = new ArrayList()
		def trayectos = Trayecto.findAll().each{
			list.add(it)
		}
		
		def trayecto = null
		for(int i=0;i<list.size;i++){
			if( list[i].estacion1.id == estacion.id)
				 trayecto = list[i]
		}
		
		if(trayecto.verificarTrayectoDespejado()=="")
			if(trayecto.estacion2.verificarEstacionDespejada()=="")
				return "CORRECTO"
			else
				return "ESTACION SIGUIENTE -"+trayecto.estacion2.verificarEstacionDespejada()
		else
			return "TRAYECTO SIGUIENTE-"+trayecto.verificarTrayectoDespejado()
	}
	
	public float obtenerLongitud(){
		float longitud = 0;
		estaciones.each{estacion ->
			if(estacion.ubicacion>longitud)
				longitud=estacion.ubicacion
		}
		return longitud
	}
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
