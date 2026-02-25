# BrziIspit - Android Quiz App 📝

**BrziIspit** je moderna Android aplikacija za kreiranje i rješavanje kvizova, izgrađena pomoću **Kotlin** jezika i **Jetpack Compose** sučelja. Projekt koristi **Room** bazu podataka za lokalnu pohranu pitanja i setova.

## 🚀 Funkcionalnosti

* **Upravljanje setovima**: Kreiranje i brisanje vlastitih setova pitanja (npr. Povijest, Geografija).
* **Editor pitanja**: Dodavanje pitanja s 4 ponuđena odgovora i definiranje točnog odgovora.
* **Igranje kviza**: 
    * **Timer**: Svako pitanje ima vremensko ograničenje od 15 sekundi.
    * **Shuffle**: Pitanja se pojavljuju nasumičnim redoslijedom pri svakom pokretanju.
    * **Bodovanje**: Praćenje rezultata u stvarnom vremenu i prikaz finalnog score-a.
* **Lokalna baza**: Svi podaci ostaju na tvom uređaju zahvaljujući Room bazi podataka.

## 🛠 Tehnologije

* **Jezik**: [Kotlin](https://kotlinlang.org/)
* **UI**: Jetpack Compose
* **Baza podataka**: Room Database (SQLite)
* **Arhitektura**: MVVM (Model-View-ViewModel)
* **Asinkronost**: Kotlin Coroutines & Flow

## 📦 Kako pokrenuti projekt

1.  Kloniraj repozitorij:
    ```bash
    git clone [https://github.com/daviddja/BrziIspitApp.git](https://github.com/daviddja/BrziIspitApp.git)
    ```
2.  Otvori projekt u **Android Studiju** (verzija Ladybug ili novija).
3.  Pričekaj da se završi Gradle sinkronizacija.
4.  Pokreni aplikaciju na emulatoru ili fizičkom uređaju.



