package function

import com.rojojun.function.RpnCalc
import org.junit.jupiter.api.Assertions.*
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

class RpnCalcTest {
     @Test
     fun `a simple sum`() {

         expectThat(RpnCalc.calc("4 5 +")).isEqualTo(9.0)

     }

     @Test
     fun `a double operation`() {
         expectThat(RpnCalc.calc("3 2 1 - +")).isEqualTo(4.0)

     }

     @Test
     fun `a division`() {

         expectThat(RpnCalc.calc("6 2 /")).isEqualTo(3.0)

     }

     @Test
     fun `a more complicated operation`() {

         expectThat(RpnCalc.calc("6 2 1 + /")).isEqualTo(2.0)

         expectThat(RpnCalc.calc("5 6 2 1 + / *")).isEqualTo(10.0)

     }

     @Test
     fun `a bit of everything`() {
         expectThat(RpnCalc.calc("2 5 * 4 + 3 2 * 1 + /")).isEqualTo(2.0)

     }
 }