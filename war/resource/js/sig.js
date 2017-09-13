var map;
google.maps.event.addDomListener(window, 'load', initialize);

var coordenadasMapa;

window.onload = function() {
	cargaInicial('Departamentos.json');
	

};

var InfoWindow = new google.maps.InfoWindow();

var marker;

function initialize() {

	var mapOptions = {
		center : new google.maps.LatLng(4.283435, -74.22404),
		zoom : 5,
		minZoom : 4,
		maxZoom : 17,
		mapTypeId : google.maps.MapTypeId.HYBRID,
		icon: './resource/img/iconM.png'
	};
	map = new google.maps.Map(document.getElementById("map"), mapOptions);

	var featureStyle = {
		fillColor : 'grey',
		fillOpacity : 0.8,
		strokeWeight : 1,
		strokeColor : '#FF8000'
	}
		
	map.data.addListener('mouseover', function(event){
		document.getElementById('info-box').textContent = event.feature.getProperty('nom_ent');
		
	});
	
	
	map.data.addListener('mouseout', function(event) {
	});
	
	map.data.addListener('click',function(event) {
						
						
						map.data.revertStyle();
						map.data.overrideStyle(event.feature, {
							strokeWeight : 4,
							strokeColor : 'red',
							fillColor : '#005454',
							fillOpacity : 0.3,							
						});		
						
						//document.getElementById('gwtContainer').textContent = event.feature.getProperty('nom_ent');
						
						
						var nombreEntidad = event.feature.getProperty("nom_ent");
						var idEntidad = event.feature.getProperty("id_entidad");
						placeMarker(event.latLng);

						if (idEntidad == "000") {
							confirm("No hay información Georreferenciada de este elemento");				
						} else {
							window.refrescarDatosJS(idEntidad);
							InfoWindow.setContent(nombreEntidad);
							window.inicializarInfoWindows(idEntidad);
							window.SuggestText(idEntidad, nombreEntidad);
						}
						InfoWindow.open(map, marker);

						var bounds = new google.maps.LatLngBounds();
						processPoints(event.feature.getGeometry(),
						bounds.extend, bounds);
						map.fitBounds(bounds);					

					});
	map.data.setStyle(featureStyle);
}


function placeMarker(location) {
	if (marker) {
		marker.setPosition(location);					
	} else {
		marker = new google.maps.Marker({
			position : location,
			map : map,
			visible: false,							
		});
		
	}
}


/**
 * Centrar Mapa
 */
function centerMap(idEntidad) {
	
	var elementsButton = document.getElementsByName(capa);
	var capa;
	
	for (var i = 0; elementsButton.length; i++) {
		if (elementsButton[i].checked) {
			capa = elementsButton[i].value;
		} 
	}

	var boundsCenter = new google.maps.LatLngBounds();
	var featureMap = map.data.getFeatureById(idEntidad);
	var geometryCenter = featureMap.getGeometry();
	
	map.data.revertStyle();
	map.data.overrideStyle(featureMap, {
		strokeWeight : 4,
		strokeColor : 'red',
		fillColor : '#005454',
		fillOpacity : 0.3,
		icon: './resource/img/iconM.png'
	});

	var nombreEntidad = featureMap.getProperty("nom_ent");
	var codEnt= featureMap.getProperty("nom_ent");
	
	processPoints(geometryCenter, boundsCenter.extend, boundsCenter);
	map.fitBounds(boundsCenter);
	
	window.refrescarDatosJS(idEntidad);
	InfoWindow.setContent(nombreEntidad);
	window.inicializarInfoWindows(idEntidad);	
	placeMarker(map.getCenter());
	InfoWindow.open(map, marker);
	
	

}


/**
 * Determina los limites de los poligonos
 * 
 * @param geometry
 * @param callback
 * @param thisArg
 */
function processPoints(geometry, callback, thisArg) {
	if (geometry instanceof google.maps.LatLng) {
		callback.call(thisArg, geometry);
	} else if (geometry instanceof google.maps.Data.Point) {
		callback.call(thisArg, geometry.get());
	} else {
		geometry.getArray().forEach(function(g) {
			processPoints(g, callback, thisArg);
		});
	}
}


function botonResetMap() {
	initialize();
	cargaInicial('Departamentos.json');
	document.getElementById("DEP").checked = true;
	window.refrescarDatosJS(000);
	loadListaEntidades("Departamentos.json");
	document.getElementById("busqueda").innerHTML= "Buscar Gobernación:";
	SuggestionSettext();
	
	
}

function labelbuscar(tipo) {
	if(tipo == "Municipios.json"){
		document.getElementById("busqueda").innerHTML = "Buscar Alcaldía:"
		document.getElementById("inf").innerHTML = "INFORMACIÓN DE LA ALCALDÍA"
	}else{if(tipo == "Departamentos.json"){
		document.getElementById("busqueda").innerHTML= "Buscar Gobernación:"		
		document.getElementById("inf").innerHTML= "INFORMACIÓN DE LA GOBERNACIÓN"	
	}else{if(tipo == "CoorR.json")
		document.getElementById("busqueda").innerHTML= "Buscar Entidad:"
		document.getElementById("inf").innerHTML= "INFORMACIÓN DE LA ENTIDAD"	
}}}




function cargarCapa2(tipo) {
	InfoWindow.close();
	window.refrescarDatosJS(000);
	labelbuscar(tipo);
	initialize();
	map.data.loadGeoJson(tipo);
	window.loadListaEntidades(tipo);
	marker = new google.maps.Marker({
		position : location,
		map : map,
		visible: false,					
	});	
	
}
 
function cargaInicial(tipo) {
	initialize();
	map.data.loadGeoJson(tipo); 
	document.getElementById("DEP").checked = true;
			
}

function MostrarOcultarcoor(tipo){		
	InfoWindow.close();
	document.getElementById("DEP").checked = false;
	document.getElementById("MUN").checked = false;
	window.refrescarDatosJS(000);
	labelbuscar(tipo);
	initialize();
	map.data.loadGeoJson(tipo);
	window.loadListaEntidades(tipo);	
	map.data.setStyle({	
		icon: './resource/img/iconM.png',		
	});	
	marker = new google.maps.Marker({
		position : location,
		map : map,
		visible: false,		
	});
}


function botonConsult() {
	InfoWindow.close();		
	window.exportarCodigo();	
}




