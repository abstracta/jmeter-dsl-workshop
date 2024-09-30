# Creando nuestra primera prueba

En esa sección la idea es poder crear nuestra primera prueba basica, debugear y ejecutar.

Para comenzar con el taller, partiremos desde el proyecto que encontramos en la carpeta [proyecto_base] (agregar_link) y es importante tener instalado y configurado el entorno con los pre-requisitos.

## ¿Que va a tener nuestra prueba?

Lo basico para poder ejecutar:

* Testplan
* Threadgroup
* Pedidos

# Debugging

En esta sección nos enfocaremos en debuggear nuestro script para situaciones mas complejas. Veremos 3 metodos para debagguear.

## View results tree

Esto mostrará el elemento incorporado de JMeter "ViewResultsTree", el cual te permite revisar el contenido de las solicitudes y respuestas, además de las métricas recopiladas (tiempo empleado, bytes enviados y recibidos, etc.) para cada solicitud enviada al servidor.

Revisar la documentación: https://abstracta.github.io/jmeter-java-dsl/guide/#view-results-tree

## metodo ShowInGui

Esto nos permite ver el mismo script en JMeter y ver los elementos de nuestra prueba mediante la GUI, bastante útil si ya se cuenta con experiencia automatizando en JMeter.

Documentación: https://abstracta.github.io/jmeter-java-dsl/guide/#test-plan-review-in-jmeter-gui

## Breakpoint

Nos permite ver información en un cierto estado de la prueba. en algunos casos necesitamos ademas agregar un postProcesor para obtener mas información de la prueba en dicho estado.

Documentación: https://abstracta.github.io/jmeter-java-dsl/guide/#debug-jmeter-code