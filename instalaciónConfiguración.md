# Instalación y configuración

Vamos a usar la versión Java de JMeterDSL y Maven para hacer el build. Por lo tanto, los requerimientos previos van a ser tener instalado Java y Maven.

Además vamos a usar IntelliJ como IDE. Utilizar un IDE tiene varias ventajas, pero no es un requerimiento obligatorio.

Siempre es recomendable utilizar las últimas versiones de dichas herramientas. Para las soluciones propuestas utilizamos: 

* Java 19+
* Maven 3.9+
* (Recomendado) IDE. Ej: IntelliJ IDEA 2022.31 (Community Edition)

Vamos a utilizar JUnit como librería de testing y AssertJ para las validaciones. Ambas en su última versión.

El archivo [`pom.xml`](pom.xml) de este directorio muestra la configuración necesaria para instalar y configurar estas librerías y JMeterDSL.

>*Tip: A efectos de este workshop vamos a crear un proyecto desde cero, pero si cuentas con un repositorio con el código de la aplicación o sistema que quieres probar es recomendable que integres JMeterDSL a ese repositorio.*

Cuando termines de instalar y configurar JMeterDSL, continúa a la Sección 2: **[Scripting básico](./src/test/java/PruebaPerformance/basico/pruebaBásica.md)**.