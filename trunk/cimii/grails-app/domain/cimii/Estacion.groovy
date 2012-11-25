package cimii

/**
 * Estacion
 * A domain class describes the data object and it's mapping to the database
 */
class Estacion extends Lugar{

	/* Default (injected) attributes of GORM */
	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
	String nombre
	float ubicacion
	
	static belongsTo	= [linea: Linea]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static mappedBy		= []	// specifies which property should be used in a mapping 

	
    static mapping = {
		tablePerHierarchy false
    }
    
	static constraints = {
		nombre blank:false
		ubicacion blank:false
		estado blank:false,inList:["Correcto","Hay obstaculo","Falta corriente electrica"]
	}
	
	public String toString(){
		return nombre
	}
	public String verificarEstacionDespejada(){
		
		String hayTren = ""
		def trenes = Tren.findAll()
		ArrayList list = new ArrayList()
		
		trenes.each{tren ->
			list.add(tren)
		}
		for (int i =0;i<list.size;i++){
			if (list[i].lugar.id == this.id)
				hayTren = "Hay tren detenido"
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
	
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
