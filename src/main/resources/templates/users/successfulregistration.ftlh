<!DOCTYPE html>
<html lang="en-us">

 <#include '../fragments/head.ftlh'>
<body>

  <#include '../fragments/header.ftlh'>
  <#include '../fragments/menu.ftlh'>
  <link rel="stylesheet" href="/css/styleUserReg.css">

<main>
    <article>

        <div class="outer">
            <img style="width:200px; height: auto;" class="inner" src="/image/successfulregistration.png">
        </div>
        <#if name??>
                        <h1>Congratulation ${name}!!!</h1>
        <#else>
            <h1>Congratulation !!!</h1>
        </#if>

        <div class="container signin">
            <p><a href="/users/login">Sign in</a>.</p>
        </div>

    </article>

</main>
</body>
</html>