import Vue from 'vue'
import Loading from './Loading.vue'

const components = [
  Loading
]

components.map(component => {
  Vue.component(component.name, component)
})
