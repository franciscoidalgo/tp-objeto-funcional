trait Criterio extends ((Dragon, Vikingo) => Boolean)

object criterioDanio extends Criterio {
  override def apply(dragon : Dragon, vikingo : Vikingo): Boolean = dragon.calcularDanio() > vikingo.calcularDanio()
}

case class criterioItem(item : Item) extends Criterio{
  override def apply(dragon: Dragon, vikingo: Vikingo) : Boolean = vikingo.item.contains(item)
}

case class Dragon (velocidadBase : Int = 60, peso : Int, raza : Raza, criterioDeMontura : Criterio){
  def calcularDanio() : Int = raza.mostrarDanio(this)
  def puedeMontar(vikingo: Vikingo) : Boolean = criterioDeMontura (this, vikingo : Vikingo)
}


trait Raza{
  def mostrarDanio(dragon: Dragon) : Int
}

case class FuriaNocturna(danio : Int) extends Raza{
  def mostrarDanio(dragon: Dragon): Int = danio
}

case class NadderMortifero () extends Raza{
  def mostrarDanio(dragon: Dragon) : Int = 150
}

case class Gronckle () extends Raza{
  def mostrarDanio (dragon: Dragon) : Int = dragon.peso * 5
}
