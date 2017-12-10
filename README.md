# MonkeyNews
Simple news site.

[![Build Status](https://travis-ci.org/minkamanki/MonkeyNews.svg?branch=master)](https://travis-ci.org/minkamanki/MonkeyNews)
[![codebeat badge](https://codebeat.co/badges/38c5af90-18d1-49af-91b4-e6c519952d24)](https://codebeat.co/projects/github-com-minkamanki-monkeynews-master)

[Wepa projekti](http://monkeynews.herokuapp.com/)

MonkeyNewsin juuresta aukee valikko josta voi joko siirtyä selaamaan artikkeleita [Browse news](http://monkeynews.herokuapp.com/home) tai siirtyä hallintapaneeliin [Add news](http://monkeynews.herokuapp.com/articles/).<br />
Paneelisssa on myös linkki [sisäänkirjautumiseen](http://monkeynews.herokuapp.com/login]).<br />
Hallintapaneelin käyttö vaatii kirjautumisen. Käytössä on vain yksinkertainen kirjautminen ja ainoat tunnukset sivulle on:<br />
User: author<br />
Password: author123

Artikkelin lisäys onnistuu hallintapaneelin [etusivulla](http://monkeynews.herokuapp.com/articles/).<br />
Kaikki paitsi Lead paragraph kenttä ovat pakollisia.<br />

Artikkelit listaantuvat samalla sivulla olevaan "Current Articles" kohtaan. Tässä myös nähdään siihen liitetyt kirjoittajat ja kategoriat.<br />

Jos kirjautunut käyttäjä menee tarkastelemaan artikkelia, voi artikkelin pohjalta lisätä sille dropdown valikosta luotuja kirjoittajia ja authoreita, joita sille ei ole vielä lisätty. <br />
Lisäsi pohjalla on "Edit this article" linkki, josta voi editoida mitä tahansa aiemmin syötettyjä atribuutteja.<br />

Hallintapaneelissa voi kirjautuneena myös lisätä kirjoittajia ja kategorioita.<br />
Uusien kirjoittajien lisääminen ja poistaminen onnistuu [täällä](http://monkeynews.herokuapp.com/authors).
Ainoa syötettävä tieto kirjoittajille on nimi. <br />
Kirjoittajien kirjoittamia artikkeleita voi selata heidän nimensä takaa aukeavaa linkkiä painamalla. Esimerkiksi [K. Koponen](http://monkeynews.herokuapp.com/authors/93).<br />
Kategorioiden lisäys ja poisto onnistuu [täältä](http://monkeynews.herokuapp.com/categories).<br />
Kategorioille voi nimen lisäksi antaa checkboxilla arvon siitä, tuleeko kategorian näkyä navigation barissa vai ei.<br />
Myös categorioittain voi tarkastella, mitä uutisia siihen on liitetty. Esim. [Viihde](http://monkeynews.herokuapp.com/categories/106).<br />


[Etusivulla](http://monkeynews.herokuapp.com/home) onnistuu uutisten selailu kirjautumatta. Navigaatio palkissa näkyy aina valikot:<br />
[Home](http://monkeynews.herokuapp.com/home), [Newest](http://monkeynews.herokuapp.com/home/newest), [Most read this week!](http://monkeynews.herokuapp.com/home/mostRead), [Edit articles](http://monkeynews.herokuapp.com/articles) ja [Log in](http://monkeynews.herokuapp.com/login).<br />

Edit articles linkistä pääsee hallintapaneeliin, ja Log in vie taas sisäänkirjautumiseen.<br />

Home vie etusivulle, jossa on listattuna viisi kaikkein uusinta uutista. Newest vie sivulle jonne on listattu uutiset uusimmasta vanhimpaan myös, mutta se näyttää jopa 30 uutista. Most read this week -linkistä löytyy viisi eniten luettua artikkelia.<br />

Näiden linkkien lisäksi navigation palkissa näkyy ne kategoriat, jotka on merkitty hallintapaneelissa siellä näkyviksi. Niistä pääsee takastelemaan uutisia, jotka on merkitty vain tietylle kategorialle. Esim.[Viihde](http://monkeynews.herokuapp.com/home/Viihde) -sivulla
näkyy vain viihde kategoriaan liitetyt uutiset.<br />

Uloskirjautuminen onnistuu hallintapaneelin etusivun pohjalla olevasta Logout napista, sama nappi löytyy myö artikkelien pohjalta, jos on kirjautunut sisään.<br />

Artikkelit:<br />
Artikkeleihin tulee automaattisesti lisäysaika, joka näkyy mitätahansa [artikkelia](http://monkeynews.herokuapp.com/articles/101) lukiessa otsikon alta. Sen ala puolella lukee myös, kuinka monta kertaa artikkelia on katsottu. Itse artikkelin tekstin alla lukee kirjoittajat sekä kategoriat.

