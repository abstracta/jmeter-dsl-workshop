# Creando una prueba básica

Vamos a crear una prueba que ejecute un pedido al sitio [PetStore](https://petstore.octoperf.com/) de [Octoperf](https://octoperf.com/).

Además agregaremos un criterio de pass/fail para validar si la ejecución del test cumple los criterios de aceptación requeridos.

El script [`PruebaPerformance.java`](./PruebaPerformance.java) de este directorio muestra una posible solución para que tengan como referencia.

# Debugging

Además veremos algunos métodos útiles para hacer debugging.

## Results Tree Visualizer

Al utilizar este método durante la ejecución del script, JMeterDSL muestra una ventana similar al elemento *View Results Tree* de JMeter.

## ShowInGui()

Permite ver el mismo script que estamos desarrollando pero en la interfaz de JMeter, también podemos interactuar con el script desde dicha interfaz. Es útil sobre todo si ya se cuenta con experiencia automatizando en JMeter.