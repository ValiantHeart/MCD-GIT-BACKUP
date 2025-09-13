/*
  Title: LED_Stream
  Purpose: Lights up LEDs in a stream and then repeats.
           an LED wil activate in a stream, then deactivate.
           The Speed at which this process occurs and repeats
           can be modified with the timer variable. It will activate 
           Pin 12 at the end of the cycle.
*/

int timer = 50;

void setup()
{
  Serial.begin(9600)
 for(int thisPin=2; thisPin < 12; thisPin++)
  {
   pinMode(thisPin, OUTPUT);
  } 
}

void loop()
{
  int Pin12 = 12;
 for(int thisPin= 12; thisPin >= 2; thisPin--)
 {
 digitalWrite(thisPin, HIGH);
 delay(timer);
 digitalWrite(thisPin, LOW);
 delay(timer);
 digitalWrite(Pin12, HIGH);
 delay(timer);
 digitalWrite(Pin12, LOW);
 delay(timer);
 Serial.write('Cycle Complete');
 } 
  Serial.write('WARNING: CYCLE HAS BEEN BROKEN');
}

