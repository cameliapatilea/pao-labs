# Proiect PAO

## Partea 1

 ### Tema proiectului - Cabinetul medical

 * Sistemul creat este menit sa serveasca la centralizarea datelor despre pacienti/medici/asistenti si respectiv docuemnteele eliberate de un cabinet medical. 

 Overall, exista 3 clase care modeleaza aplicatia. Clasele Persoana, Document si CabinetMedical.

 
 * Clasele Persoana si Document au atribute/metode(get, set) generale si sunt abstracte, pe baza carora vom defini alte clase.

 ```
Persoana este mostenita de urmatoarele clase:
  - Pacient
  - Medic
  - Asistent

  Aceste clase o extind pe cea de baza si se specializeaza fiecare in parte. Spre exemplu, un pacient poate avea anumite afectiuni, medicul are un cod de parafa, iar asistentul poate lucra in ture.
  ```

  ```
  Document are urmatoarele clase derivate:
   - TrimitereMedicala
   - ConcediuMedical
   - Reteta
   - AdeverintaMedicala

   Clasele descrise mai sus o extind pe cea de  baza, la care se mai adauga functionalitati, spre exemplu reteta are o lista de medicamente asociata, trimiterea medicala trebie sa aiba o valabilitate si o tinta(ex: catre cardiologie, recuperare medicala), iar concediul medical trebuie acordat pentru o perioada relativ scurta de timp.
  ```

  Singura clasa care nu are derivate este CabinetMedical, in care se regasesc liste de pacienti, medici si asistenti, facand practic o centralizare a datelor.


 *  Pentru fiecare clasa, cu exceptia celor abstracte, se creeaza o interfata, care va avea metodele specifice fiecarei clase in parte.
 * Fiecare interfata are asociata o clasa de serviciu care sa implementeze metodele descrise.


Mai exista o clasa abstracta GeneralEntity in care se defineste un id, un set si un get pentru atributul de mai sus, care va servi ulterior la integrarea cu baza de date. De asemenea, exista atat o interfata cat si un serviciu pentru GeneralEntity, iar cu ajutorul metodelor dezvoltate in interiorul clasei de serviciu, putem obtine orice obiect/lista de obiecte de un anumit tip pe baza unui id oferit de utilizator.

```
Exemplu de metode implementate pentru  PacientInterface:

 - getFromListById
 - adaugaAfectiune/stergAfectiune/ updateAfectiune
 - updateNume/updateVarsta
 - crearePacient
 - adaugaPacientLaLista
```

In ceea ce priveste main-ul, acesta este structurat sub forma unui meniu, implementat cu ajutorul switch statement. Utilizatorului i se va pune la dispozitie o lista de comenzi pentru fiecare tip de obiect in parte, din care trebuie sa aleaga pentru a continua inteorgarile. Pentru fiecare case in parte, va exista un submeniu, bazat tot pe switch.
Interogarile se vor termina in momentul in care utilizatorul va introduce de la tastatura numarul 0, moment in care vom iesi din instructiunea while.

Din cauza faptului ca nu este legata la o baza de date, aplicatia memoreaza pentru moment datele local, la inceputul main-ului, cu ajutorul catorva colectii de date.
