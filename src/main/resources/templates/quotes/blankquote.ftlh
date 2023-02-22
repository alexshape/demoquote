<!DOCTYPE html>
<html lang="en-us">

 <#include '../fragments/head.ftlh'>
  <body>

  <#include '../fragments/header.ftlh'>
    <#include '../fragments/menu.ftlh'>

    <main>
      <article>
          <h1>${message}</h1>

          <div class="outer">
              <img style="width:200px; height: auto;" class="inner" src="/image/blankquote.png">
          </div>
          <#--<#if user??>
             <div class="outer">
                 <a href="/quote/new">
                     <button class="custom-btn btn-16">Create new</button>
                 </a>
             </div>
          </#if>-->
      </article>

    <#include '../fragments/aside.ftlh'>

    </main>
  </body>
</html>