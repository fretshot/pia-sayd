int led = 13; // Se declara la variable led como tipo de dato entero 

// La rutina setup() se ejecuta al inicio y cuando presionas el botón reset
void setup() {                
  pinMode(led, OUTPUT);  //Inicializa el pin numero 13 (valor de variable led) como salida.
                         //(todas las salidas son digitales)       
}
// La rutina loop() se ejecuta una y otra vez por siempre.
void loop() {
  digitalWrite(led, HIGH);   // Enciende el LED (HIGH el el 1 binario o 5[V])
  delay(1000);               // Espera un segundo
  digitalWrite(led, LOW);    // Apaga el LED dejando voltaje 0[V] en la salida
  delay(1000);               // Espera por un segundo
}
