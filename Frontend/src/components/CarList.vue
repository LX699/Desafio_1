<template>
  <div>
    <label for="cantidad">Cantidad de automóviles a generar:</label>
    <input id="cantidad" type="number" v-model="cantidad" />
    <button @click="generarAutomoviles">Generar</button>
    <br />
    
    <select v-model="selectedColor">
      <option disabled value="">Seleccione un color</option>
      <option v-for="color in colores" :key="color" :value="color">{{ color }}</option>
    </select>
    <button @click="buscarPorColor">Buscar</button>

    <ul>
      <li v-for="(automovil, index) in automoviles" :key="index" style="text-align: left;"
        @click="seleccionarAutomovil(automovil)" :class="{ selected: automovil.isSelected }">
        {{ automovil.brand }}
      </li>
    </ul>
    <div v-if="automovilSeleccionado">
      <h3>Detalle del automóvil:</h3>
      <p>Marca: {{ automovilSeleccionado.brand }}</p>
      <p>Año: {{ automovilSeleccionado.year }}</p>
      <p>Color: {{ automovilSeleccionado.color }}</p>
      <p>Precio: {{ automovilSeleccionado.price }}</p>
      <p>Turbo: {{ automovilSeleccionado.turbo }}</p>
      <p>Tipo: {{ automovilSeleccionado.type }}</p>
      <p>Motor: {{ automovilSeleccionado.engine }}</p>
      <p>Cabinas: {{ automovilSeleccionado.cabins }}</p>
      <p>Sunroof: {{ automovilSeleccionado.sunroof }}</p>
      <p>Popularidad: {{ automovilSeleccionado.popularity }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      cantidad: 0,
      automoviles: [],
      automovilSeleccionado: null,
      colores: [],
      colorSeleccionado: '',
    };
  },
  mounted() {
  this.getColores();
},
  methods: {
    generarAutomoviles() {
  axios
    .get(`http://localhost:8080/automoviles/generar/${this.cantidad}`)
    .then((response) => {
      this.automoviles = response.data;
    })
    .catch((error) => {
      console.error(error);
    });
},
    seleccionarAutomovil(automovil) {
  if (automovil === this.automovilSeleccionado) {
    // El automóvil ya estaba seleccionado, así que lo deseleccionamos
    automovil.isSelected = false;
    this.automovilSeleccionado = null;
  } else {
    // Desmarca el automóvil anteriormente seleccionado
    if (this.automovilSeleccionado) {
      this.automovilSeleccionado.isSelected = false;
    }
    // Marca el automóvil seleccionado y agrega la clase CSS
    automovil.isSelected = true;
    this.automovilSeleccionado = automovil;
  }
},

getColores(){
  axios.get('http://localhost:8080/automoviles/colores')
    .then(response => {
      this.colores = response.data;
    })
    .catch(error => {
      console.error(error);
    });
},

buscarPorColor() {
  axios.get(`http://localhost:8080/automoviles/buscarPorColor?color=${this.selectedColor}`)
    .then(response => {
      this.automoviles = response.data;
    })
    .catch(error => {
      console.error(error);
    });
},


  },
  
};
</script>


<style scoped>
li.selected {
  text-decoration: underline;
  color: #0077c2;
}
</style>