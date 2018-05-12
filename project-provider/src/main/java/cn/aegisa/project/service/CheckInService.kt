package cn.aegisa.project.service

/**
 * Using IntelliJ IDEA.
 *
 * @author HNAyd.xian at 2018/5/13 20:58
 */
interface CheckInService {
    fun getInfo(): Any
    fun doSign(type: String?)
}