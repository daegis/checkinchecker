package cn.aegisa.project.web.controller

import cn.aegisa.project.service.CheckInService
import com.alibaba.fastjson.JSON
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2018/5/11 11:35
 */
@Controller
class KotlinController {

    private val log: Logger = LoggerFactory.getLogger(KotlinController::class.java)

    @Autowired
    private lateinit var checkInService: CheckInService

    @RequestMapping(value = ["/main"])
    fun mainPage(model: Model): String {
        val map = checkInService.getInfo()
        log.info("返回给前端的数据：{}", JSON.toJSONString(map))
        model.addAttribute("info", map)
        return "main"
    }

    @RequestMapping("/doSign")
    @ResponseBody
    fun doSign(type: String?): Map<*, *> {
        val map = HashMap<String, Any>()
        try {
            checkInService.doSign(type)
            map["success"] = true
        } catch (e: Exception) {
            e.printStackTrace()
            map["success"] = false
            map["message"] = e.message!!
        }
        return map
    }
}