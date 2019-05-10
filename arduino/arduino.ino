int led = 13;
int relevador = 7;
float tempC; 
int pinLM35 = 0; // Variable del pin de entrada del sensor (A0)


float actualTemp;
float maxTemp = 40;
float minTemp= 20;

void setup() {  
  
  Serial.begin(9600);              
  pinMode(led, OUTPUT);
  pinMode(relevador, OUTPUT);  
  
}

void loop() {
  //Serial.print(maxTemp);
  actualTemp = analogRead(pinLM35); 
  actualTemp = (5.0 * actualTemp * 100.0)/1024.0;
  Serial.print(actualTemp);
  
  if (Serial.available() > 0) {

      maxTemp = Serial.parseFloat();
      //Serial.println(maxTemp);
  }

  if (actualTemp > maxTemp) {
      digitalWrite(13, HIGH);
      digitalWrite(relevador,LOW);
      //Serial.println("LED ON");
    }
    
  if (actualTemp < maxTemp) {
    digitalWrite(13, LOW);
    digitalWrite(relevador,HIGH);
    //Serial.println("LED OFF");
  }
  
  delay(1000);
}
