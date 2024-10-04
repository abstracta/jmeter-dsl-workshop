# Creando una prueba básica

Vamos a crear una prueba que ejecute un pedido al sitio [PetStore](https://petstore.octoperf.com/) de [Octoperf](https://octoperf.com/).

Además agregaremos un criterio de pass/fail para validar si la ejecución del test cumple los criterios de aceptación requeridos.

El script `scriptBasico.java` muestra una posible solución para que tengan como referencia.

# Debugging

Veremos tres métodos distintos de debugging.

## Results Tree Visualizer

Al utilizar este método durante la ejecución del script, JMeterDSL muestra una ventana similar al elemento *View Results Tree* de JMeter.

## Método ShowInGui()

Permite ver el mismo script que estamos desarrollando pero en la interfaz de JMeter, también podemos interactuar con el script desde dicha interfaz. Es útil sobre todo si ya se cuenta con experiencia automatizando en JMeter.

## Breakpoint

Tal vez el método de debugging más avanzado, sobre todo si no se tiene experiencia desarrollando, pero muy útil sobre todo para scripts complejos. Permite ver el estado de las variables en cierto momento de la ejecución.
Más información sobre este método en [este link](https://abstracta.github.io/jmeter-java-dsl/guide/#debug-jmeter-code).