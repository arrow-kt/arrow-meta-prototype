package consumer

sealed class A {
  data class B(val x: Int) : A()
  data class C(val x: Int) : A()
  object Z : A()

  fun <B> fold(): B =


}

sealed class AX
data class BX(val x: Int) : AX()
data class CX(val x: Int) : AX()
object ZC : AX()