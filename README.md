# Proyecto: Procesador de Transacciones Bancarias (Java)

## 📌 Introducción

Este proyecto es una aplicación de línea de comandos (CLI) escrita en Java que procesa un archivo CSV con transacciones bancarias y genera un reporte final.  
El objetivo es facilitar el análisis rápido de operaciones financieras a partir de datos estructurados en formato CSV.

## 🔍 ¿Qué hace esta aplicación?

- **Balance Final**: Calcula la diferencia entre los montos de tipo "Crédito" y "Débito".
- **Transacción de Mayor Monto**: Identifica la transacción con el monto más alto.
- **Conteo de Transacciones**: Muestra cuántas transacciones hubo por tipo.

Además, el programa ignora diferencias entre mayúsculas/minúsculas y tildes en los tipos de transacción.


## ⚙️ Instrucciones de Ejecución

### Requisitos

- Java 8 o superior instalado.
- Editor o IDE recomendado: IntelliJ IDEA, Eclipse, VS Code, etc.


### Compilar

Desde la terminal o símbolo del sistema (cmd):

```bash
- javac Main.java
- java Main data.csv

### Salida

Reporte de Transacciones
---------------------------------------------
Balance Final: 325.00
Transacción de Mayor Monto: ID 3 - 200.00
Conteo de Transacciones: Crédito: 3 Débito: 2
