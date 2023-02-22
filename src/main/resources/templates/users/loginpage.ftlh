<!DOCTYPE html>
<html lang="en">



 <#include '../fragments/head.ftlh'>
<link rel="stylesheet" href="/css/styleUserReg.css">
<body>

  <#include '../fragments/header.ftlh'>
  <#include '../fragments/menu.ftlh'>

<main>
    <article>

            <#if user??>
                <#assign email = user.email>
            <#else>
                <#assign email = "">
            </#if>

        <form action="/users/login" method="post">
            <div class="container">
                <h1>Sign in</h1>
                   <#if haveAuthMistake?? && haveAuthMistake && messageAuth??>
                        <p class="mistakeUserForm">*<span>${messageAuth}</span>*</p>
                   </#if>
                <hr>

                <label for="username"><b>Email</b></label>
                <input type="text" placeholder="Enter username" name="username" required >

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <button type="submit" class="registerbtn">Sign in</button>

                <div class="container signin">
                    <p>Don't have an account? <a href="/users/new">Create an account!</a></p>
                </div>

            </div>

        </form>

    </article>

</main>
</body>
</html>