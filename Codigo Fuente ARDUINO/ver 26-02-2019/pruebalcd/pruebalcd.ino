 
 #include <LiquidCrystal.h>
 long  Temp;
int Entrada_S;
  LiquidCrystal lcd (12, 11, 5, 4, 3, 2);
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  lcd.begin(16,2);
  lcd.home();
  lcd.print("UNA PRUEBA LCD");
  lcd.setCursor(0,1);
  lcd.print("Solo una prueba");
  delay(1000);
  lcd.clear();
}

void loop() {
 WriteLCD();
 delay(500);
}//loop

void WriteLCD(){
 lcd.clear();
 lcd.print("INPUT: ");
 //Lee lo que esta en el PIN A0
 Entrada_S = ReadTemp();
 //Imprime la lectura
 lcd.print(Entrada_S);
 //Convierte la lectura analoga (va de 0 - 1023) 
 //a voltage de entre 0V a 5V
 Temp = Entrada_S*(5.0/1023.0);
 lcd.setCursor(0,1);
 //Imprime el volge
 lcd.print("Temp: ");
 lcd.print(Temp);
  }

int ReadTemp(){
   int val = analogRead(A0);
  return val;
  }
