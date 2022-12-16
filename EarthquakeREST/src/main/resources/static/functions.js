/**
 * 
 */
 
 
const Mymap = L.map('EQMap', {
    preferCanvas: true
    }).setView([38, 24], 6);
var myRenderer = L.canvas({ padding: 0.5 });
const attribution = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
const tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
const tiles = L.tileLayer(tileUrl,{attribution});
tiles.addTo(Mymap);
var magnitude;
var year;
var mymarker;
var markerGroup = L.layerGroup().addTo(Mymap);
var menu = document.getElementById("city");
menu.addEventListener("city", getMarker);
var coordinates={lat: 0,lon: 0};
var magnitudes={mag:0};
var dv;
//document.getElementById("latitude").textContent = latitude;
//const apiUrl = 'https://localhost:8080/';
var city;

var MyIcon01 = L.icon({
    iconUrl: '0-1-8.png',
    iconSize:     [10, 10], // size of the icon
    iconAnchor:	[5,5],
    shadowUrl: 'Outline-8.png',
    shadowSize: [12, 12]
});
var MyIcon01Half = L.icon({
    iconUrl: '0-1-8.png',
    iconSize:     [5, 5], // size of the icon
    iconAnchor:	[2.5,2.5],
    shadowUrl: 'Outline-8.png',
    shadowSize: [7, 7]
});
var MyIcon12 = L.icon({
    iconUrl: '2-1-8.png',
    iconSize:     [20, 20], // size of the icon
    iconAnchor:	[10,10],
    shadowUrl: 'Outline-8.png',
    shadowSize: [22, 22]
});
var MyIcon12Half = L.icon({
    iconUrl: '2-1.png',
    iconSize:     [5, 5], // size of the icon
    iconAnchor:	[5,5],
    shadowUrl: 'Outline-8.png',
    shadowSize: [7, 7]
});
var MyIcon23 = L.icon({
    iconUrl: '2-3-8.png',
    iconSize:     [30, 30], // size of the icon
    shadowUrl: 'Outline-8.png',
    shadowSize: [32, 32]
});
var MyIcon23Half = L.icon({
    iconUrl: '2-3.png',
    iconSize:     [5, 5], // size of the icon
    shadowUrl: 'Outline-8.png',
    shadowSize: [7, 7]
});
var MyIcon34 = L.icon({
    iconUrl: '3-4-8.png',
    iconSize:     [40, 40], // size of the icon
     shadowUrl: 'Outline-8.png',
    shadowSize: [42, 42]
});
var MyIcon34Half = L.icon({
    iconUrl: '3-4.png',
    iconSize:     [5, 5], // size of the icon
     shadowUrl: 'Outline-8.png',
    shadowSize: [7, 7]
});
var MyIcon45 = L.icon({
    iconUrl: '4-5-8.png',
    iconSize:     [50, 50], // size of the icon
    shadowUrl: 'Outline-8.png',
    shadowSize: [52, 52]
});
var MyIcon45Half = L.icon({
    iconUrl: '4-5.png',
    iconSize:     [5, 5], // size of the icon
    shadowUrl: 'Outline-8.png',
    shadowSize: [7, 7]
});
var MyIcon5plus = L.icon({
    iconUrl: '5+-8.png',
    iconSize:     [60, 60], // size of the icon
    shadowUrl: 'Outline-8.png',
    shadowSize: [62, 62]
});

var MyIcon5plusHalf = L.icon({
    iconUrl: '5+.png',
    iconSize:     [5, 5], // size of the icon
    iconAnchor:	[15,15],
    shadowUrl: 'Outline-8.png',
    shadowSize: [7, 7]
});
//,{icon:MyIcon}
//const mmarker = L.marker([38, 24]).addTo(Mymap);

async function getMarker(){
	
	//var lat = document.getElementById("latitude").value;
	//var lon = document.getElementById("longitude").value;
	
	city= document.getElementById("city").value;
	
	if(city==="Athens"){
		//document.write(city);
		coordinates.lat = 37.9;
		coordinates.lon = 23.7;
		//alert(1);
	}else
	if(city==="Heraklion"){
		//document.write(city);
		coordinates.lat = 35.3;
		coordinates.lon = 25.1;
		//alert(2);
			
		
	}else
	
	if(city==="Chania"){
		//document.write(city);
		coordinates.lat = 35.5;
		coordinates.lon = 24.0;
		
	}else
	if(city==="Thessaloniki"){
		//document.write(city);
		coordinates.lat = 40.6;
		coordinates.lon = 22.9;
		
	}else
	if(city==="Ioannina"){
		//document.write(city);
		coordinates.lat = 39.6;
		coordinates.lon = 20.8;
		
	}else
	if(document.getElementById("city").value==="Rhodes"){
		//document.write(city);
		coordinates.lat = 36.4;
		coordinates.lon = 28.2;
		
	}else
	if(city==="Larissa"){
		//document.write(city);
		coordinates.lat = 39.6;
		coordinates.lon = 22.4;
		
	}else
	if(city==="Volos"){
		//document.write(city);
		coordinates.lat = 39.3;
		coordinates.lon = 22.9;
		
	}else
	if(city==="Kws"){
		//document.write(city);
		coordinates.lat = 36.8;
		coordinates.lon = 27.1;
		
	}else
	if(city==="Sparta"){
		//document.write(city);
		coordinates.lat = 37.0;
		coordinates.lon = 22.4;
		
	}else
	if(city==="Kalamata"){
		//document.write(city);
		coordinates.lat = 37.0;
		coordinates.lon = 22.1;
		
	}else
	if(city==="Patra"){
		//document.write(city);
		coordinates.lat = 38.2;
		coordinates.lon = 21.7;
		
	}else
	if(city==="Pyrgos"){
		//document.write(city);
		coordinates.lat = 37.6;
		coordinates.lon = 21.4;
	}else
	if(city==="Mesologgi"){
		//document.write(city);
		coordinates.lat = 38.3;
		coordinates.lon = 21.4;
		
	}else
	if(city==="Kerkyra"){
		//document.write(city);
		coordinates.lat = 39.6;
		coordinates.lon = 19.9;
		
	}else
	if(city==="Alexandroupoli"){
		//document.write(city);
		coordinates.lat = 40.8;
		coordinates.lon = 25.8;
		
	}else
	if(city==="Komotini"){
		//document.write(city);
		coordinates.lat = 41.1;
		coordinates.lon = 25.4;
		
	}else
	if(city==="Kavala"){
		//document.write(city);
		coordinates.lat = 40.9;
		coordinates.lon = 24.4;
		
	}else
	if(city==="Serres"){
		//document.write(city);
		coordinates.lat = 41.0;
		coordinates.lon = 23.5;
		
	}else
	if(city==="Kozani"){
		//document.write(city);
		coordinates.lat = 40.3;
		coordinates.lon = 21.7;
		
	}else
	if(city==="Karditsa"){
		//document.write(city);
		coordinates.lat = 39.3;
		coordinates.lon = 21.9;
		//alert(7);
		
	}else
	if(city==="Rethymno"){
		//document.write(city);
		coordinates.lat = 35.3;
		coordinates.lon = 24.4;
	
	}else
	if(city==="Preveza"){
		//document.write(city);
		coordinates.lat = 38.9;
		coordinates.lon = 20.7;
		
	}
	else
	if(city==="Kastoria"){
		//document.write(city);
		coordinates.lat = 40.5;
		coordinates.lon = 21.6;
		
	}
	else
	if(city==="Xanthi"){
		//document.write(city);
		coordinates.lat = 41.1;
		coordinates.lon = 24.8;
		
	}else
	if(city==="Mytilini"){
		//document.write(city);
		coordinates.lat = 39.1;
		coordinates.lon = 26.5;
		//alert(7);
		
	}else
	if(city==="Chios"){
		//document.write(city);
		coordinates.lat = 38.3;
		coordinates.lon = 26.1;
		//alert(7);
		
	}else
	if(city==="Salamina"){
		//document.write(city);
		coordinates.lat = 37.9;
		coordinates.lon = 23.5;
	
	}else
	if(city==="Nafplio"){
		//document.write(city);
		coordinates.lat = 37.5;
		coordinates.lon = 22.8;
	
	}else
	if(city==="Mykonos"){
		//document.write(city);
		coordinates.lat = 37.4;
		coordinates.lon = 25.3;
	
	}else
	if(city==="Agios Nikolaos"){
		//document.write(city);
		coordinates.lat = 35.1;
		coordinates.lon = 25.7;
	
	}else
	if(city==="Argos"){
		//document.write(city);
		coordinates.lat = 37.6;
		coordinates.lon = 22.7;
		
	}else
	if(city==="Piraeus"){
		//document.write(city);
		coordinates.lat = 37.9;
		coordinates.lon = 23.6;
		
	}
	
	
	else{
		coordinates.lat = 0;
		coordinates.lon = 0;
	}
	
	
	
	
}

async function getMagnitude(){
	magnitude=document.getElementById("magnitude").value;
	magnitudes.mag=magnitude;
	
}

async function UpdateYear()
{
	year=document.getElementById("year").value;
	city= document.getElementById("city").value;
	magnitude=document.getElementById("magnitude").value;
	
	var data;
	var api_url;
	//const api_url;
	//const api_url = "http://localhost:8080/Earthquakes/Coordinates?latitude="+coordinates.lat+"&longitude="+coordinates.lon+"";
	if(year!=="" && city!=="" && magnitude==="" ){
		 api_url = "http://localhost:8080/Earthquakes/YearCity?year="+year+"&latitude="+coordinates.lat+"&longitude="+coordinates.lon+"";
		const response = await fetch(api_url);
		data = await response.json();
	}
	else if (city!=="" && year==="" && magnitude==="" ){
		 api_url = "http://localhost:8080/Earthquakes/Coordinates?latitude="+coordinates.lat+"&longitude="+coordinates.lon+"";
		const response = await fetch(api_url);
		data = await response.json();
		
	}else if (year!=="" && magnitude==="" && city===""){
		 api_url = "http://localhost:8080/Earthquakes/Year?year="+year+"";
		const response = await fetch(api_url);
		data = await response.json();
	}else if (magnitude!=="" && city==="" && year===""){
		 api_url = "http://localhost:8080/Earthquakes/Magnitude?magnitude="+magnitude+"";
		const response = await fetch(api_url);
		data = await response.json();
	}else if (magnitude!=="" && city!=="" && year===""){
		 api_url = "http://localhost:8080/Earthquakes/CoordsMagnitude?latitude="+coordinates.lat+"&longitude="+coordinates.lon+"&magnitude="+magnitude+"";
		const response = await fetch(api_url);
		data = await response.json();
	}else if(magnitude!=="" && city!=="" && year!==""){
		api_url = "http://localhost:8080/Earthquakes/YearCityMagnitude?year="+year+"&latitude="+coordinates.lat+"&longitude="+coordinates.lon+"&magnitude="+magnitude+"";
		const response = await fetch(api_url);
		data = await response.json();
	}
	
	else if (magnitude!=="" && city==="" && year!==""){
		const api_url = "http://localhost:8080/Earthquakes/YearMagnitude?year="+year+"&magnitude="+magnitude+"";
		const response = await fetch(api_url);
		data = await response.json();
	}
	
	//const[{date,time,latitude,longitude,depth,magnitude}]=data;
	
	
	
	
		
		for (var i = 0; i < data.length; i++) {
			mymarker = new L.circleMarker([data[i].latitude,data[i].longitude],
				{render: myRenderer})
				.addTo(markerGroup);
				
			if(data[i].magnitude<1){
				mymarker.setStyle({fillColor: '#ADF46E', color: '#ADF46E',fillOpacity: 0.3});
				mymarker.setRadius(5);
				/*mymarker.setIcon(MyIcon01Half);
				Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 9) {
					        layer.setIcon(MyIcon01Half);
					    } else {
					        layer.setIcon(MyIcon01);
					    }
					});
				});*/
			}else if(data[i].magnitude>=1&&data[i].magnitude<2){
				mymarker.setStyle({fillColor: '#DBF471', color: '#DBF471',fillOpacity: 0.3});
				mymarker.setRadius(5);
				/*Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 9) {
					        layer.setRadius(5);
					    } else {
					        layer.setRadius(10);
					    }
					});
				});*/
			}else if(data[i].magnitude>=2&&data[i].magnitude<3){
				mymarker.setStyle({fillColor: '#F0CD6A', color: '#F0CD6A',fillOpacity: 0.3});
				mymarker.setRadius(5);
				/*Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 9) {
					        layer.setRadius(5);
					    } else {
					        layer.setRadius(15);
					    }
					});
				});*/
			}else if(data[i].magnitude>=3&&data[i].magnitude<4){
				mymarker.setStyle({fillColor: '#EFB366', color: '#EFB366',fillOpacity: 0.3});
				mymarker.setRadius(5);
				/*Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 9) {
					        layer.setRadius(5);
					    } else {
					        layer.setRadius(20);
					    }
					});
				});*/
			}else if(data[i].magnitude>=4&&data[i].magnitude<5){
				mymarker.setStyle({fillColor: '#F28E63',color: '#F28E63',fillOpacity: 0.3});
				mymarker.setRadius(5);
				/*Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 9) {
					        layer.setRadius(5);
					    } else {
					        layer.setRadius(25);
					    }
					});
				});*/
			}else if (data[i].magnitude>=5){
				mymarker.setStyle({fillColor: '#F2625F', color: '#F2625F',fillOpacity: 0.3});
				mymarker.setRadius(5);
				
				/*Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 9) {
					        layer.setRadius(5);
					    } else {
					        layer.setRadius(30);
					    }
					});
				});*/
				
			}
		}
		
		Mymap.on('zoomend', function() {
					markerGroup.eachLayer(function(layer) {
					    var currentZoom = Mymap.getZoom();
					    if (currentZoom < 12) {
					        layer.setRadius(3);
					    } else {
					        layer.setRadius(30);
					    }
					});
				});
		
		
    	
		BarChart(data);
	
}

async function ClearMarkers(){
	
	markerGroup.clearLayers();
	
}

async function BarChart(data){

	
	var e1=0,e2=0,e3=0,e4=0,e5=0,e6=0;
	
	
	for(var i=0;i<data.length;i++){
		if(data[i].magnitude<1){
				
				e1++;
				
			}else if(data[i].magnitude>=1&&data[i].magnitude<2){
				/*var obj={};
				var ret = Object.assign({},data[i]);
				dataset.push(ret);*/
				e2++;
				
			}else if(data[i].magnitude>=2&&data[i].magnitude<3){
				
				e3++;
				
			}else if(data[i].magnitude>=3&&data[i].magnitude<4){
				
				e4++;
				
			}else if(data[i].magnitude>=4&&data[i].magnitude<5){
				
				e5++;
				
			}else if (data[i].magnitude>=5){
				
				e6++;
				
			}
	}
	
	
	
		
	var arr = [e1,e2,e3,e4,e5,e6];
	
	document.getElementById("demo3").innerHTML="Number of earthquakes for current query: "+(data.length) ;
	document.getElementById("demo4").innerHTML="Number of earthquakes <=1: "+e1;
	document.getElementById("demo5").innerHTML="Number of earthquakes >1 and <=2: "+e2;
	document.getElementById("demo6").innerHTML="Number of earthquakes >2 and <=3: "+e3;
	document.getElementById("demo7").innerHTML="Number of earthquakes >3 and <=4: "+e4;
	document.getElementById("demo8").innerHTML="Number of earthquakes >4 and <=5: "+e5;
	document.getElementById("demo9").innerHTML="Number of earthquakes >5: "+e6;
	
	
	 const wi=1000,he=500,radius=Math.min(wi,he)/2;
	 //const bw = (wi/dataset.length);
	 
	 dv = d3.select("svg.d33")
		 .attr("width",wi)
		 .attr('height',he);
	
	var g = dv.append('g')
	.attr("transform","translate("+(wi/2)+","+(he/2)+")");
	
	 var color = d3.scaleOrdinal(["#ADF46E","#DBF471", "#F0CD6A","#EFB366","#F28E63"  ,"#F2625F",]);
	 
	 var pie = d3.pie();
	 var arc = d3.arc()
		 .innerRadius(0)
		 .outerRadius(radius);
	var arcs = g.selectAll('arc')
					.data(pie(arr))
					.enter()
					.append('g')
					.attr('class','arc');
					
	
	
	
	arcs.append("path")
			//.attr("d",path)
			.attr("fill",function(d,i){
				return color(i);
			})
			.attr('d',arc);
			
	arcs.append('text')
			.text(function(d,i){
				if(arr[i]!=0)
				return arr[i];
			})
			.attr('d',arc)
			.attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")";  })
			  .style("text-anchor", "middle")
			  .style("font-size", 17);
			
	
			
	

}

async function deleteChart(){
	dv.selectAll('*').remove();
	
}


