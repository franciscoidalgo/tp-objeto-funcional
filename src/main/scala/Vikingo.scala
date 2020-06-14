import scala.util.Try

trait  Item {
  val efecto : Vikingo => Vikingo = ???
}

case class Vikingo(peso : Int, velocidad : Int, barbarosidad : Int, nivel : Int,
                   hambre : Int, item : Option[Item], danioBase : Int){
  def montar (dragonAMontar: Dragon) : Jinete = {
    if (dragonAMontar.puedeMontar(this)) {
      Jinete(vikingo = this, dragon = dragonAMontar)
    }else throw new RuntimeException ("No se pudo montar al dragon")
  }

  def calcularDanio() : Int = danioBase

}