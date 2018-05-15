package cn.aegisa.project.service.impl

import cn.aegisa.project.dao.service.ICommonService
import cn.aegisa.project.model.User
import cn.aegisa.project.service.CheckInService
import cn.aegisa.project.utils.LocalDateTimeUtil
import com.Ostermiller.util.MD5
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors

@Service
class CheckInServiceImpl : CheckInService {

    private val log: Logger = LoggerFactory.getLogger(CheckInServiceImpl::class.java)

    @Autowired
    private lateinit var commonService: ICommonService

    override fun getInfo(): Any {
        val now = LocalDateTime.now()
        val today = now.toLocalDate()
        val dayOfWeek = today.dayOfWeek
        val weekDayNumber = dayOfWeek.value
        log.info("今天是星期{}", weekDayNumber)
        val monday =
                if (weekDayNumber == 1) {
                    today
                } else {
                    today.minusDays((weekDayNumber - 1).toLong())
                }
        val saturnday = monday.plusDays(5L)
        val monStr = LocalDateTimeUtil.timeToString(monday.atStartOfDay())
        val satStr = LocalDateTimeUtil.timeToString(saturnday.atStartOfDay())
        val userList: List<User> = commonService.getListBySqlId(User::class.java, "selectWeekDay", "start", monStr, "end", satStr)
        val timeList = userList.stream().map { it.lastLoginTime }.collect(Collectors.toList())
        val daySet: TreeSet<LocalDate> = TreeSet()
        timeList.forEach {
            daySet.add(it.toLocalDate())
        }
        val dayList: List<LocalDate> = arrayListOf(monday,
                monday.plusDays(1L),
                monday.plusDays(2L),
                monday.plusDays(3L),
                monday.plusDays(4L))
        val resultMap: MutableMap<String, Any> = linkedMapOf()
        val finalMap: MutableMap<String, Any> = linkedMapOf()
        var secondCount = 0L
        for (day in dayList) {
            val valueMap: MutableMap<String, Any> = linkedMapOf()
            val start = day.atStartOfDay()
            val end = day.plusDays(1L).atStartOfDay()
            var dayCollection = getDayCollection(start, end, timeList)
            val dayStr = LocalDateTimeUtil.timeToString(day)
            if (dayCollection.isEmpty()) {
                // 当天的记录为空
                valueMap["first"] = "--"
                valueMap["last"] = "--"
                valueMap["period"] = "--"
            } else {
                // 当天有记录
                dayCollection = dayCollection.sortedWith(kotlin.Comparator { o1, o2 -> o1.compareTo(o2) })
                val size = dayCollection.size
                val first = dayCollection[0]
                val firstTimeStr = LocalDateTimeUtil.timeToString(first)
                valueMap["first"] = firstTimeStr
                if (size == 1) {
                    valueMap["last"] = "--"
                    valueMap["period"] = "--"
                } else {
                    val lastTime = dayCollection[size - 1]
                    val lastTimeString = LocalDateTimeUtil.timeToString(lastTime)
                    valueMap["last"] = lastTimeString
                    val between = Duration.between(lastTime, first).abs()
                    val seconds = between.seconds
                    secondCount += seconds
                    valueMap["period"] = getTime(seconds)
                }
            }
            resultMap[dayStr!!] = valueMap
        }
        val totalTimeOfWeek = getTime(secondCount)
        finalMap["total"] = totalTimeOfWeek
        finalMap["days"] = resultMap
        var count = 0
        for (entry in resultMap.entries) {
            val value = entry.value as HashMap<*, *>
            val period = value["period"]
            if (period != null && period != "--") {
                count++
            }
        }
        if (count != 0) {
            val avg = secondCount / count
            val avgTime = getTime(avg)
            finalMap["average"] = avgTime
        } else {
            finalMap["average"] = "--"
        }
        return finalMap
    }

    override fun doSign(type: String?) {
        val uu: User = commonService.get<Int, User, User>(1, User::class.java)
        if (type == null) {
            throw RuntimeException("没有密钥")
        }
        val hashString = MD5.getHashString(MD5.getHashString(type))
        if (uu.password != hashString) {
            throw RuntimeException("密钥不匹配")
        }
        val user = User()
        user.name = "sif"
        user.lastLoginTime = LocalDateTime.now()
        commonService.save(user)
    }

    private fun getDayCollection(start: LocalDateTime, end: LocalDateTime, list: List<LocalDateTime>): List<LocalDateTime> {
        val result: MutableList<LocalDateTime> = arrayListOf()
        for (localDateTime in list) {
            if (localDateTime.isAfter(start) && localDateTime.isBefore(end)) {
                result.add(localDateTime)
            }
        }
        return result
    }

    private fun getTime(second: Long): String {
        var seconds = second
        val hours = seconds / 3600
        seconds -= hours * 3600
        val minutes = seconds / 60
        seconds -= minutes * 60
        return "${hours}小时${minutes}分钟${seconds}秒"
    }
}