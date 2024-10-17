# Scripting avanzado

En esta sección vamos a ver algunos conceptos y funcionalidades un poco más complejas, que nos van a ayudar a automatizar casos de prueba más realistas. En particular vamos a ver:

* Cómo agregar pedidos POST y headers.
* Manejo del contenido estatico.
* Parametrización y correlación de variables.
* Cómo agregar tiempos de espera.
* Uso de datos externos desde un archivo CSV.

El script [`ScriptingAvanzado.java`](./ScriptingAvanzado.java) contiene una solución de referencia sobre esta sección.

> *Nota: el archivo CSV utilizado para el ejemplo contiene datos de usuarios que no existen a propósito, de forma tal de hacer fallar la assertion del script en ese paso.*

## Buenas Prácticas

Vamos a ver ahora algunas buenas prácticas recomendadas para facilitar el manejo y el entendimiento de los scripts de JMeterDSL, así como para optimizar su ejecución. En particular:

* Incluir validaciones (assertions).
* Uso de Transaction Controllers.
* Uso de HTTP Request Defaults.
* Buenas prácticas de Java (variables, modularización a nivel de código, etc.).
* Uso de Variables y Propiedades de JMeter.
* Uso de Autostop.
* Uso del jtlWriter.

El script [`BuenasPracticas.java`](./BuenasPracticas.java) contiene una posible solución de esta sección para tomar como referencia.

# Configuración de escenarios y ejecución

En esta sección veremos algunas de las opciones que presenta JMeterDSL para configurar escenarios de carga y ejecutar. Entre otras cosas vamos a ver:

* Uso de ThreadGroups, Ramps y Holds.
* Configuración según RPS.
* Método ShowTimeLine.
* Escalado en la nube.

El script [`ModeladoYNube.java`](./ModeladoYNube.java) contiene una posible solución de esta sección para tomar como referencia.

Cuando hayas agregado al script las funcionalidades avanzadas, puedes continuar con la sección 4: **[Features adicionales](./featuresAdicionales.md)**.