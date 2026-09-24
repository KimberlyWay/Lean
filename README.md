# Конвертер величин (Android)

Простое Android-приложение на Java: переводит число из одной единицы измерения
в остальные единицы той же величины (длина, масса, время).

## Как это устроено

- Один экран — `MainActivity` (`app/src/main/java/com/example/unitconverter/MainActivity.java`).
- Разметка экрана — `app/src/main/res/layout/activity_main.xml`.
- Никаких Fragment'ов, ViewModel, Jetpack Compose и других сложных вещей —
  только обычные `View` (`Spinner`, `EditText`, `Button`, `TextView`) и
  `findViewById`, как в самых первых уроках по Android.
- Логика конвертации — простой перевод в "базовую" единицу
  (метр / килограмм / секунда) и обратно, с массивами коэффициентов.

## Как открыть проект в Android Studio

1. Откройте Android Studio → **File → Open** → выберите папку проекта (корень репозитория,
   там где лежит файл `settings.gradle.kts`).
2. Дождитесь синхронизации Gradle (Android Studio сама предложит скачать
   недостающий Android SDK/AGP, если их ещё нет).
3. Нажмите **Run ▶** — приложение соберётся и запустится на эмуляторе или
   подключённом телефоне.

## Как собрать APK

### Локально
```bash
./gradlew assembleDebug
```
Готовый файл появится в `app/build/outputs/apk/debug/app-debug.apk`.

### Через GitHub Actions
При каждом push в любую ветку запускается workflow
`.github/workflows/android-build.yml`, который собирает debug APK и
прикрепляет его как Artifact к запуску (вкладка **Actions** → выбранный
запуск → **Artifacts**).

## Как добавить новую величину (для дальнейшей разработки)

В `MainActivity.java`:
1. Добавьте новую строку в массив `categories`.
2. Добавьте массив единиц (например, `String[] volumeUnits`) и массив
   коэффициентов перевода в базовую единицу (например, литры).
3. Допишите ветку `if/else` в методах `getUnitsForCategory` и
   `getFactorsForCategory`.

Больше ничего менять не нужно — весь расчёт и вывод результата уже общие
для всех величин.
