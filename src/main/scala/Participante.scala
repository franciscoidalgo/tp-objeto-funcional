trait Participante {
  def puedeCargar() : Double
  def calcularDanio() : Double
  def calcularVelocidad() : Double
  def mostrarBarbarosidad() : Int
  def estaEquipado () : Boolean
  def tieneItem(itemDeseado : Item) : Boolean

}

case class Vikingo(statsBase: StatsBase,nivel : Int = 1, hambre : Int = 0, item : Option[Item] = None) extends Participante {

  def montar (dragonAMontar: Dragon) : Jinete = {
    if (dragonAMontar.puedeMontar(this)) {
      Jinete(vikingo = this, dragon = dragonAMontar)
    }else throw new RuntimeException ("No se pudo montar al dragon")
  }

  def calcularDanio() : Double = item match {
    case arma : Some[Arma] => arma.get.danioExtra + statsBase.danioBase
    case _ => statsBase.danioBase
  }

  def esMejorQue (otroVikingo : Vikingo, posta : Posta): Boolean ={
    posta(this, otroVikingo)
  }

  def puedeCargar() : Double = statsBase.peso / 2 + statsBase.barbarosidad * 2

  def calcularVelocidad() : Double = statsBase.velocidad

  override def mostrarBarbarosidad(): Int = statsBase.barbarosidad

  override def estaEquipado(): Boolean = item.isDefined

  override def tieneItem(itemDeseado: Item): Boolean = item.contains(itemDeseado)

}

case class StatsBase (peso : Double, velocidad : Double, barbarosidad : Int, danioBase : Double)

case class Jinete (vikingo: Vikingo, dragon: Dragon) extends Participante {

  def calcularDanio () : Double = vikingo.calcularDanio() + dragon.calcularDanio()

  def calcularVelocidad () : Double = dragon.calcularVelocidad() - vikingo.statsBase.peso

  def puedeCargar () : Double = dragon.puedeCargar() - vikingo.statsBase.peso

  override def mostrarBarbarosidad(): Int = vikingo.mostrarBarbarosidad()

  override def estaEquipado(): Boolean = vikingo.estaEquipado()

  override def tieneItem(itemDeseado: Item): Boolean = vikingo.tieneItem(itemDeseado)
}