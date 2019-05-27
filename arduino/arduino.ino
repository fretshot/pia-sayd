#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Separador.h>

LiquidCrystal_I2C lcd (0x27, 16, 2);

Separador s;
int ledCalentar = 10;
int ledEnfriar = 11;
int relevador = 7;
float tempC; 
int pinLM35 = 0; // Variable del pin de entrada del sensor (A0)
float actualTemp;
float maxTemp = 40;
float minTemp = 20;
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
  pinMode(ledCalentar, OUTPUT);
  pinMode(ledEnfriar, OUTPUT);
  pinMode(relevador, OUTPUT);    
}

void loop() {

  actualTemp = analogRead(pinLM35); 
  actualTemp = (5.0 * actualTemp * 100.0)/1024.0;

  lcd.setCursor(0, 0);
  lcd.print(maxim+maxTemp+celcius);

  lcd.setCursor(0, 1);
  lcd.print(actual+actualTemp+celcius);  
  
  Serial.print(actualTemp);
  
  if (Serial.available() > 0) {

      String datosRecibidos = Serial.readString();
      String tmin = s.separa(datosRecibidos,',',0);
      String tmax = s.separa(datosRecibidos,',',1);
      maxTemp = tmax.toFloat();
      minTemp = tmin.toFloat();
  }

  if (actualTemp > maxTemp) {
      digitalWrite(relevador,LOW);
      digitalWrite(ledEnfriar, HIGH);
    }
    
  if (actualTemp < maxTemp) {
      digitalWrite(relevador,HIGH);
      digitalWrite(ledEnfriar, LOW);
  }

  if (actualTemp < minTemp) {
      digitalWrite(ledCalentar, HIGH);
    }
    
  if (actualTemp > minTemp) {
      digitalWrite(ledCalentar, LOW);
  }
  
  delay(1000);
}
