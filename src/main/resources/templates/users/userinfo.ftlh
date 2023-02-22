<!DOCTYPE html>
<html lang="en-us">
  <#include '../fragments/head.ftlh'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/profilestyle.css">
  <body>

    <#include '../fragments/header.ftlh'>
    <#include '../fragments/menu.ftlh'>
    <main>
      <article>

          <h3 style="text-align:center">User Profile Info</h3>

          <div class="card">

              <#if hasMainImgage>
                         <img src="/images/${MainImageId}" style="width:100%">
              <#else>
                        <img src="/image/blankavatar.png" alt="John" style="width:100%">
              </#if>
                  <h1>${user.name}</h1>
                  <p>Дата создания аккаунта: ${user.getCreationDateFormat()}</p>

          </div>
      </article>

    </main>
  </body>
</html>