<!DOCTYPE html>
<html lang="en-us" xmlns="http://www.w3.org/1999/html">

 <#include '../fragments/head.ftlh'>
 <link rel="stylesheet" href="/css/styleUserReg.css">
<body>

  <#include '../fragments/header.ftlh'>
  <#include '../fragments/menu.ftlh'>

<main>
    <article>

        <#if user??>
            <#assign email = user.email>
            <#assign name = user.name>
        <#else>
            <#assign email = "">
            <#assign name = "">
        </#if>

        <hr>

            <form action="/users/create" method="post">
                <div class="container">
                    <h1>Register</h1>
                    <#if haveAuthMistake?? && haveAuthMistake && haveAuthMistake??>
                        <p class="mistakeUserForm">*${messageAuth}*</p>
                    </#if>
                    <p>Please fill in this form to create an account.</p>
                    <label for="email"><b>Email</b></label>
                    <input type="text" placeholder="Enter Email" name="username" required
                           value="${email}">

                    <label for="name"><b>Name</b></label>
                    <input type="text" placeholder="Enter name" name="name" required value="${name}">

                    <label for="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>

                    <label for="psw-repeat"><b>Repeat Password</b></label>
                    <input type="password" placeholder="Repeat Password" name="password-repeat" required>
                    <hr>

                    <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
                    <button type="submit" class="registerbtn">Register</button>
                </div>

                <div class="container signin">
                    <p>Already have an account? <a href="/users/login">Sign in</a>.</p>
                </div>
            </form>

    </article>

</main>
</body>
</html>