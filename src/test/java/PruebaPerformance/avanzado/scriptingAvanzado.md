# Scripting avanzado

En esta sección vamos a ver algunos conceptos y funcionalidades un poco más complejas, que nos van a ayudar a automatizar casos de prueba más realistas. En particular vamos a ver:

* Cómo agregar pedidos POST y headers.
* Manejo del contenido estatico.
* Parametrización y correlación de variables.
* Cómo agregar tiempos de espera.
* Uso de datos externos desde un archivo CSV.

El script [`ScriptingAvanzado.java`](./ScriptingAvanzado.java) contiene una solución de referencia sobre esta sección.

## Buenas Practicas

Vamos a ver ahora algunas buenas prácticas recomendadas para facilitar el manejo y el entendimiento de los scripts de JMeterDSL, así como para optimizar su ejecución. En particular:

* Incluir validaciones (assertions).
* Transaction Controllers.
* HTTP Request Defaults.
* Buenas prácticas de Java (variables, modularización a nivel de código, etc.).
* Variables y Propiedades de JMeter.
* Autostop.
* Uso del jtlWriter.

El script [`BuenasPracticas.java`](./BuenasPracticas.java) contiene una posible solución de esta sección para tomar como referencia.

# Configuración de escenarios y ejecución

En esta sección veremos algunas de las opciones que presenta JMeterDSL para configurar escenarios de carga y ejecutar. Entre otras cosas vamos a ver:

* ThreadGroups, Ramps y Holds.
* Configuración según RPS.
* ShowTimeLine.
* Escalado en la nube.

El script [`ModeladoYNube.java`](./ModeladoYNube.java) contiene una posible solución de esta sección para tomar como referencia.