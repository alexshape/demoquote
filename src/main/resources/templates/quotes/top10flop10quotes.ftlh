<!DOCTYPE html>
<html lang="en-us">
  <#include '../fragments/head.ftlh'>
<link rel="stylesheet" href="/css/scopesstyle.css">
<link rel="stylesheet" href="/css/profilestyle.css">
<body>

    <#include '../fragments/header.ftlh'>
    <#include '../fragments/menu.ftlh'>
<main>
    <article>

        <#if showRandomQuote >
            <div class="randomQuote">
                <h2>${randomQoute.title}</h2>
                <p>${randomQoute.description}   <a href="/quote/${randomQoute.id}">read more</a></p>
            </div>
        </#if>

        <h1>Too 10 Quotes</h1>

        <div class="scopeQuotes">

            <ul class="list1a">

                <#if allQuotes?? >
                <#list allQuotes as quote>
                    <li>
                        <div class="iconblock-5">
                            <a href="/quote/${quote.id}"><h3>${quote.title}</h3></a>
                            <p>Postet by   <a href="/users/info/${quote.author.id}">${quote.author.name}</a></p>
                            <p>Score: ${quote.score}</p>

                            <form action="/quote/${quote.id}/like" method="post">
                                <input class="" type="submit" value="Like">
                            </form>
                            <form action="/quote/${quote.id}/dislike" method="post">
                                <input class="" type="submit" value="Dislike">
                            </form>
                        </div>
                    </li>
                </#list>
                </#if>

            </ul>
        </div>

    </article>

        <#include '../fragments/aside.ftlh'>

</main>
</body>
</html>