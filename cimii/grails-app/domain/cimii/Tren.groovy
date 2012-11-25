package cimii

/**
 * Tren
 * A domain class describes the data object and it's mapping to the database
 */
class Tren {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
	float velocidadMaxima
	float ubicacionActual
	float velocidadActual
	float aceleracionMaxima
	boolean puertasCerradas = true
	boolean noCerrarPuertas = false
	int cantidadReintentosCierreDePuertas = 0
	int tiempoEntreIntentos
	int tiempoEnEstacion = 0
	float aceleracion = 0
	String estado = "Correcto"
	float distanciaRestante
	float distanciaTotal
	boolean enEstacion
	
	
	static belongsTo	= [linea:Linea,lugar:Lugar]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.	
	
    static mapping = {
    }
    
	static constraints = {
		velocidadMaxima blank:false
		aceleracionMaxima blank:false
		linea blank:false
		lugar blank:false
		ubicacionActual blank:false
		velocidadActual blank:false
    }
	
	public void abrirPuertas(){
		puertasCerradas = false
	} 
	
	public boolean  verificarPuertasCerradas(){
		puertasCerradas
	}
	
	public String obtenerEstadoPuertas(){
		if(puertasCerradas==true){
			return "CERRADAS"
		}
		else{
			return "ABIERTAS"
		}
		
	}

	
	public void cerrarPuertas(){
		if(noCerrarPuertas == false)
			puertasCerradas = true
	}

	public boolean enEstacion(){
		return enEstacion
		
	}
	
	
	public String vivir(){
		
		String resultado
		if(enEstacion()){
			
			if(tiempoEnEstacion > 0){
				if(puertasCerradas==true)
					abrirPuertas()
				tiempoEnEstacion = tiempoEnEstacion -1
			}
			if(tiempoEnEstacion <= 0){
				if(puertasCerradas==true){
					return avanzarALaSiguienteEstacion();
				}else{
					if(tiempoEntreIntentos<=0){
						cerrarPuertas()
						cantidadReintentosCierreDePuertas = cantidadReintentosCierreDePuertas -1
						if(puertasCerradas==false){
							tiempoEntreIntentos = 2
							if(cantidadReintentosCierreDePuertas <= 0)
								estado = "No se pueden cerrar las puertas"
							
						}
					}else{
						tiempoEntreIntentos = tiempoEntreIntentos - 1
					}					
					
				}
				
							
			}	
				
		}
		else{
			actualizarAceleracion()
			ejecutarMovimiento()


		}
		return ""
	}
	

	
		
	public void avanzarALaSiguienteEstacion(){
		
		ArrayList list = new ArrayList()
				
		def trayectos = Trayecto.findAll().each{
			list.add(it)
		}
		
		def trayecto = null
		for(int i=0;i<list.size;i++){
			if( list[i].estacion1.id == lugar.id)
				 trayecto = list[i]
		}
				
		if (trayecto == null){
			ubicacionActual = 0
			enEstacion = true
			velocidadActual = 0
			aceleracion = 0
			lugar = Estacion.get(1)
			return
		}
		
		
		if(verificarPuertasCerradas()==true){
			estado = linea.verificarCaminoDespejado(lugar)
			if(estado=="Correcto"){
				lugar = trayecto
				enEstacion = false
				distanciaRestante = trayecto.obtenerDistancia()
				distanciaTotal = trayecto.obtenerDistancia()
				calcularMovimiento()
				ejecutarMovimiento()

			}
				
		}
		
	}
	
	int tiempoAceleracion
	int tiempoVelocidadConstante
	float aceleracionCalculada
	
	public void calcularMovimiento(){
		int frecuencia = (int)(linea.frecuencia.frecuenciaActual)
		
		tiempoAceleracion = (int) (frecuencia*0.1)
		def velocidadCrucero = distanciaTotal/(frecuencia-tiempoAceleracion)
		aceleracionCalculada = velocidadCrucero/tiempoAceleracion
			
		
		tiempoVelocidadConstante = frecuencia - 2*tiempoAceleracion
		actualizarAceleracion()
		
	}
	
	public void actualizarAceleracion(){
		if(tiempoAceleracion>0){
			tiempoAceleracion = tiempoAceleracion - 1
			aceleracion=aceleracionCalculada
		}
		else{
			if(tiempoVelocidadConstante>0){
				tiempoVelocidadConstante = tiempoVelocidadConstante - 1
				aceleracion = 0
			}
			else{
				aceleracion = -aceleracionCalculada
			}
				
		}
		
		
	}
	
	public void ejecutarMovimiento(){
		
		if(!enEstacion()){
			
			if(distanciaRestante - velocidadActual >= 0){
				distanciaRestante = distanciaRestante - velocidadActual
				ubicacionActual = ubicacionActual+velocidadActual
				
				velocidadActual = Math.max(velocidadActual + aceleracion, 0.3)
			}
			else{
				distanciaRestante = 0
				velocidadActual = 0
				aceleracion = 0
				def trayecto = lugar
				enEstacion = true
				Estacion nuevaEstacion = trayecto.estacion2
				lugar = nuevaEstacion
				ubicacionActual = nuevaEstacion.ubicacion
				tiempoEnEstacion = 5
				cantidadReintentosCierreDePuertas = 5
				tiempoEntreIntentos = 2
			}
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
