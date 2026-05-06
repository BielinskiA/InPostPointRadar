📡 InPost Point Radar – Technical Assignment
-----------------------------------------------------------------------------------------------------------------------------------------------
Target: Software Development Internship (Backend) @ InPost Technology

Project Date: May 2026

📖 Project Overview

InPost Point Radar to backendowa usługa stworzona w celu ułatwienia lokalnej nawigacji po gęstej sieci punktów InPost. Zamiast przeszukiwać surowe dane, system pozwala na precyzyjne odpytywanie bazy o punkty na poziomie mikrolokalizacji (osiedla, konkretne ulice w obrębie kodów pocztowych).

The Problem

API InPostu zwraca ogromne ilości danych w formie paginowanej. Dla użytkownika końcowego lub systemów analitycznych, filtrowanie "w locie" po fragmentach adresu (np. nazwa osiedla) przy dużym natężeniu ruchu może być nieefektywne.

The Solution

Zbudowałem warstwę API Enrichment, która:

Synchronizuje dane: Pobiera dane z globalnego API InPost i mapuje je na zoptymalizowany model relacyjny.

Udostępnia Smart Searching: Oferuje endpointy do błyskawicznego wyszukiwania punktów po mieście, kodzie pocztowym oraz – co najważniejsze – po nazwie osiedla/ulicy wewnątrz danej lokalizacji.

-----------------------------------------------------------------------------------------------------------------------------------------------
🛠️ Tech Stack
- Java 17

- Spring Boot 3.4.0

- Spring WebFlux

- Spring Data JPA

- H2 Database

- MapStruct

- Lombok

- JUnit 5

- Mockito
  
-----------------------------------------------------------------------------------------------------------------------------------------------
🚀 How to Run

Prerequisites

- JDK 17

- Gradle 8.x 

Installation & Launch

Sklonuj repozytorium:

Bash

git clone https://github.com/BielinskiA/InPostPointRadar/

Uruchom aplikację:

Bash

./gradlew bootRun

Aplikacja dostępna pod adresem: http://localhost:8081

-----------------------------------------------------------------------------------------------------------------------------------------------

🔌 API Endpoints & Usage

1. Synchronizacja (Pierwszy krok)

- Zaciąga dane z serwerów InPost do lokalnej bazy: 
POST /api/v1/points/sync

<img width="1489" height="928" alt="image" src="https://github.com/user-attachments/assets/40004afb-f89a-4198-a6c7-d8825a30aa7c" />

-----------------------------------------------------------------------------------------------------------------------------------------------

2. Przykładowe wyszukiwanie

- Wyszukiwanie po nazwie miasta:
GET /api/v1/points/search?city=Gniezno

<img width="1486" height="928" alt="image" src="https://github.com/user-attachments/assets/8dbdb1ea-306a-49b3-b825-6f827ef3fe70" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Wyszukiwanie po kodzie pocztowym: 
GET /api/v1/points/search/post-code?postCode=62-200

<img width="1489" height="929" alt="image" src="https://github.com/user-attachments/assets/9b051749-c42e-4620-a8d6-05c3efd1556b" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Wyszukiwanie po osiedlu/ulicy i mieście (np. Gniezno, os. Jagiellońskie): 
GET /api/v1/points/search/city/street?city=Gniezno&street=Jagiellońskie

<img width="1487" height="928" alt="image" src="https://github.com/user-attachments/assets/a5e3c62e-b011-4778-9d58-b9402af64e96" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Wyszukiwanie po osiedlu/ulicy i kodzie pocztowym:
GET /api/v1/points/search/city/street?city=Gniezno&street=Jagiellońskie

<img width="1487" height="930" alt="image" src="https://github.com/user-attachments/assets/54f8d03c-6ef9-423f-ab07-450c729a5a54" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Liczenie punktów:
GET /api/v1/points/count

<img width="1487" height="928" alt="image" src="https://github.com/user-attachments/assets/f7b4c9bf-14e3-4ff2-901d-c22d43553c54" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Liczenie punktów po kodzie pocztowym: 
GET /api/v1/points/search/post-code/count?postCode=62-200

<img width="1486" height="928" alt="image" src="https://github.com/user-attachments/assets/bd878b30-afad-4117-80ac-6eb88300459d" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Liczenie punktów po nazwie miasta:
GET /api/v1/points/search/count?city=Gniezno

<img width="1489" height="930" alt="image" src="https://github.com/user-attachments/assets/213a2823-dd16-4f31-8c2e-2a7b7071ed25" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Liczenie punktów po nazwie miasta i osiedlu/ulicy:
GET /api/v1/points/search/city/street/count?city=Gniezno&street=Jagiellońskie

<img width="1488" height="927" alt="image" src="https://github.com/user-attachments/assets/6eed50b6-1d54-4f4d-9813-d7dccb6f8a57" />

-----------------------------------------------------------------------------------------------------------------------------------------------

- Liczenie punktów po kodzie pocztowym i osiedlu/ulicy:
GET /api/v1/points/search/post-code/street/count?postCode=61-208&street=Oświecenia

<img width="1488" height="931" alt="image" src="https://github.com/user-attachments/assets/e81b0c0d-d754-4e50-9be0-627e9a4106f8" />

-----------------------------------------------------------------------------------------------------------------------------------------------

🧠 Architectural Decisions

- Quality over Quantity: Zamiast budować prosty wrapper na API, zdecydowałem się na implementację pełnego cyklu życia danych: Fetch -> Map -> Store -> Search.

- Normalization: Dane adresowe InPost są sklejane (street + buildingNumber) w jedną czytelną linię, co ułatwia wyszukiwanie tekstowe.

- Testing Strategy:

- SyncServiceImplTest: Testuje logikę mapowania adresów.

- PointRepositoryTest: Sprawdza poprawność zapytań SQL Case-Insensitive.

- PointControllerTest: Gwarantuje stabilność kontraktu API.

- Error Handling: Implementacja GlobalExceptionHandler zapewnia, że błędy API InPostu nie powodują "wyłożenia się" aplikacji, lecz są logowane i obsługiwane.

-----------------------------------------------------------------------------------------------------------------------------------------------
📈 Future Improvements
Geospatial Queries: Wprowadzenie Hibernate Spatial do wyszukiwania punktów w promieniu X km od współrzędnych użytkownika.

Caching: Dodanie Redisa dla najczęściej wyszukiwanych kodów pocztowych.

Frontend: Prosta mapa w React/Leaflet wyświetlająca wyniki "Radaru".
