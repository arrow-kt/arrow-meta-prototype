package arrow.sample

class TestClass {
  fun sideEffect() =
    println("BOOM!")

  suspend fun other(): Unit {
    println("other")
  }

  var d: () -> Int = {
    if (true)
      throw RuntimeException("Damn")
    3
  }

  fun s(): String {
    d = { d() - 2 }
    return "p"
  }

  val x = { println() }

  fun another2(): String {
    1; println("test");
    {
      { println() }
    }()
    return "another"
  }
}
