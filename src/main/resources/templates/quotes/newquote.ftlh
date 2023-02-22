<!DOCTYPE html>
<html lang="en-us">

 <#include '../fragments/head.ftlh'>
  <body>

  <#include '../fragments/header.ftlh'>
    <#include '../fragments/menu.ftlh'>

    <main>
      <article>


		<div class="currentQuote">

            <form action="/quote/create" method="post" enctype="multipart/form-data">
                <div>
                    <div>
                        <label class="quote-lable-textarea" for="title" >Title<span>*</span></label>
                        <input class="quote-input-textarea" type="text" name="title" id="title" required="required" aria-required="true">
                    </div>
                    <div>
                        <label class="quote-lable-textarea" for="description">Description</label>
                        <textarea class="quote-input-textarea" type="textarea" placeholder="Brief description of the article" rows="5" name="description" access="false" id="description"></textarea>
                    </div>
                    <div>
                        <label class="quote-lable-textarea" for="data" >Data<span>*</span></label>
                        <textarea class="quote-input-textarea" type="textarea" placeholder="enter text here..." name="data" access="false" rows="15" id="data" required="required" aria-required="true"></textarea>
                    </div>
                    <div>
                        <label class="quote-lable-textarea" for="image1" >Photo 1</label>
                        <input class="quote-input-textarea" type="file" name="image1" id="image1"">
                    </div>
                    <div>
                        <label class="quote-lable-textarea" for="image2" >Photo 2</label>
                        <input class="quote-input-textarea" type="file" name="image2" id="image2"">
                    </div>
                    <div>
                        <button class="quote-lable-textarea" type="submit" name="Create" access="false" style="success" id="Create">Create</button>
                    </div>
                </div>
            </form>

		</div>

      </article>

    <#include '../fragments/aside.ftlh'>

    </main>
  </body>
</html>