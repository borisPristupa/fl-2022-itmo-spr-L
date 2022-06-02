# fl-2022-itmo-spr-L-IDE
Поддержка языка L в IDEA (https://github.com/kajigor/fl-2022-itmo-spr/blob/proj/lang/L.md)

## Парсер языка L при помощи ANTLR:

Использовалась версия ANTLR runtime 4.8 в Python 3.8.

Для запуска парсера из файла необходимо выполнить команду:

```
python3 parser.py -p <file>
```
Или

```
PYTHONPATH='.' python3 parser.py -p <file>
```

из директории проекта.

Для запусков тестов используйте аналогичную команду:

```
PYTHONPATH='.' python3 -m unittest
``` 

При возникновении ошибки ```Exception: Could not deserialize ATN with version  (expected 4).```, попробуйте запустить из Pycharm установив ```requirements.txt``` или изменить версию ANTLR.



