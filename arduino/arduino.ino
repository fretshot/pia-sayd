int led = 13;
float tempC; 
int pinLM35 = 0; // Variable del pin de entrada del sensor (A0)


void setup() {  
  
  Serial.begin(9600);              
  pinMode(led, OUTPUT);  
  
}

void loop() {
  tempC = analogRead(pinLM35); 
  tempC = (5.0 * tempC * 100.0)/1024.0;

  //Serial.println("OSCAR GUILLERMO ALEMAN CASTILLO");
  Serial.print(tempC);
  
   /*
  if (Serial.available() > 0) {

    i = Serial.parseInt();
    Serial.println("Datos recibidos: "+i);
    if(i == 1){
      digitalWrite(led, HIGH);
    }else{
      digitalWrite(led, LOW);
    }
  }

  */
  
  
  delay(1000);
}
