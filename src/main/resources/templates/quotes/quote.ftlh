<!DOCTYPE html>
<html lang="en-us">

 <#include '../fragments/head.ftlh'>
  <body>

  <#include '../fragments/header.ftlh'>
    <#include '../fragments/menu.ftlh'>

    <main>
      <article>

		<div class="currentQuote">
            <h2>${quote.title}</h2>
            <#if hasMainImgage>
                <img src="/images/${MainImageId}" width="500">
            </#if>
            <p>${quote.data}</p>
            <#list images as image>
                <img src="/images/${image.id}" width="100">
            </#list>
            <p></p>
           <p style="font-weight: bold;">Created by <a href="/users/info/${author.getIdString()}">${author.name}</a> at ${quote.creationDate}</p>
		</div>

<#--          <form action="/quote/new" method="GET">
              <input type="submit" value="Create new">
          </form>-->
        <#if canDelete?? && canDelete>
                 <form action="/quote/${quote.id}/delete" method="post">
                    <input type="submit" value="Delete">
                    </form>
        </#if>


      </article>

    <#include '../fragments/aside.ftlh'>

    </main>
  </body>
</html>