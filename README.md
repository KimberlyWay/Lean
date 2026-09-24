# Конвертер величин (Android)

Простое учебное приложение на Java.

- **Главное окно**: три кнопки — «Длина», «Масса», «Температура».
- Каждая кнопка открывает своё окно с квадратиками, по одному на каждую единицу:
  - Длина — см, дм, м, км
  - Масса — мг, г, кг, т
  - Температура — °C, °F, K
- Вводишь число в любой квадратик, и остальные пересчитываются сами.

Содержание:

1. [Часть 1. Как открыть и собрать проект в Android Studio (с нуля)](#часть-1-как-открыть-и-собрать-проект-в-android-studio-с-нуля)
2. [Часть 2. Как устроен проект: какой файл за что отвечает](#часть-2-как-устроен-проект-какой-файл-за-что-отвечает)
3. [Часть 3. Азбука: что значат символы в XML и Java](#часть-3-азбука-что-значат-символы-в-xml-и-java)
4. [Часть 4. Разбор каждого файла построчно](#часть-4-разбор-каждого-файла-построчно)

---

# Часть 1. Как открыть и собрать проект в Android Studio (с нуля)

Инструкция рассчитана на случай, когда Android Studio только что установлена
и её ещё ни разу не запускали. Названия пунктов меню даны по-английски, потому что
Android Studio по умолчанию на английском.

## Шаг 0. Что нужно заранее

- Установленная **Android Studio** (скачивается с https://developer.android.com/studio).
- **Интернет**: при первом запуске скачается около 1–3 ГБ, а при первой сборке проекта ещё несколько сотен МБ.
- **Свободное место**: хотя бы 10 ГБ, с эмулятором телефона — лучше 20 ГБ.
- **Java отдельно ставить не нужно**: внутри Android Studio уже есть своя Java (она называется JBR — JetBrains Runtime).

## Шаг 1. Первый запуск Android Studio (мастер настройки)

При самом первом запуске откроется мастер настройки (Setup Wizard). Проходим его так:

1. **Import Android Studio Settings** (если спросит) → выбрать **Do not import settings** → **OK**.
   Импортировать нечего, студия ставится впервые.
2. **Help improve Android Studio** (отправлять ли статистику) → **Don't send** или **Send**, это ни на что не влияет.
3. **Welcome** → **Next**.
4. **Install Type** → выбрать **Standard** → **Next**.
   Standard ставит всё нужное с настройками по умолчанию. Custom нужен только тем, кто знает, что меняет.
5. **Select UI Theme** (тема самой студии, не приложения) → любая → **Next**.
6. **Verify Settings** — список того, что будет скачано (Android SDK, Platform-Tools, Emulator и т. д.) → **Next**.
7. **License Agreement** — слева несколько лицензий (например, `android-sdk-license`, `android-sdk-arm-dbt-license`).
   **Нужно кликнуть на каждую лицензию слева и для каждой выбрать Accept.**
   Пока не приняты все, кнопка **Finish** неактивна. Это самое частое место, где застревают.
8. **Finish** → начнётся скачивание компонентов (**Downloading Components**). Это может занять 5–30 минут.
   Когда внизу появится надпись о завершении, снова нажать **Finish**.

После этого откроется окно **Welcome to Android Studio** с кнопками **New Project**, **Open** и **Get from VCS**
(в новых версиях эта кнопка называется **Clone Repository**).

> **Что такое SDK, которое сейчас скачалось?** SDK (Software Development Kit) — это «набор разработчика»:
> библиотеки Android, инструменты сборки, эмулятор. Если код — это рецепт, то SDK — это кухня с плитой.
> Без него из кода не получится приложение.

## Шаг 2. Скачать проект с GitHub

Есть два способа, выбери любой.

### Способ А (проще): скачать ZIP

1. Открыть в браузере https://github.com/KimberlyWay/Lean
2. Нажать зелёную кнопку **Code** → **Download ZIP**.
3. Распаковать архив. Получится папка вроде `Lean-claude-greeting-bww73h`.
4. **Важно для Windows:** положить папку туда, где **в пути нет русских букв и пробелов**.
   Например, `C:\Projects\Lean`, а не `C:\Users\Лина\Рабочий стол\...`.
   С русскими буквами в пути сборка на Windows часто падает с непонятными ошибками.

### Способ Б: через Git прямо из Android Studio

1. В окне Welcome нажать **Get from VCS** / **Clone Repository** (VCS — Version Control System, система контроля версий, то есть Git).
2. **Version control**: Git. **URL**: `https://github.com/KimberlyWay/Lean.git`.
   **Directory**: папка без русских букв (например, `C:\Projects\Lean`).
3. Нажать **Clone**. Если Git не установлен, студия сама предложит его скачать (**Download and Install**) — соглашаемся.

## Шаг 3. Открыть проект

1. В окне Welcome нажать **Open**.
2. Выбрать **папку проекта** — ту, где лежат файлы `settings.gradle.kts`, `build.gradle.kts`, `gradlew`
   и папка `app`. Открывать нужно именно эту папку, а не папку `app` внутри и не ZIP-архив.
3. Нажать **OK**.
4. Появится окно **Trust and Open Project?** → нажать **Trust Project**.
   Студия спрашивает, доверяем ли мы этому коду, потому что при открытии она запускает сборочные скрипты проекта.

## Шаг 4. Дождаться синхронизации Gradle (Gradle Sync)

Сразу после открытия студия начнёт **Gradle Sync**:
внизу справа появится полоска прогресса, а внизу окна вкладка **Build** с логом.

Что происходит в этот момент:

- скачивается сама программа **Gradle** версии 8.7 (её версия записана в `gradle/wrapper/gradle-wrapper.properties`);
- скачивается **Android Gradle Plugin** 8.5.2 (записан в `build.gradle.kts`);
- студия читает проект и понимает, где код, где разметка и т. д.

**Первый раз это может занять 5–15 минут.** Ничего не трогаем, ждём, пока полоска исчезнет,
а во вкладке **Build** появится зелёная галочка и надпись вроде `BUILD SUCCESSFUL` или `Sync finished`.

### Что может всплыть во время синхронизации

- **«Project update recommended»**, **«Android Gradle Plugin can be upgraded»** или **Upgrade Assistant** — студия предлагает обновить версии.
  **Лучше отказаться** (**Don't ask for this project** или просто закрыть). Проект собран и проверен
  именно на текущих версиях, а обновление может потребовать правок.
- **«Failed to find target with hash string 'android-34'»** или **«SDK Platform 34 is not installed»** —
  не хватает Android 14 SDK. Обычно прямо в ошибке есть синяя ссылка **Install missing platform(s) and sync project** —
  нажать её и принять лицензию. Или вручную: **Tools → SDK Manager → вкладка SDK Platforms →
  поставить галочку Android 14.0 ("UpsideDownCake") / API Level 34 → Apply → OK**.
- **Ошибка про JDK / Java** (например, `requires Java 17`) — открыть
  **File → Settings** (на Mac: **Android Studio → Settings**) **→ Build, Execution, Deployment → Build Tools → Gradle**
  и в поле **Gradle JDK** выбрать **jbr-17** или **jbr-21** (встроенная Java студии). Затем **OK** и
  **File → Sync Project with Gradle Files**.
- **Ошибки скачивания** (`Could not resolve…`, `Connection timed out`) — проблемы с интернетом, VPN или прокси.
  Проверить интернет и нажать **File → Sync Project with Gradle Files** (кнопка со слоником и стрелкой вверху справа).

## Шаг 5. Где в студии искать файлы

Слева панель **Project**. Сверху у неё выпадающий список, по умолчанию выбран вид **Android**:

```
app
 ├─ manifests
 │   └─ AndroidManifest.xml
 ├─ java
 │   └─ com.example.unitconverter
 │       ├─ LengthActivity
 │       ├─ MainActivity
 │       ├─ MassActivity
 │       └─ TemperatureActivity
 └─ res
     ├─ drawable
     │   └─ box.xml
     └─ layout
         ├─ activity_length.xml
         ├─ activity_main.xml
         ├─ activity_mass.xml
         └─ activity_temperature.xml
Gradle Scripts
 ├─ build.gradle.kts (Project: UnitConverter)
 ├─ build.gradle.kts (Module :app)
 ├─ gradle-wrapper.properties
 └─ settings.gradle.kts
```

Вид **Android** — это «упрощённый» показ, а не настоящие папки на диске. Настоящие папки
показывает вид **Project** в том же выпадающем списке.

Если открыть файл разметки (`activity_main.xml`), справа вверху будут кнопки **Code / Split / Design**:
- **Code** — сам XML-текст;
- **Design** — визуальный предпросмотр экрана;
- **Split** — оба сразу.

## Шаг 6. Запустить приложение

Нужен телефон или эмулятор. Выбери один вариант.

### Вариант 1: на своём телефоне по USB

1. На телефоне: **Настройки → О телефоне → 7 раз нажать на «Номер сборки»**
   (на Xiaomi это «Версия MIUI» / «Версия ОС»). Появится надпись «Вы стали разработчиком».
2. **Настройки → Для разработчиков** (часто в «Расширенных настройках» или «Системе») → включить **Отладка по USB**.
3. Подключить телефон кабелем к компьютеру. На телефоне появится окно **«Разрешить отладку по USB?»** → **Разрешить**.
4. В студии вверху, в списке устройств, появится модель телефона.

### Вариант 2: на эмуляторе (виртуальный телефон)

1. Справа панель **Device Manager** (или **Tools → Device Manager**).
2. **+** → **Create Virtual Device**.
3. Выбрать телефон, например **Pixel 6** → **Next**.
4. Выбрать образ системы, например **API 34** (рядом может быть значок скачивания — нажать, скачать, **Finish**) → **Next** → **Finish**.
5. В списке устройств нажать ▶ рядом с созданным эмулятором. Первый запуск может быть долгим.

### Запуск

Вверху студии: слева выбрана конфигурация **app**, рядом выбрано устройство. Нажать зелёную кнопку **▶ Run 'app'**
(или **Shift+F10**). Студия соберёт приложение, установит его на устройство и откроет.

## Шаг 7. Собрать APK-файл

1. Меню **Build → Build App Bundle(s) / APK(s) → Build APK(s)**.
   В новых версиях студии пункт называется **Build → Generate App Bundles or APKs → Generate APKs**.
2. Когда сборка закончится, внизу справа появится уведомление → нажать **locate**.
3. Откроется папка с файлом `app-debug.apk`. Путь внутри проекта: `app/build/outputs/apk/debug/app-debug.apk`.

Этот файл можно скинуть на телефон и установить. Телефон может спросить разрешение
на «установку из неизвестных источников» — это нормально для APK не из Google Play.

## Шаг 8. Собрать APK без Android Studio (через GitHub)

В проекте настроен **GitHub Actions** — это сборка на серверах GitHub (файл `.github/workflows/android-build.yml`).

- После каждого `push` на вкладке **Actions** репозитория появляется запуск. В нём, в разделе **Artifacts**, лежит APK.
- Выпустить релиз: **Actions → Android Build → Run workflow**, в поле указать тег (например `v1.4.0`) → **Run workflow**.
  Через пару минут на странице **Releases** появится релиз с файлом `app-debug.apk`.

---

# Часть 2. Как устроен проект: какой файл за что отвечает

```
Lean/                                   ← корень проекта (открываем в студии именно его)
├─ settings.gradle.kts                  ← «оглавление» проекта: из каких модулей состоит и откуда качать библиотеки
├─ build.gradle.kts                     ← общие настройки сборки: какая версия Android-плагина
├─ gradlew, gradlew.bat                 ← скрипты-запускалки Gradle (для Linux/Mac и для Windows)
├─ gradle/wrapper/
│   ├─ gradle-wrapper.jar               ← маленькая программа, которая скачивает нужный Gradle
│   └─ gradle-wrapper.properties        ← какую версию Gradle качать
├─ .gitignore                           ← какие файлы не сохранять в Git
├─ .github/workflows/android-build.yml  ← инструкция для GitHub: как собрать APK на их сервере
└─ app/                                 ← модуль «app» — само приложение
    ├─ build.gradle.kts                 ← настройки приложения: имя пакета, версии Android, версия приложения
    └─ src/main/
        ├─ AndroidManifest.xml          ← «паспорт» приложения: название, тема, список окон
        ├─ java/com/example/unitconverter/
        │   ├─ MainActivity.java        ← код главного окна (3 кнопки)
        │   ├─ LengthActivity.java      ← код окна «Длина»
        │   ├─ MassActivity.java        ← код окна «Масса»
        │   └─ TemperatureActivity.java ← код окна «Температура»
        └─ res/                         ← ресурсы (res = resources): всё, что не код
            ├─ drawable/box.xml         ← рисунок рамки-квадратика
            └─ layout/                  ← разметка экранов: что где стоит
                ├─ activity_main.xml
                ├─ activity_length.xml
                ├─ activity_mass.xml
                └─ activity_temperature.xml
```

**Как всё связано, коротко:**

1. Пользователь нажимает иконку приложения. Android смотрит в `AndroidManifest.xml`, какое окно главное, и находит `MainActivity`.
2. `MainActivity.java` показывает разметку `activity_main.xml` — три кнопки.
3. У каждой кнопки в разметке написано `android:onClick="openLength"` и т. п. При нажатии Android вызывает
   одноимённый метод в `MainActivity.java`.
4. Метод открывает новое окно, например `LengthActivity`.
5. `LengthActivity.java` показывает `activity_length.xml` (квадратики) и следит, когда в них меняется текст.
6. Когда текст меняется, код переводит число в базовую единицу (метры) и из неё — во все остальные квадратики.

**Что такое Activity?** Activity (читается «активити») — это один экран приложения.
Сколько экранов, столько Activity. У нас их четыре.

**Что такое Gradle?** Gradle — программа-сборщик. Она берёт код, разметку и картинки,
компилирует и упаковывает всё в один файл `.apk`, который можно установить на телефон.
Файлы с `.gradle.kts` — это инструкции для Gradle, написанные на языке Kotlin (отсюда `.kts` — Kotlin Script).

---

# Часть 3. Азбука: что значат символы в XML и Java

Сначала общие символы, которые встречаются везде, чтобы потом не повторять их в каждой строке.

## Символы XML (файлы `.xml`: манифест, разметка, рамка)

XML — это способ описывать вещи «тегами», вложенными друг в друга, как матрёшки.

| Символ / запись | Что значит |
|---|---|
| `<?xml version="1.0" encoding="utf-8"?>` | Первая строка любого XML-файла. Говорит: «это XML версии 1.0, текст в кодировке UTF-8». UTF-8 нужна, чтобы нормально работали русские буквы. `<?` и `?>` — особые скобки для служебной строки. |
| `<Тег>` | Открывающий тег: начало элемента. Например, `<LinearLayout>` — «начинается контейнер». |
| `</Тег>` | Закрывающий тег: конец элемента. `/` в начале значит «закрываю». Всё между `<Тег>` и `</Тег>` лежит внутри этого элемента. |
| `<Тег ... />` | Самозакрывающийся тег: элемент без вложенного содержимого. `/>` в конце — это открыл и сразу закрыл. Например, `<Button ... />`. |
| `имя="значение"` | Атрибут — свойство элемента. Слева имя свойства, `=` означает «равно», справа в **двойных кавычках** значение. Кавычки обязательны, даже для чисел: `android:textSize="18sp"`. |
| `android:` | Приставка (namespace, пространство имён). Говорит, что атрибут — «андроидовский», из словаря Android. |
| `xmlns:android="http://schemas.android.com/apk/res/android"` | Объявление этой приставки. Пишется один раз в самом первом (корневом) теге файла. `xmlns` = XML NameSpace. Ссылка — не адрес сайта, куда что-то ходит, а просто уникальное имя словаря. Без этой строки слово `android:` было бы непонятно. |
| `<!-- текст -->` | Комментарий. Его видит человек, а программа полностью пропускает. |
| `@drawable/box` | `@` значит «ссылка на ресурс». `drawable` — тип ресурса (рисунки лежат в `res/drawable`), `box` — имя файла без `.xml`. |
| `@+id/editC` | `@+id/` значит «создай новый идентификатор (id) с именем `editC`». `+` — «создать, если ещё нет». По этому имени Java-код потом находит элемент. |
| `@android:style/Theme.Material` | Ссылка на ресурс, который есть в самом Android (приставка `android:` после `@`), — встроенная тема. |
| `#888888` | Цвет в шестнадцатеричном виде: `#` и три пары цифр — Red, Green, Blue (красный, зелёный, синий) от `00` до `FF`. `88` у всех трёх — серый. |

### Единицы размеров

| Запись | Что значит |
|---|---|
| `dp` | density-independent pixel, «независимый пиксель». На всех телефонах выглядит примерно одинаково физически, независимо от плотности экрана. Используется для отступов и размеров. |
| `sp` | scale-independent pixel. Как `dp`, но ещё учитывает настройку «размер шрифта» в телефоне. Используется **только для текста**. |
| `match_parent` | «Растянуться на весь размер родителя» (контейнера, внутри которого лежит элемент). |
| `wrap_content` | «Быть такого размера, чтобы влезло содержимое» — ни больше, ни меньше. |

## Символы Java (файлы `.java`)

| Символ / запись | Что значит |
|---|---|
| `;` | Конец команды. Как точка в конце предложения. Почти каждая строка-команда заканчивается на `;`. |
| `{ }` | Фигурные скобки: начало и конец **блока** — тела класса, метода, `if` и т. д. Всё между ними относится к тому, что написано перед `{`. |
| `( )` | Круглые скобки: 1) после имени метода — сюда передаются данные (параметры): `format(meters)`; 2) в `if (...)` — условие; 3) в математике — порядок действий: `(number - 32) * 5 / 9`. |
| `.` | Точка: «взять что-то у чего-то». `editCm.setText(...)` — «у квадратика `editCm` вызвать `setText`». В `package` и `import` точка разделяет части адреса: `android.os.Bundle`. |
| `,` | Запятая: разделяет параметры или несколько переменных. |
| `=` | **Присвоить** (положить значение в переменную): `meters = number / 100;`. Это **не** «равно» из математики! |
| `==` | **Сравнить**: «равно ли?». Результат — `true` (да) или `false` (нет). |
| `!=` | «Не равно?». `!` в Java означает «не». |
| `+ - * /` | Сложить, вычесть, умножить, разделить. `+` со строками склеивает текст. |
| `//` | Комментарий до конца строки. Программа его пропускает. |
| `"текст"` | Строка (текст) в двойных кавычках. |
| `'.'` | Один символ в одинарных кавычках. `'.'` — символ «точка», `','` — символ «запятая». |
| `@Override` | Аннотация «переопределяю». Значит: «этот метод уже есть у родителя, а я пишу свою версию». Если ошибиться в названии, компилятор предупредит. |
| `public` | «Доступно всем» — другие части программы (и Android) могут это вызвать. |
| `protected` | «Доступно своим и наследникам». |
| `void` | «Метод ничего не возвращает» — просто что-то делает. |
| `double` | Тип «дробное число» (например `1.3`). |
| `boolean` | Тип «да/нет»: `true` или `false`. |
| `String` | Тип «строка текста». |
| `this` | «Я сам» — текущий объект (текущее окно). |
| `new` | «Создать новый объект». `new Intent(...)` — создать новое «намерение». |
| `return` | «Выйти из метода» (и, если нужно, вернуть значение). |
| `class` | Класс — «чертёж» объекта. Каждое окно у нас — класс. |
| `extends` | «Наследует», «является разновидностью». `MainActivity extends Activity` = «MainActivity — это Activity, только наше». |
| `implements` | «Обещает уметь». `implements TextWatcher` = «этот класс умеет следить за текстом» и поэтому обязан содержать методы `TextWatcher`. |

---

# Часть 4. Разбор каждого файла построчно

## 4.1. `settings.gradle.kts` — оглавление проекта

```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
```

- `pluginManagement { ... }` — раздел «откуда брать **плагины** Gradle». Плагин — это дополнение, которое учит Gradle новому. Нам нужен плагин «собирать Android-приложения».
- `repositories { ... }` — список **репозиториев**, то есть интернет-складов, где лежат плагины и библиотеки.
- `google()` — склад Google. Там лежит Android Gradle Plugin.
- `mavenCentral()` — самый большой общий склад Java-библиотек.
- `gradlePluginPortal()` — официальный склад плагинов Gradle.
- `()` после имени — это вызов функции без параметров: «добавь этот склад».

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
```

- `dependencyResolutionManagement` — раздел «откуда брать **зависимости**» (библиотеки для самого приложения). У нас сейчас нет ни одной, но раздел стандартный: Android Studio создаёт его в любом новом проекте.
- `repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)` — правило: «склады указывать только здесь; если кто-то попробует указать склад в другом файле — ошибка». Так порядок в одном месте.

```kotlin
rootProject.name = "UnitConverter"
include(":app")
```

- `rootProject.name = "UnitConverter"` — имя всего проекта. Его видно в заголовке окна студии.
- `include(":app")` — «в проект входит модуль `app`». `:` означает «модуль в корне проекта». Модуль — это папка со своей частью программы. У нас модуль один — само приложение.

## 4.2. `build.gradle.kts` (в корне) — общие настройки

```kotlin
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.2" apply false
}
```

- Первая строка — комментарий (`//`): «файл верхнего уровня для общих настроек всех модулей». Стандартный текст из шаблона Android Studio.
- `plugins { ... }` — список плагинов.
- `id("com.android.application")` — имя плагина: «Android-приложение» (Android Gradle Plugin, сокращённо AGP).
- `version "8.5.2"` — какую версию плагина скачать.
- `apply false` — «здесь только скачай и запомни версию, но не включай». Включается он в модуле `app`, в следующем файле. Так версия задаётся один раз для всего проекта.

## 4.3. `app/build.gradle.kts` — настройки приложения

```kotlin
plugins {
    id("com.android.application")
}
```

Включаем плагин Android-приложения для модуля `app`. Версию писать не надо: она уже указана в корневом файле.

```kotlin
android {
    namespace = "com.example.unitconverter"
    compileSdk = 34
```

- `android { ... }` — все Android-настройки.
- `namespace = "com.example.unitconverter"` — пространство имён кода. Отсюда берётся адрес класса `R` (о нём ниже) — `com.example.unitconverter.R`. Должно совпадать с `package` в Java-файлах.
- `compileSdk = 34` — какой версией Android SDK **компилировать**. 34 = Android 14. Можно пользоваться всем, что есть в Android 14.

```kotlin
    defaultConfig {
        applicationId = "com.example.unitconverter"
        minSdk = 21
        targetSdk = 34
        versionCode = 4
        versionName = "1.3"
    }
```

- `defaultConfig { ... }` — основные параметры приложения.
- `applicationId` — **уникальный идентификатор приложения** на телефоне и в Google Play. Два приложения с одинаковым `applicationId` на одном телефоне быть не могут: новое заменит старое. Поэтому новая версия нашего APK устанавливается **поверх** старой.
- `minSdk = 21` — минимальная версия Android: 21 = Android 5.0. На более старых телефонах приложение не установится.
- `targetSdk = 34` — под какую версию Android приложение «рассчитано и проверено» (Android 14). Телефон включает для приложения правила поведения этой версии.
- `versionCode = 4` — номер версии **для машины**, целое число. Каждая новая версия должна иметь число больше предыдущего, иначе телефон не даст обновить.
- `versionName = "1.3"` — номер версии **для человека**, просто текст. Его видно в настройках телефона → Приложения.

```kotlin
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
```

- `compileOptions` — настройки компиляции Java.
- `sourceCompatibility = JavaVersion.VERSION_17` — «наш код написан по правилам Java 17».
- `targetCompatibility = JavaVersion.VERSION_17` — «компилируй в формат Java 17». Android Gradle Plugin 8.x рассчитан на Java 17.
- Последняя `}` закрывает блок `android {`.

## 4.4. `gradle/wrapper/gradle-wrapper.properties` — какой Gradle скачать

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.7-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

Формат `.properties`: одна настройка на строку, `имя=значение`.

- `distributionBase=GRADLE_USER_HOME` и `distributionPath=wrapper/dists` — куда распаковать скачанный Gradle: в папку пользователя `~/.gradle/wrapper/dists` (на Windows это `C:\Users\<имя>\.gradle\wrapper\dists`). Один раз скачанный Gradle переиспользуется всеми проектами.
- `distributionUrl=...gradle-8.7-bin.zip` — откуда качать и какую версию: Gradle 8.7. `bin` = только программа, без документации (меньше весит). `\:` — обратный слеш перед двоеточием нужен, потому что в формате `.properties` двоеточие — особый символ; `\` говорит «это просто двоеточие».
- `networkTimeout=10000` — ждать ответа сервера не дольше 10 000 миллисекунд (10 секунд).
- `validateDistributionUrl=true` — проверить, что ссылка рабочая, до начала скачивания.
- `zipStoreBase`, `zipStorePath` — куда положить сам скачанный ZIP-архив (туда же).

**Зачем вообще wrapper («обёртка»)?** Чтобы не нужно было ставить Gradle вручную. Скрипт `gradlew`
(Linux/Mac) или `gradlew.bat` (Windows) вместе с `gradle-wrapper.jar` сам скачивает ровно ту версию,
что записана здесь. У всех — у студии, у GitHub, у любого человека — сборка идёт на одной и той же версии.
Файлы `gradlew`, `gradlew.bat` и `gradle-wrapper.jar` стандартные: их генерирует сам Gradle, руками их не пишут и не правят.

## 4.5. `app/src/main/AndroidManifest.xml` — паспорт приложения

```xml
<?xml version="1.0" encoding="utf-8"?>
```

Служебная строка XML (см. азбуку): версия XML и кодировка UTF-8, чтобы работали русские буквы.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
```

- `<manifest` — корневой тег манифеста. Всё остальное лежит внутри него.
- `xmlns:android="..."` — объявляем приставку `android:` (см. азбуку).
- `>` — конец открывающего тега. Закроется он в самом конце файла тегом `</manifest>`.

```xml
    <application
        android:label="Конвертер величин"
        android:theme="@android:style/Theme.Material">
```

- `<application` — описание приложения в целом.
- `android:label="Конвертер величин"` — название приложения. Оно видно под иконкой на рабочем столе и в верхней полосе главного окна.
- `android:theme="@android:style/Theme.Material"` — тема оформления: встроенная в Android тёмная тема Material. Отсюда тёмный фон, светлый текст, верхняя полоса с названием и бирюзовый курсор. `@android:` — ресурс из самого Android, а не из нашего проекта.
- Иконку мы не указывали (`android:icon`), поэтому Android показывает стандартную.

```xml
        <!-- Главное окно, открывается при запуске -->
        <activity
            android:name=".MainActivity"
            android:exported="true">
```

- `<!-- ... -->` — комментарий.
- `<activity` — объявление одного окна. **Каждое окно обязательно указывать в манифесте**, иначе при попытке открыть его приложение упадёт.
- `android:name=".MainActivity"` — какой класс. Точка в начале — сокращение: «в пакете приложения», то есть полностью `com.example.unitconverter.MainActivity`.
- `android:exported="true"` — окно можно открыть «снаружи» приложения. Для главного окна это обязательно: его открывает рабочий стол (лаунчер) телефона. С Android 12 этот атрибут нужно писать явно.

```xml
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```

- `<intent-filter>` — «на какие просьбы (intent) откликается это окно».
- `<action android:name="android.intent.action.MAIN" />` — «я — главная точка входа в приложение».
- `<category android:name="android.intent.category.LAUNCHER" />` — «покажи меня иконкой в списке приложений».
- Эта пара вместе означает: «при нажатии на иконку открыть это окно». `/>` — теги самозакрывающиеся.
- `</intent-filter>` и `</activity>` — закрываем.

```xml
        <!-- Окна для каждой величины -->
        <activity android:name=".LengthActivity" android:label="Длина" />
        <activity android:name=".MassActivity" android:label="Масса" />
        <activity android:name=".TemperatureActivity" android:label="Температура" />
```

- Ещё три окна. `intent-filter` у них нет: открывает их только наше приложение, а не иконка.
- `android:label="Длина"` — заголовок окна в верхней полосе.
- `exported` не указан → по умолчанию `false`: чужие приложения эти окна открыть не могут.

```xml
    </application>

</manifest>
```

Закрываем `application` и `manifest`.

## 4.6. `app/src/main/res/drawable/box.xml` — рамка-квадратик

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Рамка-квадратик для полей ввода -->
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
```

- Папка `drawable` — для всего, что можно нарисовать: картинок и фигур. Имя файла `box` становится именем ресурса `@drawable/box`.
- `<shape` — «фигура, нарисованная кодом» (не картинка-файл).
- `android:shape="rectangle"` — прямоугольник.

```xml
    <stroke android:width="2dp" android:color="#888888" />
```

- `<stroke` — обводка (контур).
- `android:width="2dp"` — толщина линии 2dp.
- `android:color="#888888"` — серый цвет.
- Заливку (`<solid>`) мы не указали, поэтому внутри прозрачно и виден тёмный фон.

```xml
    <corners android:radius="4dp" />
```

Скругление углов радиусом 4dp — чуть-чуть, чтобы углы не были острыми.

```xml
    <padding android:left="8dp" android:top="8dp" android:right="8dp" android:bottom="8dp" />
</shape>
```

- `<padding` — внутренний отступ: расстояние от рамки до текста внутри. Слева, сверху, справа и снизу по 8dp, чтобы цифры не прилипали к рамке.
- `</shape>` — конец фигуры.

## 4.7. `app/src/main/res/layout/activity_main.xml` — разметка главного окна

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp">
```

- `<LinearLayout` — контейнер, который ставит элементы **друг за другом в линию**.
- `xmlns:android` — объявление приставки (корневой тег файла).
- `android:layout_width="match_parent"` — ширина: на весь экран.
- `android:layout_height="match_parent"` — высота: на весь экран.
- `android:orientation="vertical"` — линия **вертикальная**: элементы идут сверху вниз.
- `android:padding="24dp"` — внутренний отступ 24dp со всех сторон, чтобы кнопки не прилипали к краям экрана.

```xml
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Длина"
        android:textSize="18sp"
        android:onClick="openLength" />
```

- `<Button` — кнопка.
- `layout_width="match_parent"` — на всю ширину (за вычетом отступов родителя).
- `layout_height="wrap_content"` — высота по содержимому (по тексту).
- `android:text="Длина"` — надпись на кнопке.
- `android:textSize="18sp"` — размер шрифта 18sp.
- `android:onClick="openLength"` — **связь с кодом**: при нажатии Android найдёт в `MainActivity` метод с именем `openLength` и вызовет его. Имя должно совпадать буква в букву, а метод должен быть `public void имя(View view)`.
- `/>` — кнопка ничего внутри не содержит, закрываем сразу.

```xml
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:text="Масса"
        android:textSize="18sp"
        android:onClick="openMass" />
```

То же самое, но:
- `android:layout_marginTop="12dp"` — внешний отступ сверху 12dp: промежуток между этой кнопкой и предыдущей.
  **Разница padding и margin:** padding — отступ *внутри* элемента (от края до содержимого), margin — отступ *снаружи* (от соседей).
- Надпись «Масса», метод `openMass`.

Третья кнопка — точно так же: «Температура», метод `openTemperature`.

```xml
</LinearLayout>
```

Конец контейнера.

## 4.8. `activity_length.xml`, `activity_mass.xml`, `activity_temperature.xml` — разметка окон с квадратиками

Все три файла устроены одинаково. Разберём `activity_temperature.xml`: в нём есть всё, что есть в двух других, плюс один особый приём.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp">
```

Как в главном окне: вертикальный контейнер на весь экран, отступ 24dp.

```xml
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Введите число в любой квадратик" />
```

- `<TextView` — просто текст, который нельзя редактировать. Подсказка для пользователя.
- Ширина и высота по содержимому.

```xml
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:gravity="center_vertical"
        android:orientation="horizontal">
```

- Вложенный `LinearLayout` — **один ряд** квадратиков.
- `layout_width="match_parent"` — ряд на всю ширину. `layout_height="wrap_content"` — высотой по содержимому.
- `layout_marginTop="24dp"` — отступ от того, что выше.
- `android:gravity="center_vertical"` — выровнять всё внутри ряда **по вертикали по центру**, чтобы подпись «°C» стояла ровно посередине высоты квадратика, а не прилипала к верху.
- `android:orientation="horizontal"` — внутри ряда элементы идут **слева направо**.

```xml
        <EditText
            android:id="@+id/editC"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@drawable/box"
            android:inputType="numberDecimal|numberSigned" />
```

- `<EditText` — **поле ввода**, в которое можно писать. Это и есть «квадратик».
- `android:id="@+id/editC"` — даём полю имя `editC` (C — Celsius, Цельсий). По этому имени Java-код его найдёт: `findViewById(R.id.editC)`.
- `android:layout_width="0dp"` вместе с `android:layout_weight="1"` — приём «раздели свободное место по весам».
  Ширина `0dp` значит «сам я ширину не выбираю». Вес `1` значит «возьми долю свободного места».
  У двух квадратиков в ряду вес по 1, поэтому свободная ширина делится **поровну** — 50 на 50.
  Так ряд одинаково выглядит на любом экране, узком или широком.
- `android:background="@drawable/box"` — фон поля — наша рамка из `box.xml`. Он заменяет стандартное подчёркивание Android.
- `android:inputType="numberDecimal|numberSigned"` — какую клавиатуру показать и что разрешить вводить:
  `numberDecimal` — цифры и десятичная точка, `numberSigned` — ещё и знак минус (для отрицательной температуры).
  `|` («или», вертикальная черта) — объединить оба варианта.

```xml
        <TextView
            android:layout_width="40dp"
            android:layout_height="wrap_content"
            android:layout_marginStart="8dp"
            android:text="°C"
            android:textSize="20sp" />
```

- Подпись единицы справа от квадратика.
- `layout_width="40dp"` — **фиксированная** ширина 40dp. Так подписи «м», «км», «°C» занимают одинаковое место, и квадратики в разных рядах получаются одной ширины.
- `android:layout_marginStart="8dp"` — отступ 8dp слева (от квадратика). `Start` = «с начала строки»: в русском и английском это слева, а в арабском было бы справа.
- `text="°C"`, размер 20sp.

```xml
        <EditText
            android:id="@+id/editF"
            ...
            android:layout_marginStart="16dp"
            ... />
```

Второй квадратик в ряду (F — Fahrenheit, Фаренгейт). Всё как у первого, плюс `layout_marginStart="16dp"` — промежуток между первой парой «квадратик + подпись» и второй.
Затем его подпись `°F`, и `</LinearLayout>` закрывает первый ряд.

Второй ряд начинается так же, в нём квадратик `editK` (K — Kelvin, Кельвин) и подпись «K». А дальше особый приём:

```xml
        <View
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:layout_marginStart="64dp" />
```

- `<View` — **пустышка-распорка**, невидимый элемент.
- Зачем: у температуры три единицы, поэтому во втором ряду только один квадратик. Если он будет один с весом 1, то растянется на всю ширину, и получится длинная полоса, а не квадратик.
  Распорка с тем же весом `1` забирает себе вторую половину места, и квадратик остаётся той же ширины, что в первом ряду.
- `layout_height="0dp"` — высота ноль, она ничего не занимает по вертикали.
- `layout_marginStart="64dp"` — подгонка, чтобы ширина совпала точно. В первом ряду, кроме двух квадратиков, есть ещё фиксированные 8 + 40 (первая подпись) + 16 + 8 + 40 (вторая) = **112dp**. Во втором ряду: 8 + 40 (подпись K) + **64** = **112dp**. Фиксированное место одинаковое, значит, и оставшееся делится одинаково.

В `activity_length.xml` и `activity_mass.xml` в обоих рядах по два квадратика, поэтому распорки там нет. Имена полей:
длина — `editCm` (см), `editM` (м), `editDm` (дм), `editKm` (км); масса — `editMg` (мг), `editG` (г), `editKg` (кг), `editT` (т).
У длины и массы `inputType` тоже с `numberSigned`, хотя отрицательная длина не имеет смысла. Разметка сделана одинаковой для простоты.

## 4.9. `MainActivity.java` — код главного окна

```java
package com.example.unitconverter;
```

- `package` — «пакет», то есть адрес/папка класса. Совпадает с путём `java/com/example/unitconverter/` и с `namespace` в `build.gradle.kts`.
- `;` — конец команды.

```java
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
```

- `import` — «подключить» готовый класс из Android, чтобы писать просто `Activity`, а не полный адрес `android.app.Activity` каждый раз.
- `Activity` — базовый класс «экран».
- `Intent` — «намерение»: просьба к Android что-то сделать, например открыть другое окно.
- `Bundle` — «пакет данных». В нём Android передаёт сохранённое состояние окна. Нам он нужен только потому, что его требует `onCreate`.
- `View` — любой элемент экрана (кнопка, текст, поле…).

```java
// Главное окно: три кнопки, каждая открывает своё окно
public class MainActivity extends Activity {
```

- `public class MainActivity` — объявляем публичный класс с именем `MainActivity`. **Имя класса должно совпадать с именем файла** `MainActivity.java`.
- `extends Activity` — наш класс — разновидность `Activity`, то есть экран. Всё умение «быть экраном» берём у Android готовым.
- `{` — начало тела класса. Закроется последней `}` в файле.

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
```

- `@Override` — переопределяем метод, который есть у `Activity`.
- `protected void onCreate(Bundle savedInstanceState)` — метод `onCreate` («при создании»). **Android сам вызывает его, когда окно открывается.** `void` — ничего не возвращает. В скобках параметр: переменная `savedInstanceState` типа `Bundle`.
- `super.onCreate(savedInstanceState);` — `super` значит «родитель» (`Activity`). Сначала даём родителю выполнить его обязательную подготовку. Без этой строки приложение упадёт.
- `setContentView(R.layout.activity_main);` — «покажи на экране разметку `activity_main.xml`».
  - **Что такое `R`?** Это класс, который Android-плагин **генерирует автоматически** при сборке из папки `res`. В нём у каждого ресурса есть номер: `R.layout.activity_main`, `R.id.editC`, `R.drawable.box`… Руками `R` не пишут, и в проекте его файла не видно — он появляется при сборке. Поэтому, если в XML ошибка, `R` не сгенерируется, и красным подсветится весь Java-код.
  - `R.layout.activity_main` = «ресурс типа layout с именем activity_main».

```java
    // Кнопка "Длина"
    public void openLength(View view) {
        Intent intent = new Intent(this, LengthActivity.class);
        startActivity(intent);
    }
```

- `public void openLength(View view)` — метод, который вызывается при нажатии кнопки «Длина», через `android:onClick="openLength"` в разметке.
  Он **обязан** быть `public` (Android вызывает его «снаружи»), `void` и принимать ровно один параметр типа `View`. В `view` Android передаёт саму нажатую кнопку; нам она не нужна, но параметр обязателен.
- `Intent intent = new Intent(this, LengthActivity.class);` — создаём «намерение» и кладём в переменную `intent` типа `Intent`:
  - `this` — «откуда»: из текущего окна;
  - `LengthActivity.class` — «куда»: класс окна «Длина». `.class` — «сам класс как объект».
- `startActivity(intent);` — просим Android выполнить намерение, то есть открыть окно «Длина». Главное окно остаётся под ним. Кнопка «Назад» на телефоне закроет «Длину» и вернёт к главному окну — это Android делает сам, код не нужен.

Методы `openMass` и `openTemperature` — то же самое, только открывают `MassActivity` и `TemperatureActivity`.

```java
}
```

Конец класса.

## 4.10. `LengthActivity.java` — код окна «Длина» (самое интересное)

```java
package com.example.unitconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;
```

Новые импорты по сравнению с `MainActivity`:
- `Editable` — «редактируемый текст», то, что лежит внутри поля ввода.
- `TextWatcher` — «наблюдатель за текстом»: интерфейс, чтобы узнавать, когда в поле меняется текст.
- `EditText` — класс поля ввода (наши квадратики).
- `BigDecimal`, `MathContext` — из стандартной Java (`java.math`), для красивого вывода чисел (см. метод `format`).

```java
// Окно "Длина": вводишь число в любой квадратик - остальные пересчитываются сами
public class LengthActivity extends Activity implements TextWatcher {
```

- `extends Activity` — это экран.
- `implements TextWatcher` — окно само будет «наблюдателем за текстом». Взамен Java **требует** написать в классе три метода `TextWatcher`: `beforeTextChanged`, `onTextChanged`, `afterTextChanged`. Иначе программа не скомпилируется.

```java
    EditText editCm, editDm, editM, editKm;
```

- Объявляем четыре **переменные** типа `EditText` — «ручки», через которые код будет работать с квадратиками. Пока они пустые (`null`), заполним их в `onCreate`.
- Запятые — несколько переменных одного типа в одной строке.
- Переменные объявлены внутри класса, но вне методов — это **поля класса**. Их видно во всех методах класса.

```java
    // true, пока мы сами пишем числа в квадратики (чтобы не зациклиться)
    boolean updating = false;
```

- Переменная-флажок типа «да/нет», сначала `false` («нет»).
- **Зачем:** когда код сам пишет число в квадратик (`setText`), текст в нём меняется, и Android снова зовёт `afterTextChanged`. Тот снова пишет в квадратики, опять вызов — и так до бесконечности, приложение зависнет.
  Флажок работает так: перед записью ставим `updating = true`, а в начале `afterTextChanged` проверяем: если `updating` — значит, это мы сами пишем, выходим и ничего не делаем.

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
```

Как в `MainActivity`, только показываем разметку `activity_length.xml`.

```java
        editCm = findViewById(R.id.editCm);
        editDm = findViewById(R.id.editDm);
        editM = findViewById(R.id.editM);
        editKm = findViewById(R.id.editKm);
```

- `findViewById(R.id.editCm)` — «найди на экране элемент с id `editCm`» (тот, что в XML объявлен как `@+id/editCm`).
- `editCm = ...` — кладём найденный квадратик в переменную. Теперь через `editCm` можно читать и менять текст в квадратике «см».

```java
        // Следим за изменением текста в каждом квадратике
        editCm.addTextChangedListener(this);
        editDm.addTextChangedListener(this);
        editM.addTextChangedListener(this);
        editKm.addTextChangedListener(this);
    }
```

- `addTextChangedListener(this)` — «добавь слушателя изменений текста». Слушатель — `this`, то есть само окно (оно же `implements TextWatcher`).
  Теперь при любом изменении текста в любом из четырёх квадратиков Android вызовет наши методы.
- `}` — конец `onCreate`.

```java
    // Вызывается каждый раз, когда меняется текст в любом квадратике
    @Override
    public void afterTextChanged(Editable text) {
```

- `afterTextChanged` — «после того как текст изменился». Один из трёх методов `TextWatcher`, **главный** для нас.
- `Editable text` — Android передаёт новый текст того квадратика, где произошло изменение.

```java
        if (updating) {
            return;
        }
```

- `if (updating)` — «если флажок `updating` равен `true`…» (для `boolean` можно не писать `== true`).
- `return;` — «…то выйти из метода сразу». Это защита от зацикливания, описанная выше.

```java
        double number;
        try {
            number = Double.parseDouble(text.toString().replace(',', '.'));
        } catch (NumberFormatException e) {
            return; // там не число - ничего не делаем
        }
```

- `double number;` — объявляем дробную переменную `number` (значение положим ниже).
- `try { ... } catch (...) { ... }` — «попробуй; если случится ошибка указанного вида — сделай то, что в `catch`, вместо падения приложения».
- Читаем цепочку справа налево по смыслу:
  - `text.toString()` — превратить содержимое поля в обычную строку `String`;
  - `.replace(',', '.')` — заменить запятые на точки. Русская клавиатура может ставить `,`, а Java понимает дробные числа только с `.`;
  - `Double.parseDouble(...)` — превратить строку в число (`"1.3"` → `1.3`).
- `catch (NumberFormatException e)` — если в строке не число (пусто, одинокий `-` или `.`), `parseDouble` выбрасывает ошибку `NumberFormatException`. Мы её ловим (`e` — переменная с описанием ошибки, она нам не нужна) и просто выходим (`return`). Без `try/catch` приложение бы вылетело, когда человек стирает всё из квадратика.

```java
        // Квадратик, в который сейчас пишет пользователь
        View changed = getCurrentFocus();
```

- `getCurrentFocus()` — «какой элемент сейчас в фокусе», то есть где мигает курсор. Это тот квадратик, в который печатает человек.
- Кладём его в переменную `changed` типа `View`.

```java
        // Переводим введённое число в метры
        double meters;
        if (changed == editCm) {
            meters = number / 100;
        } else if (changed == editDm) {
            meters = number / 10;
        } else if (changed == editM) {
            meters = number;
        } else {
            meters = number * 1000;
        }
```

- **Идея всего конвертера:** не писать отдельную формулу для каждой пары (см→м, см→км, дм→км, …), а сначала перевести число в одну **базовую** единицу — метры, — а из метров уже во все остальные. Так формул вдвое меньше, чем единиц.
- `if (changed == editCm)` — «если человек пишет в квадратик "см"» (`==` — сравнение: это тот же самый объект?)
  → `meters = number / 100;` — в 1 метре 100 см, значит, метры = сантиметры / 100.
- `else if (...)` — «иначе, если…». Проверяется, только когда предыдущее условие не выполнилось.
  - дм: в 1 м 10 дм → делим на 10;
  - м: это уже метры → просто `number`.
- `else` — «во всех остальных случаях». Остался только «км»: в 1 км 1000 м → умножаем на 1000.

```java
        // Из метров - во все остальные квадратики
        updating = true;
        if (changed != editCm) editCm.setText(format(meters * 100));
        if (changed != editDm) editDm.setText(format(meters * 10));
        if (changed != editM) editM.setText(format(meters));
        if (changed != editKm) editKm.setText(format(meters / 1000));
        updating = false;
    }
```

- `updating = true;` — поднимаем флажок «это мы сами пишем» (защита от зацикливания).
- `if (changed != editCm)` — «если человек пишет **не** в "см"…» (`!=` — «не равно»). В тот квадратик, где человек печатает, писать нельзя: это сбило бы ему ввод и курсор.
- `editCm.setText(format(meters * 100));` — «…то запиши в квадратик "см" число метров × 100». Порядок выполнения изнутри наружу:
  сначала `meters * 100`, потом `format(...)` превращает число в красивую строку, потом `setText(...)` пишет её в квадратик.
- Если у `if` одна команда, фигурные скобки можно не ставить — поэтому всё в одну строку.
- Обратные формулы: см = м × 100, дм = м × 10, м = м, км = м / 1000.
- `updating = false;` — опускаем флажок. Теперь снова реагируем на ввод человека.
- `}` — конец `afterTextChanged`.

```java
    // Показываем число без лишних нулей: 1.3 вместо 1.3000000001
    String format(double number) {
        return new BigDecimal(number).round(new MathContext(10))
                .stripTrailingZeros().toPlainString();
    }
```

- Наш собственный вспомогательный метод. Принимает `double`, возвращает `String` (поэтому перед именем `String`, а не `void`).
- **Зачем он нужен:** компьютер хранит дробные числа в двоичном виде, и они бывают неточными: вместо `0.3` может получиться `0.30000000000000004`. А очень маленькие числа Java пишет в научном виде: `3.0E-5` вместо `0.00003`. Выглядит страшно.
- По шагам:
  - `new BigDecimal(number)` — превратить число в `BigDecimal`: «точное десятичное число» из стандартной Java;
  - `.round(new MathContext(10))` — округлить до 10 значащих цифр. Мусор вроде `...0000004` отрезается;
  - `.stripTrailingZeros()` — убрать нули в конце: `1.300` → `1.3`, `130000.0` → `130000`;
  - `.toPlainString()` — превратить в строку **без** научного вида: `0.00003`, а не `3E-5`.
- `return` — вернуть готовую строку тому, кто вызвал метод.
- Точки в начале второй строки — это продолжение той же цепочки с прошлой строки. Java не важно, где перенос строки, команда заканчивается только на `;`.

```java
    // Эти два метода нам не нужны, но Android требует, чтобы они были
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
```

- Два оставшихся метода `TextWatcher`: «перед изменением текста» и «во время изменения». Нам хватает `afterTextChanged`, но раз написали `implements TextWatcher`, Java требует все три. Поэтому они есть, но пустые: `{` и сразу `}`.
- `CharSequence s, int start, ...` — параметры, которые передал бы Android (текст, позиция, сколько символов изменено). `int` — целое число. Мы их не используем.
- Последняя `}` — конец класса.

## 4.11. `MassActivity.java` — окно «Масса»

Код **полностью такой же**, как у `LengthActivity`, отличаются только имена и числа:

- разметка `R.layout.activity_mass`;
- квадратики `editMg`, `editG`, `editKg`, `editT`;
- базовая единица — **граммы** (`grams`):

| Квадратик | В граммы | Из граммов |
|---|---|---|
| мг | `number / 1000` (в 1 г 1000 мг) | `grams * 1000` |
| г | `number` | `grams` |
| кг | `number * 1000` (в 1 кг 1000 г) | `grams / 1000` |
| т | `number * 1000000` (в 1 т 1 000 000 г) | `grams / 1000000` |

## 4.12. `TemperatureActivity.java` — окно «Температура»

Тоже как `LengthActivity`, но квадратиков три (`editC`, `editF`, `editK`), а базовая единица — **градусы Цельсия** (`celsius`).
Температура переводится не простым умножением, а по формулам со сдвигом:

```java
        double celsius;
        if (changed == editC) {
            celsius = number;                  // уже Цельсий
        } else if (changed == editF) {
            celsius = (number - 32) * 5 / 9;   // Фаренгейт → Цельсий
        } else {
            celsius = number - 273.15;         // Кельвин → Цельсий
        }

        updating = true;
        if (changed != editC) editC.setText(format(celsius));
        if (changed != editF) editF.setText(format(celsius * 9 / 5 + 32));  // Цельсий → Фаренгейт
        if (changed != editK) editK.setText(format(celsius + 273.15));      // Цельсий → Кельвин
        updating = false;
```

- **Фаренгейт → Цельсий:** `(F − 32) × 5 / 9`. Скобки обязательны: сначала вычесть 32, потом умножать. Без скобок Java сделала бы `32 * 5 / 9` первым (умножение и деление важнее сложения и вычитания).
- **Цельсий → Фаренгейт:** `C × 9 / 5 + 32`. Здесь скобки не нужны: умножение и деление и так выполнятся раньше `+ 32`.
- **Кельвин:** шкала сдвинута на 273.15: `K = C + 273.15`, `C = K − 273.15`.
- Пример: 36.6 °C → 36.6 × 9 / 5 + 32 = **97.88 °F**, 36.6 + 273.15 = **309.75 K**.

## 4.13. `.gitignore` — что не сохранять в Git

```
*.iml
.gradle/
/local.properties
/.idea/
.DS_Store
/build/
/captures/
.externalNativeBuild/
.cxx/
app/build/
app/release/
```

Каждая строка — шаблон файлов, которые Git будет **игнорировать** (не сохранять в репозиторий):

- `*.iml` — `*` значит «любое имя». Все файлы с расширением `.iml`: служебные файлы модулей студии.
- `.gradle/` — `/` в конце значит «папка». Кэш Gradle, пересоздаётся сам.
- `/local.properties` — `/` в начале значит «только в корне проекта». В этом файле студия пишет путь к SDK **на твоём компьютере**, у другого человека он другой, поэтому хранить его в Git нельзя. Студия создаёт этот файл сама при открытии проекта.
- `/.idea/` — настройки окна студии (какие файлы открыты и т. п.). Личное, пересоздаётся.
- `.DS_Store` — мусорный служебный файл macOS.
- `/build/`, `app/build/` — результаты сборки (в том числе APK). Их всегда можно собрать заново, хранить незачем.
- `/captures/` — снимки экрана и профилирования из студии.
- `.externalNativeBuild/`, `.cxx/` — временные файлы сборки кода на C/C++ (у нас его нет, строки из стандартного шаблона).
- `app/release/` — папка, куда студия кладёт подписанные релизные сборки.

## 4.14. `.github/workflows/android-build.yml` — сборка на GitHub

Этот файл нужен **не** приложению, а сайту GitHub: это инструкция, как собрать APK на их сервере.
Формат YAML: вложенность задаётся **отступами** (пробелами — табы нельзя!), `имя: значение`, `-` означает элемент списка, `#` — комментарий.

```yaml
name: Android Build
```

Название процесса на вкладке Actions.

```yaml
on:
  push:
    branches: [ "**" ]
    tags: [ "v*" ]
  pull_request:
    branches: [ "**" ]
  workflow_dispatch:
    inputs:
      tag:
        description: "Тег релиза (например v1.0.0)"
        required: false
        default: "v1.0.0"
```

- `on:` — **когда** запускать.
- `push:` → `branches: [ "**" ]` — при отправке кода (push) в любую ветку. `[ ]` — список, `"**"` — «любое имя».
- `tags: [ "v*" ]` — и при создании тега, начинающегося на `v` (например `v1.4.0`).
- `pull_request:` — при создании или обновлении pull request в любую ветку.
- `workflow_dispatch:` — можно запустить **вручную** кнопкой **Run workflow**.
  - `inputs:` → `tag:` — при ручном запуске будет поле ввода «tag»;
  - `description` — подпись к полю; `required: false` — можно не заполнять; `default: "v1.0.0"` — значение по умолчанию.

```yaml
permissions:
  contents: write
```

Разрешить процессу **записывать** в репозиторий — это нужно, чтобы создавать релизы и теги.

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
```

- `jobs:` — список задач. У нас одна, с именем `build`.
- `runs-on: ubuntu-latest` — выполнять на чистом виртуальном компьютере с Linux Ubuntu последней версии, который GitHub выдаёт бесплатно на каждый запуск.

```yaml
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
```

- `steps:` — шаги по порядку. Каждый шаг начинается с `-`.
- `name:` — название шага (видно в логе).
- `uses: actions/checkout@v4` — использовать готовое действие `checkout` версии 4: скачать код репозитория на этот компьютер.

```yaml
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 17
```

- Установить Java 17 (нужна для Gradle и Android-плагина).
- `with:` — параметры для действия: `distribution: temurin` — какая сборка Java (Eclipse Temurin, бесплатная), `java-version: 17` — версия.

```yaml
      - name: Grant execute permission for gradlew
        run: chmod +x gradlew
```

- `run:` — выполнить команду в терминале.
- `chmod +x gradlew` — в Linux файлу нужно разрешение «можно запускать». `chmod` — изменить права, `+x` — добавить право запуска (eXecute).

```yaml
      - name: Build debug APK
        run: ./gradlew assembleDebug --stacktrace
```

- `./gradlew` — запустить обёртку Gradle из текущей папки (`./` — «здесь»). Она сама скачает Gradle 8.7.
- `assembleDebug` — задача «собрать отладочную (debug) версию APK». Debug-версия подписывается автоматически временным ключом, поэтому её можно сразу установить.
- `--stacktrace` — если будет ошибка, вывести подробности, где именно.

```yaml
      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: unit-converter-debug-apk
          path: app/build/outputs/apk/debug/app-debug.apk
```

Сохранить готовый APK как «артефакт» запуска (его можно скачать со страницы запуска на вкладке Actions).
`name` — имя архива, `path` — путь к файлу, куда его положил Gradle.

```yaml
      - name: Publish APK to GitHub Release
        if: startsWith(github.ref, 'refs/tags/v') || github.event_name == 'workflow_dispatch'
        uses: softprops/action-gh-release@v2
        with:
          tag_name: ${{ github.event.inputs.tag || github.ref_name }}
          name: Unit Converter ${{ github.event.inputs.tag || github.ref_name }}
          files: app/build/outputs/apk/debug/app-debug.apk
```

- Шаг «опубликовать APK в релиз».
- `if:` — выполнять шаг, только если условие верно:
  - `startsWith(github.ref, 'refs/tags/v')` — запуск вызван тегом, начинающимся на `v`;
  - `||` — «или»;
  - `github.event_name == 'workflow_dispatch'` — запуск вручную кнопкой.
  При обычном push в ветку релиз не создаётся — только артефакт.
- `uses: softprops/action-gh-release@v2` — готовое действие от сообщества для создания релизов.
- `${{ ... }}` — вставка значения, которое GitHub подставит во время запуска.
  `github.event.inputs.tag || github.ref_name` — «тег, введённый при ручном запуске, а если его нет — имя тега, который вызвал запуск».
- `tag_name` — тег релиза (например `v1.3.0`), `name` — заголовок релиза, `files` — какой файл приложить.
