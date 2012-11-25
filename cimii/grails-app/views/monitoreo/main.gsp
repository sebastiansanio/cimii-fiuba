
<!doctype html>
<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="monitoreolay" />
	<title><g:message value="Sistema de monitoreo de subterráneos"  /></title>
</head>

<body>
	
<h3>${linea.nombre }</h3>
<h5>Avisos</h5>

<g:each in="${linea.trenes.sort{it.ubicacionActual}}">


<g:if test="${it.estado!='Correcto' }" >
<div class="row">
<div class="alert alert-error">
<strong>Problema detectado!</strong>${" Tren "+it.id+" : "+it.estado}
</div>
</div>	
</g:if>
</g:each>

<g:each in="${linea.estaciones.sort{it.ubicacion}}">
<g:if test="${it.estado!='Correcto' }" >
<div class="row">
<div class="alert alert-error">
<strong>Problema detectado!</strong>${" "+it.id+" : "+it.estado}
</div>
</div>	
</g:if>
</g:each>

<g:each in="${linea.trayectos.sort{it.estacion1.ubicacion}}">
<g:if test="${it.estado!='Correcto' }" >
<div class="row">
<div class="alert alert-error">
<strong>Problema detectado!</strong>${" "+it.toString()+" : "+it.estado}
</div>
</div>	
</g:if>
</g:each>



	
	
	<h5>Estaciones</h5>
	<div class="row">	
	<div class="progress">
	<g:set var="ubicacionanterior" value="${0 }" />
	<g:each in="${linea.estaciones.sort{it.ubicacion}}">
    <div class="bar bar-info" style="width:${((it.ubicacion-ubicacionanterior)/linea.obtenerLongitud()*100)-0.56 }%;"></div>
    <div class="bar bar-danger" style="width:0.5%;"></div>
    <g:set var="ubicacionanterior" value="${it.ubicacion }" />
	</g:each>
	</div>
	</div>

	<h5>Trenes</h5>
	<div class="row">	
	<div class="progress">
	<g:set var="ubicacionanterior" value="${0 }" />
	<g:each in="${linea.trenes.sort{it.ubicacionActual}}">
    <div class="bar bar-info" style="width:${((it.ubicacionActual-ubicacionanterior)/linea.obtenerLongitud()*100)-0.56}%;"></div>
    
    <g:if test="${it.enEstacion() == true}">
    <div class="bar bar-warning" style="width:0.5%;"></div>
    </g:if>
    <g:if test="${it.enEstacion() == false}">
    <div class="bar bar-danger" style="width:0.5%;"></div>
    </g:if>  
    
    <g:set var="ubicacionanterior" value="${it.ubicacionActual }" />
	</g:each>
	
	<div class="bar bar-info" style="width:${100-100*ubicacionanterior/linea.obtenerLongitud()-0.51}%;"></div>
	
	</div>
	
	

	<div class="span3">
	<h5>Estaciones</h5>
	
	<table class="span3">
	
	<tr>
	 <td>Nombre</td>
	 <td>Distancia</td>
	  <td>Estado</td>
	</tr>
	
	<g:each in="${linea.estaciones.sort{it.ubicacion}}">
	<tr>
    <td>${it.nombre}</td>
    <td>${it.ubicacion}m</td>
     <td>${it.estado}</td>
    </tr>
	</g:each>
	</table>
	
	
	</div>
	
	<div class="span8">
	<h5>Trenes</h5>
	
		
	<table class="span8">
	
	<tr>
	 <td>Tren</td>
	 <td> </td>
	 <td>Ubicacion</td>
	 <td>Distancia</td>
	 <td>Velocidad</td>
	 <td>Estado general</td>
	 <td>Estado de puertas</td>
	</tr>
	
	<g:each in="${linea.trenes.sort{it.ubicacionActual}}">
	<tr>
    <td>${it.id}<td>
    <td>${it.lugar}</td>
    <td>${Math.round(it.ubicacionActual*100)/100}m</td>
    <td>${Math.round(it.velocidadActual*100)/100}m/s</td>
    <td>${it.estado}
    </td>    
	<td>${it.obtenerEstadoPuertas()}</td>
   </tr>
   

    </g:each>


     </table>
	</div>
	
		
	<div class="span4">
	<h5>Frecuencia</h5>
    <p>Actual: Un tren cada: ${linea.frecuencia.frecuenciaActual} segundos </p>
    <p>Standard: Un tren cada: ${linea.frecuencia.frecuenciaStandard} segundos </p>
	<p>Estimado de pasajeros en la linea: ${linea.frecuencia.cantidadPersonasEnRed}
    </p>
	</div>

</div>	
<script>
var timer = setTimeout('autoReload()',500);
function autoReload(){
	window.location.reload(true);
}

</script>

</body>




</html>
