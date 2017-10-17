#include <Servo.h>

Servo left;
Servo right;

void setup() {
  // put your setup code here, to run once:
  left.attach(3);
  right.attach(4);
}

void loop() {
  // put your main code here, to run repeatedly:
  left.write(140);
  right.write(10);
  delay(2000);
  left.write(50);
  right.write(80);
 delay(2000); 
}
