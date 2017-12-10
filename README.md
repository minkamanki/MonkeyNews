# MonkeyNews
Simple news site.

[Wepa projekti](http://monkeynews.herokuapp.com/)

MonkeyNewsin juuresta aukee valikko josta voi joko siirtyä selaamaan artikkeleita "Browse news" tai siirtyä hallintapaneeliin "Add news".
Paneelisssa on myös linkki sisäänkirjautumiseen.
Hallintapaneelin käyttö vaatii kirjautumisen. Käytössä on vain yksinkertainen kirjautminen ja ainoat tunnukset sivulle on:
User: author
Password: author123

Artikkelin lisäys onnistuu hallintapaneelin [etusivulla](http://monkeynews.herokuapp.com/articles/).
Kaikki paitsi Lead paragraph kenttä ovat pakollisia.

Artikkelit listaantuvat samalla sivulla olevaan "Current Articles" kohtaan. Tässä myös nähdään siihen liitetyt kirjoittajat ja kategoriat.

Jos kirjautunut käyttäjä menee tarkastelemaan artikkelia, voi artikkelin pohjalta lisätä sille dropdown valikosta luotuja kirjoittajia ja authoreita, joita sille ei ole vielä lisätty. 
Lisäsi pohjalla on "Edit this article" linkki, josta voi editoida mitä tahansa aiemmin syötettyjä atribuutteja.

Hallintapaneelissa voi kirjautuneena myös lisätä kirjoittajia ja kategorioita.
Uusien kirjoittajien lisääminen ja poistaminen onnistuu [täällä](http://monkeynews.herokuapp.com/authors).
Ainoa syötettävä tieto kirjoittajille on nimi. 
Kirjoittajien kirjoittamia artikkeleita voi selata heidän nimensä takaa aukeavaa linkkiä painamalla. Esimerkiksi [K. Koponen](http://monkeynews.herokuapp.com/authors/93).
Kategorioiden lisäys ja poisto onnistuu [täältä](http://monkeynews.herokuapp.com/categories).
Kategorioille voi nimen lisäksi antaa checkboxilla arvon siitä, tuleeko kategorian näkyä navigation barissa vai ei.
Myös categorioittain voi tarkastella, mitä uutisia siihen on liitetty. Esim. [Viihde](http://monkeynews.herokuapp.com/categories/106).


[Etusivulla](http://monkeynews.herokuapp.com/home) onnistuu uutisten selailu kirjautumatta. Navigaatio palkissa näkyy aina valikot:
[Home](http://monkeynews.herokuapp.com/home), [Newest](http://monkeynews.herokuapp.com/home/newest), [Most read this week!](http://monkeynews.herokuapp.com/home/mostRead), [Edit articles](http://monkeynews.herokuapp.com/articles) ja [Log in](http://monkeynews.herokuapp.com/login).

Edit articles linkistä pääsee hallintapaneeliin, ja Log in vie taas sisäänkirjautumiseen.

Home vie etusivulle, jossa on listattuna viisi kaikkein uusinta uutista. Newest vie sivulle jonne on listattu uutiset uusimmasta vanhimpaan myös, mutta se näyttää jopa 30 uutista. Most read this week -linkistä löytyy viisi eniten luettua artikkelia.

Näiden linkkien lisäksi navigation palkissa näkyy ne kategoriat, jotka on merkitty hallintapaneelissa siellä näkyviksi. Niistä pääsee takastelemaan uutisia, jotka on merkitty vain tietylle kategorialle. Esim.[Viihde](http://monkeynews.herokuapp.com/home/Viihde) -sivulla
näkyy vain viihde kategoriaan liitetyt uutiset.

Uloskirjautuminen onnistuu hallintapaneelin etusivun pohjalla olevasta Logout napista, sama nappi löytyy myö artikkelien pohjalta, jos on kirjautunut sisään.

Artikkelit:
Artikkeleihin tulee automaattisesti lisäysaika, joka näkyy mitätahansa [artikkelia](http://monkeynews.herokuapp.com/articles/101) lukiessa otsikon alta. Sen ala puolella lukee myös, kuinka monta kertaa artikkelia on katsottu. Itse artikkelin tekstin alla lukee kirjoittajat sekä kategoriat.

