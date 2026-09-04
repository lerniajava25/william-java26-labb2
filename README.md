# Raytracer & Domänmodellering (OOP)

**Repo för labb 2 i kursen Java 2026. Samtliga deluppgifter för godkänd nivå är implementerade**

## Klasser
### Geometri
- Intersection
  - Record som representerar en `Ray` träff av en `Shape`
  - Innehåller information som behövs för rendering av det träffade `Shape` objektet:
    - Träffens position i världen
    - Avståndet från `Ray` objektets origin-punkt
    - Det träffade `Shape` objektets färg
- Shape
  - Abstrakt klass som fungerar som mall för olika former
  - För att slippa deklarera ett fält för varje `Shape` implementerings färg används en abstrakt klass istället för ett interface
  - Innehåller:
    - Ett fält med tillhörande getter för formens färg som en subklass kan ändra vid skapande av objekt
    - En abstrakt metod `hit()` som returnerar en `Optional<Intersection>` för att undvika `NullReferenceExceptions` som är svåra att debugga
- Sphere
  - Implementering av formen sfär, tar en mittpunkt och en radie som input
  - Implementeringen av `hit()` använder algebraisk sfär-skärning för att beräkna `Ray` träffar
    - https://www.gabrielgambetta.com/computer-graphics-from-scratch/02-basic-raytracing.html
- Triangle
  - Implementering av formen triangel, tar tre `Vector3D` punkter för positionen på triangelns hörn som input
  - Implementeringen av `hit()` följer *Möller-Trumbores* intersection algoritm för att beräkna `Ray` träffar
    - https://en.wikipedia.org/wiki/M%C3%B6ller%E2%80%93Trumbore_intersection_algorithm
### Matematik
- Color
  - Klass för att rita färger på `Shape` objekt
  - Tar ett värde för färgkanalerna röd, grön och blå respektive som input
    - Värdena på varje kanal använder metoden `clamp01()` för att hålla input värdena mellan 0 och 1
  - Har en metod `toRGB()` som konverterar respektive värde för röd, grön och blå till ett enda `int` värde som används vid skrivning av scenen till PNG-bild
    - Använder bit-shifting för att lagra respektive färgkanal på 8 bitar i en `int` som är 32 bitar.
    - De 8 bitar som representerar alfa-kanalen används inte i existerande implementering
- Ray
  - Klass som representerar rays som kan träffa `Shape` objekt i scenen så att vi kan rita grafiken
  - Tar två `Vector3D` värden som input, en för strålens ursprungliga position, *origin*, och en för strålens riktning, *direction*
  - Har en metod `pointAt()` som returnerar en `Vector3D` för positionen där strålen träffade en `Shape`
    - Tar en `double` som input för avståndet i strålens riktning som träffen registrerats på
- Vector3D
  - Klass som representerar 3d koordinater
  - Instanser kan skapas på tre sätt:
    - Utan några värden för x, y och z, som alla då initialiseras till 0
    - Med tre `double` värden för x, y och z respektive
    - Med en annan `Vector3D` instans vars värden på x, y och z då kopieras till den nya instansen
  - Har en rad metoder för aritmetik:
    - `add()` och `subtract()` för att addera eller subtrahera en annan `Vector3D` på den egna
    - `multiply()` för att multiplicera den egna vektorn med en skalär
    - `dot()` för att få skalärprodukten av en annan `Vector3D` och den egna
    - `cross()` för att få kryssprodukten av en annan `Vector3D` och den egna
    - `length()` för att få längden på vektorn
    - `normalize()` för att få den egna vektorn som en enhetsvektor
### Rendering
- ImageWriter
  - Klass som skriver ut en scen ur kamerans vinkel till en PNG-bild
  - Tar en tvådimensionell array av `Color` och en `Camera` som input
  - Använder den inbyggda `ImageIO.write()` metoden för utskrift
- Renderer
  - Klass som renderar scenen till en tvådimensionell array av `Color`
  - Tar en `Camera`, en `Scene` och en `Color` för scenens bakgrundsfärg som input
  - Hämtar alla `Ray` objekt genererade av `Camera` objektet och beräknar träffar av `Shape` objekt för varje `Ray`
### Scener
- Camera
  - Klass som representerar en "kamera" vi tittar igenom för att se scenen
  - Tar två `Vector3D` för kamerans position och riktning i världen och två `int` värden för bredden och höjden på kamerans viewport
  - Har en metod `generateViewportRays()` som skapar en `Ray` instans för varje pixel i viewporten
    - Viewporten placeras på ett bestämt avstånd från kameran för att representera dess *field-of-view*
    - Varje `Ray` instans skapas med *origin* som matchar kamerans position, och riktas därifrån mot sin motsvarande pixel i viewporten
    - På så sätt är kameran i *perspective*-läge, vilket innebär att `Shape` objekt ser olika stora ut i scenen beroende på deras avstånd från kameran
- Scene
  - Klass som representerar en "scen", eller "värld" som innehåller `Shape` objekt
  - Har en metod `addShape()` för att lägga till `Shape` objekt i scenen
  - Har en metod `findClosestHit()` som tar en `Ray` som input och returnerar den närmaste träffen av ett `Shape` objekt till kameran
