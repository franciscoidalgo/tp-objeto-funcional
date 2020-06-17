trait CriterioDeMontura extends ((Dragon, Vikingo) => Boolean)

object criterioDanio extends CriterioDeMontura {
  override def apply(dragon : Dragon, vikingo : Vikingo): Boolean = dragon.calcularDanio() > vikingo.calcularDanio()
}

case class criterioItem(item : Item) extends CriterioDeMontura{
  override def apply(dragon: Dragon, vikingo: Vikingo) : Boolean = vikingo.item.contains(item)
}

case class Dragon (velocidadBase : Double = 60, peso : Double, raza : Raza, criterioDeMontura : CriterioDeMontura){

  def calcularDanio() : Double = raza.mostrarDanio(this)

  def puedeMontar(vikingo: Vikingo) : Boolean = criterioDeMontura (this, vikingo : Vikingo)

  def calcularVelocidad (): Double = velocidadBase - peso

  def puedeCargar() : Double = peso * 0.2
}


trait Raza{
  def mostrarDanio(dragon: Dragon) : Double
}

case class FuriaNocturna(danio : Double) extends Raza{
  def mostrarDanio(dragon: Dragon): Double = danio
}

case class NadderMortifero () extends Raza{
  def mostrarDanio(dragon: Dragon) : Double = 150
}

case class Gronckle () extends Raza{
  def mostrarDanio (dragon: Dragon) : Double = dragon.peso * 5
}
