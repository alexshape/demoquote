<aside>

     <#if .now>
         efdsf
     <#else>
         fdgdfgfdgdfgdfg
     </#if>

    <#if user??>
        <#assign email = user.email>
    <#else>
        <#assign email = "">
    </#if>

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
</aside>