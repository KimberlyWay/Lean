# Конвертер величин (Android)

Простое учебное приложение на Java.

## Как устроено

- **Главное окно** (`MainActivity`): три кнопки — «Длина», «Масса», «Температура».
- Каждая кнопка открывает своё окно:
  - `LengthActivity` — см → м и м → см
  - `MassActivity` — г → кг и кг → г
  - `TemperatureActivity` — °C → °F и °F → °C
- В каждом окне: поле для числа, две кнопки и строка с результатом.

Код лежит в `app/src/main/java/com/example/unitconverter/`,
разметка экранов — в `app/src/main/res/layout/`.
Кнопки связаны с кодом через `android:onClick` в разметке.

## Как открыть в Android Studio

File → Open → выбрать папку проекта → дождаться синхронизации Gradle → Run ▶.

## Как получить APK

При каждом push GitHub Actions собирает APK (вкладка **Actions** → запуск → **Artifacts**).
Чтобы выпустить релиз: **Actions → Android Build → Run workflow**, указать тег (например `v1.2.0`).
