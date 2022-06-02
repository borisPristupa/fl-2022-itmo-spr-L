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

из директории проекта. Данный скрипт создаст файл в рабочей директории ```<filename_parsed>``` с результатом работы парсера в строковом представлении дерева. 

Для запусков тестов используйте аналогичную команду:

```
PYTHONPATH='.' python3 -m unittest
``` 

При возникновении ошибки ```Exception: Could not deserialize ATN with version  (expected 4).```, попробуйте запустить из PyCharm установив ```requirements.txt``` или изменить версию ANTLR.

Примеры тестов, которые не вызвали ошибку при парсинге:

```
if_fn() {
  if (iff) x = then else x = fi;
  f(main);
  if (x) if (y) if (z) skip else skip else skip
}

while_fn() {
  while (x) while (y) while (z) skip
}

compound_fn() {
   {}; {}; {{{{{}}}}}
}

main() {
  x(x(x(x(x(x(x(x(x()))))))));
  skip;
  x = ---------------------------x;
  x = 1 + # comment
  x
}
```

```
f(){}g(){skip}h(){skip;skip;skip}

x(x) {}
y(x, y) {}
z(x, y, z) {}

ll
(
)
{
}

ll_1
(
ll
)
{
ll
(
)
}

main() {
}

func_while(a) {
    ll();
    skip;
    # commment
    a = 2.4555;
    while (a > 0)
    {a = a - 1; b = a * 2 ^ 4}
}

func_if(a) {
    a = 0b1101;
    if (!a && b)
    c = 2
    else
    a = 2
    if (a)
    a = 2;
    b = "String"
    # comment line
}
```
Чтобы скомпилировать ANTLR из директории Parser используйте команду:

```
antlr4 -Dlanguage=Python3 L.g4 -visitor -o antlr_files
```


