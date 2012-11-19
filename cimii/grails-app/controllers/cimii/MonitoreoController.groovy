package cimii

/**
 * MonitoreoController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class MonitoreoController {

	def simuladorService
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def index () {
		redirect (action:"main")
		
	}
	def main(){
		
		def linea = Linea.get(1)
		simuladorService = new SimuladorService()
		String resultado = ""
		String cantCorridasStr
		
		int cantCorridas=1	
		if(params.id!=null)
			cantCorridasStr=params.id
		
		cantCorridas = Integer.parseInt(cantCorridasStr)
		for (int contador = 0;contador<cantCorridas;contador++){
			resultado=simuladorService.simular(linea)
		}
		
		flash.message=resultado
		
		[linea:linea]
		
	}
	
	def reset(){
		def linea = Linea.get(1)
		def trenes = linea.trenes
		def estaciones = linea.estaciones
		List list = new ArrayList()
		estaciones.sort{it.ubicacion}.each{estacion ->
			list.add(estacion)
		}
		
		
		int i=0
		trenes.each{tren ->
			tren.lugar=list[i]
			tren.ubicacionActual = list[i].ubicacion
			tren.velocidadActual = 0
			tren.enEstacion = true
			i = i + 2
		}
		redirect (action:"main")
		
	}

	
}
