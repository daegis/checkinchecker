<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>打卡时间统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="/layui/layui.all.js"></script>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
<div style="width: 100%;margin-bottom: 10px">
    <!-- layui 2.2.5 新增 -->
    <button id="btn" style="height: 200px" class="layui-btn layui-btn-fluid"><span style="font-size: 50px">我要签到</span>
    </button>
</div>
<form class="layui-form layui-form-pane" action="#">
    <div class="layui-form-item" pane>
        <label class="layui-form-label">输入密钥</label>
        <div class="layui-input-block">
            <input id="typeInput" type="password" name="type" autocomplete="off" placeholder="不要乱给我写！"
                   class="layui-input">
        </div>
    </div>
</form>
<blockquote class="layui-elem-quote">本周总时间：${info["total"]}</blockquote>
<blockquote class="layui-elem-quote">每日平均时间：${info.average}</blockquote>
<ul class="layui-timeline">

<#list info["days"]?keys as key>
<li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
        <h3 class="layui-timeline-title">${key}</h3>
        <table class="layui-table" lay-size="sm">
            <tbody>
            <tr>
                <td style="width: 88px">最早记录</td>
                <td>${info["days"]["${key}"]["first"]}</td>
            </tr>
            <tr>
                <td>最晚记录</td>
                <td>${info["days"]["${key}"]["last"]}</td>
            </tr>
            <tr>
                <td>间隔时间</td>
                <td>${info["days"]["${key}"]["period"]}</td>
            </tr>
            </tbody>
        </table>
    </div>
</li>
</#list>
    <li class="layui-timeline-item">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">未来</h3>
        </div>
    </li>
</ul>
<hr class="layui-bg-green">
<p style="margin-bottom: 20px;text-align: center">
    <a href="http://www.miitbeian.gov.cn/"><span style="font-size: 16px">辽ICP备17016924-2号</span></a>
</p>
<script>
    layui.use(['form', 'jquery', 'laydate'], function () {
        var form = layui.form
                , layer = layui.layer
                , $ = layui.jquery
                , laydate = layui.laydate;
        $(function () {
            $('#btn').click(function () {
                var key = $('#typeInput').val();
                if (key == null || key === '') {
                    alert('key不能为空');
                } else {
                    $.ajax({
                        url: '/doSign',
                        type: 'post',
                        data: {
                            "type": key
                        },
                        dataType: 'json',
                        error: function () {
                            alert('网络连接失败');
                        },
                        success: function (data) {
                            if (data.success) {
                                alert('记录成功');
                                window.location.reload();
                            } else {
                                alert(data.message);
                            }
                        }
                    })
                }
            })
        })
    });
</script>

</body>
</html>