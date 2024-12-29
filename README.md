# System Zarządzania Studentami

## Opis projektu

Projekt "System Zarządzania Studentami" to aplikacja umożliwiająca zarządzanie danymi studentów. Użytkownik może dodawać, usuwać, aktualizować oraz wyświetlać dane studentów, w tym ich oceny. System przechowuje dane o studentach w bazie danych SQLite i umożliwia obliczanie średniej ocen wszystkich zapisanych studentów.

## Funkcjonalności

- **Dodawanie studentów**  
  Umożliwia dodanie nowego studenta do bazy danych z informacjami o ID studenta, imieniu i nazwisku, wieku oraz ocenie.

- **Usuwanie studentów**  
  Pozwala na usunięcie studenta z bazy danych na podstawie jego unikalnego ID.

- **Aktualizowanie danych studenta**  
  Pozwala na modyfikację danych studenta: imię, wiek oraz ocenę.

- **Wyświetlanie wszystkich studentów**  
  Umożliwia wyświetlenie listy wszystkich studentów zapisanych w bazie danych.

- **Obliczanie średniej oceny**  
  Oblicza i zwraca średnią ocen wszystkich studentów w bazie danych.

## Instrukcje obsługi

### Wymagania

- **IntelliJ IDEA** lub inne środowisko obsługujące **Maven**.

### Uruchomienie aplikacji

1. **Klonowanie projektu**:  
    - Pobierz projekt lub skopiuj kod źródłowy do lokalnego folderu.
    - Otwórz **IntelliJ IDEA**.
    - Wybierz opcję **Open** i wskaż katalog projektu.

2. **Kompilacja projektu**:  
   Wybierz opcję **Build > Build Project**

3. **Uruchomienie aplikacji**:
    - Utwórz konfigurację uruchomieniową:  
      **Run > Edit Configurations**.
    - Kliknij **+** i wybierz **Application**.
    - W polu **Main class** wskaż:  
      `org.example.Main`.
    - Kliknij zieloną strzałkę w górnym prawym rogu, aby uruchomić projekt.

### Konfiguracja bazy danych
**Wymagana konfiguracja**:
- System korzysta z bazy danych **SQLite**. Plik bazy danych `students.db` zostanie automatycznie utworzony w katalogu projektu podczas pierwszego uruchomienia aplikacji.

**Wyświetlenie rekordów**:
- Otwórz terminal
- Wprowadź polecenie `sqlite3 students.db`
- Aby wyświetlić wszystkie rekordy z tabeli students wpisz polecenie `SELECT * FROM students;`
- Wyniki zostaną wyświetlone w terminalu, aby wyjść wpisz `.exit`
