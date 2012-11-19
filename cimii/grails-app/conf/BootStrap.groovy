import cimii.*

class BootStrap {

    def init = { servletContext ->
		
		int cantidadEstaciones = 10
		def linea = new Linea(nombre: "Linea CIM II",frecuencia:new Frecuencia(frecuenciaStandard:300,cantidadPersonasEnRed:5000,frecuenciaActual:300), sistemaDeMolinetes: new SistemaDeMolinetes(flujo:50))
		linea.save(failOnError: true)		
		
		List list = new ArrayList()
		for(int i = 0;i<cantidadEstaciones;i++){
			def estacion = new Estacion(ubicacion: 1100*i-i*i*100+i*i*i*10,nombre: "Estacion "+(i+1),estado:"CORRECTO",linea: linea)
			estacion.save(failOnError: true)
			list.add(estacion)
		}
	
		int i = 0
		for(i = 0;i<cantidadEstaciones-1;i++){
			def trayecto = new Trayecto(linea: linea,estacion1:list[i],estacion2:list[i+1],estado:"CORRECTO")		
			trayecto.save(failOnError: true)
		}			
	
		def tren = new Tren(enEstacion:true,linea:linea,lugar: list[2],velocidadMaxima: 20,aceleracionMaxima: 5,ubicacionActual:list[2].ubicacion,velocidadActual:0)
		tren.save(failOnError: true)
		
		tren = new Tren(enEstacion:true,linea:linea,lugar: list[4],velocidadMaxima: 22,aceleracionMaxima: 6,ubicacionActual:list[4].ubicacion,velocidadActual:0)
		tren.save(failOnError: true)
		
		tren = new Tren(enEstacion:true,linea:linea,lugar: list[7],velocidadMaxima: 22,aceleracionMaxima: 6,ubicacionActual:list[7].ubicacion,velocidadActual:0)
		tren.save(failOnError: true)
		
		tren = new Tren(enEstacion:true,linea:linea,lugar: list[9],velocidadMaxima: 20,aceleracionMaxima: 5,ubicacionActual:list[9].ubicacion,velocidadActual:0)
		tren.save(failOnError: true)
		
		}
    def destroy = {
    }
}
