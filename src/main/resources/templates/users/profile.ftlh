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

          <h3 style="text-align:center">User Profile Card</h3>

          <div class="card">

              <#if editProfile?? && editProfile>
                <form action="/users/edit" method="post" enctype="multipart/form-data">
                    <input class="editinput" type="submit" value="Apply">
                    <#if hasMainImgage>
                         <img src="/images/${MainImageId}" style="width:100%">
                    <#else>
                        <img src="/image/blankavatar.png" alt="John" style="width:100%">
                    </#if>
                    <div>
                        <label class="quote-lable-textarea" for="photo" >Select an account photo;</label>
                        <input class="quote-input-textarea" type="file" name="photo" id="photo"">
                    </div>

                    <div>
                        <label class="quote-lable-textarea" for="description">Имя:</label>
                        <input type="text" placeholder="Your name" name="name"
                               access="false" id="name" value="${user.name}">
                    </div>

                </form>
                  <form action="/users/profile" method="get">
                      <input class="logoutinput" type="submit" value="Cancel">
                  </form>
              <#else>
                    <form action="/users/edit" method="get">
                        <input class="editinput" type="submit" value="Edit">
                    </form>
              <#if hasMainImgage>
                         <img src="/images/${MainImageId}" style="width:100%">
              <#else>
                        <img src="/image/blankavatar.png" alt="John" style="width:100%">
              </#if>


                  <h1>${user.name}</h1>
                  <p class="title">User name: ${user.getUsername()}</p>
                  <p>Дата создания аккаунта: ${user.getCreationDateFormat()}</p>
                                <form action="/users/logout" method="post">
                                    <input class="logoutinput" type="submit" value="Log out">
                                </form>
          </#if>


          </div>

          <h3 style="text-align:center">My quotes</h3>

          <form action="/quote/new" method="get">
              <input class="editinput" type="submit" value="Add new quote" style="width: 20%">
          </form>

          <#if quotes??>
                <ul class="list1a">
                  <#list quotes as quote>
                        <a href="/quote/${quote.id}"><li>${quote.title}</li></a>
                  </#list>
                </ul>
          <#else>
            <p style="text-align:left">You still don't have any quotes</p>
          </#if>
      </article>

    </main>
  </body>
</html>