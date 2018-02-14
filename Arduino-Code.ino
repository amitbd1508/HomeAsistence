char rf[15];
int clr = 1;
int itr = 0;
const int obpin1 = 7;
const int obpin2 = 12;
const int obpin3 = 13;
const int buzzer = 9;
int isob1 = HIGH;
int isob2 = HIGH;
int isob3 = HIGH;

void setup() {

  pinMode(obpin1, INPUT);
  pinMode(obpin2, INPUT);
  pinMode(obpin3, INPUT);
  pinMode(buzzer, OUTPUT);
  Serial.begin(9600);

}

void loop() {
  isob1 = digitalRead(obpin1);
  isob2 = digitalRead(obpin2);
  isob3 = digitalRead(obpin3);


  if (((isob1 == LOW && isob2 == LOW) || (isob2 == LOW && isob3 == LOW))  ) {
    //Serial.println("enter1");
    tone(buzzer, 1000);
    delay(500);
    noTone(buzzer);
    delay(100);
    tone(buzzer, 1000);
    delay(1000);
    noTone(buzzer);
    delay(100);
    tone(buzzer, 1000);
    delay(1200);
    noTone(buzzer);
    delay(100);


    if (((isob1 == LOW && isob2 == LOW) || (isob2 == LOW && isob3 == LOW)) )
    {
      //Serial.println("enter after delay");
      if (Serial.available())                     // if date is coming from software serial port ==> data is coming from SoftSerial shield
      {

        itr = 0;
        int flag = 0;
        while (Serial.available())
        {


          int id = Serial.read();
          if (id == 3)
          {
            rf[itr] = '\0';
            flag = 0;
            
            clr = 0;
            if(itr!=0)
              Serial.println(rf);
            else Serial.println("NO RFID");
            itr = 0;

            tone(buzzer, 1000);
            //Serial.println("end");
            break;
          }
          if (flag == 1)
          {
            rf[itr] = (char)id;
            itr++;
          }

          if (id == 2) {
            flag = 1;
            itr = 0;
            //Serial.println("Start");
          }
        }
        while (1) {
          if (!((isob1 == LOW && isob2 == LOW) || (isob2 == LOW && isob3 == LOW))  ) break;
          isob1 = digitalRead(obpin1);
          isob2 = digitalRead(obpin2);
          isob3 = digitalRead(obpin3);
          //Serial.println("Loop");
        }
      }
      else{
        Serial.println("NO RFID");
      }
    }
    else {
      tone(buzzer, 500);
      delay(100);
      noTone(buzzer);
      delay(100);
      tone(buzzer, 1000);
      delay(100);
      noTone(buzzer);
    }


  }
  else
  {
    clr = 1;
    itr = 0;

    noTone(buzzer);

  }
  delay(200);




}
