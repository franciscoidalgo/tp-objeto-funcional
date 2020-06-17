trait CriterioDeAdmision extends (Vikingo => Boolean)

case class CriterioCombate (requisitoBarbarosidad : Double) extends CriterioDeAdmision {
  override def apply(vikingo: Vikingo): Boolean = vikingo.statsBase.barbarosidad > requisitoBarbarosidad || vikingo.item.isDefined
}

trait Participante {
  def puedeCargar()
  def calcularDanio()
  def calcularVelocidad()
}

case class Torneo (listaPostas : List[Posta], listaDragones : List[Dragon])

abstract class Posta(incrementoDeHambre : Int, criterioDeAdmision : CriterioDeAdmision) extends ((Vikingo, Vikingo) => Boolean) {
}

case object combate extends Posta (incrementoDeHambre = 10, criterioDeAdmision = CriterioCombate(requisitoBarbarosidad = ???)) {
  override def apply(vikingo : Vikingo, otroVikingo2 : Vikingo) : Boolean = vikingo.calcularDanio() > otroVikingo2.calcularDanio()
}