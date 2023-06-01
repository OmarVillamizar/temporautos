<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Temporautos</title>
    <style>
        .center {
            text-align: center;
        }

        .title-large {
            font-size: 500%;
            color: black;
            font-family: Comic Sans MS;
            margin-top: 20px;
        }

        .button-large {
            font-size: 400%;
            margin-top: 50px;
            font-family: Comic Sans MS;
            color: black;
            font-weight: bold;
        }

        .img-large {
            display: block;
            margin: auto;
            max-height: 40vh;
            width: 33%;
            margin-top: 50px;
        }

        .button-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
            gap: 20px;
        }

        .img-container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<h1 class="center title-large">Temporautos</h1>
<div class="img-container">
    <img class="img-large" src="https://http2.mlstatic.com/D_NQ_NP_700715-MCO53835795670_022023-O.jpg" alt="">
    <img class="img-large" src="https://upload.wikimedia.org/wikipedia/commons/c/c8/Audi_R8_-_Flickr_-_Alexandre_Pr%C3%A9vot_%28109%29_%28cropped%29.jpg" alt="">
    <img class="img-large" src="https://cdn.motor1.com/images/mgl/lEKVON/s3/roc-hobby-1-12-volkswagen-beetle-rc-car.jpg" alt="">
</div>
<div class="button-container">
    <div class="center">
        <button class="button-large">Spark</button>
    </div>
    <div class="center">
        <button class="button-large">Audi R8</button>
    </div>
    <div class="center">
        <button class="button-large">Volkswagen Beetle</button>
    </div>
</div>
</body>
</html>
