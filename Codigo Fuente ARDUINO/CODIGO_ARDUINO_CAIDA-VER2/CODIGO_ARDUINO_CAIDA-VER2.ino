/*
  ===================================================================================
  $               CODIGO DE ARDUINO PARA EL SISTEMA INTEGRADO PARA                  $
  $               ANALISIS EXPERIMENTAL EN UN LABORATORIO DE FISICA                 $
  $                                                                                 $
  $  Ultima Compilacion: 06-06-2019.                        BOARD: ARDUINO UNO R3   $
  $                                                                                 $
  $                                                                                 $
  $                                                                                 $
  $---------------------------------------------------------------------------------$
  $ (c) Eduardo Toro 2019.                                                          $  
  ===================================================================================
*/
#define beta 4090 //el beta del thermistor

float Millis1=0;//Registra el tiempo en milisegundo cuando inicio a contar.
float Millis2=0;//Registra el tiempo en Milisegundo cuando termina de contar.
float Tiempo; //Variable que almacena el tiempo en S.
float LastTime;//Variable que almacena el ultimo tiempo registrado
int pinAudioJack = 7;//Pin D7 donde esta la entrada de
                     //Signal por audioJack 

const unsigned char pinTemp = A0;//Pin AO donde esta la entrada de la temperatura
const unsigned char pinTempPasco = A1;//Pin donde se conecta el sensor de pasco 

bool WaitConfirm;//Confirma si el mensaje de espera fue envia 
int Confirm; //Confirma si la esfera esta en caida libre 
bool TimeConfirm;//Confirma que se ingreso al experimento de caida libre 
bool MillisConfirm;//Confirma si se obtuvo Millis1
bool ExpConfirm;//Confirma si el experimento fue exitoso o no 
bool TempConfirm;//Confirma si se entro al experimento de temperatura
bool TimeComm;//Confirma que se recibio respuesta por parte de JAVA 
bool StringComplete;//Confirma que el string recibido por ARDUINO este completo. 

float Temp;//Registra la temperatura captada por el Sensor. 
String ExpCommand="";//Obtiene un caracter para confirmar si el experimento fue exitoso o no.
                     // "Exitoso" (detenido por usuario programa), "Cancelado"
String InCommand;
int ExpDesicion, TempDesicion = 0;//Obtiene la desicion del experimento

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);//Comienza una comunicacion
                    //por el puerto serial
  while (!Serial){
    ; //espera a que el puerto se connecte.
    } 
  //Serial.println("Comunicacion Serial establecida!, esperando comandos");
  pinMode(pinAudioJack, INPUT);//Asigna el pin del audioJack como Input
  pinMode(pinTemp,INPUT);//Asigna el pin analogico como input
  InCommand.reserve(2000);
  Tiempo = 0;//Inicializa tiempo con 0
  LastTime=0;//Inicializa lasttime con 0
  Confirm=0;
  MillisConfirm=0;
  ExpConfirm=false;
  TempConfirm=false;
  TimeConfirm=false;
  TimeComm,WaitConfirm,StringComplete=false;
}//Setup()


void loop() {
  if (ExpDesicion==0){
        ExpDesicion = SerialDecider();
  }//Si ExpDesicion es igual a 0
  if (ExpDesicion==1){
           TimeDecide();
           if (TimeConfirm==true){
               TimeRead();
           }
  }//Caida Libre if  
  if (ExpDesicion==2){
     if (TempDesicion == 0){
       TempDesicion = TempRead();
     }//if TempDesicion = 0 
     TempWrite(TempDesicion);
    }//Temperatura if 
  if (ExpConfirm==true){
           TimeConfirm=false;
           ExpConfirm=false;
           TempDesicion=0;
           ExpDesicion=0;
           TimeComm=false;
           MillisConfirm=false;
           Serial.println("EXPCONFIRM=TRUE");
           serialFlush(); 
      }//if ExpConfirm==true
}//loop
/*
\] * TimeDecide();
 * 
 * Espera a que el programa de Java confirme si el sensor 
 * esta conectado.
 */

void TimeDecide(){
  if (WaitConfirm==false){
    Serial.println("WAITING FOR TIME");
    WaitConfirm=true;
  }
   if (TimeComm==true){
        Serial.println("TIMEEXPOK");
        TimeConfirm = true;
   }
}//TimeDecide()
 

int SerialDecider(){
  int ExpDes=0;
 *Serial.println("Bienvenido al sistema ArduPhysics!, Que Experimento Desea Realizar?");
  Serial.println("1. Caida Libre");
  Serial.println("2. Temperatura");
  Serial.println("SELECT EXPERIMENT.");
  while (Serial.available()==0){}//while serial 
  ExpDes = Serial.parseInt();
  Serial.println(ExpDes,DEC);
  Serial.flush();
  return ExpDes;
 }//SerialDecider()


/*
 * TimeRead()
 * Lee el tiempo que tarda en caer la pelota de metal desde
 * el sujetador del adaptador, hasta el pad final.
 * El tiempo leido es en Segundo. Los milisegundos no cuentan.
 * Por lo que si tardo 3:30 seg, se contara como 3 seg
*/
void TimeRead(){
   float Timer=0;
      float MillisTest=0;
   Confirm = digitalRead(pinAudioJack);
    delay(50);
    Confirm =digitalRead(pinAudioJack);
   if (Confirm==0 && MillisConfirm==false){
       Millis1=millis();
       MillisConfirm=true;
       //Serial.println("COUNTDOWN|START");
    }//if confirm==0
    Timer=1000;
    Confirm =digitalRead(pinAudioJack);
   if (Confirm==1 && MillisConfirm==true){
      MillisConfirm=false;
      Millis2=millis();
     // Serial.println("COUNTDOWN|END");
      ExpConfirm=true;
      Tiempo = (Millis2 - Millis1);
      WriteTime(Tiempo);
    }//else if
}//TimeRead 


void WriteTime(float TiempoF){
     Serial.print("Tiempo de caida|");
     Serial.println(TiempoF); 
      MillisConfirm=0;
      Millis1, Millis2 = 0;
      Confirm = 1;
      Tiempo = 0;
      ExpConfirm=true;
  delay(50);
 }//WriteTime

/*
 * TempRead()
*/
int TempRead(){
     int TempDesc=0;//Obtiene la opcion de temperatura
     String CommandRead;
     Serial.println("Como quiere mostrar el valor de temperatura?");
     Serial.println("1. Voltaje");
     Serial.println("2. Centigrados");
     Serial.println("3. Frarenheit");
     Serial.println("4. Kelvin");
     Serial.println("Otros. Cancelar Experimento");
     Serial.println("SELECT TEMP TYPE.");
     while(Serial.available()==0){}
     TempDesc=Serial.parseInt();
     delay(50);
     Serial.flush();
     return TempDesc;
  }//TempRead()

/*
   void TempWrite(int)
---------------------------------------------------
   Esta clase envia el tipo de temperatura como un string 
   de caracteres por el 
*/
void TempWrite(int OPC){
     int Lectura = 0;
     float Voltage = 0; 
     Lectura = analogRead(pinTemp);
     Voltage = Lectura*(5.0/1023.0);//convierte la lectura a un voltaje de hasta 5v
     if (OPC==1){
        Serial.print("VOLTAGE|");
         Serial.println(Voltage, DEC);
         delay(500);
     }// Mostrar Voltage 
     float tempC = Voltage*100;
     float tempF = 1.8*tempC + 32.0;
     float tempK = tempC + 273.15;
    if (OPC==2){
       Serial.print("Centigrade|");
        Serial.println(tempC);
        delay(500);
  }// Mostrar Centigrado 
    if (OPC==3){
       Serial.print("Fahrenheit|");
        Serial.println(tempF, DEC);
        delay(500);
     }//Mostrar Fahrenheit
     if (OPC==4){
         Serial.print("Kelvin|");
         Serial.println(tempK,DEC);
         delay(500);
      }//Mostrar Kelvin 
  }//TempWrite()



/*
 * SerialEvent
 * El programa entra a esta clase cuando entra una data por el Serial.
 * En este programa se utiliza para verificar si el experimento fue 
 * cancelado o terminado por el programa de JAVA.
 * 
 * Se ejecuta cada vez que se termina el bucle de Loop()
*/
void serialEvent(){
    while (Serial.available()){
         char InChar = (char)Serial.read();
          if (!isSpace(InChar)){
                InCommand+=InChar;
                Serial.println(InCommand);
       }//if 
       if (InChar == '\n') {
          StringComplete = true;
        }//if InChar == \n
    }//while
    if (StringComplete == true){
        TimeComm = TimeAccept(InCommand);
        ExpConfirm = ExpStopper(InCommand);
    }//if StringComplete == true
  }//void serial Event 

bool TimeAccept(String Command){
     bool ConfirmMessage=false;
         if (Command.equals("TIMEOK")==true){
             ConfirmMessage = true;
             Serial.println(Command);
             InCommand="";
          }//if    
         else{
             ConfirmMessage=false;
          }//else
     return  ConfirmMessage;
  }//bool timeAccept
  
 bool ExpStopper (String Command){
      bool ConfirmMessage = false;
        if (Command.equals("EXITO")||Command.equals("CANCEL")){
            Serial.println(InCommand);
            ConfirmMessage=true;
            InCommand="";
          }//if 
        else {
            ConfirmMessage=false;
        }//else
        return ConfirmMessage;
  }//ExpStopper

  void serialFlush(){
  while(Serial.available() > 0) {
    char t = Serial.read();
  }//while
}//serialFlush(); 
