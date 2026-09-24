# Конвертер величин (Android)

Простое учебное приложение на Java.

## Как устроено

- **Главное окно** (`MainActivity`): три кнопки — «Длина», «Масса», «Температура».
- Каждая кнопка открывает своё окно с квадратиками — по одному на каждую единицу:
  - `LengthActivity` — см, дм, м, км
  - `MassActivity` — мг, г, кг, т
  - `TemperatureActivity` — °C, °F, K
- Вводишь число в любой квадратик — остальные пересчитываются сами
  (через `TextWatcher`: число переводится в базовую единицу, а из неё — во все остальные).
- Тёмная тема — встроенная `Theme.Material`.

Код лежит в `app/src/main/java/com/example/unitconverter/`,
разметка экранов — в `app/src/main/res/layout/`.
Кнопки главного окна связаны с кодом через `android:onClick` в разметке.
Рамка квадратиков — `app/src/main/res/drawable/box.xml`.

## Как открыть в Android Studio

File → Open → выбрать папку проекта → дождаться синхронизации Gradle → Run ▶.

## Как получить APK

При каждом push GitHub Actions собирает APK (вкладка **Actions** → запуск → **Artifacts**).
Чтобы выпустить релиз: **Actions → Android Build → Run workflow**, указать тег (например `v1.2.0`).
