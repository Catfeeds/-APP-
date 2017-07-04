<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <link rel="shortcut icon" href="./image/bitbug_favicon.ico" type="image/x-icon"/>
    <title>腰果官方支持网页</title>
</head>
<style>
    *{
        margin: 0;
        padding: 0;
        font-family: "微软雅黑";
    }
    .container{
        min-width: 1354px;
        background: #fc9124;
        position: fixed;
        bottom:0;
        right: 0;
        left: 0;
        top: 0;

    }
    .container header{
        font-size: 55px;
        color: #fff;
        margin-left:27%;
        margin-top: 50px;
    }
    .container section{
        display: flex;
        flex-direction: row;
        margin-left: 27%;
        margin-top: 200px;
    }
    .container section .section-left{}
    .container section .section-left .left-top{
        color: #ffffff;
        font-size: 40px;
    }
    .container section .section-left .left-bottom{
        margin-top: 30px;
    }
    .container section .section-left .left-bottom img{
    }
    .container section .section-right{
        margin-left: 60px;
    }
    .container section .section-right img{
        margin-top: 20px;
    }
    .container footer{
        display: flex;
        flex-direction: row;
        margin-left: 27%;
        margin-top: 200px;
        margin-bottom: 40px;
        /*justify-content: center;*/
    }
    .container footer .footer-one-part{
        display: flex;
        flex-direction: row;
        justify-content: center;
    }
    .container footer .footer-one-part .pic{
        width: 20px;
    }
    .container footer .footer-one-part .pic img{
        width: 100%;
    }
    .container footer .footer-one-part .tel-number{
        color: #ffffff;
        font-size: 20px;
        position: relative;
        top:-3px;
        padding-left: 15px;
    }
    .container footer .footer-two-part{
        display: flex;
        flex-direction: row;
        justify-content: center;
        margin: 0 80px;
    }
    .container footer .footer-two-part .pic{
        width: 20px;
    }
    .container footer .footer-two-part .pic img{
        width: 100%;
    }
    .container footer .footer-two-part .email-number{
        color: #ffffff;
        font-size: 20px;
        position: relative;
        top:-3px;
        padding-left: 15px;
    }
    .container footer .footer-three-part{
        display: flex;
        flex-direction: row;
        justify-content: center;
    }
    .container footer .footer-three-part .pic{
        width: 20px;
    }
    .container footer .footer-three-part .pic img{
        width: 100%;
    }
    .container footer .footer-three-part .address{
        color: #ffffff;
        font-size: 20px;
        position: relative;
        top:-3px;
        padding-left: 15px;
    }
</style>
<body>
<div class="container">
    <header>腰果生活</header>
    <section>
        <div class="section-left">
            <div class="left-top">优质生活的专属平台</div>
            <div class="left-bottom">
                <img src="./image/ercode.png" alt="">
            </div>
        </div>
        <div class="section-right">
            <img src="./image/logo.png" alt="">
        </div>
    </section>
    <footer>
        <div class="footer-one-part">
            <div class="pic"><img src="./image/tel.png" alt=""></div>
            <div class="tel-number">18667131103</div>
        </div>
        <div class="footer-two-part">
            <div class="pic"><img src="./image/email.png" alt=""></div>
            <div class="email-number">lyy@yaoguo100.com</div>
        </div>
        <div class="footer-three-part">
            <div class="pic"><img src="./image/location.png" alt=""></div>
            <div class="address">杭州市西湖区古墩路同人精华大厦2座301</div>
        </div>
    </footer>
</div>
</body>
</html>