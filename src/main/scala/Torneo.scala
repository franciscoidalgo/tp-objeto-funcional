trait CriterioDeAdmision extends (Participante => Boolean)

case class CriterioCombate (requisitoBarbarosidad : Double) extends CriterioDeAdmision {
  override def apply(participante: Participante): Boolean = participante.mostrarBarbarosidad() > requisitoBarbarosidad || participante.estaEquipado()
}

case class CriterioPesca (pesoMinimo : Option[Double]) extends CriterioDeAdmision {
  override def apply(participante: Participante): Boolean = if (pesoMinimo.isDefined) participante.puedeCargar() >= pesoMinimo.get else true
}

case class CriterioCarrera (requiereMontura : Boolean) extends CriterioDeAdmision {
  override def apply(participante: Participante): Boolean = if (requiereMontura) participante match {
    case Vikingo(_,_,_,_) => false
    case Jinete(_,_) => true
  }else true

}

case class Torneo (listaPostas : List[Posta], listaDragones : List[Dragon])

abstract class Posta(criterioDeAdmision : CriterioDeAdmision) extends ((Participante, Participante) => Boolean){
  def incrementoDeHambre() : Int
}

case object combate extends Posta (criterioDeAdmision = CriterioCombate(requisitoBarbarosidad = ???)) {
  override def apply(participante : Participante, otroParticipante : Participante) : Boolean = participante.calcularDanio() > participante.calcularDanio()

  override def incrementoDeHambre(): Int = 10
}
case object pesca extends Posta (criterioDeAdmision = CriterioPesca(pesoMinimo = ???)){
  override def apply(participante: Participante, otroParticipante: Participante): Boolean = participante.puedeCargar() > otroParticipante.puedeCargar()

  override def incrementoDeHambre(): Int = 5
}

case class carrera(distanciaCarrera : Double) extends Posta (criterioDeAdmision = CriterioCarrera(???)) {
  override def incrementoDeHambre(): Int = distanciaCarrera.toInt
}
