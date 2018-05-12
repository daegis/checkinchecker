import cn.aegisa.project.utils.LocalDateTimeUtil
import com.alibaba.fastjson.JSON
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2018/5/11 19:05
 */
class TestK01 {
}

fun main(args: Array<String>) {
    val now = LocalDateTime.now()
    println(LocalDateTimeUtil.timeToString(now))
    val today = now.toLocalDate()
    println(LocalDateTimeUtil.timeToString(today))
    val dayOfWeek: DayOfWeek = today.dayOfWeek
    println(dayOfWeek.value)

    val twoDaysAgo = today.minusDays(2L)
    println(twoDaysAgo.dayOfWeek.value)


}
