<aside>

    <#if user??>
        <#assign email = user.getUsername()>
    <#else>
        <#assign email = "">
    </#if>

    <#if __user??>
        <h3>Hello ${__user.name}!</h3>
        <img src="/images/${__userMainImageId}" style="width:150px; height: 150px">
        <form action="/users/logout" method="post">
            <input class="logoutinput" type="submit" value="Log out">
        </form>
    <#else>

        <form action="/users/login/" method="POST">
            <label>Name:</label>
            <input class ="input-loginfrom" type="text" name="username" value="${email}" required>
            <label>Password:</label>
            <input class ="input-loginfrom" type="password" name="password" required>
            <#if haveAuthMistake?? && haveAuthMistake && messageAuth??>
                            <p class="mistakeUserForm">*${messageAuth}*</p>
            </#if>
            <input class ="input-loginfrom " type="submit" value="Sign in">
        </form>

        <div class="createAcc">
            <a href="/users/new">Create an account!</a>
        </div>

    </#if>

</aside>