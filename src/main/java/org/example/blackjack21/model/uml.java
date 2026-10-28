package org.example.blackjack21.model;

public class uml {
}
@startuml
class Carta {
 - String Palo
- String Rango
+ Carta(string palo, string rango)
+ GetPalo():string
+ GetRango():string
+ GetValor():int
+ ToString(): string
}
class mazo{
- cartas : Carta[52]
            - siguiente : int
- Palo: String[]
- Rango: String[]
  + Mazo()
  + barajar() : void
  + sacarCarta() : Carta
  + reiniciar() : void
  + cartasRestantes() : int
}
@enduml