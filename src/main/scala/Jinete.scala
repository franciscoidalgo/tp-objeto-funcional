case class Jinete (vikingo: Vikingo, dragon: Dragon){

  def calcularDanio () : Double = vikingo.calcularDanio() + dragon.calcularDanio()

  def calcularVelocidad () : Double = dragon.calcularVelocidad() - vikingo.statsBase.peso

  def puedeCargar () : Double = dragon.puedeCargar() - vikingo.statsBase.peso

}