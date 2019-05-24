#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd (0x27, 16, 2);

int led = 13;
int relevador = 7;
float tempC; 
int pinLM35 = 0; // Variable del pin de entrada del sensor (A0)


float actualTemp;
float maxTemp = 40;
float minTemp= 20;
String minim = "Min: ";
String maxim = "Max: ";
String actual = "Actual: ";
String celcius = " C";

void setup() {

  Wire.begin();
  lcd.begin(16, 2);
  lcd.clear();
  lcd.backlight();


  
  Serial.begin(9600);              
  pinMode(led, OUTPUT);
  pinMode(relevador, OUTPUT);  
  
}

void loop() {
  //Serial.print(maxTemp);
  actualTemp = analogRead(pinLM35); 
  actualTemp = (5.0 * actualTemp * 100.0)/1024.0;

  lcd.setCursor(0, 0);
  lcd.print(maxim+maxTemp+celcius);

  lcd.setCursor(0, 1);
  lcd.print(actual+actualTemp+celcius);  
  
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
