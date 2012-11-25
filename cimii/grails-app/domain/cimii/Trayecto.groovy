package cimii

/**
 * Trayecto
 * A domain class describes the data object and it's mapping to the database
 */
class Trayecto extends Lugar{

	/* Default (injected) attributes of GORM */
	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
	static belongsTo	= [linea:Linea,estacion1:Estacion,estacion2:Estacion] 
	
    static mapping = {
		tablePerHierarchy false
    }
    
	static constraints = {
		estado blank:false, inList:["Correcto","Hay obstaculo","Falta corriente electrica"]
		estacion1 blank:false,unique:true
		estacion2 blank:false,unique:true
    }
	
	public String verificarTrayectoDespejado(){
				
		String hayTren = ""
		def trenes = Tren.findAll()
		ArrayList list = new ArrayList()
		
		trenes.each{tren ->
			list.add(tren)
		}
		for (int i =0;i<list.size;i++){
			if (list[i].lugar.id == this.id)
				hayTren = "Tren detenido"
		}
		if(estado == "Correcto")
			return hayTren
		else{
			if(hayTren=="")
				return estado
			else
				return estado + " + " + hayTren
		}
			
	}
	
	public float obtenerDistancia(){
		def distancia = estacion2.ubicacion-estacion1.ubicacion
		return distancia
	}
	
	public String toString(){
		return "Trayecto: " + estacion1.toString() + " - " + estacion2.toString()
		
	}
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
