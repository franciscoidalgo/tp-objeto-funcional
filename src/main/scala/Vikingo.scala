trait  Item {
  val efecto : Vikingo => Vikingo
}

case class Vikingo(statsBase: StatsBase,nivel : Int, hambre : Int, item : Option[Item]) {

  def montar (dragonAMontar: Dragon) : Jinete = {
    if (dragonAMontar.puedeMontar(this)) {
      Jinete(vikingo = this, dragon = dragonAMontar)
    }else throw new RuntimeException ("No se pudo montar al dragon")
  }

  def calcularDanio() : Double = statsBase.danioBase

  def esMejorQue (otroVikingo : Vikingo, posta : Posta): Boolean ={
    posta(this, otroVikingo)
  }

  def puedeCargar() : Double = statsBase.peso / 2 + statsBase.barbarosidad * 2

  def calcularVelocidad() : Double = statsBase.velocidad

}

case class StatsBase (peso : Double, velocidad : Double, barbarosidad : Int, danioBase : Double)