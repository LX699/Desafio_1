<template>
  <div>
    <label for="cantidad">Cantidad de automóviles a generar:</label>
    <input id="cantidad" type="number" v-model="cantidad" />
    <button @click="generarAutomoviles">Generar</button>
    <br><br>

    <span style="margin-right: 10px;">Seleccionar color:</span>
<select v-model="selectedColor">
  <option disabled value="">Seleccionar</option>
  <option v-for="color in colores" :key="color" :value="color">{{ color }}</option>
  <option value="">✕ Desmarcar color</option>
</select>
<button @click="buscarPorColor">Buscar</button>

<span style="margin-right: 10px;">Seleccionar tipo:</span>
<select v-model="selectedTipo">
  <option disabled value="">Seleccionar</option>
  <option v-for="tipo in tipos" :key="tipo" :value="tipo">{{ tipo }}</option>
  <option value="">✕ Desmarcar tipo</option>
</select>
<button @click="buscarPorTipo">Buscar</button>

    <br><br>

    <form @submit.prevent="buscarPorPrecio">
  <span>Ingresar Precio:</span>
  <input type="number" v-model="maxPrice" placeholder="Ingrese el precio" @input="checkMaxPrice" />
  <button type="submit">Buscar</button>
</form>

    <div class="automoviles-container">
      <ul>
        <li v-for="(automovil, index) in automoviles" :key="index" @click="seleccionarAutomovil(automovil)"
          :class="{ selected: automovil.isSelected }">
          {{ automovil.brand }}
        </li>
      </ul>
      <div v-if="automovilSeleccionado" class="detalle-automovil">
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
      tipos: [],
      tipoSeleccionado: '',
      colorSeleccionado: '',
      maxPrice: 0
      

    };
  },
  mounted() {
  this.getColores();
  this.getTipos();

},
  methods: {
    obtenerAutomoviles() {
  axios.get('http://localhost:3001/automoviles/lista')
    .then(response => {
      this.automoviles = response.data;
    })
    .catch(error => {
      console.error(error);
    });
},
generarAutomoviles() {
  axios.get(`http://localhost:3001/automoviles/generar/${this.cantidad}`)
    .then(response => {
      this.automoviles = response.data;
      this.obtenerAutomoviles(); // Llamar a obtenerAutomoviles para obtener la lista actualizada
    })
    .catch(error => {
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
  axios.get('http://localhost:3001/automoviles/colores')
    .then(response => {
      this.colores = response.data;
    })
    .catch(error => {
      console.error(error);
    });
},

getTipos(){
  axios.get('http://localhost:3001/automoviles/tipoAutomovil')
    .then(response => {
      this.tipos = response.data;
    })
    .catch(error => {
      console.error(error);
    });
},

buscarPorTipo() {
  if (this.selectedTipo === '') {
    this.obtenerAutomoviles(); // Obtener la lista completa de automóviles sin filtro
  } else {
  axios.get(`http://localhost:3001/automoviles/buscarPorTipo?tipo=${this.selectedTipo}`)
    .then(response => {
      this.automoviles = response.data;
    })
    .catch(error => {
      console.error(error);
    });
  }
},

buscarPorColor() {
  if (this.selectedColor === '') {
    this.obtenerAutomoviles(); // Obtener la lista completa de automóviles sin filtro
  } else {
    axios.get(`http://localhost:3001/automoviles/buscarPorColor?color=${this.selectedColor}`)
      .then(response => {
        this.automoviles = response.data;
      })
      .catch(error => {
        console.error(error);
      });
  }
},

checkMaxPrice() {
    if (!this.maxPrice) {
      this.obtenerAutomoviles();
    }
  },

buscarPorPrecio() {
  axios.get('http://localhost:3001/automoviles/filtrarPorPrecio', {
      params: {
        maxPrice: this.maxPrice
      }
    })
    .then(response => {
      this.automoviles = response.data;
      if (!this.maxPrice) {
        this.generarAutomoviles();
      }
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

.automoviles-container {
  display: flex;
}

ul {
  flex: 1;
}

.detalle-automovil {
  flex: 2;
  margin-left: 20px;
}
</style>